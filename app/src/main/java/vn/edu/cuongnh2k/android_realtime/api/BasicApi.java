package vn.edu.cuongnh2k.android_realtime.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import vn.edu.cuongnh2k.android_realtime.dto.consume.LoginConsumeDto;
import vn.edu.cuongnh2k.android_realtime.dto.consume.RegisterConsumeDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;

public interface BasicApi {

    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    BasicApi BASE_API = new Retrofit.Builder()
            .baseUrl("http://13.213.53.246")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(BasicApi.class);

    @POST("/api/v1/basic/login")
    Call<BaseProduceDto> login(
            @Header("User-Agent") String userAgent,
            @Body LoginConsumeDto loginConsumeDto);

    @POST("/api/v1/basic/register")
    Call<BaseProduceDto> register(
            @Header("User-Agent") String userAgent,
            @Body RegisterConsumeDto registerConsumeDto);

    @PATCH("/api/v1/basic/refresh")
    Call<BaseProduceDto> refresh(@HeaderMap Map<String, String> headers);
}
