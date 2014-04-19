/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author campitos
 */
public class TestTodo {
    public static void main(String...x)throws Exception{
        System.out.println("probando usuario");
        
       for( Area u:DAOArea.buscarAreas()  ){
           System.out.println(u.getNombre()+u.getDescripcion()+u.getId());
       }
       
    }
}
