package com.zhuonan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description 用户登陆保存session状态
 */
@Service
public class TokenAuth {
    @Autowired
    AuthMapper authMapper;

    /**
     * 根据token，获取session
     */
    public LoginUser getSession(String token) {
        LoginUser loginUser = authMapper.queryByToken(token);
        // token不存在，或超过2小时无效
        Timestamp validTime = Timestamp.valueOf(LocalDateTime.now().minusHours(2));
        if (loginUser == null || loginUser.getLoginTime().before(validTime)) return null;
        // token有效，返回用户id
        return loginUser;
    }
}
