package javaProject.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Enseignant {
	
	@Id
	private long id_utilisateur = 0;
	
	@OneToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "id_utilisateur")
    @MapsId
    private Utilisateur utilisateur;
	
	
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "enseignant_cours",
    joinColumns = {
            @JoinColumn(name = "id_enseignant", referencedColumnName = "id_utilisateur",
                    nullable = false, updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "id_cours", referencedColumnName = "id",
                    nullable = false, updatable = false)})
    private Set<Cours> cours = new HashSet<>();


	public Enseignant() {
		super();
	}


	public Enseignant(long id_utilisateur) {
		super();
		this.id_utilisateur = id_utilisateur;
	}


	public long getId_utilisateur() {
		return id_utilisateur;
	}


	public void setId_utilisateur(long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}


	public Set<Cours> getCours() {
		return cours;
	}


	public void setCours(Set<Cours> cours) {
		this.cours = cours;
	}
	


}
