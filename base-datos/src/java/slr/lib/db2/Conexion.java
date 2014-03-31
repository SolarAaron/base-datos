/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.lib.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author T201
 */
public class Conexion {
    private String usr, pas;

    public Conexion(String user, String pass){
        usr= user;
        pas= pass;
    }

    public Connection conectar(){
        Connection ret;
        try{
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            Class.forName("oracle.jdbc.OracleDriver");
            ret = DriverManager.getConnection(url, usr, pas);
        } catch(ClassNotFoundException | SQLException ee){
            ee.printStackTrace(System.err);
            ret = null;
        }
        return ret;
    }
}
