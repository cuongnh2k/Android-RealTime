package vn.edu.cuongnh2k.android_realtime.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;

import vn.edu.cuongnh2k.android_realtime.databinding.ActivityChatBinding;
import vn.edu.cuongnh2k.android_realtime.dto.produce.ChannelProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.PayloadProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.UserChannelProduceDto;
import vn.edu.cuongnh2k.android_realtime.enums.ChannelEnum;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadReceiverDetails();
    }

    private void loadReceiverDetails() {
        ChannelProduceDto channelProduceDto = (ChannelProduceDto) getIntent().getSerializableExtra("channel");
        if (channelProduceDto.getType().equals(ChannelEnum.SINGLE)) {
            PayloadProduceDto payloadProduceDto = new Gson().fromJson(
                    new String(Base64.decode(
                            (binding.getRoot().getContext().getSharedPreferences(
                                    "RealTime",
                                    Context.MODE_PRIVATE).getString(
                                    "accessToken",
                                    null)).split("\\.")[1], Base64.URL_SAFE),
                            StandardCharsets.UTF_8),
                    PayloadProduceDto.class);
            for (UserChannelProduceDto o : channelProduceDto.getUserChannels()) {
                if (!o.getUser().getEmail().equals(payloadProduceDto.getSub())) {
                    binding.textName.setText(o.getName());
                    break;
                }
            }
        } else {
            binding.textName.setText(channelProduceDto.getName());
        }
    }

    private void setListeners() {
        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }
}