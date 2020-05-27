package javaProject.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javaProject.project.model.Utilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ProjectApplication {

	private static final Logger log = LoggerFactory.getLogger(ProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Bean
	  public CommandLineRunner demo(UtilisateurRepository repository) {
	    return (args) -> {
	   
	      log.info("Customers found with findAll():");
	      log.info("-------------------------------");
	      for (Utilisateur customer : repository.findAll()) {
	        System.out.println(customer.toString());
	      }


	    };
	  }

}
