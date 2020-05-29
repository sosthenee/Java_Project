package javaProject.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Utilisateur;
import javaProject.project.service.ApiResponse;
import javaProject.project.service.EtudiantService;
import javaProject.project.service.UtilisateurService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ProjectApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	

}
