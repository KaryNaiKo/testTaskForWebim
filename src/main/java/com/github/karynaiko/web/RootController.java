package com.github.karynaiko.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RootController{

    @GetMapping("/")
    public String root() {
        return "redirect:friends";
    }

    @GetMapping("/friends")
    public String users() {
        return "friends";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
