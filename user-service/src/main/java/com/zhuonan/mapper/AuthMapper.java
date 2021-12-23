package com.zhuonan.mapper;

import com.zhuonan.model.LoginUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description
 */
@Mapper
@Repository
public interface AuthMapper {
    @Insert({"INSERT INTO auth(user_id, token, login_time) " +
            "VALUES(#{userId}, #{token}, #{loginTime}) " +
            "ON DUPLICATE KEY UPDATE " +
            "token = #{token}, login_time = #{loginTime};"})
    void insertOrUpdate(LoginUser loginUser);
}
