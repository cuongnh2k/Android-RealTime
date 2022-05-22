package vn.edu.cuongnh2k.android_realtime.api;

import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;

public interface UserApi {

    UserApi BASE_API = new Retrofit.Builder()
            .baseUrl(Domain.domain)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
            .build()
            .create(UserApi.class);

    @GET("/api/v1/user")
    Call<BaseProduceDto> detailUser(@HeaderMap Map<String, String> headers);
}
