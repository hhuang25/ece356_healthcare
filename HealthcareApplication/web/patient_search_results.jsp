<%-- 
    Document   : patient_search_results
    Created on : Mar 22, 2015, 10:04:11 PM
    Author     : Ajanthan Asogamoorth
    IMPORTANT MUST EDIT QUERY TO RETURN WHETHER PATIENT IS EXISTING FRIEND,
    AND MAKE RESULTING MODIFICCATIOSN HERE!
--%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page import="java.util.ArrayList"%>
<%@page import="composite.PatientResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            if (_patientId == -1) {
                response.sendRedirect(request.getContextPath() + "/404Page.jsp");
            }
        %>
        <table width="90%" class="review-table">
            <thead>
                    <tr>
                        <th colspan="5"><h2>Social</h2></th>
                    </tr>
            </thead>
            <tr>
                <td>Alias</td>
                <td>City</td>
                <td>Province</td>
                <td>Number of Reviews</td>
                <td>Time of Last Review</td>
                <td>Status</td>
            </tr>
        <%! ArrayList<PatientResult> patient_info;%>
        <%
            patient_info = (ArrayList<PatientResult>) request.getAttribute(
                    "patient_info"
            );
            for (PatientResult pr : patient_info) {
        %>
        <form method="post" action="ViewPatientSearchResult">
            <tr>
                <td><%=pr.getUser().getAlias()%></td>
                <td><%=pr.getRegion().getCity()%></td>
                <td><%=pr.getRegion().getProvince()%></td>
                <td><%=pr.getNumReview()%></td>
                <%
                if(pr.getTimeReview() != null){
                %>
                    <td><%=pr.getTimeReview()%></td>
                <%
                }else{
                %>
                <td>No reviews</td>
                <%
                    }
                %>
            <input type="hidden" value="<%=pr.getPatient().getId()%>" name="patient_id">
            <% if (pr.getStatus() == 0) { %>
                <td><input type="submit" value="Send Friend Request" name="friend_request"></td>
            <% } else if (pr.getStatus() == 1) { %>
                <td>Friend Request Sent</td>
            <% } else if (pr.getStatus() == 2) { %>
                <td><input type="submit" value="Confirm Friend Request" name="friend_request"></td>
            <% } else { %>
                <td><%=pr.getUser().getAlias()%> is your friend!</td>
            <% } %>
             </tr>
        </form>
        <%
            }
        %>
        </table>
    </body>
</html>
