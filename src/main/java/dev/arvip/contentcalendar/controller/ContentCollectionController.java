package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.repository.ContentCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/local/content")
@CrossOrigin
public class ContentCollectionController {
    private final ContentCollectionRepository repository;
    public ContentCollectionController(ContentCollectionRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Content> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repository.find(id).orElseThrow(
       () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!")
    );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repository.add(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){
        if (!repository.isAvailable(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to update content, no content found");
        repository.add(content);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id){
        if(!repository.isAvailable(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete content, no content found");
        repository.delete(id);
    }

}
