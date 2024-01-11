package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.repository.ContentRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentRepository repository;
    private String SESSION_NAME = "COUNTER";

    public ContentController(ContentRepository repository){
        this.repository = repository;
    }

    @GetMapping("/counter")
    public String  getPageVisitsInSession(Principal Principal, HttpSession httpSession){
        return "Visits " + incrementCounter(httpSession, SESSION_NAME);
    }

    //counter for session.
    public String incrementCounter(HttpSession httpSession, String attr){
        var session = httpSession.getAttribute(attr) == null ? 0 : (Integer) httpSession.getAttribute(attr);
        httpSession.setAttribute(SESSION_NAME, ++session);
        httpSession.setMaxInactiveInterval(120);
        return "to the site: " + httpSession.getAttribute(attr) ;
    }

    @GetMapping("")
    public List<Content> getAll(HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content getById(@PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        if (!repository.findById(content.id()).isEmpty()) throw new ResponseStatusException(HttpStatus.OK);
        Content cnt = new Content(content.dateCreated(), content.dateUpdated(), null,
                content.desc(), content.title(), content.status(), content.contentType(), content.url());
        repository.save(cnt);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        repository.save(content);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        repository.deleteById(id);
    }


    @GetMapping("/filter/title/{title}")
    public List<Content> findAllByTitle(@PathVariable String title, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findAllByTitleContains(title);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findAllWithStatus(@PathVariable Status status, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findAllByStatusEquals(status);
    }

}
