package javaProject.project;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import javaProject.project.model.Utilisateur;

public interface UtilisateurRepository  extends CrudRepository<Utilisateur, Long>{

}
