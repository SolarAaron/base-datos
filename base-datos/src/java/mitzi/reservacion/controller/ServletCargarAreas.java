/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mitzi.reservacion.model.Area;
import mitzi.reservacion.model.Clinica;
import mitzi.reservacion.model.DAOArea;
import mitzi.reservacion.model.DAOClinica;
import mitzi.reservacion.model.DAOMedico;
import mitzi.reservacion.model.Medico;


/**
 *
 * @author mario
 */
public class ServletCargarAreas implements slr.db2.controller.IServletExtension {


    @Override
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out)
            throws ServletException, IOException{
        
        HttpSession session = request.getSession();
        try {
            session.setAttribute("resultado", (new DAOArea()).buscarAreas());    
        } catch (Exception ex) {
            session.setAttribute("resultado",new ArrayList<Area>());
        }
        
        try {
            session.setAttribute("medico", (new DAOMedico()).buscarMedico());
        } catch (Exception ex) {
            session.setAttribute("medico",new ArrayList<Medico>());
        }
        
        try {
            session.setAttribute("clinica", (new DAOClinica()).buscarClinica());
        } catch (Exception ex) {
            session.setAttribute("clinica",new ArrayList<Clinica>());
        }
        
        try{
            RequestDispatcher despachar=request.getRequestDispatcher("reservaciones.jsp");
            despachar.forward(request, response);           
        } catch (Exception ex) {
            System.err.println(ex.getStackTrace().toString());
            System.err.println("404 en reservaciones.jsp !?");
        }
    
    }   
}