/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slr.db2.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author aaron
 */
public class ServletLogout implements IServletExtension{

    @Override
    public void procesar(HttpServletRequest request, HttpServletResponse response, int method, PrintWriter out) throws ServletException, IOException {
        Cookie ckLog = null, ckSess = null;
        HttpSession ss = request.getSession(false);
        Cookie[] ckStore = request.getCookies();
        if(ckStore != null){
            for(Cookie i: ckStore){
                if(i.getName().equals("Login")){
                    ckLog = i;
                } else if(i.getName().equals("JSESSIONID")){
                    ckSess = i;
                }
            }
        }
        if(ckLog != null){
            ckLog.setMaxAge(0);
            response.addCookie(ckLog);
        }
        if(ss != null){
            ss.invalidate();
        }
    }

}
