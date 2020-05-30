package javaProject.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.model.Promotion;

public interface PromotionDao extends JpaRepository<Promotion, Long> {
	
	Promotion findByNom(String nom);

}
