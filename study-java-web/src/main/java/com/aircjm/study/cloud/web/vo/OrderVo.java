package com.aircjm.study.cloud.web.vo;

import com.aircjm.study.validate.constants.ValidateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderVo {


    /**
     * 尺码不能为空
     */
    @NotNull(groups = {ValidateGroup.SizeGroup.class}, message = "尺码 不能为空")
    private Integer size;


    @NotNull(message = "数量 不能为空")
    private Integer num;

    @NotNull(groups = {ValidateGroup.UnSizeGroup.class}, message = "类型 不能为空")
    private Integer type;


    @NotNull(groups = {ValidateGroup.UnSizeGroup.class, ValidateGroup.SizeGroup.class}, message = "价格 不能为空")
    private BigDecimal price;


}
