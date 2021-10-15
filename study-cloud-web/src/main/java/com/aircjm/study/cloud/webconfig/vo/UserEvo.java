package com.aircjm.study.cloud.webconfig.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class UserEvo {

    @ExcelProperty("姓名")
    private String name;


    @ExcelProperty("年龄")
    private Integer age;


    @ExcelProperty("结果")
    private String result;

}
