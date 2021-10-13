package com.aircjm.study.cloud.webconfig.controller;


import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.webconfig.utils.EasyExcelUtil;
import com.aircjm.study.cloud.webconfig.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/poi")
@Slf4j
public class PoiController {


    @PostMapping("/uploadExcel")
    public String test(@RequestParam("file") MultipartFile multipartFile, @RequestParam("fileType") String  fileType) {
        List<User> users = EasyExcelUtil.importExcel(multipartFile, User.class);
        log.info("userList is {}", JSONUtil.toJsonStr(users));
        return "success";
    }

}
