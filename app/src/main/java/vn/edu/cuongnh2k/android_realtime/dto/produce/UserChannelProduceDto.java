package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

public class UserChannelProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("name")
    private String name;

    @SerializedName("user")
    private UserProduceDto user;

    @SerializedName("channel")
    private ChannelProduceDto channel;

    public UserChannelProduceDto() {
    }

    public UserChannelProduceDto(Long id,
                                 Long createdDate,
                                 Long updatedDate,
                                 String name,
                                 UserProduceDto user,
                                 ChannelProduceDto channel) {
        super(id, createdDate, updatedDate);
        this.name = name;
        this.user = user;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserProduceDto getUser() {
        return user;
    }

    public void setUser(UserProduceDto user) {
        this.user = user;
    }

    public ChannelProduceDto getChannel() {
        return channel;
    }

    public void setChannel(ChannelProduceDto channel) {
        this.channel = channel;
    }
}