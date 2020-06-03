/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import javaProject.project.view.TableCalendrier;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_WEEK;
import java.util.List;
import javaProject.project.model.Seance;
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
import javax.swing.table.TableCellRenderer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Oscar
 */
@SpringBootApplication
public class VueCalendrier extends JFrame {

    String title[] = {"Heure", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};

    public JTable tableau;
    public JButton button1;
    private JMenuBar navigation = new JMenuBar();
    private JMenu MenuSalles = new JMenu("Salles");
    public JButton Accueil = new JButton ("Accueil");
    private JMenuItem ItemSalles1 = new JMenuItem("Emploi du Temps");
    private JMenuItem ItemSalles2 = new JMenuItem("Salles Libres");
    private JMenu MenuCours = new JMenu("Cours");
    private JMenuItem ItemCours1 = new JMenuItem("Emploi du temps");
    public JMenuItem ItemCours2 = new JMenuItem("Recapitulatif des cours");
    private JPanel navbar = new JPanel();
    private JPanel principal = new JPanel();
    private JPanel semaines = new JPanel();
    private JPanel calendar = new JPanel();
    private List<Seance> seance;
    public List<JButton> buttonList = new ArrayList<JButton>();
    public TableCalendrier model;
    public Color couleur;
    public JComboBox ComboAff = new JComboBox();
    public JComboBox ComboRecherche = new JComboBox();
    public JTextField Recherche = new JTextField("Rechercher");

    public VueCalendrier() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("HyperPlanning");
        this.setSize(1000, 1000);
        Accueil.setForeground(Color.WHITE);
        Accueil.setBackground(Color.DARK_GRAY);
        String[] choixAff = {"Planning en grille","Planning en liste"};
        String[] choixRecherche = {"Rechercher par nom","Rechercher par liste"};
        ComboAff = new JComboBox(choixAff);
        ComboRecherche = new JComboBox(choixRecherche);
        semaines.setLayout(new GridLayout(1, 52));
        calendar.setLayout(new BorderLayout());
        principal.setLayout(new BorderLayout());
        navbar.setLayout(new GridLayout(1,4,7,0));
        /*navbar.add(new JButton("Accueil"));
        navbar.add(new JButton("Planning"));
        navbar.add(new JButton("Vie Scolaire"));
        navbar.add(new JButton("Promotions"));*/

        this.MenuSalles.add(ItemSalles1);
        this.MenuSalles.add(ItemSalles2);
        this.MenuCours.add(ItemCours1);
        this.MenuCours.add(ItemCours2);
        this.navigation.add(Accueil);
        this.navigation.add(MenuCours);
        this.navigation.add(MenuSalles);
        
        navbar.add(navigation);
        navbar.add(ComboAff);
        navbar.add(Recherche);
        navbar.add(ComboRecherche);

        semaines.setPreferredSize(new Dimension(2600, 50));

        for (int i = 1; i < 53; ++i) {
            String j = Integer.toString(i);
            JButton nb = new JButton(j);
            buttonList.add(nb);
            semaines.add(nb);
        }

        Object[][] data = {
            {"8h00 - 8h30 ", " ", " ", " ", " ", " ", " "},
            {" 8h30 - 9h00 ", " ", " ", " ", " ", " ", " "},
            {" 9h00 - 9h30 ", " ", " ", " ", " ", " ", " "},
            {" 9h30 - 10h00", " ", " ", " ", " ", " ", " "},
            {" 10h00 - 10h30", " ", " ", " ", " ", " ", " "},
            {" 10h30 - 11h00", " ", " ", " ", " ", " ", " "},
            {" 11h00 - 11h30", " ", " ", " ", " ", " ", " "},
            {"11h30 - 12h00 ", " ", " ", " ", " ", " ", " "},
            {"12h00 - 12h30 ", " ", " ", " ", " ", " ", " "},
            {" 12h30 - 13h00 ", " ", " ", " ", " ", " ", " "},
            {" 13h00 - 13h30 ", " ", " ", " ", " ", " ", " "},
            {" 13h30 - 14h00", " ", " ", " ", " ", " ", " "},
            {" 14h00 - 14h30", " ", " ", " ", " ", " ", " "},
            {" 14h30 - 15h00", " ", " ", " ", " ", " ", " "},
            {" 15h00 - 15h30", " ", " ", " ", " ", " ", " "},
            {"15h30 - 16h00 ", " ", " ", " ", " ", " ", " "},
            {" 16h00 - 16h30 ", " ", " ", " ", " ", " ", " "},
            {" 16h30 - 17h00 ", " ", " ", " ", " ", " ", " "},
            {" 17h00 - 17h30", " ", " ", " ", " ", " ", " "},
            {" 17h30 - 18h00", " ", " ", " ", " ", " ", " "},
            {" 18h00 - 18h30", " ", " ", " ", " ", " ", " "},
            {" 18h30 - 19h00", " ", " ", " ", " ", " ", " "},
            {" 19h00 - 19h30 ", " ", " ", " ", " ", " ", " "},
            {" 19h30 - 20h00 ", " ", " ", " ", " ", " ", " "},
            {" 20h00 - 20h30", " ", " ", " ", " ", " ", " "},
            {" 20h30 - 21h00", " ", " ", " ", " ", " ", " "},
            {" 21h00 - 21h30", " ", " ", " ", " ", " ", " "},};

        model = new TableCalendrier(data, title);
        System.out.println("Nombre de colonne : " + model.getColumnCount());
        System.out.println("Nombre de ligne : " + model.getRowCount());
        tableau = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component comp = super.prepareRenderer(renderer, row, col);
                String value = (String) getModel().getValueAt(row, col);
                if (value.length() > 1) {
                    if (col != 0) {
                        comp.setBackground(setColor(value));
                    } else {
                        comp.setBackground(Color.white);
                    }
                } else {
                    comp.setBackground(Color.white);
                }
                return comp;
            }
        };
        tableau.setRowHeight(60);
        tableau.setCellSelectionEnabled(false);
        calendar.add(new JScrollPane(tableau), BorderLayout.CENTER);
        principal.add(new JScrollPane(semaines), BorderLayout.NORTH);
        principal.add((calendar), BorderLayout.CENTER);
        this.getContentPane().add((navbar), BorderLayout.NORTH);
        this.getContentPane().add((principal), BorderLayout.CENTER);

        this.setVisible(false);

    }

    public void setData(Object[][] data) {
        tableau.setModel(new TableCalendrier(data, title));
    }

    public Color setColor(String color) {

        if (color.contains("maths")) {
            couleur = Color.red;
        }
        if (color.contains("physique")) {
            couleur = Color.green;
        }
        if (color.contains("elec")) {
            couleur = Color.blue;
        }
        if (color.contains("reseau")) {
            couleur = Color.pink;
        }
        if (color.contains("informatique")) {
            couleur = Color.cyan;
        }

        return couleur;
    }
}
