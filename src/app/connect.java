/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.sql.*;

/**
 *
 * @author Bendenia M'hamed
 */
public class connect {
    String urlPilot="com.mysql.jdbc.Driver";
    String urlBasededonnee="jdbc:mysql://localhost:3306/residence_manager";
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
    Connection con;
    public connect(){
    try{
    Class.forName(urlPilot);
    System.out.println("chargement du pilote réussie");
    
 }catch(ClassNotFoundException ex){
    System.out.println(ex);
}
    //conexion a la base de donnée
 try{
     
    con=DriverManager.getConnection(urlBasededonnee+unicode,"root","");
     System.out.println("connexion a bd réussie");
     
}catch (SQLException ex)  {
    System.out.println(ex);
    
} 
}
    public Connection obtenirconnexion(){
        return con;
    }
}
