package javaProject.project.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Enseignant;

public interface EnseignantDao extends JpaRepository<Enseignant, Long> {
	
	List<Enseignant> findAll();

	@SuppressWarnings("unchecked")
	Enseignant save(Enseignant enseignant);

}
