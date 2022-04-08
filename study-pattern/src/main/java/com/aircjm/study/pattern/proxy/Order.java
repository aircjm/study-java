package com.aircjm.study.pattern.proxy;

import lombok.Data;

@Data
public class Order {

    private Object info;

    private Long id;
    private Long createTime;

}
