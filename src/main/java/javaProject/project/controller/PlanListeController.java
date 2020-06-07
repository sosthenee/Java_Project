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
			String teachString = null;
			String salleString = null;

			if(itDay.getEnseignant().size()>0){
				for(Enseignant it : itDay.getEnseignant()) {
					if(teachString == null) {
						teachString =  it.getNom()  ;
					}else {
						teachString  = teachString + "<br>" + "Mr/Mme " +it.getNom() ;
					}
					
				}
				day[n][2] = "<html>" + "Mr/Mme " + teachString +"</html>";
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

		if(this.listSeances == null)
		{
			Object[][] data2 = new Object[1][];
			for(int it = 0;it<6;it++) {
				data.add(data2);
			}
			
			return data;
		}else {


			for (Seance seance : seances) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(seance.getDate());
				int day_of_week = calendar.get(Calendar.DAY_OF_WEEK);


				switch (day_of_week) {
				case 1:
					System.out.println("Dimanche");
					break;
				case 2:
					seanceLundi.add(seance);
					break; 
				case 3:
					seanceMardi.add(seance);
					break;
				case 4:
					seanceMercredi.add(seance);
					break;
				case 5:
					seanceJeudi.add(seance);
					break;
				case 6:
					seanceVendredi.add(seance);
					break;
				case 7:
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
	}

	public void findSeanceEtudiant(Etudiant etudiant, int semaine)
	{
		setListSeances(seanceDao.findBySemaineAndGroupeContaining(semaine,etudiant.getGroupe()));
	}
	public void findSeanceEnseignant(Enseignant enseignant, int semaine)
	{
		setListSeances(seanceDao.findBySemaineAndEnseignantContaining(semaine,enseignant));
	}
	public void findSeanceGroupe(Groupe groupe, int semaine)
	{
		setListSeances(seanceDao.findBySemaineAndGroupeContaining(semaine, groupe));
	}
	public void findSeanceSalle(Salle salle, int semaine)
	{
		setListSeances(seanceDao.findBySemaineAndSalleContaining(semaine, salle));
	}

	public void allSeances(String email,  VuePlanningListe view , int semaine) {

		if(Singleton.getInfo().getDroit() == 4) {
			Etudiant etudiant = (Etudiant) utilisateurDao.findByEmail(email);
			findSeanceEtudiant(etudiant, semaine);
		}else if (Singleton.getInfo().getDroit() == 3) {
			Enseignant enseignant = (Enseignant) utilisateurDao.findByEmail(email);
			findSeanceEnseignant(enseignant, semaine);
		}
		if((Singleton.getInfo().getDroit() == 1)||(Singleton.getInfo().getDroit() == 2)) {
			if((email!= null)&&(email.split("/")[0].equals("groupeSearch")))
			{
				Groupe groupe = groupeDao.findByNom(email.split("/")[1]);
				findSeanceGroupe(groupe, semaine);
			}
			else if((email!= null)&&(email.split("/")[0].equals("salleSearch")))
			{
				Salle salle = salleDao.findByNom(email.split("/")[1]);
				findSeanceSalle(salle, semaine);
			}else {
				Utilisateur utilisateur = utilisateurDao.findByEmail(email);
				if((utilisateur == null)||(utilisateur.getDroit() == 1)) {
					setListSeances(Collections.<Seance>emptyList());
					view.Recherche.setText("Aucun utilisateur");
					view.setData(formatData(getListSeances(),view));
				}else {
					if(utilisateur.getDroit() == 4)
					{
						Etudiant i = (Etudiant) utilisateurDao.findByEmail(email);
						List<Seance> a = seanceDao.findBySemaineAndGroupeContaining(semaine,i.getGroupe());
						setListSeances(a);
						view.setData(formatData(getListSeances(),view));

					}
					if(utilisateur.getDroit() == 3)
					{
						Enseignant i = (Enseignant) utilisateurDao.findByEmail(email);
						List<Seance> a = seanceDao.findBySemaineAndEnseignantContaining(semaine,i);
						setListSeances(a);
						view.setData(formatData(getListSeances(),view));

					}
				}
			}


		}

		view.setData(formatData(getListSeances(),view));

		for ( int i =0 ; i< 52 ; i++){
			view.buttonList.get(i).setBackground(new JButton().getBackground());
		}
		view.buttonList.get(semaine-1).setBackground(Color.cyan);
	}


	public void initController(VuePlanningListe vuePlanningListe) {
		System.out.println("Init Controller Liste");
		allSeances(Singleton.getInfo().getEmail(),vuePlanningListe , 1 );

		for ( int i =0 ; i< 52 ; i++){
			final int semaine = Integer.parseInt(vuePlanningListe.buttonList.get(i).getText());
			vuePlanningListe.buttonList.get(i).addActionListener(e -> allSeances(Singleton.getInfo().getEmail(),vuePlanningListe , semaine ));
		}

	}

}
