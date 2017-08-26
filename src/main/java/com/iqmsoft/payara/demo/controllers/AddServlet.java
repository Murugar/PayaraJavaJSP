package com.iqmsoft.payara.demo.controllers;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import com.iqmsoft.payara.demo.models.MemEntity;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "CreationServlet", urlPatterns = {"/add-member"})
public class AddServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(AddServlet.class.getCanonicalName());

    private String name;
    private String dateOfBirth;
    private String dateOfHire;
    private String email;
    private String bio;
    private String memberId = "memberEntity";


    @EJB
    SessionBean bean;

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MemEntity member = new MemEntity();

        try {
            member.setName(request.getParameter("name"));
        } catch (ValidationException e) {
            log.log(Level.WARNING, "Name to be set was not valid.");
        }

        member.setDateOfBirth(request.getParameter("dateOfBirth"));
        member.setDateOfHire(request.getParameter("dateOfHire"));

        try {
            member.setEmail(request.getParameter("email"));
        } catch (ValidationException e) {
            log.log(Level.WARNING, "Email to be set was not valid.");
        }

        try {
            member.setBio(request.getParameter("bio"));
        } catch (ValidationException e) {
            log.log(Level.WARNING, "Bio to be set was not valid.");
        }


        bean.createTeamMember(member);

        //Sets the member entity and it's identifier as attributes of the request.
        request.setAttribute(memberId, member);

        //Loads the add response page.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/addresponse.jsp");
        dispatcher.forward(request,response);

    }


    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
