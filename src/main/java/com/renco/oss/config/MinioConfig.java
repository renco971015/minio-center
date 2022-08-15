package com.renco.oss.config;

import com.renco.oss.helper.MinioHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioHelper autoConfig(){
        MinioHelper minioHelper = new MinioHelper(endpoint,bucketName,accessKey,secretKey);
        return minioHelper;
    }
}
