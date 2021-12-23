package com.zhuonan.service;

import com.zhuonan.mapper.AuthMapper;
import com.zhuonan.mapper.UserMapper;
import com.zhuonan.model.LoginUser;
import com.zhuonan.model.UserBean;
import com.zhuonan.utils.HttpContent;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Author zhuonan
 * @Date 2021/10/4
 * @Description
 */
@Service
public class UserService {
    private static final String DEFAULT_AVATAR = "xxx";

    @Autowired
    UserMapper userMapper;

    @Autowired
    AuthMapper authMapper;

    public HttpContent login(String mobile, String password) {
        UserBean userBean = userMapper.login(mobile, password);
        HttpContent content;
        if (userBean != null) {
            content = HttpContent.LOGIN_SUCCESS;
            // 保存token
            String token = UUID.randomUUID().toString();     // 简单的UUID生成token
            LoginUser loginUser = new LoginUser(userBean.getId(), token, LocalDateTime.now());
            authMapper.insertOrUpdate(loginUser);
            content.setData(loginUser);
        } else {
            content = HttpContent.LOGIN_FAILURE;
        }
        return content;
    }

    public HttpContent register(String mobile, String username, String password) {
        HttpContent content;
        if (userMapper.queryByMobile(mobile) != null) {
            content = HttpContent.REGISTER_FAILURE;
        } else {
            userMapper.insert(mobile, username, password, DEFAULT_AVATAR);
            content = HttpContent.REGISTER_SUCCESS;
        }
        return content;
    }

    public HttpContent modify(UserBean userBean) {
        UserBean userOfMobile = userMapper.queryByMobile(userBean.getMobile());
        // 修改后的电话号需要未被占用
        if (userOfMobile != null && !userOfMobile.getId().equals(userBean.getId())) return HttpContent.MODIFY_FAILURE;
        if (StringUtils.isBlank(userBean.getAvatar())) userBean.setAvatar(DEFAULT_AVATAR);
        userMapper.modify(userBean);
        return HttpContent.MODIFY_SUCCESS;
    }

    public UserBean getUserInfo(Integer userId) {
        return userMapper.queryById(userId);
    }
}
