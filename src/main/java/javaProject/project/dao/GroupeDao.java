package javaProject.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.model.Groupe;

public interface GroupeDao extends JpaRepository<Groupe, Long> {
	
	Groupe findByNom(String nom);

}
