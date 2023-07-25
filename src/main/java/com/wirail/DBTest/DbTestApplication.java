package com.wirail.DBTest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
			//User usr1 = userController.createUser(new User("firstname", "lastname", "email", "password")).getBody();

			//System.out.println(new BCryptPasswordEncoder().encode("randomstring"));
			System.out.println(userController.getUserByEmail("jhonnybravo@gmail.com").toString());

		};
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
