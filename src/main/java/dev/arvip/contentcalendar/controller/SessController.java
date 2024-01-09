package dev.arvip.contentcalendar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
//@RequestMapping("/sess")
public class SessController {

    @GetMapping("/")
    public String home(Principal principal){
        return "Hello " + principal.getName();
    }
}
