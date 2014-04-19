/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aaron
 */
public interface IServletExtension {
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out)
        throws ServletException, IOException;
}
