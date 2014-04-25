/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import slr.db2.Conector;
import slr.db2.model.DaoUsuario;
import slr.db2.model.Usuario;
import slr.lib.CallbackWrapper;
import slr.lib.HashProcessor;

/**
 *
 * @author aaron
 */
public class ServletEntrada implements IServletExtension{

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param method
     * @param out
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
                    Logger.getLogger(ServletEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        try(DaoUsuario dt = new DaoUsuario(Conector.conectar(), "usuario_x", Usuario.class)){
            dt.Query("Where Login = '" + request.getParameter("nombre") + "'");
            if(dt.data.size() != 1){
                out.println("<p>");
                out.println("<b>No existe el usuario: " + request.getParameter("nombre") + "</b>");
                out.println("</p>");
            } else {
                String[] args = {"sip", "myLogin", "myPass"}, types = {"Integer Out", "Varchar2 In", "Varchar2 In"}, vals = {null, request.getParameter("nombre"), HashProcessor.hash(request.getParameter("pass") + dt.data.get(0).get("Llave_sec"), "SHA-256")};
                System.out.println("Prueba...");
                dt.Procedure("autenticar_usuariox", args, types, vals, cb);
                entra = (int)cb.data.get("entra");
                dt.Query(null);
                if(method == 1){
                    if(entra == 1){
                        Cookie ckLog = new Cookie("Login", vals[1]);
                        HttpSession ss = request.getSession();
                        ss.setAttribute("usuario", vals[1]);
                        ss.setMaxInactiveInterval(30 * 60);
                        ckLog.setMaxAge(30 * 60);
                        response.addCookie(ckLog);
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
                        out.println("<b>Acceso denegado: password incorrecto</b>");
                    }
                } else {
                    out.println("<i>Oye oye tranquilo estas usando GET</i>");
                }
            }
        } catch(Exception ee){
            System.err.println("Algo anda mal...");
            System.err.println(Arrays.toString(ee.getStackTrace()));
        }
    }
}
