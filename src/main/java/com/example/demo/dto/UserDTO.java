package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String username;
    private String name;

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
    }

}
