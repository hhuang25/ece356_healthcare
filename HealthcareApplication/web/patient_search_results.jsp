<%-- 
    Document   : patient_search_results
    Created on : Mar 22, 2015, 10:04:11 PM
    Author     : Ajanthan Asogamoorth
    IMPORTANT MUST EDIT QUERY TO RETURN WHETHER PATIENT IS EXISTING FRIEND,
    AND MAKE RESULTING MODIFICCATIOSN HERE!
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
        <%! ArrayList<PatientResult> patient_info;%>
        <%
            patient_info = (ArrayList<PatientResult>) request.getAttribute(
                    "patient_info"
            );
        %>
        <div class="content default-padding">
            <h2>Patient Search Results (<%=patient_info.size()%>)</h2>
            <%
                if (patient_info.size() > 0) {
            %>
                <table width="90%" class="list-table">
                    <thead>
                        <tr>
                            <th>Alias</th>
                            <th>City</th>
                            <th>Province</th>
                            <th>Number of Reviews</th>
                            <th>Time of Last Review</th>
                            <th>Friendship Status</th>
                        </tr>
                    </thead>
                <%
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
                            <td><%=new SimpleDateFormat("MM/dd/yyyy | h:mm a").format(new Date(pr.getTimeReview().getTime()))%></td>
                        <%
                        }else{
                        %>
                        <td>No reviews</td>
                        <%
                            }
                        %>
                    <input type="hidden" value="<%=pr.getPatient().getId()%>" name="patient_id">
                    <% if (pr.getStatus() == 0) { %>
                        <td><input type="submit" class="button wide" value="Send Request" name="friend_request"></td>
                    <% } else if (pr.getStatus() == 1) { %>
                        <td>Friend Request Sent</td>
                    <% } else if (pr.getStatus() == 2) { %>
                        <td><input type="submit" class="button wide" value="Confirm Request" name="friend_request"></td>
                    <% } else { %>
                        <td>Your Friend</td>
                    <% } %>
                     </tr>
                </form>
                <%
                    }
                %>
                </table>
            <%
            } else {
            %>
            <p>Your search returned no results.</p>
            <%
            }
            %>
            <p><button class="button large" onclick="history.back()">Back</button></p>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
