/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaProject.project.dao.CoursDao;
import javaProject.project.dao.EnseignantDao;
import javaProject.project.dao.GroupeDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.TypeCoursDao;
import javaProject.project.model.Cours;
import javaProject.project.model.EnumerableElement;
import javaProject.project.model.Seance;
import javaProject.project.model.Type_cours;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.math.*;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Promotion;
import javaProject.project.model.Salle;
import javaProject.project.model.Site;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 *
 * @author sosthene
 */
@Component
public class ModifierController {

	@Autowired
	SeanceDao seanceDao;

	@Autowired
	CoursDao courDao;

	private Date DeltaDate(Date date, int heureFin, int minuteFin) {

		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		sdf.setTimeZone(timeZone);

		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);
		cal.add(Calendar.HOUR, heureFin - cal.get(Calendar.HOUR) - 1);
		cal.add(Calendar.MINUTE, minuteFin - cal.get(Calendar.MINUTE));
		System.out.println(sdf.format(cal.getTime()));
		Date endDate = cal.getTime();
		return endDate;
	}

	private boolean checkSanceAndSalle(ArrayList<Salle> salles, Date dateDebut, int heureFin, int minuteFin) {
		Date endDate = DeltaDate(dateDebut, heureFin, minuteFin);

		ArrayList<Seance> seances = (ArrayList<Seance>) seanceDao.findAll();
		for (Seance seance : seances) {
			for (Salle salle : salles) {
				for (Salle tmpsalle : seance.getSalle()) {
					if (tmpsalle.getId() == salle.getId()) {
						Date seanceDateFin = DeltaDate(seance.getDate(), seance.getHeure_fin(), seance.getMinute_fin());

						DateTime start1 = new DateTime(dateDebut);
						DateTime end1 = new DateTime(endDate);
						DateTime start2 = new DateTime(seance.getDate());
						DateTime end2 = new DateTime(seanceDateFin);


						Interval interval = new Interval(start1, end1);
						Interval interval2 = new Interval(start2, end2);

						if (interval.overlaps(interval2)) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}
	private boolean checkSanceAndGroupe(ArrayList<Groupe> groupes, Date dateDebut, int heureFin, int minuteFin) {
		Date endDate = DeltaDate(dateDebut, heureFin, minuteFin);

		ArrayList<Seance> seances = (ArrayList<Seance>) seanceDao.findAll();
		for (Seance seance : seances) {
			for (Groupe groupe : groupes) {
				for (Groupe tmpgroupe : seance.getGroupe()) {
					if (tmpgroupe.getId() == groupe.getId()) {
						Date seanceDateFin = DeltaDate(seance.getDate(), seance.getHeure_fin(), seance.getMinute_fin());

						DateTime start1 = new DateTime(dateDebut);
						DateTime end1 = new DateTime(endDate);
						DateTime start2 = new DateTime(seance.getDate());
						DateTime end2 = new DateTime(seanceDateFin);


						Interval interval = new Interval(start1, end1);
						Interval interval2 = new Interval(start2, end2);

						if (interval.overlaps(interval2)) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}
	private boolean checkSanceAndEnseignant(ArrayList<Enseignant> enseignants, Date dateDebut, int heureFin, int minuteFin) {
		Date endDate = DeltaDate(dateDebut, heureFin, minuteFin);

		ArrayList<Seance> seances = (ArrayList<Seance>) seanceDao.findAll();
		for (Seance seance : seances) {
			for (Enseignant enseignant : enseignants) {
				for (Enseignant tmpEnseignnt : seance.getEnseignant()) {
					if (tmpEnseignnt.getId_utilisateur() == enseignant.getId_utilisateur()) {
						Date seanceDateFin = DeltaDate(seance.getDate(), seance.getHeure_fin(), seance.getMinute_fin());

						DateTime start1 = new DateTime(dateDebut);
						DateTime end1 = new DateTime(endDate);
						DateTime start2 = new DateTime(seance.getDate());
						DateTime end2 = new DateTime(seanceDateFin);


						Interval interval = new Interval(start1, end1);
						Interval interval2 = new Interval(start2, end2);

						if (interval.overlaps(interval2)) {
							return false;
						}
					}

				}
			}
		}
		return true;
	}
 /*
    renvoie un type addapté pour le calendar en fonction d'un jour donné
   * @param day recoit le jour de la semaine en string
     @return selon le jour on revoie un int qui correspond a un jour de la semaine .
   */
	public int fromDayStringToInt(String day) {
		switch (day.toLowerCase()) {

		case "monday":
			return Calendar.MONDAY;
		case "tuesday":
			return Calendar.TUESDAY;

		case "wednesday":
			return Calendar.WEDNESDAY;

		case "thursday":
			return Calendar.THURSDAY;

		case "friday":
			return Calendar.FRIDAY;

		case "saturday":
			return Calendar.SATURDAY;

		}
		return 1;
	}

	String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}
/*
    ajoute une seance dans la base de donné
   * @param la vue modifier 
     @return true si le cours a bien ete motifié ou ajouté
   */
	public boolean writeData(VueModifier view3) throws ParseException {

		Object selected_hour_debut = view3.hour_debut.getItemAt(view3.hour_debut.getSelectedIndex());
		Object selected_minute_debut = view3.minute_debut.getItemAt(view3.minute_debut.getSelectedIndex());

		int selected_hour_fin = Integer.parseInt((String) view3.hour_fin.getItemAt(view3.hour_fin.getSelectedIndex()));
		int selected_minute_fin = Integer.parseInt((String) view3.minute_fin.getItemAt(view3.minute_fin.getSelectedIndex()));
		int selected_semaine = Integer.parseInt((String) view3.semaine.getItemAt(view3.semaine.getSelectedIndex()));
		int selected_etat = Integer.parseInt((String) view3.etat.getItemAt(view3.etat.getSelectedIndex()));

		Object selected_day = view3.day.getItemAt(view3.day.getSelectedIndex());

		Cours selected_matiere = view3.matiereList.get(view3.matiere.getSelectedIndex());
		Type_cours selected_type = view3.typeList.get(view3.type.getSelectedIndex());

		ArrayList<Groupe> groupes = new ArrayList<>();
		int[] selected_groupe = view3.groupe.getSelectedIndices();
		for (int i = 0; i < selected_groupe.length; i++) {
			groupes.add(view3.groupesList.get(selected_groupe[i]));
		}

		ArrayList<Enseignant> enseignants = new ArrayList<>();
		int[] selected_enseignant = view3.proffesseur.getSelectedIndices();
		for (int i = 0; i < selected_enseignant.length; i++) {
			enseignants.add(view3.professeurList.get(selected_enseignant[i]));
		}

		ArrayList<Salle> salles = new ArrayList<>();
		salles.add(view3.sallesList.get(view3.salle.getSelectedIndex()));

		String day = String.valueOf(selected_day);
		SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy");
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		sdf.setTimeZone(timeZone);

		Calendar cal = Calendar.getInstance(timeZone);
		cal.set(Calendar.WEEK_OF_YEAR, selected_semaine);
		cal.set(Calendar.DAY_OF_WEEK, fromDayStringToInt(day));
		cal.set(Calendar.MINUTE, Integer.parseInt((String) selected_minute_debut));
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt((String) selected_hour_debut) - 1);
		System.out.println(sdf.format(cal.getTime()));
		Calendar computedCal = Calendar.getInstance();
		computedCal.setTime(cal.getTime());

		System.out.println(sdf.format(cal.getTime()));

		Cours tmpC = courDao.findById(selected_matiere.getId());


		if (view3.currentSession == null) {
			if (checkSanceAndEnseignant(enseignants, cal.getTime(), selected_hour_fin, selected_minute_fin) == false) {
				JOptionPane.showMessageDialog(null, "Le proffesseur a deja un cours a cette date");
				return false ;
			}
			if (checkSanceAndSalle(salles, cal.getTime(), selected_hour_fin, selected_minute_fin) == false) {
				JOptionPane.showMessageDialog(null, "La salle a deja un cours a cette date");
				return false ;
			}
			if (checkSanceAndGroupe(groupes, cal.getTime(), selected_hour_fin, selected_minute_fin) == false) {
				JOptionPane.showMessageDialog(null, "Le groupe a deja un cours a cette date");
				return false ;
			}else {
				Seance s = new Seance(cal.getTime(),selected_etat, selected_hour_fin, selected_minute_fin, selected_semaine, tmpC, selected_type, groupes, salles, enseignants  );
				seanceDao.save(s);
			}
		} else {
			view3.currentSession.setDate(cal.getTime());
			view3.currentSession.setHeure_fin(selected_hour_fin);
			view3.currentSession.setMinute_fin(selected_minute_fin);
			view3.currentSession.setSemaine(selected_semaine);
			view3.currentSession.setEtat(selected_etat);
			view3.currentSession.setCours(tmpC);
			view3.currentSession.setType_cours(selected_type);
			view3.currentSession.setGroupes(groupes);
			view3.currentSession.setSalle(salles);
			view3.currentSession.setEnseignant(enseignants);
			seanceDao.save(view3.currentSession);
		}

		return true;
	}


	public void initController(VueModifier view3) {
		view3.button.addActionListener(e -> {
			try {
				//injectData(elts, view3);

				boolean worked = writeData(view3);
				if (worked){
					view3.setVisible(false);
				}
			} catch (ParseException ex) {
				Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
			}
		});

	}
}
