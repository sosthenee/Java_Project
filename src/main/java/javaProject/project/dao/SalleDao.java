package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Salle;
import javaProject.project.model.Site;

public interface SalleDao extends JpaRepository<Salle, Long> {

	Salle findById(long id_salle);
	
	Salle findByNom(String nom);
 
	//GET SALLE BY SITE
	List<Salle> findAllBySite(Site site);
	
	List<Salle> findAll();
}
