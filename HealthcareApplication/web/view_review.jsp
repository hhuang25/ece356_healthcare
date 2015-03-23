<%-- 
    Document   : view_review
    Created on : Mar 23, 2015, 3:36:20 AM
    Author     : Ajanthan Asogamoorth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="composite.ReviewInfo"%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<!DOCTYPE html>
<html>
    <body>
        <%
            if (_patientId == -1) {
                response.sendRedirect(request.getContextPath() + "/404Page.jsp");
            }
        %>
        <div class="content">
            <% ReviewInfo review_info = (ReviewInfo) request.getAttribute("review_info"); %>
            <%=review_info.getReview().getRating()%>
            <%=review_info.getReview().getReview()%>
            <%=review_info.getReview().getReviewDate()%>
            <%=review_info.getDoctorName()%>

            <form method="GET" action="ViewReview">
                <% int previous_id = (Integer)request.getAttribute("previous_id"); %>
                <input type="hidden" value="<%=previous_id%>" name="review_id">
                <% if (previous_id == 0) { %>
                    <input type="submit" value="Previous" name="submit" disabled>
                <% } else { %>
                    <input type="submit" value="Previous" name="submit">
                <% } %>
            </form>

            <form method="GET" action="ViewReview">
                <% int next_id = (Integer)request.getAttribute("next_id"); %>
                <input type="hidden" value="<%=next_id%>" name="review_id">
                <% if (next_id == 0) { %>
                    <input type="submit" value="Next" name="submit" disabled>
                <% } else { %>
                    <input type="submit" value="Next" name="submit">
                <% } %>
            </form>
        </div>
    </body>
</html>
