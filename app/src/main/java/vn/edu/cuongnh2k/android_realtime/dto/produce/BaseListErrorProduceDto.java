package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BaseListErrorProduceDto implements Serializable {

    @SerializedName("content")
    private List<String> content;

    public BaseListErrorProduceDto() {
    }

    public BaseListErrorProduceDto(List<String> content) {
        this.content = content;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
