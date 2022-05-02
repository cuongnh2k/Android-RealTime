package vn.edu.cuongnh2k.android_realtime.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.cuongnh2k.android_realtime.api.BasicApi;
import vn.edu.cuongnh2k.android_realtime.api.DeviceApi;
import vn.edu.cuongnh2k.android_realtime.api.UserApi;
import vn.edu.cuongnh2k.android_realtime.databinding.ActivityMainBinding;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseDataProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.TokenProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.UserProduceDto;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
//    private StompClient mStompClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        detailUser();
        setListeners();
    }

//    private void connectStomp() {
//        // replace your websocket url
//        mStompClient = Stomp.over(WebSocket.class, "ws://localhost:8000/StompApp/websocket");
//        // replace with your topics
//        mStompClient.topic("/topic/Mytopics")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(topicMessage -> {
//
//                    toast("" + topicMessage.getPayload());
//                });
//
//
//        mStompClient.connect();
//        mStompClient.lifecycle()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(lifecycleEvent -> {
//                    switch (lifecycleEvent.getType()) {
//                        case OPENED:
//                            toast("Stomp connection opened");
//                            break;
//                        case ERROR:
//                            toast("Stomp connection error");
//                            break;
//                        case CLOSED:
//                            toast("Stomp connection closed");
//                    }
//                });
//    }

    private Boolean checkToken() {
        return getSharedPreferences(
                "RealTime",
                Context.MODE_PRIVATE).getString("accessToken", null) != null;
    }

    private void setListeners() {
        binding.imageSignOut.setOnClickListener(v -> {
            logout();
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void detailUser() {
        if (!checkToken()) {
            startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignInActivity.class));
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("User-Agent", new WebView(this).getSettings().getUserAgentString());
            map.put(
                    "Authorization",
                    "Bearer " + getSharedPreferences(
                            "RealTime",
                            Context.MODE_PRIVATE).getString("accessToken", null));
            UserApi.BASE_API.detailUser(map).enqueue(new Callback<BaseProduceDto>() {
                @Override
                public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                    if (response.isSuccessful()) {
                        UserProduceDto userProduceDto = new Gson().fromJson(
                                new Gson().toJson(response.body().getData()),
                                UserProduceDto.class);
                        binding.textName.setText(userProduceDto.getFirstName() + " " + userProduceDto.getLastName());
                        if (userProduceDto.getAvatar() != null) {
                            Picasso.get().load(userProduceDto.getAvatar()).into(binding.imageProfile);
                        }
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
                                    detailUser();
                                } else {
                                    SharedPreferences sharedPreferences = getSharedPreferences(
                                            "RealTime",
                                            Context.MODE_PRIVATE);
                                    sharedPreferences.edit().remove("accessToken").apply();
                                    sharedPreferences.edit().remove("refreshToken").apply();
                                    startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignInActivity.class));
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

    private void logout() {
        if (!checkToken()) {
            startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignInActivity.class));
        } else {
            loading(true);
            Map<String, String> map = new HashMap<>();
            map.put("User-Agent", new WebView(this).getSettings().getUserAgentString());
            map.put(
                    "Authorization",
                    "Bearer " + getSharedPreferences(
                            "RealTime",
                            Context.MODE_PRIVATE).getString("accessToken", null));
            DeviceApi.BASE_API.logout(map).enqueue(new Callback<BaseProduceDto>() {
                @Override
                public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                    if (response.isSuccessful()) {
                        showToast(response.body().getMessage());
                        SharedPreferences sharedPreferences = getSharedPreferences(
                                "RealTime",
                                Context.MODE_PRIVATE);
                        sharedPreferences.edit().remove("accessToken").apply();
                        sharedPreferences.edit().remove("refreshToken").apply();
                        startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignInActivity.class));
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
                                    logout();
                                } else {
                                    SharedPreferences sharedPreferences = getSharedPreferences(
                                            "RealTime",
                                            Context.MODE_PRIVATE);
                                    sharedPreferences.edit().remove("accessToken").apply();
                                    sharedPreferences.edit().remove("refreshToken").apply();
                                    startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignInActivity.class));
                                }
                                loading(false);
                            }

                            @Override
                            public void onFailure(Call<BaseProduceDto> call, Throwable t) {
                            }
                        });
                    }
                    loading(false);
                }

                @Override
                public void onFailure(Call<BaseProduceDto> call, Throwable t) {
                }
            });
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.imageSignOut.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.imageSignOut.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}