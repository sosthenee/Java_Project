/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import javaProject.project.dao.EtudiantDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SalleDao;
import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Promotion;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.Calendrier;
import javaProject.project.view.Fenetre;
import javaProject.project.view.LookCalendrier;
import javaProject.project.view.VueCalendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */
@Component
public class EtudiantController {


	@Autowired
	private UtilisateurDao utilisateurDao;

	@Autowired
	private EtudiantDao etudiantDao;

	@Autowired
	private PromotionDao promotionDao;


	private VueCalendrier view;

	@Autowired
	private SeanceDao seanceDao;

	public EtudiantController() {

	}

	public Object[][] formatData(List<Seance> seances) {

		Object[][] data = {
				{"8h00 - 8h30 ", " ", " ", " ", " ", " ", " "},
				{" 8h30 - 9h00 ", " ", " ", " ", " ", " ", " "},
				{" 9h00 - 9h30 ", " ", " ", " ", " ", " ", " "},
				{" 9h30 - 10h00", " ", " ", " ", " ", " ", " "},
				{" 10h00 - 10h30", " ", " ", " ", " ", " ", " "},
				{" 10h30 - 11h00", " ", " ", " ", " ", " ", " "},
				{" 11h00 - 11h30", " ", " ", " ", " ", " ", " "},
				{"11h30 - 12h00 ", " ", " ", " ", " ", " ", " "},
				{"12h00 - 12h30 ", " ", " ", " ", " ", " ", " "},
				{" 12h30 - 13h00 ", " ", " ", " ", " ", " ", " "},
				{" 13h00 - 13h30 ", " ", " ", " ", " ", " ", " "},
				{" 13h30 - 14h00", " ", " ", " ", " ", " ", " "},
				{" 14h00 - 14h30", " ", " ", " ", " ", " ", " "},
				{" 14h30 - 15h00", " ", " ", " ", " ", " ", " "},
				{" 15h00 - 15h30", " ", " ", " ", " ", " ", " "},
				{"15h30 - 16h00 ", " ", " ", " ", " ", " ", " "},
				{" 16h00 - 16h30 ", " ", " ", " ", " ", " ", " "},
				{" 16h30 - 17h00 ", " ", " ", " ", " ", " ", " "},
				{" 17h00 - 17h30", " ", " ", " ", " ", " ", " "},
				{" 17h30 - 18h00", " ", " ", " ", " ", " ", " "},
				{" 18h00 - 18h30", " ", " ", " ", " ", " ", " "},
				{" 18h30 - 19h00", " ", " ", " ", " ", " ", " "},
				{" 19h00 - 19h30 ", " ", " ", " ", " ", " ", " "},
				{" 19h30 - 20h00 ", " ", " ", " ", " ", " ", " "},
				{" 20h00 - 20h30", " ", " ", " ", " ", " ", " "},
				{" 20h30 - 21h00", " ", " ", " ", " ", " ", " "},
				{" 21h00 - 21h30", " ", " ", " ", " ", " ", " "},};




		for (Seance seance : seances) {

			Calendar calendar = Calendar.getInstance();
			TimeZone tzInFrance = TimeZone.getTimeZone("France/Paris");
			calendar.setTimeZone(tzInFrance);

			calendar.setTime(seance.getDate());


			int heure_fin = seance.getHeure_fin();
			int minute_fin = seance.getMinute_fin();
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			int minutes = calendar.get(Calendar.MINUTE);
			int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);

			int index_debut = hours - 8;

			String salleSeance = "";
			String enseiSenace = "";


			if(seance.getSalle().size() > 1) {
				for(Salle it : seance.getSalle())
				{
					salleSeance += it.getNom() + " / ";
				}
			}else {
				salleSeance = seance.getSalle().get(0).getNom();
			}

			if(seance.getEnseignant().size() > 1) {
				for(Enseignant it : seance.getEnseignant())
				{
					enseiSenace += it.getNom() + "/ ";
				}
			}else {
				enseiSenace = seance.getEnseignant().get(0).getNom();
			}


			index_debut=index_debut*2;
			if(minutes == 30){
				index_debut+=1;
			} 

			int index_fin = heure_fin - 8;
			index_fin=index_fin*2;
			if (minute_fin == 30 ) {
				index_fin += 1;
			}


			for (int i = index_debut ; i < index_fin ; i++){

				data[i][day_of_week-1]= "<html> type de cours : " + seance.getType_cours().getNom() + "<br>"+"  cours :  " + seance.getCours().getNom() +
						"  Professeur :  " + enseiSenace +  "  salle :  " + salleSeance  +"</html>";
			}
			if (index_fin - index_debut < 2 ) {
				data[index_debut][day_of_week-1]= "<html> type de cours : " + seance.getType_cours().getNom() + "<br>"+"  cours :  " + seance.getCours().getNom() +
						"  Professeur :  " +  enseiSenace +  "  salle :  " + salleSeance   +"</html>";
			}
		}
		return data;
	}

	public void allSeances(String email,  VueCalendrier view , int semaine) {
		Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);


		List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine,i.getGroupe());

		System.out.println(a);

		String title[] = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
		Object[][] data = this.formatData(a);
		view.setData(data);

	}
	public void initController(VueCalendrier view , Fenetre view2) {
		//view.button1.addActionListener(e -> allSeances(view2.mail.getText(),view));
		for ( int i =0 ; i< 52 ; i++){
			final int semaine = Integer.parseInt(view.buttonList.get(i).getText());
			view.buttonList.get(i).addActionListener(e -> allSeances(view2.mail.getText(),view , semaine ));
		}
	}

}
