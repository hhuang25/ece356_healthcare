<%-- 
    Document   : 500Page
    Created on : 23-Mar-2015, 5:34:15 PM
    Author     : Tommy
--%>

<%@ include file="/master.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content" style="text-align: center;">
            <h2>Well, this is embarrassing...</h2>
            <p>Something unexpected occurred.</p>
            <p><button class="button large" onclick="window.location='index.jsp<%=query%>';">Return Home</button></p>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>