/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Oscar
 */
public class TestPie extends JFrame {

    public TestPie(String Title){

    // create a dataset...
     Object[][] data = {
            {"Maths", " ", " ", " ", 51},
            {"Info", " ", " ", " ", 15},
            {"¨Physique", " ", " ", " ", 23},
            {"Elec", " ", " ", " ", 65},
            {"LFH", " ", " ", " ", 42}
        };

        String title[] = {"Matière", "Première Séance", "Dernière Séance", "Durée", "Nb"};
    int ligne=0;
    float total=0;
    TableCalendrier model = new TableCalendrier(data, title);
    ligne = model.getRowCount();
    for(int i = 0; i<ligne;++i)
    {
        float nbcours = (int) model.getValueAt(i,4);
        total = total + nbcours;
    }
    
    DefaultPieDataset dataset = new DefaultPieDataset();
    for(int j= 0;j<ligne;++j)
    {
        String matiere = (String) model.getValueAt(j,0);
        float heure = (int) model.getValueAt(j,4);
        dataset.setValue(matiere, (100/total)*heure);
        
    }
    // create a chart...
    JFreeChart chart = ChartFactory.createPieChart(
    "Sample Pie Chart",
    dataset,
    true, // legend?
    true, // tooltips?
    false // URLs?
    );
    // create and display a frame...
    ChartFrame frame = new ChartFrame("First", chart);
    frame.pack();
    frame.setVisible(true);
}
}
