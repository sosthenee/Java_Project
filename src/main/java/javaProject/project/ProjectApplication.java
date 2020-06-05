package javaProject.project;

import java.awt.EventQueue;

import javaProject.project.controller.CalendrierController;
import javaProject.project.controller.LoginController;
import javaProject.project.controller.PlanListeController;
import javaProject.project.controller.RecapControleur;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;
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
                CalendrierController calendrierController = context.getBean(CalendrierController.class);
                RecapControleur recapControleur = context.getBean(RecapControleur.class);
                PlanListeController planListeController = context.getBean(PlanListeController.class);
                
                //Views
                VueLogin vueLogin = context.getBean(VueLogin.class);
                VueRecap vueRecap = context.getBean(VueRecap.class);
                VueCalendrier vueCalendrier = context.getBean(VueCalendrier.class);
                VuePlanningListe vuePlanningListe = context.getBean(VuePlanningListe.class);
                
                //initController
                loginController.initController(vueLogin,calendrierController,vueCalendrier,recapControleur,vueRecap,planListeController,vuePlanningListe);
                vueLogin.setVisible(true);
            });
                         
	}
	


}
