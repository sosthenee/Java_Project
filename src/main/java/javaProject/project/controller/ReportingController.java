package javaProject.project.controller;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javaProject.project.model.Seance;

@Component
public class ReportingController {

	
	public Object[][] formatData(Object[][]dataFromRecap) {
		
		int nbMatiere = dataFromRecap.length;
		int nbTotalH = 0;
		int nbTotalMin = 0;
		String getString = null;
		Object[][] data = new Object[dataFromRecap.length][2];
		for(int i = 0 ;i<dataFromRecap.length;i++) {
			float heure = 0;
			getString = (String) dataFromRecap[i][3];
			int h = Integer.parseInt(getString.split("h")[0]);
			int min = Integer.parseInt(getString.split("h")[1]);
			if(min == 30){
				heure = (float) (heure +0.5);
			}else {
				heure = (float) h;
			}
			data[i][0] = dataFromRecap[i][0];
			data[i][1] = heure;
		}
		return data;
	}
	
	public void refreshDataPiChart(Object[][]dataFromRecap) {
		formatData(dataFromRecap);	
	}
	
	
}
