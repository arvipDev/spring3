package dev.arvip.contentcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
}
