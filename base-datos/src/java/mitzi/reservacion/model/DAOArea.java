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
public class DAOArea {
     static Conexion con;
      public DAOArea(){
        con=new Conexion();
    }
    public static  ArrayList<Area> buscarAreas()throws Exception {
       ArrayList<Area> a=new ArrayList<>();  
       //Primero nos conectamos a la base de datos
    Connection conexion= con.conectarse();
    //Crear un Statement de sql
     Statement st=  conexion.createStatement();
       ResultSet res=st.executeQuery("select * from area");
       while(res.next()){
         int id=  res.getInt(1);
         String nombre=res.getString(2);
         String descripcion=res.getString(3);
         Area ar=new Area(id,nombre,descripcion); 
         a.add(ar);
       }
       return a;
    }
}
