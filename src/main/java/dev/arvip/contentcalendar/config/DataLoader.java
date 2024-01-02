package dev.arvip.contentcalendar.config;

/*This class is run after Dependency Injection adn while the API is getting started.
        This can be used to run code that was previously run in init()@PostCOnstructor method.
        This can be used to write some boot strap code.*/

import dev.arvip.contentcalendar.controller.ContentController;
import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.model.Type;
import dev.arvip.contentcalendar.repository.ContentCollectionRepository;
import dev.arvip.contentcalendar.repository.ContentJdbcTemplateRepository;
import dev.arvip.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.function.Consumer;
@Profile("!dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Arvind");
    }
}
