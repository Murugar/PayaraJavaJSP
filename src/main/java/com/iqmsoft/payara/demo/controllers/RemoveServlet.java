package com.iqmsoft.payara.demo.controllers;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iqmsoft.payara.demo.models.MemEntity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "DeletionServlet", urlPatterns = {"/delete-member"})
public class RemoveServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(RemoveServlet.class.getCanonicalName());

    private Long Id;
    private String name;

    @EJB
    SessionBean bean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        name = request.getParameter("name");
        String responseMessage;
        try {
            Id = Long.parseLong(request.getParameter("id"));
            bean.removeTeamMember(Id);
            responseMessage = "Successfully deleted member \"" + name + "\" with ID \"" + Id + "\".";
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Could not parse id: \"" + request.getParameter("id") + "\" and so could not delete member");
            responseMessage = "Could not delete member \"" + name + "\" with ID \"" + request.getParameter("id") + "\".";
        }
        request.setAttribute("responseMessage", responseMessage);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles HTTP <code>POST</code> method.
     * @param request - servlet request
     * @param response - servlet response
     * @throws ServletException - if a servlet error occurs
     * @throws IOException - if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
