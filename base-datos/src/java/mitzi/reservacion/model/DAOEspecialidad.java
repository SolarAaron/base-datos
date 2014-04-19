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
import mitzi.reservacion.model.Conexion;
import mitzi.reservacion.model.Especialidad;

/**
 *
 * @author mario
 */
public class DAOEspecialidad {
     static Conexion con;
      public DAOEspecialidad(){
        con=new Conexion();
    }
    public static  ArrayList<Especialidad> buscarEspecialidad()throws Exception {
       ArrayList<Especialidad> especialidad=new ArrayList<Especialidad>();  
       //Primero nos conectamos a la base de datos
    Connection conexion= con.conectarse();
    //Crear un Statement de sql
     Statement st=  conexion.createStatement();
       ResultSet res=st.executeQuery("select * from especialidad");
       while(res.next()){
         int id_especialidad=  res.getInt(1);
         int id_area= res.getInt(2);
         String nombre=res.getString(3);
         String descripcion=res.getString(4);
         Especialidad esp=new Especialidad(id_especialidad,id_area,nombre,descripcion); 
         especialidad.add(esp);
       }
       return especialidad;
    }
}
