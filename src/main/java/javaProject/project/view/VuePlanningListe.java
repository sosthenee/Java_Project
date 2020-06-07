/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumnModel;


import org.springframework.boot.autoconfigure.SpringBootApplication;

import util.cst;

/**
 *
 * @author Oscar
 */

public class VuePlanningListe extends JFrame{
	String[] titre ={"", "","","",""};

	public JTable tableau;
	public JTable InfoJour;
	public JMenuBar navigation = new JMenuBar();
	public JMenu MenuSalles = new JMenu("Salles");
	public JMenuItem ItemSalles1 = new JMenuItem("Emploi du Temps");
	public JMenuItem ItemSalles2 = new JMenuItem("Salles Libres");
	public JMenu MenuCours = new JMenu("Cours");
	public JMenuItem ItemCours1 = new JMenuItem("Emploi du temps");
	public JMenuItem ItemCours2 = new JMenuItem("Recapitulatif des cours");
	public JPanel navbar = new JPanel();
	public JPanel principal = new JPanel();
	public JPanel semaines = new JPanel();
	public List<JButton> buttonList = new ArrayList<JButton>();
	public JPanel calendar = new JPanel();
	public JPanel JourListe = new JPanel();
	public JTextField Recherche = new JTextField();
	public JComboBox ComboAff = new JComboBox();
	public JComboBox ComboRecherche = new JComboBox();
	public Object[][] data;
	public List<JTable> mesJours = new ArrayList<JTable>();
	public JPanel interJPanel = new JPanel();

	public int nbJour = 0;


	public int getNbJour() {
		return nbJour;
	}

	public void setNbJour(int nbJour) {
		this.nbJour = nbJour;
	}

	public VuePlanningListe()
	{
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		this.setSize(700, 240);
		String[] choixAff = {"Planning en liste","Planning en grille"};
		String[] choixRecherche = {"Saisie du nom","Liste"};
		ComboAff = new JComboBox(choixAff);
		ComboRecherche = new JComboBox(choixRecherche);
		semaines.setLayout(new GridLayout(1, 52));
		navbar.setLayout(new GridLayout (1,4));
		calendar.setLayout(new BorderLayout());
		interJPanel.setLayout(new GridLayout(7,1));
		principal.setLayout(new BorderLayout());
		JourListe.setLayout(new GridLayout(12,1));
		//JourListe.setLayout(new GridBagLayout());


		this.MenuSalles.add(ItemSalles1);
		this.MenuSalles.add(ItemSalles2);
		this.MenuCours.add(ItemCours1);
		this.MenuCours.add(ItemCours2);
		this.navigation.add(MenuCours);
		this.navigation.add(MenuSalles);

		GridBagConstraints contraintesNum;
		contraintesNum = new GridBagConstraints();
		contraintesNum.weightx = 0.5;
		contraintesNum.weighty = 0.5;
		contraintesNum.gridwidth = GridBagConstraints.REMAINDER;

		GridBagConstraints contraintesInfo;
		contraintesInfo = new GridBagConstraints();
		contraintesInfo.gridwidth = GridBagConstraints.REMAINDER;
		//contraintesInfo.fill = GridBagConstraints.HORIZONTAL;
		contraintesInfo.weightx = 1;
		//contraintesInfo.ipady = 10; 

		semaines.setPreferredSize(new Dimension(2500, 50));
		for (int i = 1; i < 53; ++i) {
			String j = Integer.toString(i);
			JButton nb = new JButton(j);
			buttonList.add(nb);
			semaines.add(nb);

		}
		principal.setBackground(Color.RED);

		principal.add(new JScrollPane(semaines), BorderLayout.NORTH);
		principal.add(interJPanel, BorderLayout.CENTER);
		this.getContentPane().add(principal);

	}

	public void setData(List<Object[][]> data) {

		String day = null;

		interJPanel.removeAll();
	
		for(int d = 0; d<6;d++) {
			if(d == 0)day = "Lundi";
			if(d == 1)day = "Mardi";
			if(d == 2)day = "Mercredi";
			if(d == 3)day = "Jeudi";
			if(d == 4)day = "Vendredi";
			if(d == 5)day = "Samedi";
			titre[0] = day;
			if(data != null) {
				if(data.get(d).length != 0) {
					
						JTable dayJTable = new JTable(new TableCalendrier(data.get(d), titre));
						dayJTable.setRowHeight(1,20);
						if(data.get(d)[0]!=null) {
							if(data.get(d)[0][2] != null) {
								if(data.get(d)[0][2].toString().split("<br>") != null) {
									dayJTable.setRowHeight(d,20*data.get(d)[0][2].toString().split("<br>").length);
								}
							}
						}			
						dayJTable.disable();
						mesJours.add(dayJTable);
						interJPanel.add(new JScrollPane(dayJTable),BorderLayout.CENTER);
						interJPanel.revalidate();
						interJPanel.repaint();
					
					
				}else {
					Object[][] data2 = new Object[1][];
					JTable dayJTable = new JTable(new TableCalendrier(data2, titre));
				}
				interJPanel.revalidate();
				interJPanel.repaint();
				
			}else {
				Object[][] data2 = new Object[1][];
				JTable dayJTable = new JTable(new TableCalendrier(data2, titre));
				interJPanel.revalidate();
				interJPanel.repaint();
			}
			
			
					
		}
	
	}



}
