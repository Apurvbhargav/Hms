import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

public class NewBooking extends JFrame implements ActionListener {

    NewBooking() {

        this.setComponents();
        this.setSize(900, 550);
        this.setVisible(true);
         

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9;
    JButton b1;
    JTextArea area;

    void setComponents() {
        l1 = new JLabel("NAME");
        l2 = new JLabel("ID");
        l3 = new JLabel("ADDRESS");
        l4 = new JLabel("CONTACT");
        l5 = new JLabel("ROOM TYPE");
        l6 = new JLabel("TOTAL GUESTS");
        l7 = new JLabel("CHECKIN DATE");
        l8 = new JLabel("CHECKOUT DATE");

        l9 = new JLabel("ROOM NO");

        setLayout(null);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);
        add(l9);

        l1.setBounds(50, 30, 200, 50);
        l2.setBounds(50, 60, 200, 50);
        l3.setBounds(50, 92, 200, 50);
        l4.setBounds(50, 127, 200, 50);
        l5.setBounds(50, 161, 200, 50);
        l6.setBounds(50, 195, 200, 50);
        l7.setBounds(50, 230, 200, 50);
        l8.setBounds(50, 265, 200, 50);
        l9.setBounds(50, 300, 200, 50);

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t7 = new JTextField();
        t8 = new JTextField();
        t9 = new JTextField();

        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l3.setFont(new Font("Serif", Font.BOLD, 20));
        l4.setFont(new Font("Serif", Font.BOLD, 20));
        l5.setFont(new Font("Serif", Font.BOLD, 20));
        l6.setFont(new Font("Serif", Font.BOLD, 20));
        l7.setFont(new Font("Serif", Font.BOLD, 20));
        l8.setFont(new Font("Serif", Font.BOLD, 20));
        l9.setFont(new Font("Serif", Font.BOLD, 20));

        t1.setBounds(250, 30, 200, 30);
        t2.setBounds(250, 65, 200, 30);
        t3.setBounds(250, 100, 200, 30);
        t4.setBounds(250, 135, 200, 30);
        t5.setBounds(250, 170, 200, 30);
        t6.setBounds(250, 205, 200, 30);
        t7.setBounds(250, 240, 200, 30);
        t8.setBounds(250, 275, 200, 30);
        t9.setBounds(250, 310, 200, 30);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);
        add(t7);
        add(t8);
        add(t9);

        b1 = new JButton("BOOK");
        b1.setBounds(100, 400, 100, 50);
        b1.setFont(new Font("Arial", Font.BOLD, 18));
        add(b1);

        area=new JTextArea("Rooms Available\nRoom no.    Room_Type   Status    Price");
        area.setBounds(500,50,350,400);
        area.setFont(new Font("Arial", Font.BOLD, 18));

        area.setEditable(false);
        JScrollPane scrollableTextArea = new JScrollPane(area);  
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        add(scrollableTextArea);  
        add(area);
         //public void show(){

        try {

            Connection con = DB.dbconnect();

            java.sql.Statement stm = con.createStatement();

            String q = "Select * from roomsdetails ; ";// String query = "select * from person ;";
            ResultSet rs = stm.executeQuery(q);

            while (rs.next()) { 
                String str1 = " \n  "+ rs.getString(1) + "                  "  + rs.getString(2) + "      "
                        + rs.getString(3) + "     " + rs.getString(4) +"\n";
                         
                
                area.append(str1);
                 
            }
            con.close();

        } catch (SQLException e1) {
             
            e1.printStackTrace();
        }

    //}
        b1.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {

        String str = ae.getActionCommand();
        if (str.equals("BOOK")) {
            String name = t1.getText();
            String id = t2.getText();
            String address = t3.getText();
            String contact = t4.getText();
            String roomtype = t5.getText();
            String totalguests = t6.getText();
            String checkin = t7.getText();
            String checkout = t8.getText();
            String roomallocated = t9.getText();
             

            
            try {
                Connection con = DB.dbconnect();
                String query1 = "insert into newbooking(name,id,address,contact,roomtype,totalguests,checkin,checkout,roomallocated) values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query1);

                pst.setString(1, name);
                pst.setString(2, id);
                pst.setString(3, address);
                pst.setString(4, contact);
                pst.setString(5, roomtype);
                pst.setString(6, totalguests);
                pst.setString(7, checkin);
                pst.setString(8, checkout);
                pst.setString(9, roomallocated);
                pst.executeUpdate();
                con.close();
            
                    String roomno2=roomallocated;
                    String roomtype2=roomtype;
                    String status2="Available";
                    int price2;

                     
                    Connection con3 = DB.dbconnect();
                    String query3 = "insert into temproom(roomno,roomtype,status,price) values(?,?,?,?)";
    PreparedStatement pst3 = con3.prepareStatement(query3);
    pst3.setString(1, roomno2);
    pst3.setString (2, roomtype2);
    pst3.setString(3, status2);
    if (roomtype.equals("Normal")) {
        price2=600;
    
    pst3.setInt(4, price2);}
    else if (roomtype.equals("Suite")) {
        price2=1200;
    
    pst3.setInt(4, price2);
    }
    else if (roomtype.equals("King")) {
        price2=2000;
    
    pst3.setInt(4, price2);
    }
     
    pst3.executeUpdate();
                    

             con3.close();
               String query2 = "DELETE FROM roomsdetails WHERE roomno=" + roomallocated + " LIMIT 1";
               Connection con2 = DB.dbconnect();
               try (java.sql.Statement stm = con2.createStatement()) {
                   stm.executeUpdate(query2);
               } catch (SQLException e1) {
   
                   e1.printStackTrace();
               }
                dispose();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "DATA is saved");

        }

    }

   /* public void show(){

        try {

            Connection con = DB.dbconnect();

            java.sql.Statement stm = con.createStatement();

            String q = "Select * from roomsdetails ; ";// String query = "select * from person ;";
            ResultSet rs = stm.executeQuery(q);

            if (rs.next()) {
                String str1 = "" + rs.getString(1) + ""  + rs.getString(2) + ""
                        + rs.getString(3) + " " + rs.getString(4) +"\n";
                         
                
                area.append(str1);
                 
            }
            con.close();

        } catch (SQLException e1) {
             
            e1.printStackTrace();
        }

    }*/
    public static void main(String[] args) {
        new NewBooking();
    }

}
