package javaProject.project;

import java.awt.EventQueue;
import javaProject.project.controller.EtudiantController;
import javaProject.project.controller.UtilisateurController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Utilisateur;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import javaProject.project.view.Fenetre;
import javaProject.project.view.LookCalendrier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
@EnableAutoConfiguration
public class ProjectApplication {

	
	public static void main(String[] args) {
		//SpringApplication.run(ProjectApplication.class, args);
                
        
        
            //ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Fenetre.class)
           // .headless(false).run(args);

            EventQueue.invokeLater(() -> {
                ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectApplication.class).headless(false).run(args);
                
                // Login
                UtilisateurController userController = context.getBean(UtilisateurController.class);
                Fenetre loginView = context.getBean(Fenetre.class);
                userController.initController(loginView);
                
                // Seance edtudiant
                EtudiantController studController = context.getBean(EtudiantController.class);
                LookCalendrier ex2 = context.getBean(LookCalendrier.class);
                studController.initController(ex2, loginView);
                
                
                userController.setSeanceEtudiant(ex2);
                //userController.addUser("plop", "plop");
                loginView.setVisible(true);
            });
                //Fenetre fen = new Fenetre();
                
               
	}
	

}
