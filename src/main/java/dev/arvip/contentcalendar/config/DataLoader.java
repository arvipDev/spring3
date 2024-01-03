package dev.arvip.contentcalendar.config;
/*This class is run after Dependency Injection adn while the API is getting started.
        This can be used to run code that was previously run in init()@PostCOnstructor method.
        This can be used to write some boot strap code.*/

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Person;
import dev.arvip.contentcalendar.repository.ContentRepository;
import dev.arvip.contentcalendar.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

//@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final ObjectMapper mapper;
    private final ContentRepository contentRepository;

    @Autowired
    public DataLoader(PersonRepository repository, ObjectMapper mapper, ContentRepository contentRepository){
        this.personRepository = repository;
        this.mapper = mapper;
        this.contentRepository = contentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
            contentRepository.saveAll(mapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
        }

        try(InputStream input = TypeReference.class.getResourceAsStream("/data/person.json")){
            personRepository.saveAll(mapper.readValue(input, new TypeReference<List<Person>>(){}));
        }

    }
}



/*

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ContentRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(ContentRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(repository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")) {
                repository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Content>>(){}));
            }
        }
    }
}
*/
