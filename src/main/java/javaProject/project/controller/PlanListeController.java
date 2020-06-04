package javaProject.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Seance;
import util.cst;

@Component
public class PlanListeController {

	@Autowired
	UtilisateurDao utilisateurDaoR;
	@Autowired
	SeanceDao seanceDao;
	
	private Object[][] data; 

	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();


	public Object[][] formatData(List<Seance> seances) {
		data = cst.getCalendarBlankData2();
		int a =0;
//		for (Seance seance : seances) {
//			data[a][0] = seance.getCours().getNom();
//			data[a][1] =  seance.getDate();
//			data[a][2] = seance.getDate();
//			data[a][3] =  seance.getMinute_fin()  ;
//			data[a][4] = 1;
//			a++;
//		}


		return data;
	}
	
	

}
