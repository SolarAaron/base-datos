/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slr.db2.Conector;
import slr.db2.lib.Dao;
import slr.db2.model.Usuario;
import slr.lib.CallbackWrapper;

/**
 *
 * @author aaron
 */
public class ServletLectura implements IServletExtension{

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out)
        throws ServletException, IOException{
        Double ps, al, res;
        response.setContentType("text/html;charset=UTF-8");
            int entra;
            CallbackWrapper<CallableStatement> cb = new CallbackWrapper<CallableStatement>(){
                @Override
                public void exec(CallableStatement arg){
                    try{
                        data.put("entra", arg.getInt(1));
                    } catch(SQLException ex){
                        Logger.getLogger(ServletLectura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            try(Dao<Usuario> dt = new Dao<>(Conector.conectar(), "usuario_1", Usuario.class)){
                String[] args = {"sip", "myLogin", "myPass"}, types = {"Integer Out", "Varchar2 In", "Varchar2 In"}, vals = {null, request.getParameter("nombre"), request.getParameter("pass")};
                System.out.println("Prueba...");
                dt.Procedure("autenticar_usuario", args, types, vals, cb);
                entra = (int)cb.data.get("entra");
                dt.Query(null);
                if(method == 1){
                    if(entra == 1){
                        out.println("<p>");
                        out.println("<b>YA!</b> Estas adentro " + request.getParameter("nombre") + "!<br>");
                        out.println("Vienes desde " + request.getRemoteAddr());
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
                    } else {
                        out.println("<b>NO entras chavo</b>");
                    }
                } else {
                    out.println("<i>Oye oye tranquilo estas usando GET</i>");
                }
            } catch(Exception ee){
                System.err.println("Algo anda mal...");
                System.err.println(ee.getStackTrace().toString());
            }
    }
}
