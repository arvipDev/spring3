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
/*
* CTRL SHIFT / for block comment
* CTRL / for line comment
* */

@EnableConfigurationProperties({ContentCalendarConfiguration.class})
@SpringBootApplication
public class Application {
	//https://start.spring.io/
	//https://www.youtube.com/watch?v=UgX5lgv4uVM

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		//ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
/*		ApplicationContext c = SpringApplication.run(Application.class, args);
		System.out.println("______________");
		System.out.println(c.getApplicationName());*/
/*		RestTemplate restTemp = (RestTemplate) context.getBean("restTemplate");
		System.out.println(restTemp);
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);*/
		//----------------------
		SpringApplication.run(Application.class, args);;
	}

	/*@Bean
	CommandLineRunner commandLineRunner(ContentRepository contentRepository, PersonRepository personRepository){
		return args -> {
			Person person = new Person(
					"Arvind",
					"Purushotham",
					32,
					Sex.MALE,
					"+1-990-2930-265",
					"13245 Monroe Way",
					null
			);
			personRepository.save(person);
			Content content = new Content(
					LocalDateTime.now(),
					null,
					null,
					"First content",
					"Nothing much just a content",
					Status.IDEA,
					Type.ARTICLE,
					""
			);
			contentRepository.save(content);
		};
	}*/
}
