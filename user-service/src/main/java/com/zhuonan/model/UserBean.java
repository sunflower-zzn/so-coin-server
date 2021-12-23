package com.zhuonan.model;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description
 */
public class UserBean {
    // id，自增主键
    private Integer id;

    // 电话号码，唯一索引
    private String mobile;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 头像链接
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
