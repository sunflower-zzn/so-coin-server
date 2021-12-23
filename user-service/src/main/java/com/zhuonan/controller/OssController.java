package com.zhuonan.controller;

import com.zhuonan.utils.AliOssUtil;
import com.zhuonan.utils.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zhuonan
 * @Date 2021/12/7
 * @Description aliyun oss controller
 */
@RestController
@RequestMapping("/oss")
public class OssController {
    private static final Logger logger = LoggerFactory.getLogger(AliOssUtil.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseResult upload(@RequestBody MultipartFile file) {
        try {
            return ResponseResult.buildSuccess(AliOssUtil.upload(file));
        } catch (Exception e) {
            logger.info("[ERROR][user:oss-upload]" + e.getMessage());
            return ResponseResult.buildFailure(e.getMessage());
        }
    }
}
