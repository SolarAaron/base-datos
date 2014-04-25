/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class DAOClinica {
       Conexion con;
      public DAOClinica(){
        con=new Conexion();
    }
    public  ArrayList<Clinica> buscarClinica()throws Exception {
       ArrayList<Clinica> cli=new ArrayList<Clinica>();  
       //Primero nos conectamos a la base de datos
    Connection conexion= con.conectarse();
    //Crear un Statement de sql
     Statement st=  conexion.createStatement();
       ResultSet res=st.executeQuery("select * from clinica");
       while(res.next()){
         int id=  res.getInt(1);
         String nombre=res.getString(2);
         String direccion=res.getString(3);
          String telefono=res.getString(4);
         Clinica c=new Clinica(id,nombre,direccion,telefono); 
         cli.add(c);
       }
       return cli;
    }
}
