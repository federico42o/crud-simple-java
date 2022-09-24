/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud2;

import config.Conexion;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Alta {
    
    String id;
    String nombres;
    String dni;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void insertClients(JTextField pNombres, JTextField pDni){
        
        setNombres(pNombres.getText());
        setDni(pDni.getText());
        
        Conexion objConexion = new Conexion();
        
        
        String query = ("INSERT INTO persona(dni, nombre) values (?,?)");
        
        try {
            CallableStatement cs = objConexion.stablishConecction().prepareCall(query);
            
            cs.setString(1, getNombres());
            cs.setString(2, getDni());
            cs.execute();
            
            
            JOptionPane.showMessageDialog(null,"Se inserto correctamente el cliente");
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Hubo un error: "+ e.toString());
           
        }
        
        
    }
    
    public void showClients (JTable pTbClients){
    
            Conexion objConexion = new Conexion();
            
            DefaultTableModel dtable = new DefaultTableModel();
            
            TableRowSorter<TableModel> sortTable = new TableRowSorter<> (dtable);
            
            pTbClients.setRowSorter(sortTable);
            
            
            String query = "";
            
                dtable.addColumn("ID");
                dtable.addColumn("DNI");
                dtable.addColumn("Nombres");
            
                 
                pTbClients.setModel(dtable);
                
                
                query = "Select * from persona";
                
                
                String[] dataClient = new String[3];
                Statement st;
                
                   
                try {
                    st = objConexion.stablishConecction().createStatement();
                    ResultSet rs = st.executeQuery(query);
                    
                    while (rs.next()){
                        dataClient[0] = rs.getString(1); // Id
                        dataClient[1] = rs.getString(2); // Nombre
                        dataClient[2] = rs.getString(3); // DNI
                        
                        dtable.addRow(dataClient);
                    }
                    pTbClients.setModel(dtable);
                    } catch (Exception e) {
                        
                        JOptionPane.showMessageDialog(null,"Ha ocurrido un error con los registros: " +  e.toString());
                    }
                        
                    
        
    }

        public void SelectClient(JTable pTbClient, JTextField pId, JTextField pName, JTextField pDni){
            
             try {
                int fila = pTbClient.getSelectedRow();
                if (fila >= 0) {
                    pId.setText((pTbClient.getValueAt(fila, 0).toString()));
                    pName.setText((pTbClient.getValueAt(fila, 1).toString()));
                    pDni.setText((pTbClient.getValueAt(fila, 2).toString()));
                    
                } 
                else {
                
                JOptionPane.showMessageDialog(null ,  "Fila no seleccionada.");
                
                        }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Ha ocurrido un error con la seleccion: " +  e.toString());
            }
             
            
            
        };
        
        public void DeleteClients(JTextField pId){
          setId(pId.getText());
        
            Conexion objConexion = new Conexion();
        
        
        String query = ("DELETE FROM persona WHERE id=(?)   ");
        
        try {
            CallableStatement cs = objConexion.stablishConecction().prepareCall(query);
            
            cs.setString(1, getId());
  
            cs.execute();
            
        
        } catch (Exception e) {
                        
                        JOptionPane.showMessageDialog(null,"Ha ocurrido un error con los registros: " +  e.toString());
                    };
    
    };
        

};