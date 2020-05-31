package javaProject.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "etudiant")
public class Etudiant extends Utilisateur {

    @Column(name = "numero")
    private long numero = 0;

    @ManyToOne(targetEntity = Groupe.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_groupe")
    private Groupe groupe;

    public Etudiant() {
        super();
    }


    public Etudiant(long numero) {
        super();
        this.numero = numero;
    }

    public long getId() {
        return id;
    }

    public long getNumero() {
        return numero;
    }
    
    public Groupe getGroupe() {
        return groupe;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String toString() {
        String str = "NUMERO Etudiant : " + this.getNumero() + "\n";
        //str += 			"ID Groupe : " + this.getIdGroupe() + "\n";
        str += "\n.....................................\n";
        return str;
    }

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	@Override
	public String toString() {
		return "Etudiant [id_utilisateur=" + id_utilisateur + ", numero=" + numero + ", utilisateur=" + utilisateur
				+ ", groupe=" + groupe + "]";
	}
	
	


}
