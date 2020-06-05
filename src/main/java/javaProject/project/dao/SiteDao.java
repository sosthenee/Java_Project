/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.dao;

import javaProject.project.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sosthene
 */
public interface SiteDao extends JpaRepository<Site, Long> {
	
	Site findById(long id_site);


    
}
