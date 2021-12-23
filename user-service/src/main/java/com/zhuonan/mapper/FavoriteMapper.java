package com.zhuonan.mapper;

import com.zhuonan.model.FavoriteBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description 用户收藏mapper
 */
@Mapper
@Repository
public interface FavoriteMapper {
    @Insert("INSERT INTO favorite(user_id, node_label, node_name) " +
            "VALUES(#{userId},#{nodeLabel},#{nodeName});")
    void insert(@Param("userId") Integer userId, @Param("nodeLabel") String nodeLabel, @Param("nodeName") String nodeName);

    @Delete("DELETE FROM favorite WHERE id = #{id};")
    void delete(@Param("id") Integer id);

    @Select("SELECT * FROM favorite WHERE user_id = #{userId};")
    List<FavoriteBean> queryByUser(@Param("userId") Integer userId);
}
