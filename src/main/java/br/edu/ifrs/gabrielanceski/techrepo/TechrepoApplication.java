package br.edu.ifrs.gabrielanceski.techrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TechrepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechrepoApplication.class, args);
	}

}
