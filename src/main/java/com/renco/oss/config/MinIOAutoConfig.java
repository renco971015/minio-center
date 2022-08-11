package com.renco.oss.config;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOAutoConfig {


    @ConditionalOnMissingBean
    @SneakyThrows
    @Bean
    public MinioClient minioClient(MinIOProperties minIOProperties) {
        MinioClient minioClient = new MinioClient(minIOProperties.getEndpoint(), minIOProperties.getAccessKey(), minIOProperties.getSecretKey());
        return minioClient;
    }
}
