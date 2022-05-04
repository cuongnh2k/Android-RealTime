package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("roles")
    private List<RoleProduceDto> roles;

    @SerializedName("devices")
    private List<DeviceProduceDto> devices;

    @SerializedName("userChannels")
    private List<UserChannelProduceDto> userChannels;

    @SerializedName("messages")
    private List<MessageProduceDto> messages;

    public UserProduceDto() {
    }

    public UserProduceDto(
            Long id,
            Long createdDate,
            Long updatedDate,
            String email,
            String firstName,
            String lastName,
            String avatar,
            List<RoleProduceDto> roles,
            List<DeviceProduceDto> devices,
            List<UserChannelProduceDto> userChannels,
            List<MessageProduceDto> messages) {
        super(id, createdDate, updatedDate);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
        this.roles = roles;
        this.devices = devices;
        this.userChannels = userChannels;
        this.messages = messages;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<RoleProduceDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleProduceDto> roles) {
        this.roles = roles;
    }

    public List<DeviceProduceDto> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceProduceDto> devices) {
        this.devices = devices;
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
