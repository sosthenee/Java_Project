package javaProject.project.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.UtilisateurDao;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Etudiant;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;
import javaProject.project.model.Utilisateur;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueRecap;

@Component
public class RecapControleur {

	@Autowired
	UtilisateurDao utilisateurDaoR;
	@Autowired
	SeanceDao seanceDao;

	private CurentUserSingleton Singleton = CurentUserSingleton.getInstance();

	public Object[][] formatData(List<Seance> seances) {
		Object[][] data = {
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "},
				{"", " ", " ", " ", " "}
		};

		int a =0;
		for (Seance seance : seances) {

			int ocur = 0;

			for(int var = 0; var<seances.size();var++) {

				if(seance.getCours().getNom().equals(data[var][0]) == true) {

					data[var][4] = (Integer) data[var][4] + 1;
					data[var][3] = (Integer) data[var][3] + seance.getMinute_fin();
					ocur +=1;

					Timestamp stamp = (Timestamp) data[var][1];

					if(stamp.after(seance.getDate())) {
						data[var][1] = seance.getDate();
					}else {
						data[var][2] = seance.getDate();			
					}
					break;				
				}
			}

			if(ocur == 0) {

				data[a][0] = seance.getCours().getNom();
				data[a][1] =  seance.getDate();
				data[a][2] = seance.getDate();
				data[a][3] =  seance.getMinute_fin()  ;
				data[a][4] = 1;
			}

			a++;
		}

		return data;	
	}

	public void ControlFrames(VueCalendrier view,VueRecap vueRecap) {
		view.setVisible(true);
		vueRecap.setVisible(false);
	}

	public void initController(VueRecap view, VueCalendrier viewCalendrier) {
		System.out.println("Init ControllerRecap");
		view.ItemCours1.addActionListener(e -> ControlFrames(viewCalendrier, view));
	}
}
