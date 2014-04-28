/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slr.db2.Conector;
import slr.db2.model.DaoUsuario;
import slr.db2.model.Usuario;

/**
 *
 * @author aaron
 */
public class ServletRegistro implements IServletExtension{

    @Override
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out) throws ServletException, IOException {
        try(DaoUsuario dt = new DaoUsuario(Conector.conectar(), "usuario_x", Usuario.class)){
            dt.Query(null);
            out.println("<br>Tenemos " + dt.data.size() + " usuarios:<br>");
            for(Usuario i: dt.data){
                out.println(i.get("Login") + "<br>");
            }
            out.println("<div id=\"inserciones\"></div>");
            out.println("<br>Insertar usuarios:<br>");
            out.println("<label for:=\"nuinput\">Login:</label><input type=\"text\" name=\"nuinput\" id=\"nombre\"/>");
            out.println("<label for:=\"npinput\">Password:</label><input type=\"password\" name=\"npinput\" id=\"pwd\"/>");
            out.println("<a id=\"insertar\" class=\"ui-btn ui-icon-plus ui-btn-icon-left\">Insertar</a>");
            out.println("</p>");
        } catch(Exception e){
            e.printStackTrace(System.err);
        }
    }

}
