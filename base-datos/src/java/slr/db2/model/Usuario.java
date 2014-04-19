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

    public Usuario(int Id, String Login, String Password, String Llave_sec) throws Exception{
        super(campos);
        this.set("Id", Id);
        this.set("Login", Login);
        this.set("Password", Password);
        this.set("Llave_sec", Llave_sec);
    }

    @Override
    public String[] columns(){
        return campos;
    }

}
