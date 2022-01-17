package com.aircjm.study.validate.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SaveOrderRequest {


    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "名称不能为空")
    private String name;


    @NotNull(message = "订单类型 不能为空")
    private Integer orderType;

    private OrderVo orderVo;


}
