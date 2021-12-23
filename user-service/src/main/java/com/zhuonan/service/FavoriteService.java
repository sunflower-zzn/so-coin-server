package com.zhuonan.service;

import com.zhuonan.mapper.FavoriteMapper;
import com.zhuonan.model.FavoriteBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description 收藏service
 */
@Service
public class FavoriteService {
    @Autowired
    FavoriteMapper favoriteMapper;

    public void addFavorite(Integer userId, String nodeLabel, String nodeName) {
        favoriteMapper.insert(userId, nodeLabel, nodeName);
    }

    public void deleteFavorite(Integer id) {
        favoriteMapper.delete(id);
    }

    public List<FavoriteBean> queryByUser(Integer userId) {
        return favoriteMapper.queryByUser(userId);
    }
}
