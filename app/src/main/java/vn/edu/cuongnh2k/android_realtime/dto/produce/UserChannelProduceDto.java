package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

public class UserChannelProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("name")
    private String name;

    @SerializedName("user")
    private Object user;

    @SerializedName("channel")
    private Object channel;

    public UserChannelProduceDto() {
    }

    public UserChannelProduceDto(Long id,
                                 Long createdDate,
                                 Long updatedDate,
                                 String name,
                                 Object user,
                                 Object channel) {
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

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getChannel() {
        return channel;
    }

    public void setChannel(Object channel) {
        this.channel = channel;
    }
}