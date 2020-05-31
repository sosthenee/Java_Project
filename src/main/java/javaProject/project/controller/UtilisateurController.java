package javaProject.project.controller;

import java.awt.event.ActionEvent;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.Fenetre;
import javaProject.project.view.LookCalendrier;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
public class UtilisateurController {

    @Autowired
    private UtilisateurDao utilisateurDao;
    private Fenetre view;
    
    private LookCalendrier seanceEdtudiant;

    public UtilisateurController() {
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
            this.seanceEdtudiant.setVisible(true);
            this.view.setVisible(false);
         }
         System.out.println(u);
     }

    public void initController(Fenetre view) {
       view.valider.addActionListener(e -> login(view.mail.getText(), view.mdp.getText()));
       this.view = view;
    }
    
    public void setSeanceEtudiant(LookCalendrier seanceEdtudiant) {
        this.seanceEdtudiant = seanceEdtudiant;
    }

}
