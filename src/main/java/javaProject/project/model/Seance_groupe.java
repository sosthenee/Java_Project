package javaProject.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Seance_groupe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_seance = 0;
	
	@OneToOne
	@JoinColumn(name = "id")
	@MapsId
	private Seance seance;

	public Seance_groupe() {
		super();
	}

	public Seance_groupe(long id_seance, String nom) {
		super();
		this.id_seance = id_seance;
	}

	public long getId_seance() {
		return id_seance;
	}

	public void setId_seance(long id_seance) {
		this.id_seance = id_seance;
	}


}
