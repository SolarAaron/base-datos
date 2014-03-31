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
import slr.lib.ICallback;
import slr.lib.db2.Conexion;
import slr.lib.db2.Dao;
import slr.lib.db2.Usuario;

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

        try (PrintWriter out = response.getWriter()){
            /* TODO output your page here. You may use following sample code. */
            int entra;
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
            try (Dao<Usuario> dt = new Dao<>(new Conexion("*****", "*****").conectar(), "usuario_1", Usuario.class)){
                String[] args = {"sip", "myLogin", "myPass"}, types = {"Integer Out", "Varchar2 In", "Varchar2 In"}, vals = {null, request.getParameter("nombre"), request.getParameter("pass")};
                System.out.println("Prueba...");
                dt.Proc("autenticar_usuario", args, types, vals, cb);
                entra = (int) cb.data.get(0);
                dt.qry(null);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Entrada basica</title>");
                out.println("<link href=\"css/cascade.css\" type=\"text/css\" rel=\"stylesheet\">");
                out.println("</head>");
                out.println("<body>");
                if(entra == 1){
                    out.println("<h1>YA!</h1> Estas adentro " + request.getParameter("nombre") + "!<br>");
                    out.println("Checate esta: vienes desde " + request.getRemoteAddr());
                    out.println("<br><br>Por ser tu te muestro los usuarios registrados pero shhhh!!!");
                    out.println("<br>Tenemos " + dt.data.size() + " usuarios:<br>");
                    for(Usuario i: dt.data){
                        out.println(i.get("Login") + "<br>");
                    }
                } else {
                    out.println("NO entras chavo");
                }
                out.println("</body>");
                out.println("</html>");
            }
        } catch (Exception ee){
            System.out.println("Algo anda muy mal...");
            System.out.println(ee.getMessage());
            for(StackTraceElement i: ee.getStackTrace()){
                System.out.println(i);
            }
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
