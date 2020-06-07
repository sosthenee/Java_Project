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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static util.cst.getCalendarBlankData;

/**
 *
 * @author Oscar
 */
public class VueCalendrier extends JFrame {

    String title[] = {"Heure", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public JTable tableau;
    private JMenuBar navigation = new JMenuBar();
    public JButton Report = new JButton("Reporting");
    private JButton Cours = new JButton("Cours");
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
    

    private String selectedSemaine;

    public String getSelectedSemaine() {
        return selectedSemaine;
    }

    public void setSelectedSemaine(String selectedSemaine) {
        this.selectedSemaine = selectedSemaine;
    }



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

        this.navigation.add(Cours);
        this.navigation.add(Report);

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

        Object[][] data = getCalendarBlankData();

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
       
        
         ActionListener testListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String s = (String) ComboAff.getSelectedItem();

                switch (s) {
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
        this.getContentPane().add(navbar, BorderLayout.NORTH);
        this.getContentPane().add((Onglets), BorderLayout.CENTER); 

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

        if (color.contains("Maths")) {
            couleur = Color.red;
        }
        if (color.contains("Physique")) {
            couleur = Color.green;
        }
        if (color.contains("Electronique")) {
            couleur = Color.orange;
        }
        if (color.contains("Reseau")) {
            couleur = Color.pink;
        }
        if (color.contains("Informatique")) {
            couleur = Color.cyan;
        }

        return couleur;
    }

    
}
