package com.example.demo;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {
	private static final Logger log =  LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository) {
		return (args) -> {
			// save a few users
//			repository.save(new User("zjam", "zhan", new Date(), new Contact("mal", "mal", 18, "87759697758")));
//			repository.save(new User("zhan", "serik", new Date(), new Contact("mal", "mal", 18, "87759697758")));


			// fetch all Users
//			log.info("User found with findAll():");
//			log.info("-------------------------------");
//			User user  = repository.findByUsername("zhan").get();
//			log.info(user.toString());

			log.info("");

			// fetch an individual User by ID
//			User user = repository.findById(1L);
//			log.info("user found with findById(1):");
//			log.info("--------------------------------");
//			log.info(user.toString());
//			log.info("");
//
//			// fetch user by last name
//			log.info("User found with findByLastName('asd'):");
//			log.info("--------------------------------------------");
//			repository.findByLastName("asd").forEach(asd -> {
//				log.info(asd.toString());
//			});
			// for (User asd : repository.findByLastName("asd")) {
			//  log.info(asd.toString());
			// }
			log.info("");
		};
	}

}
