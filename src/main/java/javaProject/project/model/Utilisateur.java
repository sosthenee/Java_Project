package javaProject.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="utilisateur") 
public class Utilisateur {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id = 0;
	
	@Column(name = "email")
	private String email = "";
	
	@Column(name = "password")
	private String password = "";
	
	@Column(name = "nom")
	private String nom = "";
	
	@Column(name = "prenom")
	private String prenom = "";
	
	@Column(name = "droit")
	private int droit;


	public Utilisateur(){}

	public Utilisateur(long id, String email, String password,String nom, String prenom, int droit) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.droit = droit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getDroit() {
		return droit;
	}

	public void setDroit(int droit) {
		this.droit = droit;
	}

	public String toString(){
		String str = 	"Email : " + this.getEmail() + "\n";
		str += 			"NOM : " + this.getNom() + "\n";
		str += 			"PRENOM : " + this.getPrenom() + "\n";
		str +=			"Droit : " + this.getDroit();
		str +=			"\n.....................................\n";
		return str;
	}

}
