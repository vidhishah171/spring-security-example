package com.example.springSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springSecurity.repo.AuthorityRepository;
import com.example.springSecurity.repo.UserRepository;

@EnableJpaRepositories(basePackageClasses = {UserRepository.class, AuthorityRepository.class})
@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
