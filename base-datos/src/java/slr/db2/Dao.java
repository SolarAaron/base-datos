/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import slr.lib.ICallback;

/**
 *
 * @author T201
 * @param <T> tipo Pod
 */
public class Dao<T extends Pod> implements AutoCloseable {
    public LinkedList<T> data; // moddable results
    private Connection src;

    public Dao(Connection x) throws Exception{
        if(x == null){
            throw new Exception("No hay conexion a DB");
        }
        src = x;
        data = new LinkedList<>();
    }



    public void Proc(String proc, String[] args, String[] types, Object[] vals, ICallback<CallableStatement> cbck) throws SQLException{ // arg type := val -- pad with null
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
        try(CallableStatement stmt = src.prepareCall("{call "+proc+argTemp+"}")){
            for(int i = 0; i < args.length; i++){
                switch(types[i].toLowerCase()){
                    case "varchar in":
                    case "varchar2 in":
                    case "char in":
                    case "char2 in":
                        stmt.setString(i + 1, (String) vals[i]);
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
