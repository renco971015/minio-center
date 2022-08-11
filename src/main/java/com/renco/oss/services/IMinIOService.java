package com.renco.oss.services;

import org.springframework.web.multipart.MultipartFile;

public interface IMinIOService {

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    String upload(MultipartFile multipartFile);

    /**
     * 获取文件url
     * @param key
     * @return
     */
    String getUrlByKey(String key);

}
