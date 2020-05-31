/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import org.springframework.stereotype.Component;

/**
 *
 * @author sosthene
 */
@Component
public class LookCalendrier extends JFrame {

    public JButton button1;
    public JButton button2;
    public JButton button3;
    public JPanel panel;
    public JTable Tab;

    public LookCalendrier() {

        this.setSize(300, 300);
        createPanel();
        setLayout(new GridLayout(6,16));
    }

    private void createPanel() {

        button1 = new JButton("Refresh");
        // button1.addActionListener(new tablo());

        Tab = new JTable();
        Tab.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"Name", "Patient", "Date", "Time", "Profession"}));

        panel = new JPanel();

        panel.add(button1);

        getContentPane().add(Tab.getTableHeader(), BorderLayout.SOUTH);
        getContentPane().add(Tab, BorderLayout.SOUTH);

       this.getContentPane().add(panel);
       this.setVisible(false);
    }
}
