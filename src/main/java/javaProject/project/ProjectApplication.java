package javaProject.project;

import java.awt.EventQueue;

import javaProject.project.controller.CalendrierController;
import javaProject.project.controller.LoginController;

import javaProject.project.controller.ModifierController;

import javaProject.project.controller.PlanListeController;
import javaProject.project.controller.RecapController;
import javaProject.project.controller.ReportingController;

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
                CalendrierController calendrierController = context.getBean(CalendrierController.class);
                ModifierController modifierController = context.getBean(ModifierController.class);
                PlanListeController planListeController = context.getBean(PlanListeController.class);
                RecapController  recapController= context.getBean(RecapController.class);
                ReportingController reportingController = context.getBean(ReportingController.class);
                loginController.setCalendrierController(calendrierController);
                loginController.setPlanListeController(planListeController);
                loginController.setRecapController(recapController);
                
                //Views
                VueLogin vueLogin = new VueLogin();
                VueRecap vueRecap = new VueRecap();
                VuePlanningListe vuePlanningListe = new VuePlanningListe();
                VueModifier vuemodifier = new VueModifier();
                VueCalendrier vueCalendrier = new VueCalendrier(vueRecap, vuePlanningListe);
                
                loginController.setVueCalendrier(vueCalendrier);
                loginController.setVueLogin(vueLogin);
                loginController.setVuePlanningListe(vuePlanningListe);
                loginController.setVueRecap(vueRecap);
                loginController.setVuemodifier(vuemodifier);
                
               
                //initController
                loginController.initController(vueLogin);
                modifierController.initController(vuemodifier);

                vueLogin.setVisible(true);

            });
                         
	}
	


}
