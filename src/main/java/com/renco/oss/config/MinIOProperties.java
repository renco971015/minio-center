package com.renco.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : xurunhao
 * @date   : 2022/8/11
 * @description : MinIO配置参数
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minIO")
public class MinIOProperties {

    /**
     * 端点
     */
    private String endpoint;

    /**
     * 默认的桶名称
     */
    private String bucketName;

    /**
     * 访问key
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;
}
