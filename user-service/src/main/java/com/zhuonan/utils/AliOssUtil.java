package com.zhuonan.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author zhuonan
 * @Date 2021/12/7
 * @Description 阿里云OSS工具类
 */
public class AliOssUtil {
    private static final Logger logger = LoggerFactory.getLogger(AliOssUtil.class);

    public static String upload(MultipartFile sourceFile) throws IOException {
        // 获取文件名
        String fileName = sourceFile.getOriginalFilename();
        //uuid生成主文件名
        String prefix = UUID.randomUUID().toString();
        assert fileName != null;
        //源文件的扩展名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //创建File类型的临时文件
        File tempFile = null;
        tempFile = File.createTempFile(prefix, suffix);
        //将MultipartFile转换成File
        sourceFile.transferTo(tempFile);
        return upload(tempFile);
    }

    public static String upload(File file) {
        String endpoint = "xxx";
        String accessKeyId = "xxx";
        String accessKeySecret = "xxx";
        String bucketName = "so-coin";
        String filePath = "avatar/";
        String fileName = file.getName();
        String newFileName = UUID.randomUUID() + fileName.substring(fileName.indexOf("."));
        //创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //上传文件到指定位置，并使用UUID更名
        ossClient.putObject(bucketName, filePath + newFileName, file);
        //拼接URL
        String url = "xxx" + filePath + newFileName;
        ossClient.shutdown();
        return url;
    }
}
