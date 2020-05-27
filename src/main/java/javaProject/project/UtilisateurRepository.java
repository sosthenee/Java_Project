package javaProject.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javaProject.project.model.Utilisateur;

public interface UtilisateurRepository  extends JpaRepository<Utilisateur, Long>{

}
