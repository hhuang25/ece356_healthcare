<%-- 
    Document   : view_friend_requests
    Created on : Mar 22, 2015, 5:20:11 PM
    Author     : Ajanthan Asogamoorthy
--%>
<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page import="java.util.ArrayList"%>
<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content">
        <%! ArrayList<User> users_request_friend;%>
        <%
            users_request_friend = (ArrayList<User>) request.getAttribute(
                    "users_request_friend"
            );
            for (User u : users_request_friend) {
        %>
        <form method="post" action="ViewFriendRequests">
            <%=u.getAlias()%>
            <%=u.getEmail()%>
            <input type="hidden" value="<%=u.getId()%>" name="user_id">
            <input type="submit" name="Accept as Friend">
        </form
        <%
            }
        %>
        </div>
        <%@ include file="/footer.jsp" %>

    </body> 
</html>
