package com.aircjm.java.base.anki.anki.vo;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;

import java.util.List;

/**
 * @author aircjm
 */
@Data
public class Note {
    private String deckName;
    private String modelName;
    private Fields fields;
    private Audio audio;
    private List<String> tags;


    public static Note convert(String front, String desc, List<String> tags) {
        Note note = new Note();
        note.setDeckName("trello");
        note.setModelName("Markdown");

        Fields fields = new Fields();
        fields.setFront(front);
        fields.setBack(desc.replace("\n", "<br>"));
        note.setFields(fields);
        note.setTags(tags);

        return note;
    }
}
