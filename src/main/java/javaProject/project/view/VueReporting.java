/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Oscar
 */
public class VueReporting extends JFrame {

	public ChartFrame frame ;
	public String title[] = {"Matière", "Première Séance", "Dernière Séance", "Durée", "Nb"};
	public DefaultPieDataset dataset = new DefaultPieDataset();
	public JFreeChart chart;
	public VueReporting(String Title){

		 chart = ChartFactory.createPieChart(
				"Diagramme récapitulatif du cours",
				dataset,
				true, 
				true, 
				false 
				);
		frame = new ChartFrame("Diagramme récapitulatif du cours", chart);
		frame.pack();
		frame.setLocationRelativeTo(null);

	}

	public void setData(Object[][]data){
		float total=0;
		if(data.length != 0)
		{
			if(data[0][0] != null) {
				for(int i = 0; i<data.length;++i)
				{
					float nbcours = (float) data[i][1];
					total = total + nbcours;
				}
				for(int j= 0;j<data.length;++j)
				{
					String matiere = (String) data[j][0];
					float heure = (float) data[j][1];
					dataset.setValue(matiere, (100/total)*heure);
				}
			}
		}

	}

}
