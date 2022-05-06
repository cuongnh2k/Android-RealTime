package vn.edu.cuongnh2k.android_realtime.adapters;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.edu.cuongnh2k.android_realtime.databinding.ItemContainerSentMessageBinding;
import vn.edu.cuongnh2k.android_realtime.dto.produce.MessageProduceDto;

public class ChatAdapter {
    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        @SuppressLint("SimpleDateFormat")
        void setData(MessageProduceDto messageProduceDto) {
            binding.textMessage.setText(messageProduceDto.getContent());
            binding.textDateTime.setText(new SimpleDateFormat("dd-M-yyyy hh:mm:ss")
                    .format(new Date(messageProduceDto.getCreatedDate())));
        }
    }
}
