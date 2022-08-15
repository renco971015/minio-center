package com.renco.oss.controller;

import com.renco.oss.helper.MinioHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    MinioHelper minioHelper;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        return minioHelper.upload(multipartFile);
    }

    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    public String getUrlByKey(String key){
        return minioHelper.getUrlByKey(key);
    }

    @RequestMapping(value = "/getUrlPre",method = RequestMethod.GET)
    public Map<String, String> getUrlPre(String key){
        return minioHelper.getUploadUrlPolicy(key);
    }
}
