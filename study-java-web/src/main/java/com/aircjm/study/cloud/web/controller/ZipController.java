package com.aircjm.study.cloud.web.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.rimlook.framework.core.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


@RequestMapping("/zip")
@RestController
public class ZipController {



    @GetMapping("/createZip")
    public void createZip(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dirPath = System.getProperty("user.dir")+ "/tmp/data"+ DateUtil.current();
        File dir = FileUtil.file(dirPath);
        boolean mkdirResult = dir.mkdirs();
        if (!mkdirResult) {
            throw new BusinessException("创建文件夹失败");
        }

        File touchA = FileUtil.touch(dirPath + "/a.txt");
        FileUtil.appendString("hello World", touchA, "utf-8");
        File touchB = FileUtil.touch(dirPath + "/b.txt");
        FileUtil.appendString("hello Intellij idea", touchB, "utf-8");
        File zip = ZipUtil.zip(dirPath);

        String agent = request.getHeader("user-agent");

        String filename = zip.getName();
        if (agent.contains("FireFox")) {
            filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        } else {
            filename = URLEncoder.encode(filename, "UTF-8");
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String mineType = request.getServletContext().getMimeType(filename);
        response.setContentType(mineType);
        response.setHeader("Content-disposition", "attachment; filename=" + filename);


        ServletOutputStream out = response.getOutputStream();
        out.write(FileUtil.readBytes(zip));
        out.close();

//        byte[] buffer = new byte[1024];
//
//        int len;
//        while ((len = input.read(buffer)) > 0) {
//            out.write(buffer, 0, len);
//        }
    }

}
