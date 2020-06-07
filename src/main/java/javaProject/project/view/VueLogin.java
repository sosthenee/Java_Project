
package javaProject.project.view;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class VueLogin extends JFrame {
  public JFormattedTextField mail = new JFormattedTextField();
  public JPasswordField mdp = new JPasswordField();
  public JButton valider = new JButton ("Valider");

  public VueLogin(){
    this.setTitle("Box Layout");
    this.setSize(300, 120);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);

    JPanel b1 = new JPanel();
    b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
    b1.add(new JLabel("Email"));
    b1.add(Box.createRigidArea(new Dimension(50,0)));
    b1.add(mail);

    JPanel b2 = new JPanel();
    b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
    b2.add(new JLabel("Mot de Passe"));
    b2.add(Box.createRigidArea(new Dimension(5,0)));
    b2.add(mdp);

    JPanel b3 = new JPanel();
    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
    b3.add(valider);
    
    
    JPanel b4 = new JPanel();
    b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
    b4.add(b1);
    b4.add(b2);
    b4.add(b3);

    this.getContentPane().add(b4);
    this.setVisible(true);
  }
  
}
