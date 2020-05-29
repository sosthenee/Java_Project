package javaProject.project.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.model.Etudiant;

public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
	 
}
