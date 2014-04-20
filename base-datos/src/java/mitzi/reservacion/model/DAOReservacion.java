/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author mario
 */
public class DAOReservacion{
    static Conexion con;

    public DAOReservacion(){
        con = new Conexion();
    }

    public void reservar(Reservacion r) throws Exception{
        Connection cone = con.conectarse();
        CallableStatement callate = cone.prepareCall("{call reservar(?,?,?,?,?,?)}");
        callate.setInt(1, r.getId());
        callate.setInt(2, r.getId_usuario());
        callate.setInt(3, r.getId_especialidad());
        callate.setInt(4, r.getId_doctor());
        callate.setInt(5, r.getId_clinica());
        callate.setTimestamp(6, r.getFecha());
        callate.executeUpdate();
        callate.close();
        cone.close();
        System.out.println("Se realizo la reservacion con exito");

    }

    public static ArrayList<Reservacion> buscarReservaciones() throws Exception{
        ArrayList<Reservacion> reserva = new ArrayList<Reservacion>();
        //Primero nos conectamos a la base de datos
        Connection conexion = con.conectarse();
        //Crear un Statement de sql
        Statement st = conexion.createStatement();
        ResultSet res = st.executeQuery("select * from reservacion order by id_reservacion");
        while(res.next()){
            int id = res.getInt(1);
            int usuario = res.getInt(2);
            int especialidad = res.getInt(3);
            int doctor = res.getInt(4);
            int clinica = res.getInt(5);
            Timestamp fecha = res.getTimestamp(6);
            Reservacion r = new Reservacion(id, usuario, especialidad, doctor, clinica, fecha);
            reserva.add(r);
        }

        return reserva;
    }
}
