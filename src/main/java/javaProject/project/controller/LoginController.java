package javaProject.project.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javaProject.project.dao.EtudiantDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueRecap;

import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
public class LoginController {

	@Autowired
	private UtilisateurDao utilisateurDao;

	private VueLogin view;

	
	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public LoginController() {
	}


	public void addUser(String email, String password) {
		Utilisateur u = new Utilisateur(email, password);
		utilisateurDao.save(u);
	}

	public void login(String email, String password, CalendrierController calendrierController,VueCalendrier calendar,RecapControleur recapControleur,VueRecap vueRecap) {
		Utilisateur u = new Utilisateur();
		u = utilisateurDao.findFirstByEmailAndPassword(email , password);
		if( u ==  null){
			JOptionPane.showMessageDialog(null, "veuillez rentrer une bonne combinaison");
		} else {
			calendar.setVisible(true);
			this.view.setVisible(false);

			if (u.getDroit() == 4) {
				Singleton.setInfo(u);
				calendrierController.allSeances(email, calendar, 1);
				recapControleur.allSeances(email, vueRecap);
			}
			if (u.getDroit() == 3) {
				Singleton.setInfo(u);
				calendrierController.allSeances(email, calendar, 1);
				recapControleur.allSeances(email, vueRecap);
			}
			if (u.getDroit() == 2) {
				Singleton.setInfo(u);
				calendrierController.allSeances(email, calendar, 1);
				recapControleur.allSeances(email, vueRecap);
			}if (u.getDroit() == 1) {
				Singleton.setInfo(u);
				calendrierController.allSeances(email, calendar, 1);
				recapControleur.allSeances(email, vueRecap);
			}
		}
		System.out.println(u);
	}

	public void initController(VueLogin view, CalendrierController calendrierController,VueCalendrier calendar,RecapControleur recapControleur,VueRecap vueRecap) {
		view.getRootPane().setDefaultButton(view.valider);
		view.valider.addActionListener(e -> login(view.mail.getText(), view.mdp.getText(),calendrierController,calendar,recapControleur,vueRecap));
		this.view = view;
		
	}

	
}
