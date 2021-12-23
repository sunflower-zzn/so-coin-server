package com.zhuonan.controller;

import com.zhuonan.model.UserBean;
import com.zhuonan.service.UserService;
import com.zhuonan.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhuonan
 * @Date 2021/10/4
 * @Description
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String HEADER_USER_ID = "X-User-Id";

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody UserBean userBean) {
        try {
            return ResponseResult.buildSuccess(userService.login(userBean.getMobile(), userBean.getPassword()).toMap());
        } catch (Exception e) {
            logger.info("[ERROR][user:login]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@RequestBody UserBean userBean) {
        try {
            return ResponseResult.buildSuccess(userService.register(userBean.getMobile(), userBean.getUsername(), userBean.getPassword()).toMap());
        } catch (Exception e) {
            logger.info("[ERROR][user:register]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseResult modify(@RequestBody UserBean userBean) {
        try {
            return ResponseResult.buildSuccess(userService.modify(userBean).toMap());
        } catch (Exception e) {
            logger.info("[user-service:modify]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseResult getUserInfo() {
        try {
            Integer userId = Integer.valueOf(request.getHeader(HEADER_USER_ID));
            return ResponseResult.buildSuccess(userService.getUserInfo(userId));
        } catch (Exception e) {
            logger.info("[ERROR][user:login]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }
}
