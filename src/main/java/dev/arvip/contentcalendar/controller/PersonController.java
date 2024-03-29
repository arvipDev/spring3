package dev.arvip.contentcalendar.controller;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.model.Sex;
import dev.arvip.contentcalendar.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonRepository repository;
    private String SESSION_NAME = "COUNTER";

    public PersonController(PersonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public List<Person> getAll(HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findAll();
    }

    //counter for session.
    public String incrementCounter(HttpSession httpSession, String attr){
        var session = httpSession.getAttribute(attr) == null ? 0 : (Integer) httpSession.getAttribute(attr);
        httpSession.setAttribute(SESSION_NAME, ++session);
        httpSession.setMaxInactiveInterval(120);
        return "to the site: " + httpSession.getAttribute(attr) ;
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Person person, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        System.out.println(person);
        if (!repository.findById(person.personId()).isEmpty())  throw new ResponseStatusException(HttpStatus.OK);
        Person per = new Person(person.firstName(), person.lastName(), person.age(), person.sex(),
                person.phoneNumber(), person.address(), null);
        repository.save(per);

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@RequestBody Person person, @PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        if(repository.findById(person.personId()).isEmpty()) {
            repository.save(new Person(person.firstName(), person.lastName(), person.age(), person.sex(),
                    person.phoneNumber(), person.address(), null));
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
        repository.save(person);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        repository.deleteById(id);
    }

    @GetMapping("/filter/age/{age}")
    public List<Person> getAllByAge(@PathVariable Integer age, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.getAllByAge(age);
    }

    @GetMapping("/filter/sex/{sex}")
    public Person getBySex(@PathVariable String sex, HttpSession httpSession){
        incrementCounter(httpSession, SESSION_NAME);
        return repository.getBySex(Sex.valueOf(sex.toUpperCase()));
    }

}
