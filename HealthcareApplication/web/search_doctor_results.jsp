<%-- 
    Document   : search_doctor_results
    Created on : 22-Mar-2015, 11:38:53 PM
    Author     : huang
--%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page import="java.util.ArrayList"%>
<%@page import="composite.DoctorReview"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctors</title>
    </head>
    <body>
        <div class="content">
            <%! ArrayList<DoctorReview> doctorReviews;%>
        <%
            doctorReviews = (ArrayList<DoctorReview>) request.getAttribute(
                    "doctor_reviews"
            );
            for (DoctorReview ds : doctorReviews) {
        %>
            <% 
            if(ds.getDoctor() != null){   
            %>
            <form method="post" action="ProfileServlet">
                <%=ds.getDoctor().getFirstName()%>
                <%=ds.getDoctor().getLastName()%>
                <%=ds.getDoctor().getMiddleName()%>
                <%=ds.getDoctor().getGender()%>
                <%=ds.getDoctor().getYearLicenseObtained()%>
                <%=ds.getAverageRating()%>
                <%=ds.getNumberOfReviews()%>
                <input type="hidden" value="<%=ds.getDoctor().getId()%>" name="docId">
                <input type="hidden" value="<%=ds.getDoctor().getUserId()%>" name="user_id">
                <input type="submit" value="View Profile">
            </form>
                <%        
                }
            %>
        <%
            }
        %>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
