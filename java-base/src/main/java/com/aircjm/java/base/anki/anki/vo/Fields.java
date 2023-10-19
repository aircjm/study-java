package com.aircjm.java.base.anki.anki.vo;

import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author aircjm
 */
@Data
public class Fields {
    // todo 确认下序列化段值
    @JsonProperty(value = "Question")
    @Alias("Question")
    private String front;
    @Alias("Answer")
    @JsonProperty(value = "Answer")
    private String back;
}
