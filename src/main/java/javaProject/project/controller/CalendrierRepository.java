/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sosthene
 */
public interface CalendrierRepository extends JpaRepository<Seance, Long> {
   
}
