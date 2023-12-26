package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.model.Sex;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonCollectionRepository {

    private final List<Person> personList = new ArrayList<>();

    public PersonCollectionRepository(){}

    public List<Person> findAll(){
        return personList;
    }

    public Optional<Person> find(Integer id){
        //if(personList.stream().filter(p -> p.personId().equals(id)).count() == 1) return personList.get(id);
        return personList.stream().filter(p -> p.personId().equals(id)).findFirst();
    }

    public void create(Person person){
        personList.removeIf(p -> p.personId().equals(person.personId()));
        personList.add(person);
    }

    public void delete(Integer personId){
        personList.removeIf(p -> p.personId().equals(personId));
    }

    public boolean isAvailable(Integer personId){
        return personList.stream().anyMatch(p -> p.personId().equals(personId));
    }

    @PostConstruct
    public void init(){
        Person person = new Person(
                "Arvind",
                "Purushotham",
                32,
                Sex.MALE,
                "+1-990-2930-265",
                "Monroe way, Thornton",
                1
        );
        personList.add(person);
    }

}
