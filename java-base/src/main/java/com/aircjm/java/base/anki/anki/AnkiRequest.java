package com.aircjm.java.base.anki.anki;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author aircjm
 */
@Data
@Slf4j
public class AnkiRequest {
    private String action;
    private int version;
    private Object params;
}
