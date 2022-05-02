package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import vn.edu.cuongnh2k.android_realtime.enums.MessageEnum;

public class MessageProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("updatedBy")
    private String updatedBy;

    @SerializedName("content")
    private String content;

    @SerializedName("type")
    private MessageEnum type;

    @SerializedName("user")
    private Object user;

    @SerializedName("channel")
    private Object channel;

    public MessageProduceDto() {
    }

    public MessageProduceDto(Long id,
                             Long createdDate,
                             Long updatedDate,
                             String createdBy,
                             String updatedBy,
                             String content,
                             MessageEnum type,
                             Object user,
                             Object channel) {
        super(id, createdDate, updatedDate);
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.content = content;
        this.type = type;
        this.user = user;
        this.channel = channel;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageEnum getType() {
        return type;
    }

    public void setType(MessageEnum type) {
        this.type = type;
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