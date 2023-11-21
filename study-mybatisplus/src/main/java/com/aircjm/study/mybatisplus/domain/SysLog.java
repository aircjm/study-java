package com.aircjm.study.mybatisplus.domain;

import com.aircjm.study.mybatisplus.vo.SysLogRequestJson;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

@Data
@TableName(value = "sys_log", autoResultMap = true)
public class SysLog {


    private Long id;
    private String logSerial;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private SysLogRequestJson requestJson;

    private String responseJson;
    private Integer deleted;
}
