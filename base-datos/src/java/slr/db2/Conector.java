/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2;

import java.sql.Connection;
import slr.lib.db2.Conexion;
import slr.lib.db2.DBType;

/**
 *
 * @author Aaron
 */
public class Conector{
    public static Connection conectar(){
        return new Conexion("*****", "*****", null).conectar(DBType.ORATHIN, "localhost:1521");
    }
}
