package javaProject.project.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javaProject.project.dao.GroupeDao;
import javaProject.project.dao.SalleDao;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Salle;

import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueRecap;

@Component
public class RecapController {

	@Autowired
	UtilisateurDao utilisateurDaoR;
	@Autowired
	SeanceDao seanceDao;
	
	@Autowired
	GroupeDao groupeDao;
	
	@Autowired
	SalleDao salleDao;


	private List<Seance> listSeances;

	public List<Seance> getListSeances() {
		return listSeances;
	}

	public void setListSeances(List<Seance> oui) {
		this.listSeances = oui;
	}

	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public Object[][] formatData(List<Seance> seances) {

		//Data table edition find nb col four nb cours !=

		if(this.listSeances == null)
		{
			 Object[][] data = new Object[1][5];
			 return data;
		}else {	
			List<Long> idsList = new ArrayList<>();
			for (Seance seance : seances) {
				if(Collections.frequency(idsList, seance.getCours().getId()) == 0) {
					idsList.add(seance.getCours().getId());
				}
			}

			//Declare Data table
			Object[][] data = new Object[idsList.size()][5];
			for(int i = 0;i<idsList.size();i++) {
				for(int j = 0; j<5; j++) {
					data[i][j] = "";
				}
			}

			//Insert Data
			int colData=0;
			List<String> crStrings = new ArrayList<>();

			for (Seance seance : seances) {

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(seance.getDate());
				int heure_fin = seance.getHeure_fin();
				int minute_fin = seance.getMinute_fin();
				int hours = calendar.get(Calendar.HOUR_OF_DAY);
				int minutes = calendar.get(Calendar.MINUTE);

				int start = minutes + hours*60;
				int fin = minute_fin + heure_fin*60;
				int dur = fin - start;
				int h = dur / 60;
				int mi = dur % 60;


				//Parcours si cours already here and update data
				if(Collections.frequency(crStrings, seance.getCours().getNom()) == 0) {
					data[colData][0] = seance.getCours().getNom();
					data[colData][1] =  seance.getDate();
					data[colData][2] = seance.getDate();
					data[colData][3] =  h + "h" + mi  ;
					data[colData][4] = 1;
					crStrings.add(seance.getCours().getNom());
					colData++;

				}else {
					int var = 0;
					for (String cours : crStrings) {
				        if (cours.equals(seance.getCours().getNom())) {
				        	data[var][4] = (Integer) data[var][4] + 1;
							String[] testString = data[var][3].toString().split("h");
							int jai = dur + Integer.parseInt(testString[1]) + Integer.parseInt(testString[0])*60;
							int h2 = jai / 60;
							int mi2 = jai % 60;
							if(mi2 == 0 ) data[var][3] = h2 + "h" + mi2 + "0";
							else {
								data[var][3] = h2 + "h" + mi2;
							}
		
							Timestamp stamp = (Timestamp) data[var][1];
	
							if(stamp.after(seance.getDate())) {
								data[var][1] = seance.getDate();
							}else {
								data[var][2] = seance.getDate();			
							}
				        }
				        var++;
				    }
					
				}


			}
		
		return data;	
		}
	}

	public void findAllSeanceEtudiant(Etudiant etudiant)
	{
		setListSeances(seanceDao.findByGroupeContaining(etudiant.getGroupe()));
	}
	public void findAllSeanceEnseignant(Enseignant enseignant)
	{
		setListSeances(seanceDao.findByEnseignantContaining(enseignant));
	}
	public void findAllSeanceGroupe(Groupe groupe)
	{
		setListSeances(seanceDao.findByGroupeContaining(groupe));
	}
	public void findAllSeanceSalle(Salle salle)
	{
		setListSeances(seanceDao.findBySalleContaining(salle));
	}


	//Fetch all seance 
	public void allSeances(String email,  VueRecap view) {

		if(Singleton.getInfo().getDroit() == 4) {
			Etudiant etudiant = (Etudiant) utilisateurDaoR.findByEmail(email);
			findAllSeanceEtudiant(etudiant);
		}else if (Singleton.getInfo().getDroit() == 3) {
			Enseignant enseignant = (Enseignant) utilisateurDaoR.findByEmail(email);
			findAllSeanceEnseignant(enseignant);
		}
		if(Singleton.getInfo().getDroit() == 1) {

			if((email!= null)&&(email.split("/")[0].equals("groupeSearch")))
			{
				Groupe groupe = groupeDao.findByNom(email.split("/")[1]);
				findAllSeanceGroupe(groupe);
			}
			else if((email!= null)&&(email.split("/")[0].equals("salleSearch")))
			{
				Salle salle = salleDao.findByNom(email.split("/")[1]);
				findAllSeanceSalle(salle);
			}
			else {
				Utilisateur utilisateur = utilisateurDaoR.findByEmail(email);
				if(utilisateur == null) {
					setListSeances(Collections.<Seance>emptyList());
				}
				
				
				else {
					if(utilisateur.getDroit() == 4)
					{
						Etudiant etudiant = (Etudiant) utilisateur;
						findAllSeanceEtudiant(etudiant);

					}
					if(utilisateur.getDroit() == 3)
					{
						Enseignant enseignant = (Enseignant) utilisateur;
						findAllSeanceEnseignant(enseignant);

					}
				}
			}
			


		}
		view.setData(this.formatData(getListSeances()));
	}




	public void initController(VueRecap vueRecap, VueCalendrier viewCalendrier) {
		System.out.println("Init ControllerRecap");
		allSeances(Singleton.getInfo().getEmail(), vueRecap);
	}
}
