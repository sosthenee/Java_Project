package javaProject.project.controller;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import java.awt.event.ActionEvent;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javaProject.project.dao.CoursDao;
import javaProject.project.dao.EnseignantDao;
import javaProject.project.dao.GroupeDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SalleDao;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.SiteDao;
import javaProject.project.dao.TypeCoursDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Cours;
import javaProject.project.model.Enseignant;
import javaProject.project.model.EnumerableElement;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;

import javaProject.project.model.Site;

import javaProject.project.model.Utilisateur;

import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueCalendrier;

import javaProject.project.view.VueModifier;
import javax.swing.JTable;
import org.springframework.data.jpa.repository.JpaRepository;

import javaProject.project.view.VueRecap;
import javaProject.project.view.VueReporting;
import util.cst;

@Component
public class CalendrierController {

    @Autowired
    UtilisateurDao utilisateurDao;

    @Autowired
    SeanceDao seanceDao;

    @Autowired
    GroupeDao groupeDao;

    @Autowired
    EnseignantDao enseignantDao;

    @Autowired
    CoursDao coursDao;

    @Autowired
    PromotionDao promotionDao;

    @Autowired
    TypeCoursDao TypeCoursDao;

    @Autowired
    SiteDao siteDao;

    @Autowired
    SalleDao salleDao;

    private Object[][] data;

    Map<String, Seance> mapCoordToSeance = new HashMap<String, Seance>();

    private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

    private List<Seance> listSeances;

	public List<Seance> getListSeances() {
		return listSeances;
	}

	public void setListSeances(List<Seance> oui) {
		this.listSeances = oui;
	}

	private String emailRechString;

	public String getEmailRechString() {
		return emailRechString;
	}

