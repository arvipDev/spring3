package dev.arvip.contentcalendar.controller;


import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.repository.PersonCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping("/api/person")
@RestController
public class PersonController {

    private final PersonCollectionRepository repository;

    @Autowired
    public PersonController(PersonCollectionRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Person> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{personId}")
    public Person getById(@PathVariable Integer personId){
        return repository.find(personId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Record not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Person person){
        if(repository.isAvailable(person.personId())) throw new ResponseStatusException(HttpStatus.OK, "Person record already exists");
        repository.create(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{personId}")
    public void update(@RequestBody Person person, @PathVariable Integer personId){
        if(!repository.isAvailable(personId)) {
            repository.create(person);
            throw new ResponseStatusException(HttpStatus.CREATED, "Person not found, new record created");
        } else repository.create(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{personId}")
    public void remove(@PathVariable Integer personId){
        if (repository.isAvailable(personId)) repository.delete(personId);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete as record not found in repo");
    }

}
