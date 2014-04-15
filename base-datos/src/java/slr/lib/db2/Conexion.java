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
    private String usr, pas, dab;

    public Conexion(String user, String pass, String db){
        usr= user;
        pas= pass;
        dab = db;
    }

    public Connection conectar(DBType tt, String server){
        Connection ret;
        try{
            String url = null;
            switch(tt){
                case ORATHIN:
                    url = "jdbc:oracle:thin:@" + server + ":XE";
                    Class.forName("oracle.jdbc.OracleDriver");
                    break;
                case MYSQL:
                    url = "jdbc:mysql://" + server + "/" + dab;
                    Class.forName("com.mysql.jdbc.Driver");
                    break;
                case MSSQL:
                    url = "jdbc:microsoft:sqlserver://" + server + ";DatabaseName=" + dab;
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    break;
                case ODBC:
                    url = "jdbc:odbc:" + dab;
                    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                    break;
            }
            ret = DriverManager.getConnection(url, usr, pas);
        } catch(ClassNotFoundException | SQLException ee){
            ee.printStackTrace(System.err);
            ret = null;
        }
        return ret;
    }
}
