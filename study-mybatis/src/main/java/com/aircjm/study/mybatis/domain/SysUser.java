package com.aircjm.study.mybatis.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class SysUser {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
