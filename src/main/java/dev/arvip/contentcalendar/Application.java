package dev.arvip.contentcalendar;

import dev.arvip.contentcalendar.config.ContentCalendarConfiguration;
import dev.arvip.contentcalendar.model.*;
import dev.arvip.contentcalendar.repository.ContentRepository;
import dev.arvip.contentcalendar.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@EnableConfigurationProperties({ContentCalendarConfiguration.class})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);;
	}
}
