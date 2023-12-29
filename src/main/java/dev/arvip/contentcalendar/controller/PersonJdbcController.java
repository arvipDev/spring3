package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.repository.PersonJdbcTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/jdbc/person")
public class PersonJdbcController {

    private final PersonJdbcTemplateRepository repository;

    @Autowired
    public PersonJdbcController(PersonJdbcTemplateRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Person> getAll(){
        return repository.getAll();
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable Integer id){
        Person person = repository.getById(id);
        if (person == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return person;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Person person){
        //if (repository.getById(person.personId()) != null) throw new ResponseStatusException(HttpStatus.OK);
        repository.addPerson(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{person_id}")
    public void update(@RequestBody Person person, @PathVariable Integer person_id){
        repository.updatePerson(person, person_id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.deletePerson(id);
    }

}
