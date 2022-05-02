package vn.edu.cuongnh2k.android_realtime.activities;

import android.content.Intent;
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
import vn.edu.cuongnh2k.android_realtime.databinding.ActivitySignUpBinding;
import vn.edu.cuongnh2k.android_realtime.dto.consume.RegisterConsumeDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;


public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.textSignIn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SignInActivity.class)));
        binding.buttonSignUp.setOnClickListener(v -> {
            if (isValidRegisterDetails()) {
                register();
            }
        });
    }

    private void register() {
        loading(true);
        BasicApi.BASE_API.register(
                new WebView(this).getSettings().getUserAgentString(),
                new RegisterConsumeDto(
                        binding.inputEmail.getText().toString().trim(),
                        binding.inputPassword.getText().toString(),
                        binding.inputFirstName.getText().toString().trim(),
                        binding.inputLastName.getText().toString().trim())).enqueue(new Callback<BaseProduceDto>() {
            @Override
            public void onResponse(Call<BaseProduceDto> call, Response<BaseProduceDto> response) {
                if (response.isSuccessful()) {
                    System.out.println(response.body());
                    showToast(response.body().getMessage());
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
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

    private Boolean isValidRegisterDetails() {
        if (binding.inputFirstName.getText().toString().trim().isEmpty()) {
            showToast("first name cannot be blank");
            return false;
        } else if (binding.inputLastName.getText().toString().trim().isEmpty()) {
            showToast("last name cannot be blank");
            return false;
        } else if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("email cannot be blank");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString().trim()).matches()) {
            showToast("wrong email format");
            return false;
        } else if (binding.inputPassword.getText().toString().isEmpty()) {
            showToast("cannot be blank");
            return false;
        } else if (!binding.inputPassword.getText().toString().equals(binding.inputConfirmPassword.getText().toString())) {
            showToast("confirm password incorrect");
            return false;
        } else {
            return true;
        }
    }

    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.buttonSignUp.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.buttonSignUp.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}