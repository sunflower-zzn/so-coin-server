package com.zhuonan.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description
 */
public class LoginUser {
    private Integer userId;
    private String token;
    private Timestamp loginTime;

    public LoginUser(Integer userId, String token, LocalDateTime loginTime) {
        this.userId = userId;
        this.token = token;
        this.loginTime = Timestamp.valueOf(loginTime);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }


}
