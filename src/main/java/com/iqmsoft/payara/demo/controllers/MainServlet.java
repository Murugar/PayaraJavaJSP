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


@WebServlet(name = "ManagementServlet", urlPatterns = {"/manage-member"})
public class MainServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MainServlet.class.getCanonicalName());

    private Long Id;
    private String name;
    private String email;
    private String bio;
    private String memberId = "memberEntity";

    @EJB
    SessionBean bean;

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            log.log(Level.INFO, "Could not parse the Id to long, for parameter value: " + request.getParameter("id"));
        }

        name = request.getParameter("name");
        email = request.getParameter("email");
        bio = request.getParameter("bio");

        MemEntity member = bean.getTeamMemberById(Id);

        if (member.getName().equals(name) == false) {
            String previousName = member.getName();
            bean.updateTeamMemberName(Id, name);
            log.log(Level.INFO, "Updated the name of team member \"" + previousName + "\" to \"" + member.getName() + "\".");
        }
        System.out.println(email);
        if (member.getEmail().equals(email) == false) {
            String previousEmail = member.getEmail();
            bean.updateTeamMemberEmail(Id, email);
            log.log(Level.INFO, "Updated the email of team member \"" + previousEmail + "\" to \"" + member.getEmail() + "\".");
        }
        if (member.getBio().equals(bio) == false) {
            String previousBio = member.getBio();
            bean.updateTeamMemberBio(Id,bio);
            log.log(Level.INFO, "Updated the bio of team member \"" + previousBio + "\" to \"" + member.getBio() + "\".");
        }

        member = bean.getTeamMemberById(Id);


        //Set member object and its bean identifier as attributes of the http request.
        request.setAttribute(memberId,member);

        //Loads the manage member page with updated values.
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/managemember.jsp");
        dispatcher.forward(request, response);

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
