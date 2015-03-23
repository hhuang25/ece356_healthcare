<%-- 
    Document   : patient_search_results
    Created on : Mar 22, 2015, 10:04:11 PM
    Author     : Ajanthan Asogamoorth
    IMPORTANT MUST EDIT QUERY TO RETURN WHETHER PATIENT IS EXISTING FRIEND,
    AND MAKE RESULTING MODIFICCATIOSN HERE!
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="composite.PatientResult"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%! ArrayList<PatientResult> patient_info;%>
        <%
            patient_info = (ArrayList<PatientResult>) request.getAttribute(
                    "patient_info"
            );
            for (PatientResult pr : patient_info) {
        %>
        <form method="post" action="AddFriendServlet">
            Alias:<%=pr.getUser().getAlias()%>
            City:<%=pr.getRegion().getCity()%>
            Province:<%=pr.getRegion().getProvince()%>
            Num Reviews:<%=pr.getNumReview()%>
            Time of last review:<%=pr.getTimeReview()%>
            
            <input type="hidden" value="<%=pr.getPatient().getId()%>" name="patient_id">
            <% if (pr.getStatus() == 0) { %>
                <input type="submit" value="Send Friend Request" name="friend_request">
            <% } else if (pr.getStatus() == 1) { %>
                Friend Request Sent
            <% } else if (pr.getStatus() == 2) { %>
                <input type="submit" value="Confirm Friend Request" name="friend_request">
            <% } else { %>
                <%=pr.getUser().getAlias()%> is your friend!
            <% } %>
            
        </form>
        <%
            }
        %>
    </body>
</html>
