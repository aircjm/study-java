package com.aircjm.study.cloud.web.vo;

import com.aircjm.study.cloud.web.jackson.BigDecimalStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BigDecimalStringSerializerVo {


    private Long id;


    @JsonSerialize(using = BigDecimalStringSerializer.class)
    private BigDecimal usage;


}
