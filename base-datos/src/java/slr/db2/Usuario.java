/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2;

import slr.lib.db2.Pod;

/**
 *
 * @author aaron
 */
public class Usuario extends Pod{
    private static final String[] campos = {"Id", "Login", "Password"};

    Usuario(){
        super(campos);
    }

    Usuario(String[] args){
        super(campos, args);
    }

    @Override
    public String[] columns(){
        return campos;
    }

}
