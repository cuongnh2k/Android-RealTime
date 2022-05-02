package vn.edu.cuongnh2k.android_realtime.dto.produce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.edu.cuongnh2k.android_realtime.enums.RoleEnum;

public class RoleProduceDto extends BaseDataProduceDto<Long> {

    @SerializedName("name")
    private RoleEnum name;

    @SerializedName("users")
    private List<UserProduceDto> users;

    public RoleProduceDto() {
    }

    public RoleProduceDto(
            Long id,
            Long createdDate,
            Long updatedDate,
            RoleEnum name,
            List<UserProduceDto> users) {
        super(id, createdDate, updatedDate);
        this.name = name;
        this.users = users;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    public List<UserProduceDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserProduceDto> users) {
        this.users = users;
    }
}
