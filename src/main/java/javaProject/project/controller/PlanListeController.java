package javaProject.project.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Seance;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueRecap;
import util.cst;

@Component
public class PlanListeController {

	@Autowired
	UtilisateurDao utilisateurDao;
	@Autowired
	SeanceDao seanceDao;
	
	private List<Seance> listSeances;

	public List<Seance> getListSeances() {
		return listSeances;
	}

	public void setListSeances(List<Seance> oui) {
		this.listSeances = oui;
	}




	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public Object[][] getDataDay(List<Seance> seanceDay){


		String[][] day = new String[seanceDay.size()][5];
		int n = 0;

		Collections.sort(seanceDay, new Comparator<Seance>() {
			@Override
			public int compare(Seance s1, Seance s2) {
				return s1.getDate().compareTo(s2.getDate());
			}
		});

		for (Seance itDay : seanceDay) {

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(itDay.getDate());
			int heure_fin = itDay.getHeure_fin();
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			int minutes = calendar.get(Calendar.MINUTE);
			int minute_fin = itDay.getMinute_fin();

			if(minutes == 0) day[n][0] = hours + "h" + minutes + "0 - " + heure_fin+ "h" + minute_fin;
			else {
				day[n][0] = hours + "h" + minutes + " - " + heure_fin+"h" + minute_fin;
			}


			day[n][1] = itDay.getCours().getNom();
                        if(itDay.getEnseignant().size()>0){
			day[n][2] = "Mr/Mme " + itDay.getEnseignant().get(0).getNom();
                        }
                        if(itDay.getSalle().size()>0){
                        day[n][3] = itDay.getSalle().get(0).getNom() + " - " + itDay.getSalle().get(0).getSite().getNom();
                        }
                        
                        day[n][4] = itDay.getType_cours().getNom();

			n++;
		}

		return day;

	}

	public List<Object[][]> formatData(List<Seance> seances,VuePlanningListe view) {
		List<Seance> seanceLundi = new ArrayList<Seance>();
		List<Seance> seanceMardi = new ArrayList<Seance>();
		List<Seance> seanceMercredi = new ArrayList<Seance>();
		List<Seance> seanceJeudi = new ArrayList<Seance>();
		List<Seance> seanceVendredi = new ArrayList<Seance>();
		List<Seance> seanceSamedi = new ArrayList<Seance>();
		List<Object[][]> data = new ArrayList<Object[][]>();
		for(int var = 0;var<6;var++) {
			view.mesJours.get(var).hide();

		}

		System.out.println("mais " + seances);
		int l = 0;

		for (Seance seance : seances) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(seance.getDate());
			int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
			

			switch (day_of_week) {
			case 1:
				System.out.println("Dimanche");
				break;
			case 2:
				view.mesJours.get(0).show();
				seanceLundi.add(seance);
				break; 
			case 3:
				view.mesJours.get(1).show();
				seanceMardi.add(seance);
				break;
			case 4:
				view.mesJours.get(2).show();
				seanceMercredi.add(seance);
				break;
			case 5:
				view.mesJours.get(3).show();
				seanceJeudi.add(seance);
				break;
			case 6:
				view.mesJours.get(4).show();
				seanceVendredi.add(seance);
				break;
			case 7:
				view.mesJours.get(5).show();
				seanceSamedi.add(seance);
				break;
			}
		}

		data.add(getDataDay(seanceLundi));
		data.add(getDataDay(seanceMardi));
		data.add(getDataDay(seanceMercredi));
		data.add(getDataDay(seanceJeudi));
		data.add(getDataDay(seanceVendredi));
		data.add(getDataDay(seanceSamedi));

		return data;
	}
	
	public void allSeances(String email,  VuePlanningListe view , int semaine) {

		if(Singleton.getInfo().getDroit() == 4) {
			System.out.println("Seance etudiant");
			Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
			List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine,i.getGroupe());
			setListSeances(a);
			//Object[][] data = this.formatData(getListSeances(),view);
			view.setData(formatData(getListSeances(),view));
		}else if (Singleton.getInfo().getDroit() == 3) {
			System.out.println("Seance enseignant");
			Enseignant i = (Enseignant) utilisateurDao.findByEmail(email);
			List<Seance> a = seanceDao.findBySemaineAndEnseignantContaining(semaine,i);
			setListSeances(a);
			System.out.println(a);
			//Object[][] data = this.formatData(a);
			view.setData(formatData(getListSeances(),view));
		}
		for ( int i =0 ; i< 52 ; i++){
			view.buttonList.get(i).setBackground(new JButton().getBackground());
		}
		view.buttonList.get(semaine-1).setBackground(Color.cyan);
	}
	

	public void initController(VuePlanningListe vuePlanningListe) {
		System.out.println("Init Controller Liste");
		for(int i = 0; i<vuePlanningListe.mesJours.size();i++)
			vuePlanningListe.mesJours.get(i).hide();
		for ( int i =0 ; i< 52 ; i++){
			final int semaine = Integer.parseInt(vuePlanningListe.buttonList.get(i).getText());
			vuePlanningListe.buttonList.get(i).addActionListener(e -> allSeances(Singleton.getInfo().getEmail(),vuePlanningListe , semaine ));
		}
		allSeances(Singleton.getInfo().getEmail(),vuePlanningListe , 1 );

	}

}
