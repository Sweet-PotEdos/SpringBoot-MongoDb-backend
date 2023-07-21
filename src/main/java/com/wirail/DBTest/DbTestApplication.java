package com.wirail.DBTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DbTestApplication {

	//Structure : Controller -> Service -> Repository

	public static void main(String[] args) {
		SpringApplication.run(DbTestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserController userController){
		return args -> {
			System.out.println("start of the program ");

			// .getBody method is necessary because the user controller returns a ResponseEntity<User>
			User usr1 = userController.createUser(new User("jhonnybravo", "coolpssw")).getBody();

			System.out.println(userController.getAllUsers().toString());

		};
	}

}
