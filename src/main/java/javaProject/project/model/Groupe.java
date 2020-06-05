package javaProject.project.model;

import java.util.HashSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import java.util.Set;


@Entity
public class Groupe implements EnumerableElement{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id = 0;
	
	@Column(name = "nom")
	private String nom ="";
	
	@ManyToOne
	@JoinColumn(name="id_promotion")
	private Promotion promotion;
	
    
	public Groupe () {}
	
	public Groupe(long id, String nom) {
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
	
	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	@Override
	public String toString() {
		return "Groupe [id=" + id + ", nom=" + nom + ", promotion=" + promotion + "]";
	}


}
