package javaProject.project.dao;

import java.util.List;

import java.util.Optional;

import javaProject.project.model.Groupe;
import javaProject.project.model.Salle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javaProject.project.model.Enseignant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Seance;

@Repository
public interface SeanceDao extends JpaRepository<Seance, Long> {

	List<Seance> findAll();
	
	Seance findById(long id);
	
	//GET BY SEMAINE
	List<Seance> findBySemaine(int semaine);


  List<Seance> findBySemaineAndGroupeContaining(int semaine , Groupe groupe );
	

	List<Seance> findByGroupeContaining(Groupe groupe);
	
	//GET BY ENSEIGNANT
	List<Seance> findByEnseignantContaining(Enseignant enseignant);
	
	//GET BY SALLE
	List<Seance> findBySalleContaining(Salle salle);
	
	@SuppressWarnings("unchecked")
	Seance save(Seance seance);	
}
