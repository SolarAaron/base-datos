/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mitzi.reservacio.controller;

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
import mitzi.reservacion.model.Area;
import mitzi.reservacion.model.DAOArea;
import mitzi.reservacion.model.DAOClinica;
import mitzi.reservacion.model.DAOMedico;


/**
 *
 * @author mario
 */
public class ServletCargarAreas extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        try {
            request.setAttribute("resultado",DAOArea.buscarAreas());
            
            
            
        } catch (Exception ex) {
            request.setAttribute("resultado","vacioo lipi");
           
        }
         try {
            request.setAttribute("medico",DAOMedico.buscarMedico());
            
            
            
        } catch (Exception ex) {
            request.setAttribute("medico","vacioo lipi");
           
        }
        try {
            request.setAttribute("clinica",DAOClinica.buscarClinica());
        } catch (Exception ex) {
            request.setAttribute("clinica","vacioo lipi");
        }
  try{
            RequestDispatcher despachar=request.getRequestDispatcher("reservaciones.jsp");
            despachar.forward(request, response);
            
        } catch (Exception ex) {  }
        
        
           
       
            
         
        
        
    }          
    

      
        
    }
