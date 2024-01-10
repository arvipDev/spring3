package dev.arvip.contentcalendar.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
//@RequestMapping("/sess")
public class SessController {

    private String SESS_COUNT = "SESS_COUNT";

    @GetMapping("/")
    public String home(Principal principal, HttpSession httpSession){
        System.out.println(principal.toString());
        incrementCounter(httpSession, SESS_COUNT);
        return "Hello " + principal.getName();
    }

    @GetMapping("/count")
    public String getCounter(HttpSession httpSession){
        return incrementCounter(httpSession, SESS_COUNT);
    }

    public String incrementCounter(HttpSession httpSession, String attr){
        System.out.println(httpSession.getAttributeNames());
        System.out.println("Creating time: " + httpSession.getCreationTime());
        System.out.println("Last accessed time: " + httpSession.getLastAccessedTime());
        System.out.println("ID " + httpSession.getId());

        var session = httpSession.getAttribute(attr) == null ? 0 : (Integer) httpSession.getAttribute(attr);
        httpSession.setAttribute(attr, ++session);
        httpSession.setMaxInactiveInterval(120);
        return "SESS_COUNT: " + httpSession.getAttribute(attr);
    }
}
