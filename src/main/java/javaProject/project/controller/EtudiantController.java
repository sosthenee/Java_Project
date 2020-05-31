/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import java.util.List;
import java.util.Optional;
import javaProject.project.dao.EtudiantDao;
import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.Calendrier;
import javaProject.project.view.Fenetre;
import javaProject.project.view.LookCalendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */
@Component
public class EtudiantController {
    
    @Autowired
    private UtilisateurDao utilisateurDao;
    
    @Autowired
    private EtudiantDao etudiantDao;
    
    
    private LookCalendrier view;
    
    @Autowired
    private SeanceDao seanceDao;

    public EtudiantController() {
        
    }
    
     public void allSeances(String email) {
        Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
        
        List<Seance> a = seanceDao.findByGroupeContaining(i.getGroupe());
         
        System.out.println(a);

        
    }
    
     
        
    public void initController(LookCalendrier view , Fenetre view2) {
       view.button1.addActionListener(e -> allSeances(view2.mail.getText()));
    
    }
}
