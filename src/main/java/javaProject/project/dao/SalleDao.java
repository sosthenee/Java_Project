package javaProject.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javaProject.project.model.Salle;

public interface SalleDao extends JpaRepository<Salle, Long> {

	Salle findById(long id_salle);
	Salle findByNom(String nom);

	//Get List Idseance from id_salle
	@Query(value = "SELECT * FROM seance_salles s WHERE s.salle_id = ?1",
			nativeQuery = true)  
	List<Long> findallSeanceList(long id_salle);
}
