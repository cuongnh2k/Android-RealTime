package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.edu.cuongnh2k.android_realtime.enums.ChannelEnum;

public class ChannelProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private ChannelEnum type;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("userChannels")
    private List<UserChannelProduceDto> userChannels;

    @SerializedName("messages")
    private List<MessageProduceDto> messages;

    public ChannelProduceDto() {
    }

    public ChannelProduceDto(
            Long id,
            Long createdDate,
            Long updatedDate,
            String name,
            ChannelEnum type,
            String avatar,
            List<UserChannelProduceDto> userChannels,
            List<MessageProduceDto> messages) {
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<UserChannelProduceDto> getUserChannels() {
        return userChannels;
    }

    public void setUserChannels(List<UserChannelProduceDto> userChannels) {
        this.userChannels = userChannels;
    }

    public List<MessageProduceDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageProduceDto> messages) {
        this.messages = messages;
    }
}