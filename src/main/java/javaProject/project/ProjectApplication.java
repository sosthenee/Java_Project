package javaProject.project;

import java.awt.EventQueue;

import javaProject.project.controller.CalendrierController;
import javaProject.project.controller.LoginController;

import javaProject.project.controller.ModifierController;

import javaProject.project.controller.PlanListeController;
import javaProject.project.controller.RecapControleur;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;

import javaProject.project.view.VueModifier;

import javaProject.project.view.VueRecap;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableAutoConfiguration
public class ProjectApplication {

	
	public static void main(String[] args) {


            EventQueue.invokeLater(() -> {
                ConfigurableApplicationContext context = new SpringApplicationBuilder(ProjectApplication.class).headless(false).run(args);
                
                //Controllers
                LoginController loginController = context.getBean(LoginController.class);
                  
                //Views

                VueLogin vueLogin = new VueLogin();
                VueRecap vueRecap = new VueRecap();
                VuePlanningListe vuePlanningListe = new VuePlanningListe();
                VueCalendrier vueCalendrier = new VueCalendrier(vueRecap, vuePlanningListe);

                VueModifier vuemodifier = new VueModifier();
                
                //initController
                loginController.initController(vueLogin);

                vueLogin.setVisible(true);

            });
                         
	}
	


}
