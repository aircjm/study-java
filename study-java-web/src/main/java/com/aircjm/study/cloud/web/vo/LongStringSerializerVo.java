package com.aircjm.study.cloud.web.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class LongStringSerializerVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long createBy;

}
