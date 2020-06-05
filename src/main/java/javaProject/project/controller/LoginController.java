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
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueModifier;
import javaProject.project.view.VueRecap;

import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
public class LoginController {

	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
	RecapControleur recapControleur;
	
	@Autowired
	CalendrierController calendrierController;
	
	@Autowired
	PlanListeController planListeController;
		
	@Autowired
	VueCalendrier vueCalendrier;
	
	@Autowired
	VueRecap vueRecap;
	
	@Autowired
	VuePlanningListe vuePlanningListe;
  
  @Autowired
  ModifierController modifierController
    
  @Autowired
  VueModifier vuemodifier
  
	
	private VueLogin vueLogin;


	
	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public LoginController() {
	}


	public void addUser(String email, String password) {
		Utilisateur u = new Utilisateur(email, password);
		utilisateurDao.save(u);
	}

	public void login(String email, String password) {

		Utilisateur u = new Utilisateur();
		u = utilisateurDao.findFirstByEmailAndPassword(email , password);
		if( u ==  null){
			JOptionPane.showMessageDialog(null, "veuillez rentrer une bonne combinaison");
		} else {
			vueCalendrier.setVisible(true);
			this.vueLogin.setVisible(false);

			if (u.getDroit() == 4) {
				Singleton.setInfo(u);

			}
			if (u.getDroit() == 3) {
				Singleton.setInfo(u);
			}
			if (u.getDroit() == 2) {
				Singleton.setInfo(u);
			}if (u.getDroit() == 1) {
				Singleton.setInfo(u);
			}
            calendrierController.initController(vueCalendrier,vueLogin,vuemodifier);
            recapControleur.initController(vueRecap,vueCalendrier);
            planListeController.initController(vuePlanningListe);
            modifierController.initController(vuemodifier);
                
		}
		System.out.println(u);
	}

	public void initController(VueLogin view) {
		view.getRootPane().setDefaultButton(view.valider);
		view.valider.addActionListener(e -> login(view.mail.getText(), view.mdp.getText()));

		this.vueLogin = view;
		
	}

	
}
