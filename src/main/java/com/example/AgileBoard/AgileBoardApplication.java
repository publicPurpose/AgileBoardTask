package com.example.AgileBoard;

import com.example.AgileBoard.model.UserDto;
import com.example.AgileBoard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AgileBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileBoardApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {

			//save a couple of users
			userRepository.save(new UserDto((long) 1, "ala", new BCryptPasswordEncoder().encode("ala")));
			userRepository.save(new UserDto((long) 2, "mary", new BCryptPasswordEncoder().encode("mary")));

		};
	}
}
