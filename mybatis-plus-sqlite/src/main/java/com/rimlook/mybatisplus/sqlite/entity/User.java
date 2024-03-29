package com.rimlook.mybatisplus.sqlite.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author nieqiuqiu
 */
@Data
@TableName("sys_user")
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;

    private Integer userStatus;
    private Integer isDelete;

}
