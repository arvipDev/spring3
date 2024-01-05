package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.model.Sex;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends ListCrudRepository<Person, Integer> {

    List<Person> getAllByAge(Integer age);

    Person getBySex(Sex sex);

}
