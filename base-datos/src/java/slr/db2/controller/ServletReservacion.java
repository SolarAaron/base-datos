/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mitzi.reservacion.model.DAOReservacion;
import mitzi.reservacion.model.Reservacion;

/**
 *
 * @author aaron
 */
public class ServletReservacion implements IServletExtension{

    /**
     *
     * @param request
     * @param response
     * @param method
     * @param out
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out) throws ServletException, IOException {
        DAOReservacion acc = new DAOReservacion();
        Reservacion inn = new Reservacion(0, Integer.parseInt(request.getParameter("IdUs")), Integer.parseInt(request.getParameter("IdEsp")), Integer.parseInt(request.getParameter("IdDoc")), Integer.parseInt(request.getParameter("IdClin")), new Timestamp(Long.parseLong(request.getParameter("tsdate"))));
        try {
            acc.reservar(inn);
            out.println("Reservacion exitosa");
        } catch (Exception ex) {
            Logger.getLogger(ServletReservacion.class.getName()).log(Level.SEVERE, null, ex);
            out.println("Fallo en reservacion");
        }
    }
    
}
