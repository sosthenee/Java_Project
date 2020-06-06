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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_WEEK;
import java.util.List;
import javaProject.project.model.Seance;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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

    String title[] = {"Heure", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public JTable tableau;
    private JMenuBar navigation = new JMenuBar();
    private JMenu MenuSalles = new JMenu("Salles");
    public JButton Accueil = new JButton ("Accueil");
    private JMenuItem ItemSalles1 = new JMenuItem("Emploi du Temps");
    private JMenuItem ItemSalles2 = new JMenuItem("Salles Libres");
    private JMenu MenuCours = new JMenu("Cours");
    private JMenuItem ItemCours1 = new JMenuItem("Emploi du temps");
    public JMenuItem ItemCours2 = new JMenuItem("Recapitulatif des cours");
    private JPanel navbar = new JPanel();
    public JPanel navbarInf = new JPanel();
    public JPanel navbarInfInter = new JPanel();
    private JPanel principal = new JPanel();
    private JPanel semaines = new JPanel();
    private JPanel calendar = new JPanel();
    private List<Seance> seance;
    public List<JButton> buttonList = new ArrayList<JButton>();
    public TableCalendrier model;
    public Color couleur;

    public JComboBox ComboChoixSelect = new JComboBox();
    public JComboBox ComboAff = new JComboBox();
    public JComboBox ComboRecherche = new JComboBox();
    public JTabbedPane Onglets =new JTabbedPane();
    public JTextField Recherche = new JTextField("Rechercher");
    public JComboBox listeRecherhe = new JComboBox();
    public JPanel Onglet1 =new JPanel();
    public JPanel Onglet2 =new JPanel(); 



    public VueCalendrier(VueRecap FenRecap,VuePlanningListe FenListe) {

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("HyperPlanning");
        this.setSize(1000, 1000);
        
        JPanel Onglet1 =new JPanel();
        Onglet1.setLayout(new BorderLayout());
        JPanel Onglet2 =new JPanel(); 
        Onglet2.setLayout(new BorderLayout());
        Onglets.setBounds(50,50,200,200); 
        Onglets.add("Planning",Onglet1);  
        Onglets.add("Récapitulatif des cours",Onglet2);
        
        Accueil.setForeground(Color.WHITE);
        Accueil.setBackground(Color.DARK_GRAY);
        String[] choixAff = {"Planning en grille", "Planning en liste"};
        String[] choixRecherche = {"Rechercher par nom", "Rechercher par liste"};
        String[] choixSelcet = {"Etudiant", "Enseignant","Groupe","Salle"};

        ComboChoixSelect = new JComboBox(choixSelcet);
        listeRecherhe = new JComboBox();
        ComboAff = new JComboBox(choixAff);
        ComboRecherche = new JComboBox(choixRecherche);
        semaines.setLayout(new GridLayout(1, 52));
        calendar.setLayout(new BorderLayout());
        principal.setLayout(new BorderLayout());
        navbar.setLayout(new BorderLayout());
        navbarInf.setLayout(new GridLayout (1,5, 7, 0));
        navbarInfInter.setLayout(new BorderLayout());

        this.MenuSalles.add(ItemSalles1);
        this.MenuSalles.add(ItemSalles2);
        this.MenuCours.add(ItemCours1);
        this.MenuCours.add(ItemCours2);
        this.navigation.add(Accueil);
        this.navigation.add(MenuCours);
        this.navigation.add(MenuSalles);

        navbar.add(navigation);
        navbarInf.add(ComboAff);
        navbarInf.add(ComboChoixSelect);
        navbarInf.add(ComboRecherche);
        navbarInf.add(Recherche);
        navbarInf.add(listeRecherhe);

 


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
        tableau.getTableHeader().setReorderingAllowed(false);
        calendar.add(new JScrollPane(tableau), BorderLayout.CENTER);
        JPanel navinter = new JPanel();
       navinter.setLayout(new BorderLayout());
       //navinter.add(navbarInf, BorderLayout.NORTH);
       navinter.add(new JScrollPane(semaines), BorderLayout.CENTER);
        principal.add((navinter), BorderLayout.NORTH);
        principal.add((calendar), BorderLayout.CENTER);


        
        
        JPanel PanRecap = new JPanel();
        PanRecap.setLayout(new BorderLayout());
        PanRecap.add(FenRecap.getContentPane(),BorderLayout.CENTER);
        
        JPanel PanListe = new JPanel();
        PanListe.setLayout(new BorderLayout());
        PanListe.add(navbar,BorderLayout.NORTH);
        PanListe.add(FenListe.getContentPane(),BorderLayout.CENTER);

        Onglet1.add(navbarInf, BorderLayout.NORTH);
        Onglet1.add(principal, BorderLayout.CENTER);
       
        
         ActionListener testListener = new ActionListener() {//add actionlistner to listen for change
            @Override
            public void actionPerformed(ActionEvent e) {


                String s = (String) ComboAff.getSelectedItem();//get the selected item

                switch (s) {//check for a match
                    case "Planning en grille":
                        
                          principal.setVisible(true);
                           PanListe.setVisible(false);
                           Onglet1.add(principal, BorderLayout.CENTER);
                          
                        break;
                    case "Planning en liste":
                         System.out.println("Planning en liste");
                           principal.setVisible(false);
                            PanListe.setVisible(true);
                            Onglet1.add(PanListe, BorderLayout.CENTER);
                        break;
                      
                }
            }

        };
       
       ComboAff.addActionListener(testListener);
       principal.setVisible(true);
       PanListe.setVisible(false);
        
        Onglet2.add(PanRecap, BorderLayout.CENTER);
        this.getContentPane().add(navbar, BorderLayout.NORTH); //CODE BON
        this.getContentPane().add((Onglets), BorderLayout.CENTER); //CODE BON

        this.setVisible(false);

    }

    public void setData(Object[][] data) {
        tableau.setModel(new TableCalendrier(data, title));
    }
    
    @SuppressWarnings("unchecked")
	public void setList(String[]objectList) {
    	this.listeRecherhe.setModel( new DefaultComboBoxModel(objectList) );
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
