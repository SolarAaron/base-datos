/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2.model;

import slr.db2.lib.Pod;

/**
 *
 * @author aaron
 */
public class Usuario extends Pod{
    private static final String[] campos = {"Id", "Login", "Password", "Llave_sec"};

    public Usuario(){
        super(campos);
    }

    public Usuario(String[] args){
        super(campos, args);
    }

    @Override
    public String[] columns(){
        return campos;
    }

}
