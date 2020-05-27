package javaProject.project.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Salle {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id = 0;
	
	@Column(name = "nom")
	private String nom ="";
	
	@Column(name = "capacite")
	private int capacite = 0;
	
	@Column(name = "id_site")
	private long id_site = 0;

        
      
	public Salle() {
		super();
	}

	public Salle(long id, String nom, int capacite, long id_site  ) {
		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.id_site = id_site;
               

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

	public long getId_site() {
		return id_site;
	}

	public void setId_site(long id_site) {
		this.id_site = id_site;
	}
	
	public String toString(){
		String str = 	"NOM Salle : " + this.getNom() + "\n";
		str += 			"CAPACITE : " + this.getCapacite() + "\n";
		str += 			"ID site : " + this.getId_site() + "\n";
		str +=			"\n.....................................\n";
		return str;
	}

}
