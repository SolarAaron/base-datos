<%-- 
    Document   : reservaciones
    Created on : Apr 23, 2014, 7:19:46 PM
    Author     : mario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/themes/slr.css" rel="stylesheet" type="text/css"/>
        <link href="css/themes/jquery.mobile.icons.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery.mobile.structure-1.4.2.css" rel="stylesheet" type="text/css"/>
        <script src="jscript/jquery-1.11.0.js" type="text/javascript"></script>
        <script src="jscript/jquery.mobile-1.4.2.js" type="text/javascript"></script>
        <script src="jscript/Misc.js" type="text/javascript"></script>
    </head>
    <body>
        <div data-role="page" id="reservar">
            <div data-role="header">
                <h1>Reservaciones</h1>
            </div><div data-role="content">
                

                <form action="cargar-areas" >
                  
              
              
                <table><tr><td>Area</td>
                            <td><select name="area"><c:forEach items="${resultado}" var="item">
                                  <option value=${item.id}>${item.nombre}</option></c:forEach> --></select>
                            </td> 
                    </tr><tr>
                            <td>Clinicas</td>
                            <td><select name="clinica"><c:forEach items="${clinica}" var="item">
                                  <option value=${item.id}>${item.nombre}</option></c:forEach> --></select>
                            </td>
                    </tr><tr>
                            <td>Especialista</td>
                            <td><select name="Especialista"><c:forEach items="${medico}" var="item">
                                  <option value=${item.id}>${item.nombre}</option></c:forEach> --></select>
                            </td>
                    </tr> </table>   
                    
                    
                    <input type="submit" value="Reservar">
                      </form>
            </div>
            
        </div>
    </body>
</html>
