package javaProject.project.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Enseignant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Seance;

@Repository
public interface SeanceDao extends JpaRepository<Seance, Long> {

	Seance findById(long id_seance);


	//Renvoie la liste de seance en fonction de la semaine
	List<Seance> findBySemaine(int semaine);

	//Renvoie l'id de la salle en fonction de l'id de la seance
	@Query(value = "SELECT * FROM seance_salles u WHERE u.seance_id = ?",
			nativeQuery = true)
	Long findSalleIdforSeance(long id_seance);
	
	List<Seance> findByGroupeContaining(Groupe groupe);
	
	List<Seance> findByEnseignantContaining(Optional<Enseignant> enseignant);

}
