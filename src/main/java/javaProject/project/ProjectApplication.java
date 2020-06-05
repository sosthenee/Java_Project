package javaProject.project;

import java.awt.EventQueue;

import javaProject.project.controller.CalendrierController;
import javaProject.project.controller.LoginController;
import javaProject.project.controller.ModifierController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueModifier;
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
                VueModifier modifier = context.getBean(VueModifier.class);
                calendrierController.initController(calendrier, loginView , modifier);
                userController.setCalendrierView(calendrier);
                
                //Modifier Controller
                ModifierController ModifierController = context.getBean(ModifierController.class);
                ModifierController.initController(modifier);
                
                loginView.setVisible(true);
            });
                         
	}
	


}
