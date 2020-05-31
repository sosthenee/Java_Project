/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import javaProject.project.view.TableCalendrier;
import java.awt.BorderLayout;
import java.awt.Dimension;
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

/**
 *
 * @author Oscar
 */
public class VueCalendrier extends JFrame {

    private JTable tableau;
    private JMenuBar navigation = new JMenuBar();
    private JMenu MenuSalles = new JMenu("Salles");
    private JMenuItem ItemSalles1 = new JMenuItem("Emploi du Temps");
    private JMenuItem ItemSalles2 = new JMenuItem("Salles Libres");
    private JMenu MenuCours = new JMenu("Cours");
    private JMenuItem ItemCours1 = new JMenuItem("Emploi du temps");
    private JMenuItem ItemCours2 = new JMenuItem("Recapitulatif des cours");
    private JPanel navbar = new JPanel();
    private JPanel principal = new JPanel();
    private JPanel semaines = new JPanel();
    private JPanel calendar = new JPanel();

    public VueCalendrier() {
        String[] MenuSalles = {"Salles", "Emploi du temps", "Salles Libres"};
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTable");
        this.setSize(700, 240);
        semaines.setLayout(new GridLayout(1, 52));
        calendar.setLayout(new BorderLayout());
        principal.setLayout(new BorderLayout());
        navbar.setLayout(new GridLayout(1, 5));
        navbar.add(new JButton("Accueil"));
        navbar.add(new JButton("Planning"));
        navbar.add(new JButton("Vie Scolaire"));
        navbar.add(new JButton("Promotions"));
        navbar.add(new JComboBox(MenuSalles));

        this.MenuSalles.add(ItemSalles1);
        this.MenuSalles.add(ItemSalles2);
        this.MenuCours.add(ItemCours1);
        this.MenuCours.add(ItemCours2);
        //this.bartest.add(MenuSalles);
        this.navigation.add(MenuCours);
        this.setJMenuBar(navigation);

        /*test.setMaximumSize( new Dimension(Integer.MAX_VALUE,200));
    test.setMinimumSize( new Dimension(Integer.MAX_VALUE,200));*/
        semaines.setPreferredSize(new Dimension(2500, 50));
        for (int i = 1; i < 53; ++i) {
            //semaines[i]= new JButton(" "+i);
            String j = Integer.toString(i);
            semaines.add(new JButton(j));
        }

        Object[][] data = {
            {"8h30 - 10h ", " ", " ", " ", " ", " ", " "},
            {" 10h15 - 11h45 ", " ", " ", " ", " ", " ", " "},
            {" 12h - 13h30 ", " ", " ", " ", " ", " ", " "},
            {" 13h45 - 15h15", " ", " ", " ", " ", " ", " "},
            {" 15h30 - 17h", " ", " ", " ", " ", " ", " "},
            {" 17h15 - 18h45", " ", " ", " ", " ", " ", " "},
            {" 19h - 20h30", " ", " ", " ", " ", " ", " "}
        };

        String title[] = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

        TableCalendrier model = new TableCalendrier(data, title);
        System.out.println("Nombre de colonne : " + model.getColumnCount());
        System.out.println("Nombre de ligne : " + model.getRowCount());
        tableau = new JTable(model);
        tableau.setRowHeight(70);
        //calendar.add(new JScrollPane(tableau), BorderLayout.CENTER);
        calendar.add(new JScrollPane(tableau), BorderLayout.CENTER);
        //this.getContentPane().add(calendar);
        principal.add(new JScrollPane(semaines), BorderLayout.NORTH);
        principal.add((calendar), BorderLayout.CENTER);
        //this.getContentPane().add(new JScrollPane(test), BorderLayout.NORTH);
        //this.getContentPane().add((calendar), BorderLayout.CENTER);
        //this.getContentPane().add(navbar, BorderLayout.NORTH);
        this.getContentPane().add((principal), BorderLayout.CENTER);
        // this.setContentPane(test);
        //this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
        //this.getContentPane().add(test);
    }
}
