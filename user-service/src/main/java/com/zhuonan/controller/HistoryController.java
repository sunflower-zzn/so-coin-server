package com.zhuonan.controller;

import com.zhuonan.service.HistoryService;
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
 * @Date 2021/12/13
 * @Description
 */
@RequestMapping(value = "/ask")
@RestController
public class HistoryController {
    private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
    private static final String HEADER_USER_ID = "X-User-Id";

    @Autowired
    HistoryService historyService;

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    public ResponseResult askQuestion(@RequestParam String question) {
        try {
            Integer userId = Integer.valueOf(request.getHeader(HEADER_USER_ID));
            return ResponseResult.buildSuccess(historyService.askQuestion(userId, question));
        } catch (Exception e) {
            logger.info("[ERROR][ask:question]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ResponseResult getHistory() {
        try {
            Integer userId = Integer.valueOf(request.getHeader(HEADER_USER_ID));
            return ResponseResult.buildSuccess(historyService.getHistory(userId));
        } catch (Exception e) {
            logger.info("[ERROR][ask:history]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }
}
