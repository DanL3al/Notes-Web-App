package com.example.demo.dto;


import com.example.demo.model.Note;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NoteDTO {

    private long id;
    private String message;

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.message = note.getMessage();
    }

}
