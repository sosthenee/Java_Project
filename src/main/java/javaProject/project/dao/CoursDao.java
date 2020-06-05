package javaProject.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.model.Cours;



public interface CoursDao extends JpaRepository<Cours, Long> {
	
	Cours findById(long id_cours);

}
