package dev.arvip.contentcalendar;

import dev.arvip.contentcalendar.model.Content;
import dev.arvip.contentcalendar.model.Status;
import dev.arvip.contentcalendar.model.Type;
import dev.arvip.contentcalendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
/*
* CTRL SHIFT / for block comment
* CTRL / for line comment
* */

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

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository){
		return args -> {
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
			repository.save(content);
		};
	}
}
