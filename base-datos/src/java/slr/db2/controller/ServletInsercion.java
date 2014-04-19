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
import slr.db2.model.Usuario;
import slr.lib.CallbackWrapper;
import slr.db2.lib.Dao;

/**
 *
 * @author aaron
 */
public class ServletInsercion extends HttpServlet{

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int method)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = response.getWriter()){
            int nId;
            CallbackWrapper<CallableStatement> cb = new CallbackWrapper<CallableStatement>(){

                @Override
                public void exec(CallableStatement arg){
                    try{
                        data.put("nuevo", arg.getInt(1));
                        data.put("usuario", arg.getString(2));
                    } catch(SQLException err){
                        Logger.getLogger(ServletInsercion.class.getName()).log(Level.SEVERE, null, err);
                    }
                }
            };
            try(Dao<Usuario> dt = new Dao<>(Conector.conectar(), "usuario_1", Usuario.class)){
                if(method == 0){
                    out.println("<b>Insertar con GET? Que locura!!</b><br>");
                } else {
                    String[] args = {"sip", "myLogin", "myPass"}, types = {"Integer Out", "Varchar2 In", "Varchar2 In"}, vals = {null, request.getParameter("login"), request.getParameter("pase")};
                    dt.Procedure("crear_usuario", args, types, vals, cb);
                    nId = (int)cb.data.get("nuevo");
                    out.println("Insertado: " + cb.data.get("usuario") + "<br>");
                }
            } catch(SQLException ee){
                if(method == 0){
                    out.println("<b>Insertar con GET? Que locura!!</b><br>");
                } else {
                    out.println("<b>No insertado: " + ee.getMessage() + "</b><br>");
                }
            }
        } catch(Exception ee){
            System.out.println("Algo anda muy mal...");
            System.out.println(ee.getMessage());
            for(StackTraceElement i: ee.getStackTrace()){
                System.out.println(i);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        processRequest(request, response, 0);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        processRequest(request, response, 1);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo(){
        return "Short description";
    }// </editor-fold>
}
