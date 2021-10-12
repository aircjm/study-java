package com.aircjm.study.cloud.postgis.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
@TableName("study_user")
public class User {
    @TableId
    private Long id;

    private String name;
    private Integer age;
    private String email;

    private Integer isDel;
}
