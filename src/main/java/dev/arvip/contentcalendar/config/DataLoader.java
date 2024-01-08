package dev.arvip.contentcalendar.config;
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

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;
    private final ContentRepository contentRepository;

    @Autowired
    public DataLoader(PersonRepository repository, ObjectMapper objectMapper, ContentRepository contentRepository){
        this.personRepository = repository;
        this.objectMapper = objectMapper;
        this.contentRepository = contentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if(contentRepository.count() == 0){
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/content.json")){
                contentRepository.saveAll(objectMapper.readValue(inputStream, new TypeReference<List<Content>>(){}));
            }
        }
        if (personRepository.count() == 0){
            try(InputStream input = TypeReference.class.getResourceAsStream("/data/person.json")){
                personRepository.saveAll(objectMapper.readValue(input, new TypeReference<List<Person>>(){}));
            }
        }
    }
}
