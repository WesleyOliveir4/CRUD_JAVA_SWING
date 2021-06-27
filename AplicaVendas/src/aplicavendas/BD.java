/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicavendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author wesley
 */
public class BD {

    public Connection connection = null;


    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DBNAME = "aplicavendas";
    String parametros = "?useTimezone=true&serverTimezone=UTC";
    String serverName = "localhost";    //caminho do servidor do BD
    private final String URL = "jdbc:mysql://localhost:3306/" + DBNAME + parametros;
    private final String LOGIN = "root";
    private final String SENHA = "admin";


//método para conexão com banco de dados
 
    public boolean getConnection(){
   
      
  
    
        
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,LOGIN, SENHA);
            System.out.println("Conectou");
            return (true);
            
        }
        catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado "+ erro.toString());
            return (false);
        }
        catch (SQLException erro){
            System.out.println("Falha ao conectar "+ erro.toString());
            return(false);
        }
    }
    
    public void close(){
        try{
            connection.close();
            System.out.println("Desconectou ");
        }
        catch (SQLException erro){
    }
}
}


