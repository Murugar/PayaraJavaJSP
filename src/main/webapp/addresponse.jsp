<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<jsp:useBean id="memberEntity" scope="request" class="com.iqmsoft.payara.demo.models.MemEntity"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Added</title>
    </head>
    <body>

    <p>
    Added Member: <jsp:getProperty name="memberEntity" property="name" />

    <br>

    With age: <jsp:getProperty name="memberEntity" property="age" />
    </p>

    Search for the team member <a href="${pageContext.request.contextPath}/index.jsp">here</a>

    </body>
</html>