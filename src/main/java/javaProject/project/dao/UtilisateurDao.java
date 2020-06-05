package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Utilisateur;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long>{

	Utilisateur findById(long i);
	
	Utilisateur findByEmail(String email);
	
	Utilisateur findByNom(String nom);
	
	List<Utilisateur> findByDroit(int i);
        	
	@SuppressWarnings("unchecked")
	Utilisateur save(Utilisateur utilisateur);
	
	//For login return the User or return user = null
	Utilisateur findFirstByEmailAndPassword(String email,String password);
	
}
