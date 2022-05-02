package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseProduceDto implements Serializable {

    @SerializedName("message")
    private String message;

    @SerializedName("errorCode")
    private Integer errorCode;

    @SerializedName("success")
    private Boolean success;

    @SerializedName("data")
    private Object data;

    public BaseProduceDto() {
    }

    public BaseProduceDto(String message, Integer errorCode, Boolean success, Object data) {
        this.message = message;
        this.errorCode = errorCode;
        this.success = success;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseProduceDto{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
