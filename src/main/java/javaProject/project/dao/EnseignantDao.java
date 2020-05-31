package javaProject.project.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Enseignant;

public interface EnseignantDao extends JpaRepository<Enseignant, Long> {

	//get all seance id from enseignant
	@Query(value = "SELECT * FROM seance_enseignants u WHERE u.enseignant_id = ?",
			nativeQuery = true)
	List<Long> findAllSeancesIdEnseignant(long id_enseignant);
	
        
	@SuppressWarnings("unchecked")
	Enseignant save(Enseignant enseignant);
	
}
