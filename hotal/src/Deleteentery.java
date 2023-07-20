import java.sql.Connection;
import java.sql.SQLException;

public class Deleteentery {
    
int id=544;

    String query2 = "DELETE FROM login WHERE id=" + id + " LIMIT 1";
            Connection con = DB.dbconnect();{
            try (java.sql.Statement stm = con.createStatement()) {
                stm.executeUpdate(query2);
            } catch (SQLException e1) {

                e1.printStackTrace();
            }
}
public static void main(String[] args) {
    new Deleteentery();
}
}
