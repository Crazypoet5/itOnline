package com.rjw.itonline.common.component;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 * @program: itonline
 * @description: 阿里云服务的图片缓存功能
 * @author: 饶嘉伟
 * @create: 2020-12-13 14:56
 **/

@Data
@Component
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix="aliyun.oss")
public class ALiOssComponent {
    String endpoint;
    String accessKeyId ;
    String accessKeySecret ;
    String bucketName ;
}
