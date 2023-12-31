package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.repository.ContentRepository;
import dev.arvip.contentcalendar.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonRepository repository;

    @Autowired
    public PersonController(PersonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Person> getAll(){
        return repository.findAll();
    }


}
