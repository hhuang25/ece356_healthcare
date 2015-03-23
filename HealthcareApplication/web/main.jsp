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
        <div class="content default-padding" style="text-align: center;">
            <h1>Welcome <%=_firstName%>!</h1>
            <table colspan="2" class="horizontal-center main-table">
                <tr>
                    <td>
                        <button class="button large" onclick="window.location.href='FindDoctorServlet';">Find Doctor</button>
                    </td>
                    <td>
                        <button class="button large" onclick="window.location.href='ViewFriendRequests';">View Friend Requests</button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button class="button large" onclick="window.location.href='ProfileServlet?docId=<%=_docId%>';">View Profile</button>
                    </td>
                    <td>
                        <button class="button large" onclick="window.location.href='FindPatientServlet';">Find Patients</button>
                    </td>
                </tr>
            </table>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
