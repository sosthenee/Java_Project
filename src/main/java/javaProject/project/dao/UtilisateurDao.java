package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Utilisateur;

@Repository
public interface UtilisateurDao  extends JpaRepository<Utilisateur, Long>{

	Utilisateur findByEmail(String email);
	
	Utilisateur findByNom(String nom);
	
	Utilisateur save(Utilisateur utilisateur);
}
