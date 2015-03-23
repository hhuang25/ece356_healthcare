<%-- 
    Document   : 404Page
    Created on : 23-Mar-2015, 4:00:46 PM
    Author     : Tommy
--%>

<%@ include file="/master.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content" style="text-align: center;">
            <h2>Sorry! Page does not Exist</h2>
            <p><button class="button large" onclick="window.location='index.jsp<%=query%>';">Return Home</button></p>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>