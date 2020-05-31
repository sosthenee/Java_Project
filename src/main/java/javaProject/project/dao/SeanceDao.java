package javaProject.project.dao;

import java.util.List;
import javaProject.project.model.Groupe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Seance;

@Repository
public interface SeanceDao extends JpaRepository<Seance, Long> {

	Seance findById(long id_seance);

	//Get List seance from list of seance IDs
	@Query("SELECT s FROM Seance s WHERE s.id IN (:ids_seances)")  
	List<Seance> findallSeanceList(@Param("ids_seances")List<Long> ids_seances);
	//Get List seance by semaine
	List<Seance> findBySemaine(int semaine);

	//get salle for seance
	@Query(value = "SELECT * FROM seance_salles u WHERE u.seance_id = ?",
			nativeQuery = true)
	Long findSalleIdforSeance(long id_seance);
        
        List<Seance> findByGroupeContaining(Groupe g);

}
