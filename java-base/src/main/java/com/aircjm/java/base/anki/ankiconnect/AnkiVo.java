package com.aircjm.java.base.anki.ankiconnect;

import lombok.Data;

import java.util.Map;

/**
 * @author aircjm
 */
@Data
public class AnkiVo {

    private String action;

    private Integer version = 5;

    private Map<String, Object> params;
}
