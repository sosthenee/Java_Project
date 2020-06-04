package javaProject.project.controller;

import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueRecap;
import util.cst;

@Component
public class CalendrierController {


    @Autowired
    UtilisateurDao utilisateurDao;
    @Autowired
    SeanceDao seanceDao;

    private Object[][] data;   
    
    private CurentUserSingleton Singleton = CurentUserSingleton.getInstance(); 

    private List<Seance> listSeances;
    
	public List<Seance> getListSeances() {
		return listSeances;
	}

	public void setListSeances(List<Seance> oui) {
		this.listSeances = oui;
	}

	public Object[][] formatData(List<Seance> seances) {

		 data = cst.getCalendarBlankData();  


		for (Seance seance : seances) {

			Calendar calendar = Calendar.getInstance();
		

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
			}else if(seance.getSalle().size() == 1) {
				salleSeance = seance.getSalle().get(0).getNom();
			}else {
				salleSeance = "Null";
			}

			if(seance.getEnseignant().size() > 1) {
				for(Enseignant it : seance.getEnseignant())
				{
					enseiSenace += it.getNom() + " / ";
				}
			}else if(seance.getEnseignant().size() == 1) {
				enseiSenace = seance.getEnseignant().get(0).getNom();
			}
			else {
				enseiSenace = "Null";
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
		
		if(Singleton.getInfo().getDroit() == 4) {
			System.out.println("Seance etudiant");
			Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
			List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine,i.getGroupe());
			setListSeances(a);
			Object[][] data = this.formatData(a);
			view.setData(data);
		}else if (Singleton.getInfo().getDroit() == 3) {
			System.out.println("Seance enseignant");
			Enseignant i = (Enseignant) utilisateurDao.findByEmail(email);
			List<Seance> a = seanceDao.findBySemaineAndEnseignantContaining(semaine,i);
			setListSeances(a);
			System.out.println(a);
			Object[][] data = this.formatData(a);
			view.setData(data);
		}
	}
	
	public void edtFindByName(String name, VueCalendrier view, int semaine) {
		
		Utilisateur utilisateur = utilisateurDao.findByNom(name);
		List<Seance> a;

		if((Singleton.getInfo().getDroit() == 4)&&(utilisateur.getDroit()==4)) {
			System.out.println("Seance etudiant");
			Etudiant i = (Etudiant) utilisateur;
			a = seanceDao.findBySemaineAndGroupeContaining(semaine,i.getGroupe());
			Object[][] data = this.formatData(a);
			System.out.println(a);
			view.setData(data);
		}else if ((Singleton.getInfo().getDroit() == 3)&&(utilisateur.getDroit()==3)) {
			System.out.println("Seance enseignant");
			Enseignant i = (Enseignant) utilisateur;
			a = seanceDao.findBySemaineAndEnseignantContaining(semaine,i);
			System.out.println(a);
			Object[][] data = this.formatData(a);
			view.setData(data);
		}else {
			a = Collections.<Seance>emptyList();;
			Object[][] data = this.formatData(a);
			view.setData(data);
		}
		
	}
	
	public void ControlFrames(VueCalendrier view,VueRecap vueRecap,RecapControleur recapControleur,String email) {
		System.out.println("ezejazejoeojzejiozajozahioe");
		recapControleur.allSeances(email, vueRecap);
	}
	
	public void ControlFrameStyleAff(String stylePlann, VuePlanningListe vuePlanningListe, VueCalendrier vueCalendrier, PlanListeController planListeController,VuePlanningListe planningListe) {
		if(stylePlann.equals("Planning en grille") == true) {
			System.out.println("GRILLE");
		}else if(stylePlann.equals("Planning en liste") == true) {
			vuePlanningListe.setVisible(true);
			vueCalendrier.setVisible(false);
			try {
				vuePlanningListe.setData(planListeController.formatData(getListSeances(),planningListe));

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Liste");

		}
		
	}
	
	public void initController(VueCalendrier view , VueLogin view2,VueRecap vueRecap,RecapControleur recapControleur,VuePlanningListe vuePlanningListe,PlanListeController planListeController) {
		System.out.println("Init Controller Calendrier");
		for ( int i =0 ; i< 52 ; i++){
			final int semaine = Integer.parseInt(view.buttonList.get(i).getText());
			view.buttonList.get(i).addActionListener(e -> allSeances(view2.mail.getText(),view , semaine ));
			view.Recherche.addActionListener(e -> edtFindByName(view.Recherche.getText(),view , semaine ));		
		}
		//view.Onglets.addActionListener(e -> ControlFrames(view,vueRecap,recapControleur,view2.mail.getText()));
		view.ComboAff.addActionListener(f -> ControlFrameStyleAff(view.ComboAff.getSelectedItem().toString(),vuePlanningListe,view,planListeController,vuePlanningListe));

	}

}
