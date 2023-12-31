package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.repository.ContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final ContentRepository repository;

    public ContentController(ContentRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content getById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content){
        if (!repository.findById(content.id()).isEmpty()) throw new ResponseStatusException(HttpStatus.OK);
        Content cnt = new Content(content.dateCreated(), content.dateUpdated(), null,
                content.desc(), content.title(), content.status(), content.contentType(), content.url());
        repository.save(cnt);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public void update(@RequestBody Content content, @PathVariable Integer id){
        //This is just an update and not upsert
        repository.save(content);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        repository.deleteById(id);
    }


    @GetMapping("/filter/title/{title}")
    public List<Content> findAllByTitle(@PathVariable String title){
        return repository.findAllByTitleContains(title);
    }

    @GetMapping("/filter/status/{status}")
    public List<Content> findAllWithStatus(@PathVariable Status status){
        return repository.findAllByStatusEquals(status);
    }

}
