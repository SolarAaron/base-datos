<%-- 
    Document   : reservaciones
    Created on : Apr 23, 2014, 7:19:46 PM
    Author     : mario
--%>

<%@page import="mitzi.reservacion.model.DAOMedico"%>
<%@page import="mitzi.reservacion.model.DAOClinica"%>
<%@page import="java.util.List"%>
<%@page import="mitzi.reservacion.model.DAOArea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <div id="reservar">
            <form action="cargar-areas" >
                Bienvenido usuario: <div id="username">${sessionScope.usuario}</div>
                <table><tr><td>Area</td>
                        <td><select name="area" id="area">
                                <c:forEach items="${sessionScope.resultado}" var="item">
                                <option value=${item.getId()}>${item.getNombre()}</option></c:forEach></select>
                        </td> 
                    </tr><tr>
                        <td>Clinicas</td>
                        <td><select name="clinica" id="clinica">
                            <c:forEach items="${sessionScope.clinicas}" var="item">
                                <option value=${item.getId()}>${item.getNombre()}</option></c:forEach></select>
                        </td>
                    </tr><tr>
                        <td>Especialista</td>
                        <td><select name="especialista" id="especialista">
                                <c:forEach items="${sessionScope.medicos}" var="item">
                                    <option value=${item.getId()}>${item.getNombre()}</option></c:forEach></select>
                        </td>
                    </tr><tr>
                        <td>Fecha</td>
                        <td><input type="text" data-role="date" id="fecha" />
                        </td>
                    </tr>
                </table>
                
                <input type="submit" value="Reservar" id="botonReservar">
            </form>
        </div>