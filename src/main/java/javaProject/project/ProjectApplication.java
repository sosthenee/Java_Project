package javaProject.project;

import java.awt.EventQueue;

import javaProject.project.controller.CalendrierController;
import javaProject.project.controller.LoginController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueCalendrier;
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
                
                // Login
                LoginController userController = context.getBean(LoginController.class);
                VueLogin loginView = context.getBean(VueLogin.class);
                userController.initController(loginView);
                
                //Calendar Controller
                CalendrierController calendrierController = context.getBean(CalendrierController.class);
                VueCalendrier calendrier = context.getBean(VueCalendrier.class);
                calendrierController.initController(calendrier, loginView);
                userController.setCalendrierView(calendrier);
            
                loginView.setVisible(true);
            });
                         
	}
	


}
