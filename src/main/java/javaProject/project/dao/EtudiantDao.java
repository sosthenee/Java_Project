package javaProject.project.dao;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Etudiant;

@Repository
public interface EtudiantDao extends JpaRepository<Etudiant, Long> {
	
	//get all seance id from student
	@Query(value = "SELECT * FROM seance_groupes u WHERE u.groupe_id = ?",
			 nativeQuery = true)
	List<Long> findAllSeancesIdEtudiant(long id_groupe);
	
	@SuppressWarnings("unchecked")
	Etudiant save(Etudiant etudiant);
	 
}
