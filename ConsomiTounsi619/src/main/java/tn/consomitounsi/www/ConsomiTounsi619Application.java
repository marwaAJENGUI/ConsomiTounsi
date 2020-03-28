package tn.consomitounsi.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
public class ConsomiTounsi619Application {

	public static void main(String[] args) {
		SpringApplication.run(ConsomiTounsi619Application.class, args);
	}
	
}
