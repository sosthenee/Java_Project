/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import util.cst;

/**
 *
 * @author Oscar
 */
public class VuePlanningListe extends JFrame{
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
    public JPanel calendar = new JPanel();
    public JPanel JourListe = new JPanel();
    public JTextField Recherche = new JTextField();
    public JComboBox ComboAff = new JComboBox();
    public JComboBox ComboRecherche = new JComboBox();
    public Object[][] data;
    
    public VuePlanningListe()
    {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTable");
        this.setSize(700, 240);
        String[] choixAff = {"en grille","en liste"};
        String[] choixRecherche = {"Saisie du nom","Liste"};
        ComboAff = new JComboBox(choixAff);
        ComboRecherche = new JComboBox(choixRecherche);
        semaines.setLayout(new GridLayout(1, 52));
        navbar.setLayout(new GridLayout (1,4));
        calendar.setLayout(new BorderLayout());
        principal.setLayout(new BorderLayout());
        //JourListe.setLayout(new GridLayout(12,1));
        JourListe.setLayout(new GridBagLayout());
        
        
        this.MenuSalles.add(ItemSalles1);
        this.MenuSalles.add(ItemSalles2);
        this.MenuCours.add(ItemCours1);
        this.MenuCours.add(ItemCours2);
        this.navigation.add(MenuCours);
        this.navigation.add(MenuSalles);
        //this.setJMenuBar(navigation);
        navbar.add(navigation);
        navbar.add(Recherche);
        navbar.add(ComboAff);
        navbar.add(ComboRecherche);
        
        GridBagConstraints contraintesNum;
        contraintesNum = new GridBagConstraints();
        contraintesNum.gridwidth = GridBagConstraints.REMAINDER;
        
        GridBagConstraints contraintesInfo;
        contraintesInfo = new GridBagConstraints();
        contraintesInfo.gridwidth = GridBagConstraints.REMAINDER;
        contraintesInfo.fill = GridBagConstraints.HORIZONTAL;
        contraintesInfo.weightx = 1;
        contraintesInfo.ipady = 20; 
        

        
        semaines.setPreferredSize(new Dimension(2500, 50));
        for (int i = 1; i < 53; ++i) {
            String j = Integer.toString(i);
            semaines.add(new JButton(j));
        }
        for(int d = 1; d<7;++d)
        {
            JPanel Date = new JPanel(new BorderLayout());
            JPanel Liste = new JPanel(new BorderLayout());
            String day = Integer.toString(d);
            Date.add(new JLabel(day));
            Object[][] data = cst.getCalendarBlankData();
            String[] titre ={"Heure", "Matière","Enseignant","Groupe","Salle","Num séance","Type cours"};
             
            TableCalendrier model2 = new TableCalendrier(data,titre);
            InfoJour = new JTable(model2);
            InfoJour.setTableHeader(null);
            InfoJour.setRowHeight(18);
            Liste.add((InfoJour), BorderLayout.CENTER);
            JourListe.add(Date,contraintesNum);
            JourListe.add((Liste),contraintesInfo);
            System.out.println("Nombre de colonne : " + model2.getColumnCount());
        System.out.println("Nombre de ligne : " + model2.getRowCount());
        }
        
        principal.add(new JScrollPane(semaines), BorderLayout.NORTH);
        principal.add(new JScrollPane(JourListe), BorderLayout.CENTER);
        this.getContentPane().add(navbar,BorderLayout.NORTH);
        this.getContentPane().add((principal), BorderLayout.CENTER);

  }
};

    
    

