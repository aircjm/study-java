package com.aircjm.java.base.vo;

import cn.hutool.core.net.NetUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserParam {


    private String name;
    private String host= NetUtil.getLocalHostName();

    private Integer age;


}
