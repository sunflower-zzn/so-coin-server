package com.zhuonan.utils;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description
 */
public enum HttpContent {
    LOGIN_SUCCESS(200, "登录成功", true),
    LOGIN_FAILURE(4001, "登录失败，用户名或密码错误", false),
    REGISTER_SUCCESS(200, "注册成功", true),
    REGISTER_FAILURE(4002, "注册失败，手机号已被注册", false),
    MODIFY_SUCCESS(200, "修改信息成功", true),
    MODIFY_FAILURE(4003, "修改信息失败，手机号已被注册", false),
    ;


    private int code;
    private String msg;
    private Object data;

    HttpContent(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> toMap() {
        return ImmutableMap.<String, Object>builder()
                .put("code", code)
                .put("msg", msg)
                .put("data", data)
                .build();
    }
}
