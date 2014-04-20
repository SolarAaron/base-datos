/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.model;

import java.sql.Timestamp;
import slr.db2.lib.Pod;

/**
 *
 * @author Aaron
 *
 * Para accesar a bd utiliza slr.db2.lib.Dao asi:
 *
 * import slr.db2.Conector;
 * import slr.db2.lib.Dao;
 *
 * Dao<Reservacion_pod> = new Dao<>(Conector.conectar(), "reservacion", Reservacion_pod.class);
 */
public class Reservacion_pod extends Pod{
    private final static String[] campos = {"id", "id_usuario", "id_especialidad", "id_doctor", "id_clinica", "fecha"};

    public Reservacion_pod(){
        super(campos);
    }

    public Reservacion_pod(int id, int id_usuario, int id_especialidad, int id_doctor, int id_clinica, Timestamp fecha) throws Exception{
        super(campos);
        this.set("id", id);
        this.set("id_usuario", id_usuario);
        this.set("id_especialidad", id_especialidad);
        this.set("id_doctor", id_doctor);
        this.set("id_clinica", id_clinica);
        this.set("fecha", fecha);
    }

    @Override
    public String[] columns(){
        return campos;
    }
}
