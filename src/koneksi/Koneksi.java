package koneksi;


import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suryadi
 */
public class Koneksi {
    private Connection con;
    private String url;
    private String username;
    private String password;

    public Koneksi() {
        url = "jdbc:mysql://localhost:3306/pa3";
        username = "root";
        password = "";
    }
    
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try{
            con = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }   
}
