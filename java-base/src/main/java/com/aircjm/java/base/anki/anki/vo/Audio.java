package com.aircjm.java.base.anki.anki.vo;

import lombok.Data;

import java.util.List;

/**
 * @author aircjm
 */
@Data
public class Audio {

    private String url;
    private String filename;
    private String skipHash;
    private List<String> fields;
}
