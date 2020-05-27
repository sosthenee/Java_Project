package javaProject.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Enseignant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_utilisateur = 0;

    @ManyToOne
    @JoinColumn(name = "id_cours")
    private Cours cours;

    public Enseignant() {
    }

    public Enseignant(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

}
