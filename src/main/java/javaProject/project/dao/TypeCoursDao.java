/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.dao;

import javaProject.project.model.Type_cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sosthene
 */

@Repository

public interface TypeCoursDao extends JpaRepository<Type_cours, Long>{
    
    Type_cours findById(long id);

}
