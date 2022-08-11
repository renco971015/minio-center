package com.renco.oss.services.impl;

import com.renco.oss.context.DefaultContext;
import com.renco.oss.services.IMinIOService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
@Slf4j
public class MinIOServiceImpl implements IMinIOService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String defaultBucketName;

    @Override
    public String upload(MultipartFile multipartFile) {
        final String fileName = multipartFile.getOriginalFilename();
        this.upload(defaultBucketName, multipartFile);
        return getFileUrl(defaultBucketName, fileName, DefaultContext.DEFAULT_EXP_TIME);
    }

    /**
     * 文件上传
     *
     * @param bucketName 桶名称
     * @param file       文件
     * @return 文件url
     */
    @SneakyThrows
    public String upload(String bucketName, MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            final String fileName = file.getOriginalFilename();
            this.upload(bucketName, fileName, is);
            return getFileUrl(bucketName, fileName, DefaultContext.DEFAULT_EXP_TIME);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (Objects.nonNull(is)) {
                    is.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * 获取minio文件的下载地址
     *
     * @param bucketName 桶名称
     * @param fileName   文件名
     */
    @SneakyThrows
    public String getFileUrl(String bucketName, String fileName, Integer expires) {
        return minioClient.presignedGetObject(bucketName, fileName, expires);
    }

    /**
     * 文件上传
     *
     * @param bucketName 桶名称
     * @param fileName   上传后的文件名称
     * @param stream     文件输入流
     * @return 文件url
     */
    @SneakyThrows
    public String upload(String bucketName, String fileName, InputStream stream) {
        minioClient.putObject(bucketName, fileName, stream, new PutObjectOptions(stream.available(), -1));
        return getFileUrl(bucketName, fileName, DefaultContext.DEFAULT_EXP_TIME);
    }

    @Override
    public String getUrlByKey(String key) {
        return this.getFileUrl(defaultBucketName, key, DefaultContext.DEFAULT_EXP_TIME);
    }
}
