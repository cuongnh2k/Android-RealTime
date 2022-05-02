package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseDataProduceDto<ID> implements Serializable {

    @SerializedName("id")
    private ID id;

    @SerializedName("createdDate")
    private Long createdDate;

    @SerializedName("updatedDate")
    private Long updatedDate;

    public BaseDataProduceDto() {
    }

    public BaseDataProduceDto(ID id, Long createdDate, Long updatedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Long updatedDate) {
        this.updatedDate = updatedDate;
    }
}
