package com.renco.oss.controller;

import com.renco.oss.services.IMinIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    IMinIOService minIOService;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        return minIOService.upload(multipartFile);
    }

    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    public String getUrlByKey(String key){
        return minIOService.getUrlByKey(key);
    }
}
