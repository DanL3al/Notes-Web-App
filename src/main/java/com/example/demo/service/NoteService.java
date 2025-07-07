package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;


    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public List<Note> findUserNotes(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        return noteRepository.findNotesByUsers(user);
    }

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    public Note updateNote(Note note){
        return noteRepository.save(note);
    }

    public Optional<Note> findNoteById(Long noteId){
        return noteRepository.findById(noteId);
    }

    public void deleteNote(Long noteId){
        noteRepository.deleteById(noteId);
    }


}
