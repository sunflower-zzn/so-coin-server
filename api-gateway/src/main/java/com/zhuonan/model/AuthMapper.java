package com.zhuonan.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author zhuonan
 * @Date 2021/12/4
 * @Description
 */
@Mapper
@Repository
public interface AuthMapper {
    @Select("SELECT * FROM auth WHERE token = #{token}")
    LoginUser queryByToken(@Param("token") String token);
}
