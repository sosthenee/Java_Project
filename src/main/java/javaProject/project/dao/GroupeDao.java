package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Groupe;
import javaProject.project.model.Promotion;

public interface GroupeDao extends JpaRepository<Groupe, Long> {

	Groupe findByNom(String nom);
	
	List<Groupe> findAll();
	
	List<Groupe> findByPromotion(Promotion promotion);
	
}
