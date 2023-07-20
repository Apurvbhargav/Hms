import java.awt.Font;

//import java.net.Socket;

//import com.mysql.cj.protocol.Resultset;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

//import java.sql.SQLException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

public class NewUserSignup extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4;
    JTextField t2, t3, t4;
    JButton b1;

    NewUserSignup() {
        // con = DB.dbconnect();
        this.setComponents();
        this.setSize(500, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void setComponents() {
        setLayout(null);
        l1 = new JLabel("New User SignUp ");
        l1.setBounds(130, 30, 300, 40);
        l1.setFont(new Font("Amatic SC", Font.BOLD, 30));
        add(l1);

        l2 = new JLabel("Name ");
        l2.setBounds(20, 110, 100, 30);
        l2.setFont(new Font("Amatic SC", Font.BOLD, 20));
        add(l2);

        t2 = new JTextField();
        t2.setBounds(130, 110, 200, 30);
        add(t2);

        l3 = new JLabel("Passward ");
        l3.setBounds(20, 150, 100, 30);
        l3.setFont(new Font("Amatic SC", Font.BOLD, 20));
        add(l3);

        t3 = new JTextField();
        t3.setBounds(130, 150, 200, 30);
        add(t3);

        l4 = new JLabel("Contact ");
        l4.setBounds(20, 190, 100, 30);
        l4.setFont(new Font("Amatic SC", Font.BOLD, 20));
        add(l4);

        t4 = new JTextField();
        t4.setBounds(130, 190, 200, 30);
        add(t4);

        b1 = new JButton("SAVE");
        b1.setBounds(150, 270, 100, 50);
        b1.setFont(new Font("Amatic SC", Font.BOLD, 20));
        add(b1);
        b1.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        String str = ae.getActionCommand();
        if (str.equals("SAVE")) {
            String name = t2.getText();
            String pass = t3.getText();
            String contact = t4.getText();
            String id = name + "@hotal";

            try {
                Connection con = DB.dbconnect();
                String query1 = "insert into login(id,pass,name,contact) values(?,?,?,?)";//(name,password,contact,id) values(?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query1);

                pst.setString(1, id);
                pst.setString(2, pass);
                pst.setString(3, name);
                pst.setString(4, contact);

                pst.executeUpdate();

                con.close();
                dispose();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Signup Successful" + "\nPlease Note Your ID-->" + id);
            new App2();
        }
    }

}
