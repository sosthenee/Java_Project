/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaProject.project.vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

 
public class Fenetre extends JFrame {
  private JFormattedTextField mail = new JFormattedTextField();
  private JPasswordField mdp = new JPasswordField();
  private JButton valider = new JButton ("Valider");
  public Fenetre(){
      this.setTitle("Box Layout");
    this.setSize(300, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
     valider.addActionListener(new BoutonListener());
    

    JPanel b1 = new JPanel();
    //On définit le layout en lui indiquant qu'il travaillera en ligne
    b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
    b1.add(new JLabel("Email"));
    b1.add(Box.createRigidArea(new Dimension(50,0)));
    b1.add(mail);

    JPanel b2 = new JPanel();
    //Idem pour cette ligne
    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
    b2.add(new JLabel("Mot de Passe"));
    b2.add(Box.createRigidArea(new Dimension(5,0)));
    b2.add(mdp);

    JPanel b3 = new JPanel();
    //Idem pour cette ligne
    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
    b3.add(valider);

    JPanel b4 = new JPanel();
    //On positionne maintenant ces trois lignes en colonne
    b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
    b4.add(b1);
    b4.add(b2);
    b4.add(b3);
		
    this.getContentPane().add(b4);
    this.setVisible(true);
  }
  class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.out.println("TEXT : Mail : " + mail.getText());
      System.out.println("TEXT : MdP : " + mdp.getText());
    }
  
}
  class StateListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
    }
  }
}