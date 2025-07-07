package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "signup";
    }

    @GetMapping("/notes")
    public String notes(){
        return "note";
    }

}
