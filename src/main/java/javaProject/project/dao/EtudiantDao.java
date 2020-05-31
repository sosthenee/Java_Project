package javaProject.project.dao;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Cours;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Utilisateur;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
	
	Etudiant findById(long i);
	
	//Ajout Etudiant
	@SuppressWarnings("unchecked")
	Etudiant save(Etudiant etudiant);
	
}
