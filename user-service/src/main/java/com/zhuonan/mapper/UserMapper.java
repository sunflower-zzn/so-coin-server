package com.zhuonan.mapper;


import com.zhuonan.model.UserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @Author zhuonan
 * @Date 2021/10/4
 * @Description
 */
@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM user WHERE mobile = #{mobile} AND password = #{password};")
    UserBean login(@Param("mobile") String mobile, @Param("password") String password);

    @Insert("INSERT INTO user(mobile,username,password,avatar) " +
            "VALUES(#{mobile},#{username},#{password},#{avatar});")
    void insert(@Param("mobile") String mobile, @Param("username") String username, @Param("password") String password, @Param("avatar") String avatar);

    @Select("SELECT * FROM user WHERE mobile = #{mobile};")
    UserBean queryByMobile(@Param("mobile") String mobile);

    @Select("SELECT * FROM user WHERE id = #{id};")
    UserBean queryById(@Param("id") Integer id);

    @Update("UPDATE user SET mobile=#{mobile}, username=#{username}, password=#{password}, avatar=#{avatar} WHERE id =#{id};")
    void modify(UserBean userBean);
}
