package javaProject.project.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Cours implements EnumerableElement{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
            
	@Column(name = "nom")
	private String nom ="";
	
	public Cours() {}
	
	public Cours(long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

        @Override
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Cours [id=" + id + ", nom=" + nom + "]";
	}
	
	


}
