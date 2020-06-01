/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */
@Component
public class Calendrier extends JFrame {

    public JComboBox day;
    public JComboBox month;
    public JComboBox year;
    public JComboBox hour;
    public JComboBox minute;
    public JComboBox profession;
    public JButton button;

    private JLabel messageLabel;
    private JPanel panel;
    public TextField t1;

    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> professionList = new ArrayList<>();

    private final int WINDOW_WIDTH = 200;   // Window width
    private final int WINDOW_HEIGHT = 200;  // Window height

    public Calendrier() {

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        createPanel();

        //add(panel);
    }

    private void createPanel() {
        int j = professionList.size();
        profession = new JComboBox();
        for (int i = 0; i <= j - 1; i++) {
            profession.addItem(professionList.get(i));
        }
        System.out.println("le cadfgggggggatch ");

        messageLabel = new JLabel("Select a Date");
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        String[] years = {"2020", "2021"};
        String[] hours = {"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", };
        String[] minutes = {"00", "30"};

        t1 = new TextField("your name", 20);

        month = new JComboBox(months);
        day = new JComboBox(days);
        year = new JComboBox(years);
        hour = new JComboBox(hours);
        minute = new JComboBox(minutes);

        button = new JButton("take an appointment");

        //button.addActionListener(new writeData());
        panel = new JPanel(new GridLayout(8, 1));
        panel.add(day);
        panel.add(month);
        panel.add(year);
        panel.add(hour);
        panel.add(minute);
        panel.add(profession);
        panel.add(t1);
        panel.add(button);

        //add(panel);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
}
