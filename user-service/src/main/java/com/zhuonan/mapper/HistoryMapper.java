package com.zhuonan.mapper;

import com.zhuonan.model.HistoryBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/13
 * @Description
 */
@Mapper
@Repository
public interface HistoryMapper {
    @Insert({"INSERT INTO history(user_id, question, answer) " +
            "VALUES(#{userId}, #{question}, #{answer});"})
    void insert(HistoryBean historyBean);

    @Select({"SELECT question FROM history WHERE user_id = #{userId};"})
    List<String> queryByUser(Integer userId);
}
