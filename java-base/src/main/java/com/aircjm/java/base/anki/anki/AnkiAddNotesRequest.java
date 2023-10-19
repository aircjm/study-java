package com.aircjm.java.base.anki.anki;

import com.aircjm.java.base.anki.anki.vo.Note;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
public class AnkiAddNotesRequest {
    List<Note> notes;
}
