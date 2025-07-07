package com.example.demo.controller;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String header) {
        String token = "";
        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            if(jwtService.isTokenValid(token)) {
                Optional<User> user = userService.findById(jwtService.getUserId(token));
                if(user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
                return ResponseEntity.ok(new UserDTO(user.get()));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

}
