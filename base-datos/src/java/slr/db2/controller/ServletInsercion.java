/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import slr.db2.Conector;
import slr.db2.model.DaoUsuario;
import slr.db2.model.Usuario;
import slr.lib.CallbackWrapper;
import slr.lib.HashProcessor;

/**
 *
 * @author aaron
 */
public class ServletInsercion implements IServletExtension{

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
            int nId;
            CallbackWrapper<CallableStatement> cb = new CallbackWrapper<CallableStatement>(){
                @Override
                public void exec(CallableStatement arg){
                    try{
                        data.put("nuevo", arg.getInt(1));
                    } catch(SQLException err){
                        Logger.getLogger(ServletInsercion.class.getName()).log(Level.SEVERE, null, err);
                    }
                }
            };
            try(DaoUsuario dt = new DaoUsuario(Conector.conectar(), "usuario_1", Usuario.class)){
                if(method == 0){
                    out.println("<b>Insertar con GET? Que locura!!</b><br>");
                } else {
                    String sal = HashProcessor.generateSalt(20);
                    String[] args = {"sip", "myLogin", "myPass", "mySal"}, types = {"Integer Out", "Varchar2 In", "Char2 In", "Char2 In"}, vals = {null, request.getParameter("login"), HashProcessor.hash(request.getParameter("pase") + sal, "SHA-256"), sal};
                    dt.Procedure("crear_ux", args, types, vals, cb);
                    nId = (int)cb.data.get("nuevo");
                    out.println("Insertado: " + vals[1] + "<br>");
                }
            } catch(Exception ee){
                if(method == 0){
                    out.println("<b>Insertar con GET? Que locura!!</b><br>");
                } else {
                    out.println("<b>No insertado: " + ee.getMessage() + "</b><br>");
                }
            }
    }
}
