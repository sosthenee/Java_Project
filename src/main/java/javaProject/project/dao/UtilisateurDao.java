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
        
        Utilisateur findById(long id);
	
	@SuppressWarnings("unchecked")
	Utilisateur save(Utilisateur utilisateur);
	
	//For login return the User or return user = null
	Utilisateur findFirstByEmailAndPassword(String email,String password);
	
	//Renvoie la liste des Utilisateurs en fonction de leur droit 1 : admin / 2 : ref Pedago / 3 : enseignant / 4: etudiant
	List<Utilisateur> findByDroit(int numDroit);
	
}
