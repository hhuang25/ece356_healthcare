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
            <div style="text-align: center;">
                <h1>
                    Welcome
                    <%
                        if (_docId != -1) {
                    %>
                    Doctor 
                    <%
                        }
                    %>
                    <%=_firstName%>!
                </h1>
            </div>
            <table colspan="2" class="horizontal-center main-table">
                <%
                    if (_patientId != -1) {
                %>
                    <tr>
                        <td><span class="title">Doctors Search</span><br/>
                            <span class="text">
                            Filter based on various criteria to find the right doctor for you.</span>
                        </td>
                        <td style="text-align: right;">
                            <button class="button large" onclick="window.location.href='FindDoctor';">Find</button>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="title">Patients Search</span><br/>
                            <span class="text">
                                Search for patients according to their alias, city and province.
                            </span>
                        </td>
                        <td style="text-align: right;">
                            <button class="button large" onclick="window.location.href='FindPatient';">Find</button>
                        </td>
                    </tr>
                    <tr>
                        <td><span class="title">View Friend Requests</span><br/>
                            <span class="text">View the patients that have added you as a friend.</span></td>
                        <td style="text-align: right;">
                            <button class="button large" onclick="window.location.href='ViewFriendRequests';">View</button>
                        </td>
                    </tr>
                <%
                    } else {
                %>
                    <tr>
                        <td><span class="title">View Personal Profile</span><br/>
                            <span class="text">View your personal information and reviews.</span></td>
                        <td style="text-align: right;">
                            <button class="button large" onclick="window.location.href='Profile?docId=<%=_docId%>';">View</button>
                        </td>
                    </tr>
                <%
                    }
                %>
            </table>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
