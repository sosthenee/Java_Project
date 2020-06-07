package javaProject.project.controller;



import org.springframework.stereotype.Component;

import javaProject.project.view.VueReporting;


@Component
public class ReportingController {


	public Object[][] formatData(Object[][]dataFromRecap) {

		String getString = null;
		Object[][] data = new Object[dataFromRecap.length][2];
		for(int i = 0 ;i<dataFromRecap.length;i++) {
			float heure = 0;
			getString = (String) dataFromRecap[i][3];
			if(getString != null) {
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
		}
		return data;
	}

	public void refreshDataPiChart(Object[][]dataFromRecap, VueReporting vueReporting) {
		vueReporting.setData(formatData(dataFromRecap));	
	}

}
