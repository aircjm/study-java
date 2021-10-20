package com.aircjm.study.cloud.webconfig.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.net.URL;

@Data
public class UserImageEvo {

    @ExcelProperty("姓名")
    private String name;


    @ExcelProperty("年龄")
    private Integer age;


    @ExcelProperty("头像")
    private URL imageUrl;


    @ExcelProperty("结果")
    private String result;

}
