<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<jsp:useBean id="memberEntity" scope="request" class="com.iqmsoft.payara.demo.models.MemEntity"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Member: <jsp:getProperty name="memberEntity" property="name" /></title>
    </head>
    <body>

    <h2>Member Profile</h2>

    <form name="Manage Member Form" action="manage-member" method="POST">

        <p>
        ID: <input type="hidden" name="id" value=<jsp:getProperty name="memberEntity" property="id" />> <jsp:getProperty name="memberEntity" property="id" /></input>

        <br>

        Name: <input type="text" name="name" value=<jsp:getProperty name="memberEntity" property="name" />></input>

        <br>

        Age: <jsp:getProperty name="memberEntity" property="age" />

        <br>

        For: <jsp:getProperty name="memberEntity" property="memberAge" />

        <br>

        Email: <input type="text" name="email" value=<jsp:getProperty name="memberEntity" property="email" />></input>
        </p>


        <p>
        Bio:<br> <textarea name="bio" maxlength="250" rows="5" cols="75"><jsp:getProperty name="memberEntity" property="bio"/></textarea>
        </p>

        <p>
        <input type="submit" value="Update Details">
        </p>
    </form>
    <form action="delete-member">
    <input type="hidden" name="id" value=<jsp:getProperty name="memberEntity" property="id" />></input>
    <input type="hidden" name="name" value=<jsp:getProperty name="memberEntity" property="name" />></input>
        <p>
        <input type="submit" value="Delete Member">
        </p>
    </form>
    <form action="${pageContext.request.contextPath}/index.jsp">
        <p>
        <input type="submit" value="Home">
        </p>
    </form>

    </body>
</html>