import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.JFrame;

public class BookingDiary extends JFrame {

    BookingDiary() {
        fetch();

    }

    public static void main(String[] args) {
        new BookingDiary();
    }

    public void fetch() {

        try {

            Connection con = DB.dbconnect();
            String query = "SELECT * FROM newbooking";

            Statement stm = con.createStatement();
            ResultSet res = stm.executeQuery(query);

            String columns[] = { "name", "id", "address", "roomtype", "checkin", "checkout", "roomallocated" };

            String data[][] = new String[12][7];

            int i = 0;
            while (res.next()) {
                String name = res.getString("name");
                String id = res.getString("id");
                String address = res.getString("address");
                String roomtype = res.getString("roomtype");
                String checkin = res.getString("checkin");
                String checkout = res.getString("checkout");
                String roomallocated = res.getString("roomallocated");
                data[i][0] = name + "";
                data[i][1] = id;
                data[i][2] = address;
                data[i][3] = roomtype;
                data[i][4] = checkin;
                data[i][5] = checkout;
                data[i][6] = roomallocated;
                i++;
            }

            DefaultTableModel model = new DefaultTableModel(data, columns);
            JTable table = new JTable(model);
            table.setShowGrid(true);
            table.setShowVerticalLines(true);

            JScrollPane pane = new JScrollPane(table);

            JFrame f = new JFrame();

            JPanel panel = new JPanel();
            panel.add(pane);

            f.add(panel);

            f.setSize(800, 600);
            // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            f.setVisible(true);

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
