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
            <div class="horizontal-center">
                <div style="text-align: center;">
                    <h2>Your Friend Requests</h2>
                </div>
                <%! ArrayList<User> users_request_friend;%>
                <%
                    users_request_friend = (ArrayList<User>) request.getAttribute(
                            "users_request_friend"
                    );
                %>
                <div style="text-align: center;">
                <% if (users_request_friend == null || users_request_friend.isEmpty()) {
                %>
                    <p>You have no pending friend requests at this time</p>
                <%
                } else {
                %>
                    <p>You have <b><%=users_request_friend.size()%></b> pending request(s)</p>
                <%
                }
                %>
                </div>
                <%
                    for (User u : users_request_friend) {
                %>
                <form method="post" action="ViewFriendRequests">
                    <table class="friend-requests-table horizontal-center">
                        <tr>
                            <td><b><%=u.getAlias()%></b><br/><%=u.getEmail()%></td>
                            <td style="text-align: right;">
                                <input type="hidden" value="<%=u.getId()%>" name="user_id">
                                <input type="submit" value="Accept" class="button small">
                            </td>
                        </tr>
                    </table>
                </form>
                <%
                    }
                %>
            </div>
         </div>
        <%@ include file="/footer.jsp" %>
    </body> 
</html>
