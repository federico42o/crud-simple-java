/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Conexion {
    Connection conectar = null;
    
    String user = "root";
    String password = "admin12345";

    
    String cadena = "jdbc:mysql://localhost:3306/registro";
    
    public Connection stablishConecction(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,user, password);
            //JOptionPane.showMessageDialog(null, "Conexion establecida");
            
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Error  : "+ e.toString());
        }
        return conectar;
    }
    
}
