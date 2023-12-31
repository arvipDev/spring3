package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.repository.ContentJdbcTemplateRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/jdbc/content")
@CrossOrigin
public class ContentJdbcController {
    private final ContentJdbcTemplateRepository repository;

    public ContentJdbcController(ContentJdbcTemplateRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll(){
        return repository.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        Content content = repository.getById(id);
        if (content == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        else return content;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void add(@RequestBody Content content){
        repository.createContent(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        repository.updateContent(content, id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.deleteContent(id);
    }

}
