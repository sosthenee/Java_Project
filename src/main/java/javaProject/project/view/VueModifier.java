/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import javaProject.project.model.Cours;
import javaProject.project.model.Enseignant;
import javaProject.project.model.EnumerableElement;
import javaProject.project.model.Groupe;
import javaProject.project.model.Promotion;
import javaProject.project.model.Salle;
import javaProject.project.model.Seance;
import javaProject.project.model.Site;
import javaProject.project.model.Type_cours;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */

public class VueModifier extends JFrame {

    public JComboBox day;
    public JComboBox hour_debut;
    public JComboBox minute_debut;
    public JComboBox hour_fin;
    public JComboBox minute_fin;
    public JComboBox matiere;
    public JButton button;
    public JList proffesseur;
    public JComboBox semaine;
    public JComboBox etat;
    public JList groupe;
    public JComboBox salle;
    public JComboBox type;
    
    public String buttonTitle;

    private JLabel messageLabe1;

    private JPanel panel = new JPanel(new GridLayout(15, 2));
    private JPanel panel2 = new JPanel();

    public ArrayList<Type_cours> typeList = new ArrayList<>();
    public ArrayList<Enseignant> professeurList = new ArrayList<>();
     public ArrayList<Salle> sallesList = new ArrayList<>();
    public ArrayList<Groupe> groupesList = new ArrayList<>();
    public ArrayList<Cours> matiereList = new ArrayList<>();
    
    
    DefaultListModel<String> model = new DefaultListModel<>();
    DefaultListModel<String> model2 = new DefaultListModel<>();

    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 700;
    
    public Seance currentSession;
  

    public VueModifier() {

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        createPanel();

        //add(panel);
    }

    private void createPanel() {

        String[] etats = {"0", "1", "2"};
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] hours = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
        String[] minutes = {"00", "30"};
        String[] semaines = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52"};

      
        day = new JComboBox(days);
        semaine = new JComboBox(semaines);

        hour_debut = new JComboBox(hours);
        minute_debut = new JComboBox(minutes);

        hour_fin = new JComboBox(hours);
        minute_fin = new JComboBox(minutes);

        proffesseur = new JList(model2);
        groupe = new JList(model);
        groupe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        matiere = new JComboBox();
        salle = new JComboBox();
        etat = new JComboBox(etats);
        type = new JComboBox();

        button = new JButton("-");

        panel.add(messageLabe1 = new JLabel("Choisir un jour"));
        panel.add(day);

        panel.add(messageLabe1 = new JLabel("Choisir une semaine"));
        panel.add(semaine);

        panel.add(messageLabe1 = new JLabel("Choisir une heure de debut"));
        panel.add(hour_debut);

        panel.add(messageLabe1 = new JLabel("Choisir une minute de debut"));
        panel.add(minute_debut);

        panel.add(messageLabe1 = new JLabel("Choisir une heure de fin"));
        panel.add(hour_fin);

        panel.add(messageLabe1 = new JLabel("Choisir une minute de fin"));
        panel.add(minute_fin);

        panel.add(messageLabe1 = new JLabel("Choisir une matiere"));
        panel.add(matiere);

        panel.add(messageLabe1 = new JLabel("Choisir un proffesseur"));
        panel.add(proffesseur);
           
        panel.add(messageLabe1 = new JLabel("Choisir un Etat"));
        panel.add(etat);
        
        panel.add(messageLabe1 = new JLabel("Choisir un groupe"));
        panel.add(groupe);

        panel.add(messageLabe1 = new JLabel("Choisir un Type de cours"));
        panel.add(type);

        panel.add(messageLabe1 = new JLabel("Choisir une salle"));
        panel.add(salle);

     

        panel2.add(button);
        
        JPanel RobertPaninter = new JPanel();
        RobertPaninter.setLayout(new BorderLayout());
       
        RobertPaninter.add(new JScrollPane(panel) , BorderLayout.CENTER);
        RobertPaninter.add(panel2, BorderLayout.SOUTH);
        
        this.getContentPane().add(RobertPaninter, BorderLayout.CENTER);
        this.setVisible(false);
    }

    public void setListEnseignant(ArrayList<EnumerableElement> professeurList) {
        proffesseur.removeAll();
        model2.removeAllElements();
        for (int i = 0; i < professeurList.size(); i++) {
            this.professeurList.add((Enseignant) professeurList.get(i));
            model2.addElement(professeurList.get(i).getNom());
        }
    }

    

    public void setListType_cours(ArrayList<EnumerableElement> type_coursList) {
        type.removeAllItems();
        for (int i = 0; i < type_coursList.size(); i++) {
            this.typeList.add((Type_cours) type_coursList.get(i));
            type.addItem(type_coursList.get(i).getNom());
        }
    }

    public void setListCours(ArrayList<EnumerableElement> coursList) {
        matiere.removeAllItems();
        for (int i = 0; i < coursList.size(); i++) {
            this.matiereList.add((Cours) coursList.get(i));
            matiere.addItem(coursList.get(i).getNom());
        }
    }

    public void setListGroupe(ArrayList<EnumerableElement> groupeList) {
        groupe.removeAll();
        model.removeAllElements();
        for (int i = 0; i < groupeList.size(); i++) {
            this.groupesList.add((Groupe) groupeList.get(i));
            model.addElement(groupeList.get(i).getNom());
        }
        //groupe = new JList<String>(model);
        //groupe.setListData(this.groupesList);
    }


    public void setListSalle(ArrayList<EnumerableElement> salleList) {
        salle.removeAllItems();
        for (int i = 0; i < salleList.size(); i++) {
            this.sallesList.add((Salle) salleList.get(i));
            salle.addItem(salleList.get(i).getNom());
        }
    }

    public void setCoordinates(String hour, String minute , String hourEnd, String minuteEnd,  String header , String semaines) {
      
        day.setEditable(true);
        day.setSelectedItem(header);
        day.setEditable(false);
        
        hour_debut.setEditable(true);
        hour_debut.setSelectedItem(hour);
        hour_debut.setEditable(false);

        hour_fin.setEditable(true);
        hour_fin.setSelectedItem(hourEnd);
        hour_fin.setEditable(false);
        
        minute_debut.setEditable(true);
        minute_debut.setSelectedItem(minute);
        minute_debut.setEditable(false);
        
        minute_fin.setEditable(true);
        minute_fin.setSelectedItem(minuteEnd);
        minute_fin.setEditable(false);
        
        semaine.setEditable(true);
        semaine.setSelectedItem(semaines);
        semaine.setEditable(false);

    }
    
    
      
      public void setButtonContent(String title) {
        this.button.setText(title);
    }

    public void setCurrentSession(Seance session) {
        this.currentSession = session;
    }
}
