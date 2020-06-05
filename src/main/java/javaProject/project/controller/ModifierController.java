/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaProject.project.dao.CoursDao;
import javaProject.project.dao.EnseignantDao;
import javaProject.project.dao.GroupeDao;
import javaProject.project.dao.PromotionDao;
import javaProject.project.dao.SeanceDao;
import javaProject.project.dao.TypeCoursDao;
import javaProject.project.model.Cours;
import javaProject.project.model.EnumerableElement;
import javaProject.project.model.Seance;
import javaProject.project.model.Type_cours;
import javaProject.project.view.VueCalendrier;
import javaProject.project.view.VueLogin;
import javaProject.project.view.VueModifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.math.*;
import java.util.Locale;
import javaProject.project.model.Enseignant;
import javaProject.project.model.Groupe;
import javaProject.project.model.Salle;
import javaProject.project.model.Site;
import javax.transaction.Transactional;
/**
 *
 * @author sosthene
 */
@Component
public class ModifierController {

    @Autowired
    SeanceDao seanceDao;
    
  
    public void writeData(VueModifier view3) throws ParseException {

        Object selected_hour_debut = view3.hour_debut.getItemAt(view3.hour_debut.getSelectedIndex());
        Object selected_minute_debut = view3.minute_debut.getItemAt(view3.minute_debut.getSelectedIndex());

        int selected_hour_fin = Integer.parseInt((String) view3.hour_fin.getItemAt(view3.hour_fin.getSelectedIndex()));
        int selected_minute_fin = Integer.parseInt((String) view3.minute_fin.getItemAt(view3.minute_fin.getSelectedIndex()));

        Object selected_day = view3.day.getItemAt(view3.day.getSelectedIndex());
        Object selected_month = view3.month.getItemAt(view3.month.getSelectedIndex());
        Object selected_year = view3.year.getItemAt(view3.year.getSelectedIndex());

        Cours selected_matiere = view3.matiereList.get(view3.matiere.getSelectedIndex());
        Type_cours selected_type = view3.typeList.get(view3.type.getSelectedIndex());
        

        String day = String.valueOf(selected_day);
        day = day.substring(0,3);
         
        String month = String.valueOf(selected_month);
        month = month.substring(0,3);
         
       int selected_semaine = Integer.parseInt((String) view3.semaine.getItemAt(view3.semaine.getSelectedIndex()));
      // int selected_promotion = Integer.parseInt((String) view3.promotion.(view3.promotion.getSelectedIndex()));
      
        ArrayList<Groupe> groupes = new ArrayList<>();
        int[] selected_groupe = view3.groupe.getSelectedIndices();
        for (int i = 0; i < selected_groupe.length; i++) {
            groupes.add(view3.groupesList.get(selected_groupe[i]));
        }
        
        ArrayList<Enseignant> enseignants = new ArrayList<>();
        int[] selected_enseignant = view3.groupe.getSelectedIndices();
        for (int i = 0; i < selected_enseignant.length; i++) {
            enseignants.add(view3.professeurList.get(selected_enseignant[i]));
        }
        
        ArrayList<Salle> salles = new ArrayList<>();
        salles.add(view3.sallesList.get(view3.salle.getSelectedIndex()));
        
        
        Site selected_site = view3.sitesList.get(view3.site.getSelectedIndex());

        String selected_date = (String)   day+ " " +month + " 18";
        String selected_time_start = selected_hour_debut + ":" + selected_minute_debut + ":00";
        
        String dateTime = selected_date + " " + selected_time_start + " UTC "+selected_year;
        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
        Date date = parser.parse(dateTime);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);

        System.out.println(date);

        Seance s  = new Seance( date , 1 , selected_hour_fin , selected_minute_fin ,selected_semaine,  selected_matiere ,  selected_type, groupes , salles ,enseignants);
        seanceDao.save(s);
    }
   


    

    public void initController(VueModifier view3) {
        view3.button.addActionListener(e -> {
            try {
                //injectData(elts, view3);
                writeData(view3);
                view3.setVisible(false);
                                
            } catch (ParseException ex) {
                Logger.getLogger(ModifierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
