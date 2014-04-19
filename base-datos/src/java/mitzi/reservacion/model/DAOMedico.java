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
public class DAOMedico {
    
          static Conexion con;
      public DAOMedico(){
        con=new Conexion();
    }
    public static  ArrayList<Medico> buscarMedico()throws Exception {
       ArrayList<Medico> med=new ArrayList<Medico>();  
       //Primero nos conectamos a la base de datos
    Connection conexion= con.conectarse();
    //Crear un Statement de sql
     Statement st=  conexion.createStatement();
       ResultSet res=st.executeQuery("select * from medico");
       while(res.next()){
         int id=res.getInt(1);
         String nombre=res.getString(2);
         String apellido=res.getString(3);
          int especialidad=res.getInt(4);
          int clinica=res.getInt(5);
            Medico me=new Medico(id,nombre,apellido,especialidad,clinica); 
         med.add(me);
       }
       return med;
    }
}
