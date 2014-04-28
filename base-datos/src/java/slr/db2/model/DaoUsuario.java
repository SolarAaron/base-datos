/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import slr.lib.CallbackWrapper;

/**
 *
 * @author jtorres
 */
public class DaoUsuario implements AutoCloseable{
    String table;
    public LinkedList<Usuario> data; // moddable results
    private Connection src;
    private Class<Usuario> clcon;

    public DaoUsuario(Connection x, String tab, Class<Usuario> type) throws Exception{
        if(x == null){
            throw new Exception("No hay conexion a DB");
        }
        src = x;
        table = tab;
        data = new LinkedList<>();
        clcon = type;
    }

    public void Query(String sql_extra){
        String[] cols;
        String spec = "";
        ResultSet res;

        try(Statement sta = src.createStatement()){
            cols = clcon.newInstance().columns();
            for(int i = 0; i < cols.length; i++){
                if(i != 0){
                    spec = spec + ", ";
                }
                spec = spec + cols[i];
            }
            System.out.println("Select " + spec + " from " + table + (sql_extra == null ? "" : " " + sql_extra));
            res = sta.executeQuery("Select " + spec + " from " + table + (sql_extra == null ? "" : " " + sql_extra));
            data.clear();
            while(res.next()){
                Usuario tmp = clcon.newInstance();
                for(String i: cols){
                    tmp.set(i, res.getObject(i));
                }
                data.add(tmp);
            }
        } catch(Exception ee){
            System.out.println("Ohh...");
            System.out.println(ee.getMessage());
        }
    }

    public void Procedure(String proc, String[] args, String[] types, Object[] vals, CallbackWrapper<CallableStatement> cbck) throws SQLException{ // arg type := val -- pad with null
        String argTemp = "";
        for(int i = 0; i < args.length; i++){
            if(i == 0){
                argTemp += "(";
            } else {
                argTemp += ", ";
            }
            argTemp += "?";
            if((i + 1) == args.length){
                argTemp += ")";
            }
        }
        System.out.println("{call " + proc + argTemp + "}");
        try(CallableStatement stmt = src.prepareCall("{call " + proc + argTemp + "}")){
            for(int i = 0; i < args.length; i++){
                switch(types[i].toLowerCase()){
                    case "varchar in":
                    case "varchar2 in":
                    case "char in":
                    case "char2 in":
                        stmt.setString(i + 1, (String)vals[i]);
                        break;
                    case "timestamp in":
                        stmt.setTimestamp(i + 1, (Timestamp)vals[i]);
                        break;
                    case "integer in":
                        stmt.setInt(i + 1, (int)vals[i]);
                        break;
                    case "integer out":
                        stmt.registerOutParameter(i + 1, java.sql.Types.INTEGER);
                        break;
                    case "boolean out":
                        stmt.registerOutParameter(i + 1, java.sql.Types.BOOLEAN);
                        break;
                }
            }
            stmt.execute();
            if(cbck != null){
                cbck.exec(stmt);
            }
            System.out.println("Operacion exitosa");
        }
    }

    @Override
    public void close() throws Exception {
        src.close();
    }

}
