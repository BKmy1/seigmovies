package com.example.seigmovies.controller;

import com.example.seigmovies.config.COSConfig;
import com.example.seigmovies.utils.qUtils;
import com.qcloud.cos.COSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class COSController {

    @Autowired
    private COSClient cosClient;


    /**
     * 文件上传到腾讯云测试
     */
    @PostMapping("/test/upload")
    public Boolean uploadFile(MultipartFile file) {
        COSConfig cosConfig = new COSConfig();
        COSClient cosClient = cosConfig.getCOSClient();
        boolean upload = qUtils.upload(file, cosClient);
        System.out.println("上传为：" + upload);
        return upload;
    }

    /**
     * 多文件上传
     *
     * @param files 文件集合
     * @return
     */
    @PostMapping("/test/files")
    public Boolean uploadFiles(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (!qUtils.upload(file, cosClient)) {
                System.out.println("上传失败");
                return false;
            }
        }
        System.out.println("上传成功");
        return true;
    }
}
