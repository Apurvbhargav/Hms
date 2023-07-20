import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 import javax.swing.JTextArea;
import javax.swing.JTextField;

 

public class Checkout extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField t1, t2;
    JButton b1, b2, b3;
    JTextArea ar;

    Checkout() {

        this.setComponents();
        this.setSize(750, 550);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    public void setComponents() {
        setTitle("Checkout Section");
        setLayout(null);
        l1 = new JLabel("Enter id  for checkout and bill");
        l1.setBounds(20, 30, 300, 30);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        add(l1);
        l2 = new JLabel("Total Amount");
        l2.setBounds(50, 380, 300, 30);
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        add(l2);

        t1 = new JTextField();
        t1.setBounds(50, 80, 200, 30);
        add(t1);
        t2 = new JTextField();
        t2.setBounds(50, 420, 200, 30);
        add(t2);

        b1 = new JButton("Get Details");
        b1.setBounds(75, 120, 100, 30);
        add(b1);

        b2 = new JButton("Checkout And Pay");
        b2.setBounds(155, 460, 150, 30);
        add(b2);
        b3 = new JButton("Generate Bill");
        b3.setBounds(365, 320, 150, 30);
        add(b3);

        ar = new JTextArea();
        ar.setBounds(50, 160, 300, 200);
        add(ar);
        ar.setFont(new Font("Serif", Font.BOLD, 20));

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

    }

    String checkout;
    String checkin;
    String roomtype;
    int money;
    String roomno;

    public void actionPerformed(ActionEvent e) {

        String str = e.getActionCommand();
        String id = t1.getText();

        if (str.equals("Get Details")) {
            

            try {

                Connection con = DB.dbconnect();

                java.sql.Statement stm = con.createStatement();

                String q = "Select * from newbooking where id='" + id + "'";
                ResultSet rs = stm.executeQuery(q);

                if (rs.next()) {
                    String str1 = "Name :" + rs.getString(1) + "\nRoom no.: " + rs.getString(9) + "\nRoom Type : "
                            + rs.getString(5) + "\nCheckin Date : " + rs.getString(7) + "\nCheckout Date :"
                            + rs.getString(8) + "\nContact :" + rs.getString(4);
                    ar.selectAll();
                    ar.replaceSelection("");
                    ar.append(str1);
                    checkout = rs.getString(8);
                    checkin = rs.getString(7);
                    roomtype = rs.getString(5);
                    roomno = rs.getString(9);
                    

                }
                con.close();

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
        int d1 = 0;
        int d2 = 0;
        if (str.equals("Generate Bill")) {

            char[] ch = checkin.toCharArray();
            char[] ch2 = checkout.toCharArray();

            for (int i = 0; i < 2; i++)
                d1 = d1 * 10 + (int) ch[i];
            for (int i = 0; i < 2; i++)
                d2 = d2 * 10 + (int) ch2[i];
            int days = d2 - d1;

            if (roomtype.equals("Normal")) {
                money = days * 600;
                t2.setText(String.valueOf(money));
            }

            else if (roomtype.equals("Suite")) {
                money = days * 1200;
                t2.setText(String.valueOf(money));
            } else if (roomtype.equals("King")) {
                money = days * 2000;
                t2.setText(String.valueOf(money));
            }

        }
        
        

        if (str.equals("Checkout And Pay"))

        {
            t1.setText("");
            t2.setText("");
            ar.selectAll();
            ar.replaceSelection("");
            JOptionPane.showMessageDialog(null, "CheckOut Successful ");

            String query2 = "DELETE FROM newbooking WHERE id=" + id + " LIMIT 1";
            Connection con = DB.dbconnect();
            try (java.sql.Statement stm = con.createStatement()) {
                stm.executeUpdate(query2);
                con.close();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
            String roomno2=roomno;
            String roomtype="";
            String status="";
            String price="";
            try {
                Connection con2 = DB.dbconnect();
            String q2 = "Select * from temproom where roomno='" + roomno2 + "'";
            java.sql.Statement stm2 = con.createStatement();
            ResultSet rs2 = stm2.executeQuery(q2);
            if (rs2.next()) {
                roomno2=rs2.getString(1);
                 roomtype=rs2.getString(2);
                 status=rs2.getString(3);
                 price=rs2.getString(4);
            }
             con2.close();
             Connection con4 = DB.dbconnect();
                String query1 = "insert into roomsdetails(roomno,roomtype,status,price) values(?,?,?,?)";
                PreparedStatement pst4 = con4.prepareStatement(query1);

                pst4.setString(1, roomno2);
                pst4.setString(2, roomtype);
                pst4.setString(3, status);
                pst4.setString(4, price);
                pst4.executeUpdate();
                 
                
            } catch (Exception e2) {
                 
            }
            try {
                 
                
            } catch (Exception e3) {
                 
            }
             
             
        
          

        }
    }

    

}
