package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.config.ContentCalendarConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @Value("${an: default app name}")
    private String an;
    @Value("${as: default app status}")
    private String as;

    @GetMapping("/atValue")
    public Map<String, String> print(){
        return Map.of("appName", an, "appStatus", as);
    }
    private final ContentCalendarConfiguration config;
    public HomeController(ContentCalendarConfiguration config){
        this.config = config;
    }

    @GetMapping("/atConfig")
    public ContentCalendarConfiguration printTwo(){
        return config;
    }
}
