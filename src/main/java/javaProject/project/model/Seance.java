package javaProject.project.model;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = 0;

    @Column(name = "semaine")
    private int semaine = 0;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany
    @JoinTable(name = "seance_groupes",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "groupe_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Groupe> groupe = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "seance_salles",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "salle_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Salle> salle = new HashSet<>();
    
    @ManyToMany
    @JoinTable(name = "seance_enseignants",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "enseignant_id", referencedColumnName = "id_utilisateur",
                            nullable = false, updatable = false)})
    private Set<Enseignant> enseignant = new HashSet<>();
    
   
    public Seance(Date date, int semaine  ) {
        this.date = date;
        this.semaine = semaine;
        
    }

}
