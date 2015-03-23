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
            <input type="submit" value="Add as Friend">
        </form>
        <%
            }
        %>
    </body>
</html>
