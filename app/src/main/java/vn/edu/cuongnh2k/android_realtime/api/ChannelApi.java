package vn.edu.cuongnh2k.android_realtime.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import vn.edu.cuongnh2k.android_realtime.dto.produce.BaseProduceDto;

public interface ChannelApi {

    Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ChannelApi BASE_API = new Retrofit.Builder()
            .baseUrl("http://13.213.53.246")
            .addConverterFactory(GsonConverterFactory.create(GSON))
            .build()
            .create(ChannelApi.class);

    @GET("/api/v1/channel/user")
    Call<BaseProduceDto> getAllChannel(
            @HeaderMap Map<String, String> headers,
            @QueryMap Map<String,String> params);
}
