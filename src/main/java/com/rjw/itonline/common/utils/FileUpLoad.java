package com.rjw.itonline.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.rjw.itonline.common.component.ALiOssComponent;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @program: itonline
 * @description: 利用阿里云的OSS上传文件
 * @author: 饶嘉伟
 * @create: 2020-12-13 15:56
 **/
@Service
public class FileUpLoad {
    @Autowired
    private ALiOssComponent aliOss;

    final String avatarUPloadPath = "avatar/";

    public String upload(InputStream is, String root,String originalName) throws IOException {

// 上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = root +"/"+
                new DateTime ().toString ("yyyy/MM/dd")+"/"
                + UUID.randomUUID ().toString () +originalName;

        if(aliOss==null){
            System.err.println ("error");
        }
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder ().build (aliOss.getEndpoint (), aliOss.getAccessKeyId (), aliOss
                .getAccessKeySecret ());

        ossClient.putObject (aliOss.getBucketName (), objectName, is);
        is.close ();
// 关闭OSSClient。
        ossClient.shutdown ();
         //返回url地址
        return "https://" + aliOss.getBucketName ()+ "." + aliOss.getEndpoint () + "/" + objectName;
    }
}
