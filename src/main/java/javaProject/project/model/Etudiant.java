package javaProject.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="etudiant") 
public class Etudiant {
	
	@Id
	private long id_utilisateur = 0;

	
	@Column(name = "numero")
	private long numero = 0;
	
	@OneToOne
    @JoinColumn(name = "id_utilisateur")
    @MapsId
    private Utilisateur utilisateur;
	
	@ManyToOne(targetEntity = Groupe.class)
	@JoinColumn(name="id_groupe")
	private Groupe groupe;
	
	public Etudiant () {}
	
	public Etudiant(long id_utilisateur, long numero) {
		this.id_utilisateur = id_utilisateur;
		this.numero = numero;
	}
	
	public long getId() {
		return id_utilisateur;
	}

	public void setId(long id_utilisateur) {
		this.id_utilisateur = id_utilisateur;
	}
	
	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}
	
	

	public String toString(){
		String str = 	"NUMERO Etudiant : " + this.getNumero() + "\n";
		//str += 			"ID Groupe : " + this.getIdGroupe() + "\n";
		str +=			"\n.....................................\n";
		return str;
	}

}
