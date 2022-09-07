import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    Connection connection;

    public DB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/planets","root", "");
        }catch(Exception e){

        }
    }
}
