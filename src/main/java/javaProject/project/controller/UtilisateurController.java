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
import javaProject.project.view.Fenetre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;



@Component
public class UtilisateurController {

    @Autowired(required=false)
    private UtilisateurDao utilisateurDao;
    
    @Autowired(required=false)
    private EtudiantDao etudiantDao;
    
    @Autowired(required=false)
    private SeanceDao seanceDao;
    private Fenetre view;


    public UtilisateurController() {
    }
    
//    public void addUser(String email, String password) {
//        Utilisateur u = new Utilisateur(email, password);
//        utilisateurDao.save(u);
//    }
    
    public void getAllEnsigna() {
    	Utilisateur newE;
    	newE = utilisateurDao.findById(16);
    	Etudiant nEtudiant;
    	nEtudiant = etudiantDao.findById(newE.getId());
    	List<Seance> edtList ;
    	edtList = seanceDao.findByGroupeContaining(nEtudiant.getGroupe());
        System.out.println(edtList);
    }
    
    public void initController(Fenetre view) {
        view.valider.addActionListener(e -> getAllEnsigna());
    }

}
