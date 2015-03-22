<%-- 
    Document   : master
    Created on : 21-Mar-2015, 11:55:28 PM
    Author     : Tommy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huang Healthcare</title>
        <link rel="shortcut icon" href="src/img/logo.ico">
        <link rel="stylesheet" href="src/css/main.css" />
    </head>
    <body>
        <% Object user = session.getAttribute("UserSession"); %>
        <div class="header">
            <div class="logo"></div>
            <span class="title"><a href="index.jsp">Huang Healthcare</a></span>
            <span class="login">
                <%
                    if (user != null) {
                %>
                    <span style="color: #696969"><%=user%></span>
                    <span class="dot"></span>
                    <a href="LogoutServlet">Logout</a>
                <%
                    } else {
                %>
                    <a href="DBTestServlet">Register</a>
                    <span class="dot"></span>
                    <a href="login.jsp">Login</a>
                <%
                    }
                %>
            </span>
        </div>
    </body>
</html>