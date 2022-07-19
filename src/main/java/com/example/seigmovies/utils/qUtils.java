package com.example.seigmovies.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * 腾讯云  上传存储桶
 */
public class qUtils {
    public static boolean upload(MultipartFile file, COSClient cosClient) {
        //由于MultipartFile文件内的内容为空的时候也判断为空，所以需要额外添加一个Objects.isNull(file)

//        if (file.isEmpty() && Objects.isNull(file)) {
//            System.out.println("未指定文件");
//        }

        //1.【先将文件上传到本地】
        // 指定本地存储位置
        String localPath = "D:\\log";
        //获取文件名称
        String fileName = file.getOriginalFilename();
        // 指定要上传的文件
        File desFile = new File(localPath + "/" + fileName);
        try {
            file.transferTo(desFile);
        } catch (IOException e) {
            e.printStackTrace();
            //文件上传失败就返回错误响应
            return false;
        }

        //2.【将文件上传到腾讯云】
        // 指定文件将要存放的存储桶
        String bucketName = "";
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        // 指定腾讯云的上传文件路径
        String originPath = "image/";
        String key = originPath + "/" + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, desFile);
        //将请求发送
        cosClient.putObject(putObjectRequest);
        return true;
    }
}
