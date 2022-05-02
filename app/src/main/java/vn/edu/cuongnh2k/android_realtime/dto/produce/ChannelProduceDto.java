package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.edu.cuongnh2k.android_realtime.enums.ChannelEnum;

public class ChannelProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private ChannelEnum type;

    @SerializedName("userChannels")
    private List<Object> userChannels;

    @SerializedName("messages")
    private List<Object> messages;

    public ChannelProduceDto() {
    }

    public ChannelProduceDto(
            Long id,
            Long createdDate,
            Long updatedDate,
            String name,
            ChannelEnum type,
            List<Object> userChannels,
            List<Object> messages) {
        super(id, createdDate, updatedDate);
        this.name = name;
        this.type = type;
        this.userChannels = userChannels;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChannelEnum getType() {
        return type;
    }

    public void setType(ChannelEnum type) {
        this.type = type;
    }

    public List<Object> getUserChannels() {
        return userChannels;
    }

    public void setUserChannels(List<Object> userChannels) {
        this.userChannels = userChannels;
    }

    public List<Object> getMessages() {
        return messages;
    }

    public void setMessages(List<Object> messages) {
        this.messages = messages;
    }
}