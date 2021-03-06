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
        <% bean.User user = (bean.User)session.getAttribute("UserSession"); %>
        <% String query = "";
           if (user != null) {
               query = "?redirect";
           }
        %>
        <div class="header">
            <div class="logo"></div>
            <span class="title"><a href="index.jsp<%=query%>">Huang Healthcare</a></span>
            <span style="float: right;">
                <%
                    if (user != null) {
                        
                %>
                    <span style="color: #696969"><%=user.getAlias()%></span>
                    <div class="dot"></div>
                    <button class="inverted-button header" onclick="document.location.href='Logout'">Logout</button>
                <%
                    } else {
                %>
                    <button class="inverted-button header" onclick="window.location='login.jsp';">Login</button>
                <%
                    }
                %>
            </span>
        </div>
    </body>
</html>