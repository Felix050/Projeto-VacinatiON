package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
//import javax.swing.JOptionPane;

public class ConnectionFactory {  
    private static String usuario="root";
    private static String senha="vinicius12";
    private static String host="localhost";
    private static String porta="3306";
    private static String bd="vacination_teste1";
    
     public static Connection obterConexao () {
       try{
            Connection c = DriverManager.getConnection (
                    "jdbc:mysql://" + host + ":" + porta + "/" +
                    bd + "?useTimezone=true&serverTimezone=America/Sao_Paulo", usuario, senha);
            
            //JOptionPane.showMessageDialog(null, "Deu certo");
            return c;
        }
        catch(Exception e){
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Deu errado");
            return null;
        }
    }
}