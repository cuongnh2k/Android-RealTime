package vn.edu.cuongnh2k.android_realtime.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.cuongnh2k.android_realtime.api.BasicApi;
import vn.edu.cuongnh2k.android_realtime.databinding.ActivitySignInBinding;
import vn.edu.cuongnh2k.android_realtime.dto.consume.LoginConsumeDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.TokenProduceDto;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if (checkToken()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        setListeners();
    }

    private Boolean checkToken() {
        return getSharedPreferences(
                "RealTime",
                Context.MODE_PRIVATE).getString("accessToken", null) != null;
    }

    private void setListeners() {
        binding.textCreateNewAccount.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), vn.edu.cuongnh2k.android_realtime.activities.SignUpActivity.class)));
        binding.buttonSignIn.setOnClickListener(v -> {
            if (isValidSignInDetails()) {
                signIn();
            }
        });
    }

    private void signIn() {
        loading(true);
        BasicApi.BASE_API.login(
                new WebView(this).getSettings().getUserAgentString(),
                new LoginConsumeDto(
                        binding.inputEmail.getText().toString().trim(),
                        binding.inputPassword.getText().toString())).enqueue(new Callback<BaseProduceDto>() {
            @Override
            public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body());
                    showToast(response.body().getMessage());
                    TokenProduceDto tokenProduceDto = new Gson().fromJson(
                            new Gson().toJson(response.body().getData()),
                            TokenProduceDto.class);
                    System.out.println(tokenProduceDto);
                    SharedPreferences sharedPreferences = getSharedPreferences(
                            "RealTime",
                            Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString(
                            "accessToken",
                            tokenProduceDto.getAccessToken()).apply();
                    sharedPreferences.edit().putString(
                            "refreshToken",
                            tokenProduceDto.getRefreshToken()).apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    showToast(new Gson().fromJson(response.errorBody().charStream(), BaseProduceDto.class).getMessage());
                }
                loading(false);
            }

            @Override
            public void onFailure(Call<BaseProduceDto> call, Throwable t) {
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignInDetails() {
        if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("email cannot be blank");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString().trim()).matches()) {
            showToast("wrong email format");
            return false;
        } else if (binding.inputPassword.getText().toString().isEmpty()) {
            showToast("password cannot be blank");
            return false;
        } else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.buttonSignIn.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}