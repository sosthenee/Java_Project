package javaProject.project.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.ManyToMany;
import java.util.Set;
import javax.persistence.CascadeType;
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
	
	@Column(name = "heure_fin")
	private int heure_fin = 0;
	
        @Column(name = "minute_fin")
	private int minute_fin = 0;
	
	@Column(name = "etat")
	private int etat = 0;
	
	@OneToOne
	@JoinColumn(name="id_cours")
	private Cours cours;
	
	@OneToOne
	@JoinColumn(name="id_type")
	private Type_cours type_cours;
  
  
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "seance_groupes",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "groupe_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Groupe> groupe = new  ArrayList<Groupe>();
    
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "seance_salles",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "salle_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Salle> salle = new ArrayList<Salle>();
   
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "seance_enseignants",
            joinColumns = {
                    @JoinColumn(name = "seance_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "enseignant_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private List<Enseignant> enseignant = new ArrayList<>();
	
	public Seance() {
		super();
	}

	       
        public Seance( Date date, int etat, int heure_fin, int minute_fin , int semaine , Cours matiere , Type_cours type ) {
		super();		
		this.date = date;
		this.heure_fin = heure_fin;
                this.minute_fin = minute_fin;
                this.semaine=semaine;
		this.etat = etat;
                this.cours = matiere;
                this.type_cours = type;
	}
        
           public Seance( Date date, int etat, int heure_fin, int minute_fin , int semaine , Cours matiere , Type_cours type, ArrayList<Groupe> groupe , ArrayList<Salle> salle , ArrayList<Enseignant> enseignant) {
		super();		
		this.date = date;
		this.heure_fin = heure_fin;
                this.minute_fin = minute_fin;
                this.semaine=semaine;
		this.etat = etat;
                this.cours = matiere;
                this.type_cours = type;
                this.groupe = groupe;
                this.salle = salle;
                this.enseignant = enseignant;
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
	
	
	public List<Enseignant> getEnseignant() {
		System.out.println(enseignant);
		return enseignant;
	}

	public void setEnseignant(List<Enseignant> enseignant) {
		this.enseignant = enseignant;
	}
	
	

	public List<Salle> getSalle() {
		return salle;
	}

	public void setSalle(List<Salle> salle) {
		this.salle = salle;
	}

	@Override
	public String toString() {
		return "Seance [id=" + id + ", semaine=" + semaine + ", date=" + date
				+  ", heure_fin=" + heure_fin + ", etat=" + etat + ", cours=" + cours
				+ ", type_cours=" + type_cours + "]";
	}

    public int getMinute_fin() {
        return minute_fin;
    
    }
    
    public void setMinute_fin(int minute_fin) {
		this.minute_fin = minute_fin;
	}

	
}
