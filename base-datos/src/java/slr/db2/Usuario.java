/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2;

/**
 *
 * @author aaron
 */
public class Usuario extends Pod{
    private static final String[] campos = {"Id", "Login", "Password"};

    Usuario(){
        super(campos);
    }

}
