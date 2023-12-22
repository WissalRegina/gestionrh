package prjtrh;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    static Connection c ;
    public  Connect(){
    }
    public static Connection connecter(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projetdb";
            String user = "root" ;
            String password = "" ;
            c = DriverManager.getConnection(url,user,password);
        }catch(Exception e){
            System.out.println("la connexion a échoué");
            e.printStackTrace();
            
        }
        return c ;
    }


}
