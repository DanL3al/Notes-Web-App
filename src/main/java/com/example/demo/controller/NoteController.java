package com.example.demo.controller;

import com.example.demo.dto.NoteDTO;
import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.NoteService;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")

public class NoteController {

    private final NoteService noteService;
    private final UserService userService;
    private final JwtService jwtService;

    public NoteController(NoteService noteService, UserService userService, JwtService jwtService) {
        this.noteService = noteService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(@RequestHeader("Authorization") String auth) {

        String token = "";

        if(auth == null || !auth.startsWith("Bearer ")) return ResponseEntity.status
                (HttpStatus.BAD_REQUEST).build();

        token = auth.substring(7);

        if(jwtService.isTokenValid(token)){
            Long userId = jwtService.getUserId(token);
            if(userId == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            List<Note> notes = noteService.findUserNotes(userId);
            List<NoteDTO> result = new ArrayList<>();
            for (Note note : notes) {
                result.add(new NoteDTO(note));
            }
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestHeader("Authorization") String auth
            , @RequestBody Note note) {

        String token = "";

        if(auth == null || !auth.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authorization header is" +
                    " incorrect");
        }

        token = auth.substring(7);

        if(jwtService.isTokenValid(token)){

            if(note.getMessage() == null || note.getMessage().isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message is empty");
            }

            Long userId = jwtService.getUserId(token);
            Optional<User> user = userService.findById(userId);
            if(user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            note.setUsers(user.get());
            noteService.saveNote(note);
            return ResponseEntity.ok(new NoteDTO(note));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");

    }

    @PutMapping("/{noteId}")
    public ResponseEntity<?> updateNote(@RequestHeader("Authorization") String auth,
                                        @PathVariable Long noteId, @RequestBody Note note) {

        String token = "";

        if(auth == null || !auth.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Auth missing");
        }

        token = auth.substring(7);

        if(jwtService.isTokenValid(token)){
            Note noteToAtt = noteService.findNoteById(noteId).orElse(null);
            if(noteToAtt == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
            noteToAtt.setMessage(note.getMessage());
            noteService.saveNote(noteToAtt);
            return ResponseEntity.ok(new NoteDTO(note));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId) {
        noteService.deleteNote(noteId);
    }


}
