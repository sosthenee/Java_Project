/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaProject.project.dao.SeanceDao;
import javaProject.project.view.Calendrier;
import javaProject.project.view.LookCalendrier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */
@Component
public class CalendrierController {

    @Autowired
    private SeanceDao repository;
    private Calendrier view;
    private LookCalendrier view2;
  
    public CalendrierController() {

    }

    public void writeData() {

        Object selected_hour = view.hour.getItemAt(view.hour.getSelectedIndex());
        Object selected_minute = view.minute.getItemAt(view.minute.getSelectedIndex());
        Object selected_day = view.day.getItemAt(view.day.getSelectedIndex());
        Object selected_month = view.month.getItemAt(view.month.getSelectedIndex());
        Object selected_year = view.year.getItemAt(view.year.getSelectedIndex());
        Object selected_prof = view.profession.getItemAt(view.profession.getSelectedIndex());

        String selected_date = selected_year + "-" + selected_month + "-" + selected_day;
        String selected_time = selected_hour + ":" + selected_minute + ":00";
        String selected_name = view.t1.getText();

    }

    public void initController(Calendrier view) {
        view.button.addActionListener(e -> writeData());
    }

    public void initControllerLook(LookCalendrier view2) {
        view2.button1.addActionListener(e -> writeData());
    }

    
}
