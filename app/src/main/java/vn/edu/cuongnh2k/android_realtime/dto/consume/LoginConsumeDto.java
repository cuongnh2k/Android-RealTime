package vn.edu.cuongnh2k.android_realtime.dto.consume;

import java.io.Serializable;

public class LoginConsumeDto implements Serializable {

    private String email;

    private String password;

    public LoginConsumeDto() {
    }

    public LoginConsumeDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginConsumeDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
