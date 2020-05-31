package javaProject.project.model;

import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class Seance {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id = 0;
	
	@Column(name = "semaine")
	private int semaine = 0;

	@Column(name = "date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "heure_debut")
	private int heure_debut = 0;
	
	@Column(name = "heure_fin")
	private int heure_fin = 0;
	
	@Column(name = "etat")
	private int etat = 0;
	
	@OneToOne
	@JoinColumn(name="id_cours")
	private Cours cours;
	
	@OneToOne
	@JoinColumn(name="id_type")
	private Type_cours type_cours;
  
  
    @ManyToMany(cascade=CascadeType.ALL)
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
                    @JoinColumn(name = "enseignant_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Enseignant> enseignant = new HashSet<>();
	
	public Seance() {
		super();
	}

	public Seance(long id, int semaine, Date date, int heure_debut, int heure_fin, int etat) {
		super();
		this.id = id;
		this.semaine = semaine;
		this.date = date;
		this.heure_debut = heure_debut;
		this.heure_fin = heure_fin;
		this.etat = etat;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHeure_debut() {
		return heure_debut;
	}

	public void setHeure_debut(int heure_debut) {
		this.heure_debut = heure_debut;
	}

	public int getHeure_fin() {
		return heure_fin;
	}

	public void setHeure_fin(int heure_fin) {
		this.heure_fin = heure_fin;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public Cours getCours() {
		return cours;
	}

	public void setCours(Cours cours) {
		this.cours = cours;
	}

	public Type_cours getType_cours() {
		return type_cours;
	}

	public void setType_cours(Type_cours type_cours) {
		this.type_cours = type_cours;
	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", semaine=" + semaine + ", date=" + date
				+ ", heure_debut=" + heure_debut + ", heure_fin=" + heure_fin + ", etat=" + etat + ", cours=" + cours
				+ ", type_cours=" + type_cours + "]";
	}

	
}
