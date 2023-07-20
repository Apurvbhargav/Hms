
import java.awt.Font;

//import java.net.Socket;

//import com.mysql.cj.protocol.Resultset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
//import java.awt.Image;
//import java.awt.Toolkit;

class App2 extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;

    JTextField t1;
    JPasswordField t2;
    JButton b1, b2;
    Connection con;

    App2() {
        con = DB.dbconnect();
        this.setComponents();
        this.setSize(750, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setComponents() {
        setTitle("Server Login");
        l1 = new JLabel(" ID");
        l2 = new JLabel(" Password");

        t2 = new JPasswordField();
        t1 = new JTextField();
        b1 = new JButton("Login");
        //Image img = Toolkit.getDefaultToolkit().getImage("img.png");

        setLayout(null);

        l1.setBounds(200, 80, 200, 20);

        t1.setBounds(300, 80, 150, 25);

        l2.setBounds(200, 130, 100, 20);

        t2.setBounds(300, 130, 150, 25);
        b1.setBounds(270, 180, 150, 25);

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        b1.setFont(new Font("Arial", Font.BOLD, 18));

        l3 = new JLabel("Welcome to Hotel login");
        add(l3);
        l3.setBounds(150, 30, 300, 20);

        l3.setFont(new Font("Arial", Font.BOLD, 20));

        b1.addActionListener((ActionListener) this);

        l4 = new JLabel("New User ");
        l4.setBounds(270, 250, 150, 25);
        ;
        l4.setFont(new Font("Arial", Font.BOLD, 20));

        b2 = new JButton("Signup");
        b2.setBounds(430, 250, 150, 50);
        ;
        b2.setFont(new Font("Arial", Font.BOLD, 20));
        add(b2);

        add(l4);

        add(l1);
        add(l2);
        add(t1);
        add(t2);
        add(b1);
        b2.addActionListener((ActionListener) this);

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();

        if (str.equals("Login")) {

            String id = t1.getText();
            char[] pass = t2.getPassword();

            User user = getAuth(id, pass);
            if (user != null) {
                new MainInterface();

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid details");
            }
        } else if (str.equals("Signup")) {
            new NewUserSignup();
            dispose();

        }

    }

    private User getAuth(String id, char[] pass) {
        User user = null;
        PreparedStatement p = null;
        String pass2 = new String(pass);
        try {
            String sql = "SELECT * FROM login WHERE id=? AND pass= ? ";
            p = con.prepareStatement(sql);
            p.setString(1, id);
            p.setString(2, pass2);
            
             
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                user = new User();
                user.pass = rs.getString("pass");
                user.id = rs.getString("id");
                 

            }
            p.close();
            con.close();

        } catch (Exception e) {

        }
        return user;

    }

}

public class App {
    public static void main(String args[]) {
        new App2();

    }
}