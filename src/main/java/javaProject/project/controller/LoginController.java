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
import javaProject.project.view.VueReporting;

import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoginController {


    @Autowired
    private UtilisateurDao utilisateurDao;

    private VueCalendrier vueCalendrier;

    private VueLogin vueLogin;

    private VuePlanningListe vuePlanningListe;

    private VueModifier vuemodifier;

    private VueRecap vueRecap;
    
    private VueReporting vueReporting;
    
    private CalendrierController calendrierController;

    private RecapController recapController;

    private PlanListeController planListeController;
        


	public void setCalendrierController(CalendrierController calendrierController) {
        this.calendrierController = calendrierController;
    }

    public void setRecapController(RecapController recapController) {
        this.recapController = recapController;
    }


    public void setPlanListeController(PlanListeController planListeController) {
        this.planListeController = planListeController;
    }


    public void setVueCalendrier(VueCalendrier vueCalendrier) {
        this.vueCalendrier = vueCalendrier;
    }

    public void setVueLogin(VueLogin vueLogin) {
        this.vueLogin = vueLogin;
    }

    public void setVuePlanningListe(VuePlanningListe vuePlanningListe) {
        this.vuePlanningListe = vuePlanningListe;
    }

    public void setVuemodifier(VueModifier vuemodifier) {
        this.vuemodifier = vuemodifier;
    }

    public void setVueRecap(VueRecap vueRecap) {
        this.vueRecap = vueRecap;
    }
    
    public void setVueReporting(VueReporting vueReporting) {
    	this.vueReporting = vueReporting;
    }

   
    private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

    public LoginController() {
   
    }

    public void addUser(String email, String password) {
        Utilisateur u = new Utilisateur(email, password);
        utilisateurDao.save(u);
    }


    public void login(String email, String password) {

        Utilisateur u = new Utilisateur();
        u = utilisateurDao.findFirstByEmailAndPassword(email, password);
        if (u == null) {
            JOptionPane.showMessageDialog(null, "veuillez rentrer une bonne combinaison");
        } else {
            vueCalendrier.setVisible(true);
            this.vueLogin.setVisible(false);
            Singleton.setInfo(u);

            calendrierController.initController(vueCalendrier, vueLogin, vuemodifier ,vuePlanningListe , vueRecap ,  recapController , planListeController, vueReporting);
            recapController.initController(vueRecap, vueCalendrier,vueReporting);
            planListeController.initController(vuePlanningListe);

        }
        System.out.println(u);
    }

    public void initController(VueLogin view) {
        view.getRootPane().setDefaultButton(view.valider);
        view.valider.addActionListener(e -> login(view.mail.getText(), view.mdp.getText()));

        this.vueLogin = view;

    }

}
