package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Groupe;
import javaProject.project.model.Promotion;

public interface PromotionDao extends JpaRepository<Promotion, Long> {

	Promotion findByNom(String nom);
	
	Promotion findById(long i);
	
	List<Promotion> findAll();

}
