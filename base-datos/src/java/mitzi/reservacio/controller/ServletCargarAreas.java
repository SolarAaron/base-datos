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
import mitzi.reservacion.model.DAOArea;
import mitzi.reservacion.model.Area;


/**
 *
 * @author mario
 */
public class ServletCargarAreas extends HttpServlet {
DAOArea areas=new DAOArea();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    
    try{
        
        ArrayList x=new ArrayList();
        for(Area a:DAOArea.buscarAreas()){
            
            Area ar=new Area(a.getId(),a.getNombre(),a.getDescripcion());
            x.add(ar);
            
        }
        
        
        try{
            
            request.setAttribute("resultado",x);
            
        } catch (Exception ex) {  }
        
        
    } catch (Exception ex) {Logger.getLogger(ServletCargarAreas.class.getName()).log(Level.SEVERE, null, ex);
  }
        
        
    }          
    

      
        
    }
