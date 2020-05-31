package javaProject.project.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Salle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id = 0;
	
	@Column(name = "nom")
	private String nom ="";
	
	@Column(name = "capacite")
	private int capacite = 0;
	
	@ManyToOne
	@JoinColumn(name="id_site")
	private Site site;

        
      
	public Salle() {
		super();
	}


	public Salle(long id, String nom, int capacite) {

		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}


	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", site=" + site + "]";
	}


}
