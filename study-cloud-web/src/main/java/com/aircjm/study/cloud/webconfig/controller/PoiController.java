package com.aircjm.study.cloud.webconfig.controller;


import cn.hutool.json.JSONUtil;
import com.aircjm.study.cloud.webconfig.utils.EasyExcelUtil;
import com.aircjm.study.cloud.webconfig.vo.UserEvo;
import com.alibaba.excel.EasyExcel;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author aircjm
 */
@RestController
@RequestMapping(value = "/poi")
@Slf4j
public class PoiController {


    @PostMapping("/uploadExcel")
    public String uploadExcel(@RequestParam("file") MultipartFile multipartFile, @RequestParam("fileType") String fileType) {
        List<UserEvo> users = EasyExcelUtil.importExcel(multipartFile, UserEvo.class);
        log.info("{} userList is {}", fileType, JSONUtil.toJsonStr(users));
        return "success";
    }


    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<UserEvo> userList = Lists.newArrayList();
        for (int i = 0; i < 5000; i++) {
            UserEvo user = new UserEvo();
            user.setName("姓名" + i);
            user.setAge(i);
            userList.add(user);
        }


        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserEvo.class).sheet("模板").doWrite(userList);
    }


}