	public void setEmailRechString(String emailRechString) {
		this.emailRechString = emailRechString;
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
			String groupeSeance ="";


			if(seance.getSalle().size() > 1) {
				for(Salle it : seance.getSalle())
				{
					salleSeance += it.getNom() + " / ";
				}
			}else if(seance.getSalle().size() == 1) {
				salleSeance = seance.getSalle().get(0).getNom();
			}else {
				salleSeance = "Pas de salle attribuée";
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
				enseiSenace = "Pas encore attribué";
			}
			
			if(seance.getGroupe().size() > 1) {
				for(Groupe it : seance.getGroupe())
				{
					groupeSeance += it.getNom() + " / ";
				}
			}else if(seance.getGroupe().size() == 1) {
				groupeSeance = seance.getGroupe().get(0).getNom();
			}
			else {
				groupeSeance = "Pas encore attribué";
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
			String state;
			if(seance.getEtat() == 0) {
				state = "Annulée-";
			}else if (seance.getEtat() == 1) {
				state="";
			}else {
				state="En cours de validation-";
			}
			
			
			for (int i = index_debut ; i < index_fin ; i++){

				data[i][day_of_week-1]= "<html>"+"<strong>" + state + "</strong>" + seance.getCours().getNom() +" - " +seance.getType_cours().getNom() + "<br>"+
						"  Mr/Mme " + enseiSenace +  "<br>" + salleSeance  +"<br>"+groupeSeance+"</html>";
                        mapCoordToSeance.put(String.valueOf(i)+'-'+String.valueOf(day_of_week - 1), seance);

			}
			if (index_fin - index_debut < 2 ) {
				data[index_debut][day_of_week-1]= "<html>"+"<strong>" + state + "</strong>" + seance.getCours().getNom() +" - " +seance.getType_cours().getNom() + "<br>"+
						"  Mr/Mme " + enseiSenace +  "<br>" + salleSeance +"<br>"+groupeSeance +"</html>";
                        mapCoordToSeance.put(String.valueOf(index_debut)+'-'+String.valueOf(day_of_week - 1), seance);

			}
		}
		return data;
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

	public void allSeances(String email,  VueCalendrier view , int semaine) {

		if(Singleton.getInfo().getDroit() == 4) {
			Etudiant etudiant = (Etudiant) utilisateurDao.findByEmail(email);
			findSeanceEtudiant(etudiant, semaine);
		}
		if (Singleton.getInfo().getDroit() == 3) {
			Enseignant enseignant = (Enseignant) utilisateurDao.findByEmail(email);
			findSeanceEnseignant(enseignant, semaine);
		}
		if((Singleton.getInfo().getDroit() == 1)||(Singleton.getInfo().getDroit() == 2)) {
			if((getEmailRechString()!= null)&&(getEmailRechString().split("/")[0].equals("groupeSearch")))
			{
				Groupe groupe = groupeDao.findByNom(getEmailRechString().split("/")[1]);
				findSeanceGroupe(groupe, semaine);
			}
			else if((getEmailRechString()!= null)&&(getEmailRechString().split("/")[0].equals("salleSearch")))
			{
				Salle salle = salleDao.findByNom(getEmailRechString().split("/")[1]);
				findSeanceSalle(salle, semaine);
			}
			else {
				Utilisateur utilisateur = utilisateurDao.findByEmail(getEmailRechString());
				if(utilisateur == null) {
					setListSeances(Collections.<Seance>emptyList());
					view.Recherche.setText("Aucun utilisateur");
				}else {
					if(utilisateur.getDroit() == 4)
					{
						Etudiant etudiant = (Etudiant) utilisateur;
						findSeanceEtudiant(etudiant, semaine);
					}
					if(utilisateur.getDroit() == 3)
					{
						Enseignant enseignant = (Enseignant) utilisateur;
						findSeanceEnseignant(enseignant, semaine);
					}
				}
			}
		}
		view.setData(this.formatData(getListSeances()));
		for ( int i =0 ; i< 52 ; i++){
			view.buttonList.get(i).setBackground(new JButton().getBackground());		
		}
		view.buttonList.get(semaine-1).setBackground(Color.cyan);
		view.setSelectedSemaine(String.valueOf(semaine));
	}

	//ADMIN
	public void edtFindByName(String name, VueCalendrier view, int semaine,VuePlanningListe vuePlanningListe, VueRecap vueRecap ,RecapController recapController, PlanListeController planListeController, VueReporting vueReporting) {

		String typeTofecthString = view.ComboChoixSelect.getModel().getSelectedItem().toString();

		if(typeTofecthString.equals("Etudiant")) 
		{
			Utilisateur utilisateur = utilisateurDao.findByNom(name);

			if(utilisateur == null) {
				view.Recherche.setText("Aucun Etudiant");
				setListSeances(Collections.<Seance>emptyList());
			}else
			{
				if(utilisateur.getDroit() == 4) {
					setEmailRechString(utilisateur.getEmail());
					allSeances("", view, semaine);
					recapController.allSeances(getEmailRechString(), vueRecap,vueReporting);
					planListeController.allSeances(getEmailRechString(), vuePlanningListe, semaine);
				}
				else {
					view.Recherche.setText("Aucun Etudiant");
					setListSeances(Collections.<Seance>emptyList());
					view.setData(this.formatData(getListSeances()));
					vueRecap.setData(recapController.formatData(getListSeances()));
					vuePlanningListe.setData(planListeController.formatData(getListSeances(), vuePlanningListe));
				}
			}

		}

		if(typeTofecthString.equals("Enseignant")) 
		{
			Utilisateur utilisateur = utilisateurDao.findByNom(name);

			if(utilisateur == null) {
				setListSeances(Collections.<Seance>emptyList());
				view.Recherche.setText("Aucun Enseignant");
				view.setData(this.formatData(getListSeances()));
				vueRecap.setData(recapController.formatData(getListSeances()));
				vuePlanningListe.setData(planListeController.formatData(getListSeances(), vuePlanningListe));
			}else
			{
				if(utilisateur.getDroit() == 3) {
					setEmailRechString(utilisateur.getEmail());
					allSeances("", view, semaine);
					recapController.allSeances(getEmailRechString(), vueRecap,vueReporting);
					planListeController.allSeances(getEmailRechString(), vuePlanningListe, semaine);

				}
				else {
					view.Recherche.setText("Aucun Enseignant");
					setListSeances(Collections.<Seance>emptyList());
					view.setData(this.formatData(getListSeances()));
					vueRecap.setData(recapController.formatData(getListSeances()));
					vuePlanningListe.setData(planListeController.formatData(getListSeances(), vuePlanningListe));
				}
			}
		}
		if(typeTofecthString.equals("Groupe")) {

			Groupe groupe = groupeDao.findByNom(name);
			if(groupe == null) {
				setListSeances(Collections.<Seance>emptyList());
				view.Recherche.setText("Aucun Groupe");
				view.setData(this.formatData(getListSeances()));
				vueRecap.setData(recapController.formatData(getListSeances()));
				vuePlanningListe.setData(planListeController.formatData(getListSeances(), vuePlanningListe));
			}
			else {
				setEmailRechString("groupeSearch/" + groupe.getNom());
				allSeances("", view, semaine);
				recapController.allSeances(getEmailRechString(), vueRecap,vueReporting);
				planListeController.allSeances(getEmailRechString(), vuePlanningListe, semaine);
			}
		}

		if(typeTofecthString.equals("Salle")) {
			Salle salle = salleDao.findByNom(name);

			if(salle == null) {
				setListSeances(Collections.<Seance>emptyList());
				view.Recherche.setText("Aucune Salle");
				view.setData(this.formatData(getListSeances()));
				vueRecap.setData(recapController.formatData(getListSeances()));
				vuePlanningListe.setData(planListeController.formatData(getListSeances(), vuePlanningListe));
			}
			else {
				setEmailRechString("salleSearch/" + salle.getNom());
				allSeances("", view, semaine);
				recapController.allSeances(getEmailRechString(), vueRecap,vueReporting);
				planListeController.allSeances(getEmailRechString(), vuePlanningListe, semaine);
			}
		}

	}


	public String[] getList(String type) {

		//ETUDIANT
		if(type.equals("Etudiant")) {
			List<Utilisateur> userList = utilisateurDao.findByDroit(4);
			String[] listtElement = new String[userList.size()];
			int i =0;
			for(Utilisateur it : userList) {
				listtElement[i] = it.getNom();
				i++;
			}
			return listtElement;
		}else if(type.equals("Enseignant")) {
			List<Utilisateur> userList = utilisateurDao.findByDroit(3);
			String[] listtElement = new String[userList.size()];
			int i =0;
			for(Utilisateur it : userList) {
				listtElement[i] = it.getNom();
				i++;
			}
			return listtElement;
		}
		else if(type.equals("Groupe")) {
			List<Groupe> groupeList = groupeDao.findAll();
			String[] listtElement = new String[groupeList.size()];
			int i =0;
			for(Groupe it : groupeList) {
				listtElement[i] = it.getNom();
				i++;
			}	
			return listtElement;
		}
		else if(type.equals("Salle")) {
			List<Salle> SalleList = salleDao.findAll();
			String[] listtElement = new String[SalleList.size()];
			int i =0;
			for(Salle it : SalleList) {
				listtElement[i] = it.getNom();
				i++;
			}
			return listtElement;
		}
		return null;
	}

	public void rechercheType(String type_recherche, VueCalendrier vueCalendrier) {
		String typeTofecthString = vueCalendrier.ComboChoixSelect.getModel().getSelectedItem().toString();
		if(type_recherche.equals("Rechercher par liste")) {
			vueCalendrier.setList(getList(typeTofecthString));
			vueCalendrier.Recherche.setVisible(false);
			vueCalendrier.listeRecherhe.setVisible(true);
		}else {
			vueCalendrier.Recherche.setVisible(true);
			vueCalendrier.listeRecherhe.setVisible(false);
		}
	}



	public ArrayList<EnumerableElement> DaoGetListData(JpaRepository dao) {

        ArrayList<EnumerableElement> temp_values = new ArrayList<>();
        var objects = dao.findAll();
        for (int i = 0; i < objects.size(); i++) {
            EnumerableElement elt = (EnumerableElement) objects.get(i);
            temp_values.add(elt);
        }
        return temp_values;
    }

    public void resetData(VueModifier view3) {
        view3.typeList.clear();
        view3.professeurList.clear();
        view3.sallesList.clear();
        view3.groupesList.clear();
        view3.matiereList.clear();
    }

    public void populateData(VueModifier view3, String hour, String minute, String hourEnd, String minuteEnd, String header, String semaine) {
        view3.setListEnseignant(DaoGetListData(enseignantDao));
        view3.setListCours(DaoGetListData(coursDao));
        view3.setListGroupe(DaoGetListData(groupeDao));
        view3.setListType_cours(DaoGetListData(TypeCoursDao));
        view3.setListSalle(DaoGetListData(salleDao));
        view3.setCoordinates(hour, minute, hourEnd, minuteEnd, header, semaine);

    }


	public void initController(VueCalendrier vueCalendrier , VueLogin vueLogin, VueModifier view3, VuePlanningListe vuePlanningListe , VueRecap vueRecap ,RecapController recapController ,PlanListeController planListeController, VueReporting vueReporting) {
		System.out.println("Init Controller Calendrier");
		allSeances(vueLogin.mail.getText(), vueCalendrier, 1);
		setEmailRechString(vueLogin.mail.getText());
		vueCalendrier.listeRecherhe.hide();
		for ( int i =0 ; i< 52 ; i++){
			final int semaine = Integer.parseInt(vueCalendrier.buttonList.get(i).getText());
			vueCalendrier.buttonList.get(i).addActionListener(e -> allSeances(getEmailRechString(),vueCalendrier , semaine ));
			vueCalendrier.Recherche.addActionListener(e -> edtFindByName(vueCalendrier.Recherche.getText(),vueCalendrier , semaine, vuePlanningListe , vueRecap , recapController , planListeController,vueReporting ));		
			vuePlanningListe.buttonList.get(i).addActionListener(e -> planListeController.allSeances(getEmailRechString(),vuePlanningListe , semaine ));
			vueCalendrier.listeRecherhe.addActionListener(e ->edtFindByName(vueCalendrier.listeRecherhe.getModel().getSelectedItem().toString(),vueCalendrier , semaine, vuePlanningListe , vueRecap , recapController , planListeController,vueReporting ));
		}
		if((Singleton.getInfo().getDroit() == 1)||(Singleton.getInfo().getDroit() == 2)) {
			vueCalendrier.ComboChoixSelect.setVisible(true);
			vueCalendrier.ComboRecherche.setVisible(true);
			vueCalendrier.Recherche.setVisible(true);
		}else {
			vueCalendrier.ComboChoixSelect.setVisible(false);
			vueCalendrier.ComboRecherche.setVisible(false);
			vueCalendrier.Recherche.setVisible(false);
			vueCalendrier.Report.setVisible(false);
		}
		vueCalendrier.ComboChoixSelect.addActionListener(l-> rechercheType((String)vueCalendrier.ComboRecherche.getModel().getSelectedItem(),vueCalendrier));
		vueCalendrier.ComboRecherche.addActionListener(emailRechString -> rechercheType((String)vueCalendrier.ComboRecherche.getModel().getSelectedItem(),vueCalendrier));
		vueCalendrier.Report.addActionListener(l -> vueReporting.frame.setVisible(true));
		vueCalendrier.tableau.addMouseListener(new MouseAdapter() {
			@Override
            public void mouseClicked(final MouseEvent e) {
                if (e.getClickCount() == 1) {
                    final JTable jTable = (JTable) e.getSource();
                    final int row = jTable.getSelectedRow();
                    final int column = jTable.getSelectedColumn();
                    final String header = jTable.getColumnName(column);
                    final String valueInCell = (String) jTable.getValueAt(row, column);
                    final String valueOfTime = (String) jTable.getValueAt(row, 0);

                    String hour = valueOfTime.substring(0, 2);
                    String minute = valueOfTime.substring(3, 5);

                    String hourEnd = valueOfTime.substring(8, 10);
                    String minuteEnd = valueOfTime.substring(11, 13);
                    String semaine = vueCalendrier.getSelectedSemaine();
                    System.out.print(minute);
                    if (Singleton.getInfo().getDroit() == 1) {
                        if (valueInCell.length() <= 1) {
                            if (column != 0) {
                                resetData(view3);
                                view3.setCurrentSession(null);
                                populateData(view3, hour, minute, hourEnd, minuteEnd, header, semaine);
                                view3.setVisible(true);
                                view3.setButtonContent("Create session");
                            }
                        } else {
                            if (column != 0) {
                                resetData(view3);
                                populateData(view3, hour, minute, hourEnd, minuteEnd, header, semaine);
                                view3.setCurrentSession(mapCoordToSeance.get(row + "-" + column));
                                view3.setButtonContent("Update session");
                                view3.setVisible(true);
                            }
                        }
                    }
                }
            }
        }
        );

    }

}
