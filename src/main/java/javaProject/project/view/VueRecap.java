/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Oscar
 */
public class VueRecap extends JFrame{

    String title[] = {"Matière", "Première Séance", "Dernière Séance", "Durée", "Nb"};

    private JTable tableau;
    private JMenuBar navigation = new JMenuBar();
    private JMenu MenuSalles = new JMenu("Salles");
    private JMenuItem ItemSalles1 = new JMenuItem("Emploi du Temps");
    private JMenuItem ItemSalles2 = new JMenuItem("Salles Libres");
    private JMenu MenuCours = new JMenu("Cours");
    public JMenuItem ItemCours1 = new JMenuItem("Emploi du temps");
    private JMenuItem ItemCours2 = new JMenuItem("Recapitulatif des cours");
    private JPanel navbar = new JPanel();
    private JPanel principal = new JPanel();
    private JPanel recap = new JPanel();
    private JTextField Recherche = new JTextField("Rechercher");
    private JComboBox ComboAff = new JComboBox();
    private JComboBox ComboRecherche = new JComboBox();
    public JButton Accueil = new JButton ("Accueil");

    public VueRecap() {
   
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Récapitulatif des cours");
        this.setSize(700, 240);
        String[] choixAff = {"Planning en grille","Planning en liste"};
        String[] choixRecherche = {"Rechercher par nom","Rechercher par liste"};
        ComboAff = new JComboBox(choixAff);
        ComboRecherche = new JComboBox(choixRecherche);
        Accueil.setForeground(Color.WHITE);
        Accueil.setBackground(Color.DARK_GRAY);
        navbar.setLayout(new GridLayout (1,4, 5,0));
        recap.setLayout(new BorderLayout());
        principal.setLayout(new BorderLayout());
        this.MenuSalles.add(ItemSalles1);
        this.MenuSalles.add(ItemSalles2);
        this.MenuCours.add(ItemCours1);
        this.MenuCours.add(ItemCours2);
        this.navigation.add(Accueil);
        this.navigation.add(MenuCours);
        this.navigation.add(MenuSalles);
        //

        Object[][] data = {
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "},
            {" ", " ", " ", " ", " "}
        };


        TableCalendrier model = new TableCalendrier(data, title);
        System.out.println("Nombre de colonne : " + model.getColumnCount());
        System.out.println("Nombre de ligne : " + model.getRowCount());
        tableau = new JTable(model);
        Object test2 = tableau.getValueAt(2,3);
        System.out.println("La case est :" + test2);
        tableau.setRowHeight(40);
        //calendar.add(new JScrollPane(tableau), BorderLayout.CENTER);
        recap.add(new JScrollPane(tableau), BorderLayout.CENTER);
        //this.getContentPane().add(calendar);
        principal.add((recap), BorderLayout.CENTER);
        //this.getContentPane().add(new JScrollPane(test), BorderLayout.NORTH);
        //this.getContentPane().add((calendar), BorderLayout.CENTER);
        //this.getContentPane().add(navbar, BorderLayout.NORTH);
        this.getContentPane().add(navbar,BorderLayout.NORTH);
        this.getContentPane().add((principal), BorderLayout.CENTER);
        
        // this.setContentPane(test);
        //this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        //this.getContentPane().add(test);
        this.setVisible(false);

    }
    
    public void setData(Object[][] data) {
        tableau.setModel(new TableCalendrier(data, title));
    }
}