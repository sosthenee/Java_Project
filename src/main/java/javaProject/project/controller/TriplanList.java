package javaProject.project.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import javaProject.project.model.Seance;


public class TriplanList {


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

}
