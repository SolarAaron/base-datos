/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.contr;

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
import slr.db2.*;
import slr.lib.ICallback;

/**
 *
 * @author Aaron
 */
public class ServletRespuesta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            /* TODO output your page here. You may use following sample code. */
            int entra = 0;
            ICallback<CallableStatement> cb = new ICallback<CallableStatement>(){
                    @Override
                    public void exec(CallableStatement arg) {
                    try {
                        data.add(arg.getInt(1));
                    } catch (SQLException ex) {
                        Logger.getLogger(ServletRespuesta.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }

            };
            try (Dao<Usuario> dt = new Dao<>(new Conexion().conectar())){
            String[] args = {"sip", "myLogin", "myPass"}, types = {"Integer Out", "Varchar2 In", "Varchar2 In"}, vals = {null, request.getParameter("nombre"), request.getParameter("pass")};
            System.out.println("Prueba...");
            dt.Proc("autenticar_usuario", args, types, vals, cb);
            entra = (int) cb.data.get(0);
            } catch (Exception ee){
                System.out.println("Algo anda muy mal...");
                System.out.println(ee.getMessage());
                for(StackTraceElement i: ee.getStackTrace()){
                    System.out.println(i);
                }
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            if(entra == 1){
                out.println("<h1>YA!</h1> estas adentro " + request.getParameter("nombre") + "!<br>");
                out.println("Checate esta: vienes desde " + request.getRemoteAddr());
            } else {
                out.println("NO entras chavo");
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
