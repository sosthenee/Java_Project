package javaProject.project.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Seance;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VuePlanningListe;
import javaProject.project.view.VueRecap;
import util.cst;

@Component
public class PlanListeController {

	@Autowired
	UtilisateurDao utilisateurDaoR;
	@Autowired
	SeanceDao seanceDao;
	
	
	TriplanList triplanList = new TriplanList();

	private List<Object[][]> data = new ArrayList<Object[][]>(); 
	public List<Seance> seanceLundi = new ArrayList<Seance>();
	public List<Seance> seanceMardi = new ArrayList<Seance>();
	public List<Seance> seanceMercredi = new ArrayList<Seance>();
	public List<Seance> seanceJeudi = new ArrayList<Seance>();
	public List<Seance> seanceVendredi = new ArrayList<Seance>();
	public List<Seance> seanceSamedi = new ArrayList<Seance>();

	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public List<Object[][]> formatData(List<Seance> seances,VuePlanningListe view) {
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
		this.data.add(triplanList.getDataDay(seanceLundi));
		this.data.add(triplanList.getDataDay(seanceMardi));
		this.data.add(triplanList.getDataDay(seanceMercredi));
		this.data.add(triplanList.getDataDay(seanceJeudi));
		this.data.add(triplanList.getDataDay(seanceVendredi));
		this.data.add(triplanList.getDataDay(seanceSamedi));

		return data;
	}

	public void initController(VuePlanningListe view) {
		System.out.println("Init Controller Liste");
		for(int i = 0; i<view.mesJours.size();i++)
			view.mesJours.get(i).hide();
	}

}
