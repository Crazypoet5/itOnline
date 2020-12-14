package com.rjw.itonline.edu.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.rjw.itonline.common.utils.FileUpLoad;
import com.rjw.itonline.common.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: itonline
 * @description: 服务器上的文件控制
 * @author: 饶嘉伟
 * @create: 2020-12-13 16:32
 **/
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {
    @Autowired
    FileUpLoad fileUpLoad;
    @PostMapping("upload")
    public Result upload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("module") String module) throws IOException {
        InputStream inputStream = file.getInputStream ();
        String originalFilename = file.getOriginalFilename ();
        String uploadUrl = fileUpLoad.upload (inputStream, originalFilename);
        //返回r对象
        return Result.ok ().message ("文件上传成功").data ("url", uploadUrl);
    }
}
