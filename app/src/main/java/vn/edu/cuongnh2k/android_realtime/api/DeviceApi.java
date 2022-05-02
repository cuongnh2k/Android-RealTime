package vn.edu.cuongnh2k.android_realtime.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.HeaderMap;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;

public interface DeviceApi {

    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    DeviceApi BASE_API = new Retrofit.Builder()
            .baseUrl("http://13.213.53.246")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(DeviceApi.class);

    @DELETE("/api/v1/device/logout")
    Call<BaseProduceDto> logout(@HeaderMap Map<String, String> headers);
}
