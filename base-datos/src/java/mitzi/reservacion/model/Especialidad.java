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
public class Especialidad {
    private int id_especialidad;
    private int id_area;
    private String nombre;
    private String descripcion;

    public Especialidad(int id_especialidad, int id_area, String nombre, String descripcion) {
        this.id_especialidad = id_especialidad;
        this.id_area = id_area;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public int getId_area() {
        return id_area;
    }

    public void setId_area(int id_area) {
        this.id_area = id_area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
