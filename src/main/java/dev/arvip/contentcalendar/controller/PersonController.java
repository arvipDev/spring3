package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.model.Sex;
import dev.arvip.contentcalendar.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Person> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Person person){
        System.out.println(person);
        if (!repository.findById(person.personId()).isEmpty())  throw new ResponseStatusException(HttpStatus.OK);
        Person per = new Person(person.firstName(), person.lastName(), person.age(), person.sex(),
                person.phoneNumber(), person.address(), null);
        repository.save(per);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@RequestBody Person person, @PathVariable Integer id){
        if(repository.findById(person.personId()).isEmpty()) {
            repository.save(new Person(person.firstName(), person.lastName(), person.age(), person.sex(),
                    person.phoneNumber(), person.address(), null));
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
        repository.save(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @GetMapping("/filter/age/{age}")
    public List<Person> getAllByAge(@PathVariable Integer age){
        return repository.getAllByAge(age);
    }

    @GetMapping("/filter/sex/{sex}")
    public Person getBySex(@PathVariable String sex){
        return repository.getBySex(Sex.valueOf(sex.toUpperCase()));
    }

}
