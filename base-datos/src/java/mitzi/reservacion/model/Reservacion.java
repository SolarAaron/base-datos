/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.model;

/**
 *
 * @author mario
 */
public class Reservacion {
  private int id;
   private int id_usuario;
   private int id_especialidad;
   private int id_doctor;
   private int id_clinica;
   private String fecha;

    public Reservacion(int id, int id_usuario, int id_especialidad, int id_doctor, int id_clinica, String fecha) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_especialidad = id_especialidad;
        this.id_doctor = id_doctor;
        this.id_clinica = id_clinica;
        this.fecha = fecha;
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_clinica() {
        return id_clinica;
    }

    public void setId_clinica(int id_clinica) {
        this.id_clinica = id_clinica;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
      
}
