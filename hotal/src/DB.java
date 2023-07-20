import java.sql.*;


public class DB {
    Connection con=null;
    java.sql.PreparedStatement pst;
    public static Connection dbconnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");  
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root","");
            return conn;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
    

