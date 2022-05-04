package vn.edu.cuongnh2k.android_realtime.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;
import java.util.List;

import vn.edu.cuongnh2k.android_realtime.R;
import vn.edu.cuongnh2k.android_realtime.activities.ChatActivity;
import vn.edu.cuongnh2k.android_realtime.databinding.ItemContainerUserBinding;
import vn.edu.cuongnh2k.android_realtime.dto.produce.ChannelProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.PayloadProduceDto;
import vn.edu.cuongnh2k.android_realtime.dto.produce.UserChannelProduceDto;
import vn.edu.cuongnh2k.android_realtime.enums.ChannelEnum;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<ChannelProduceDto> channelProduceDtoList;

    public UserAdapter(List<ChannelProduceDto> channelProduceDtoList) {
        this.channelProduceDtoList = channelProduceDtoList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerUserBinding itemContainerUserBinding = ItemContainerUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new UserViewHolder(itemContainerUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.setUserData(channelProduceDtoList.get(position));
    }

    @Override
    public int getItemCount() {
        return channelProduceDtoList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ItemContainerUserBinding binding;

        UserViewHolder(ItemContainerUserBinding itemContainerUserBinding) {
            super(itemContainerUserBinding.getRoot());
            binding = itemContainerUserBinding;
        }

        void setUserData(ChannelProduceDto channelProduceDto) {
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
                        binding.textEmail.setText(o.getUser().getEmail());
                        binding.textName.setText(o.getName());
                        if (o.getUser().getAvatar() == null) {
                            binding.imageProfile.setImageResource(R.drawable.ic_person_primary);
                        } else {
                            Picasso.get().load(o.getUser().getAvatar()).into(binding.imageProfile);
                        }
                        break;
                    }
                }
            } else {
                binding.textName.setText(channelProduceDto.getName());
                if (channelProduceDto.getAvatar() == null) {
                    binding.imageProfile.setImageResource(R.drawable.ic_group_person);
                } else {
                    Picasso.get().load(channelProduceDto.getAvatar()).into(binding.imageProfile);
                }
            }
            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(binding.getRoot().getContext().getApplicationContext(), ChatActivity.class);
                intent.putExtra("channel", channelProduceDto);
                binding.getRoot().getContext().startActivity(intent);
            });
        }
    }
}
