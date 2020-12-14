package com.rjw.itonline;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @program: itonline
 * @description:
 * @author: 饶嘉伟
 * @create: 2020-12-09 20:10
 **/

public class GeneralTests {
    @Test
    void changeFiles() throws IOException {
        BufferedWriter bos=new BufferedWriter(
                new FileWriter (new File ("output.txt")));
        BufferedReader bis=new BufferedReader (
                (new FileReader (new File("input.txt"))));
        String s="";
        while((s=bis.readLine ())!=null){
            if(s.trim ().length ()>3){
                bos.write (s);
                bos.newLine ();
                bos.flush ();
            }
        }
        bis.close ();
        bos.close ();
    }
    @Test
    public void TestALiOss() throws IOException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//        用户登录名称 rao@1163873362626384.onaliyun.com
//        AccessKey ID LTAI4GKL8FD5PbmdeFzmXBHt
//        AccessKey Secret M5fvYqTdqcucPKzvzGk1Er7gKnjsYa
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4GKL8FD5PbmdeFzmXBHt";
        String accessKeySecret = "M5fvYqTdqcucPKzvzGk1Er7gKnjsYa";
        String bucketName = "itonline";
// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "avatar/test.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder ().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件到指定的存储空间（bucketName）并将其保存为指定的文件名称（objectName）。
        String path = "C:\\Users\\29907\\Pictures\\1593244789662.jpg";
        InputStream is=new FileInputStream (new File (path));
        ossClient.putObject(bucketName, objectName, is);
        is.close ();
// 关闭OSSClient。
        ossClient.shutdown();
    }
}
