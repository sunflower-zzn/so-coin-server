package com.zhuonan.controller;

import com.zhuonan.service.FavoriteService;
import com.zhuonan.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhuonan
 * @Date 2021/12/5
 * @Description
 */
@RestController
@RequestMapping(value = "/favor")
public class FavoriteController {
    private static final Logger logger = LoggerFactory.getLogger(FavoriteController.class);
    private static final String HEADER_USER_ID = "X-User-Id";

    @Autowired
    HttpServletRequest request;

    @Autowired
    FavoriteService favoriteService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResponseResult addFavorite(@RequestParam String nodeLabel, @RequestParam String nodeName) {
        try {
            Integer userId = Integer.valueOf(request.getHeader(HEADER_USER_ID));
            favoriteService.addFavorite(userId, nodeLabel, nodeName);
            return ResponseResult.buildSuccess();
        } catch (Exception e) {
            logger.info("[ERROR][favor:add]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResponseResult deleteFavorite(@RequestParam Integer id) {
        try {
            favoriteService.deleteFavorite(id);
            return ResponseResult.buildSuccess();
        } catch (Exception e) {
            logger.info("[ERROR][favor:delete]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseResult queryByUser() {
        try {
            Integer userId = Integer.valueOf(request.getHeader(HEADER_USER_ID));
            return ResponseResult.buildSuccess(favoriteService.queryByUser(userId));
        } catch (Exception e) {
            logger.info("[ERROR][favor:query]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }
}
