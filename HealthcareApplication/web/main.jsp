<%-- 
    Document   : main
    Created on : 22-Mar-2015, 2:51:16 AM
    Author     : Tommy
--%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content default-padding">
            <h1>Welcome <%=_firstName%>!</h1>
            <li><a href="FindDoctorServlet">Find Doctor</a></li>
            <li><a href="ViewFriendRequests">View Friend Requests</a></li>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
