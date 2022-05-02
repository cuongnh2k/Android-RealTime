package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

public class DeviceProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("userAgent")
    private String userAgent;

    @SerializedName("user")
    private UserProduceDto user;

    public DeviceProduceDto() {
    }

    public DeviceProduceDto(Long id,
                            Long createdDate,
                            Long updatedDate,
                            String userAgent,
                            UserProduceDto user) {
        super(id, createdDate, updatedDate);
        this.userAgent = userAgent;
        this.user = user;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public UserProduceDto getUser() {
        return user;
    }

    public void setUser(UserProduceDto user) {
        this.user = user;
    }
}
