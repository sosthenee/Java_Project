package javaProject.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long>{

	Utilisateur findByEmail(String email);
	
	Utilisateur findByNom(String nom);
        
        Utilisateur findById(long id);
	
	@SuppressWarnings("unchecked")
	Utilisateur save(Utilisateur utilisateur);
	
	//For login return the User or return user = null
	Utilisateur findFirstByEmailAndPassword(String email,String password);
	
}
