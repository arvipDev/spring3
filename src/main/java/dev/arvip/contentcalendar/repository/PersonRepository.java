package dev.arvip.contentcalendar.repository;

import dev.arvip.contentcalendar.model.Person;
import org.springframework.data.repository.ListCrudRepository;

public interface PersonRepository extends ListCrudRepository<Person, Integer> {

}
