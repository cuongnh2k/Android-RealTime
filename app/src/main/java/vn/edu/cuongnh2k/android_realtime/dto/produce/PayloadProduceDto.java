package vn.edu.cuongnh2k.android_realtime.dto.produce;

import java.io.Serializable;
import java.util.Arrays;

public class PayloadProduceDto implements Serializable {

    private String sub;

    private String[] roles;

    private String iss;

    private Long exp;

    private String type;

    public PayloadProduceDto(String sub, String[] roles, String iss, Long exp, String type) {
        this.sub = sub;
        this.roles = roles;
        this.iss = iss;
        this.exp = exp;
        this.type = type;
    }

    public PayloadProduceDto() {
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PayloadProduceDto{" +
                "sub='" + sub + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", iss='" + iss + '\'' +
                ", exp=" + exp +
                ", type='" + type + '\'' +
                '}';
    }
}
