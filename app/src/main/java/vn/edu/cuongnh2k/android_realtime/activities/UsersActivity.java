package vn.edu.cuongnh2k.android_realtime.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.cuongnh2k.android_realtime.R;
import vn.edu.cuongnh2k.android_realtime.adapters.UserAdapter;
import vn.edu.cuongnh2k.android_realtime.api.BasicApi;
import vn.edu.cuongnh2k.android_realtime.api.ChannelApi;
import vn.edu.cuongnh2k.android_realtime.databinding.ActivityUsersBinding;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseListProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.ChannelProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.TokenProduceDto;

public class UsersActivity extends AppCompatActivity {

    private ActivityUsersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getAllChannel();
        setListeners();
    }

    private Boolean checkToken() {
        return getSharedPreferences(
                "RealTime",
                Context.MODE_PRIVATE).getString("accessToken", null) != null;
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });
    }

    private void getAllChannel() {
        if (!checkToken()) {
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        } else {
            Map<String, String> headers = new HashMap<>();
            headers.put("User-Agent", new WebView(this).getSettings().getUserAgentString());
            headers.put(
                    "Authorization",
                    "Bearer " + getSharedPreferences(
                            "RealTime",
                            Context.MODE_PRIVATE).getString("accessToken", null));

            Map<String, String> params = new HashMap<>();
            params.put("page", "0");
            params.put("size", "100");

            ChannelApi.BASE_API.getAllChannel(headers).enqueue(new Callback<BaseProduceDto>() {
                @Override
                public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                    if (response.isSuccessful()) {
                        BaseListProduceDto baseListProduceDto = new Gson().fromJson(
                                new Gson().toJson(response.body().getData()),
                                BaseListProduceDto.class);

                        List<ChannelProduceDto> channelProduceDto=new Gson().fromJson(
                                new Gson().toJson(baseListProduceDto.getContent()),
                                (Type) ChannelProduceDto.class);

                        RecyclerView lvDanhBa;
                        List<ChannelProduceDto> dsDanhBa;
                        UserAdapter channelAdapter;
                        lvDanhBa = binding.usersRecyclerView;

                        channelAdapter = new UserAdapter(UsersActivity.this, R.layout.item_container_user, channelProduceDto);
                        lvDanhBa.setAdapter(channelAdapter);

                    } else {
                        Map<String, String> map = new HashMap<>();
                        map.put("User-Agent", new WebView(binding.getRoot().getContext()).getSettings().getUserAgentString());
                        map.put(
                                "Authorization",
                                "Bearer " + getSharedPreferences(
                                        "RealTime",
                                        Context.MODE_PRIVATE).getString("refreshToken", null));
                        BasicApi.BASE_API.refresh(map).enqueue(new Callback<BaseProduceDto>() {
                            @Override
                            public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                                if (response.isSuccessful()) {
                                    TokenProduceDto tokenProduceDto = new Gson().fromJson(
                                            new Gson().toJson(response.body().getData()),
                                            TokenProduceDto.class);
                                    SharedPreferences sharedPreferences = getSharedPreferences(
                                            "RealTime",
                                            Context.MODE_PRIVATE);
                                    sharedPreferences.edit().putString(
                                            "accessToken",
                                            tokenProduceDto.getAccessToken()).apply();
                                    getAllChannel();
                                } else {
                                    SharedPreferences sharedPreferences = getSharedPreferences(
                                            "RealTime",
                                            Context.MODE_PRIVATE);
                                    sharedPreferences.edit().remove("accessToken").apply();
                                    sharedPreferences.edit().remove("refreshToken").apply();
                                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                                }
                            }

                            @Override
                            public void onFailure(Call<BaseProduceDto> call, Throwable t) {
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<BaseProduceDto> call, Throwable t) {
                }
            });
        }
    }
}