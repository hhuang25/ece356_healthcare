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
        <div class="content" style="text-align: center;">
            <% ReviewInfo review_info = (ReviewInfo) request.getAttribute("review_info"); %>

            <h2>Review <%=review_info.getDoctorName()%></h2>
            <p><%=review_info.getReview().getRating()%></p>
            <p><%=review_info.getReview().getReview()%></p>
            <p><%=review_info.getReview().getReviewDate()%></p>
            <form method="GET" action="ViewReview">
                <% int previous_id = (Integer)request.getAttribute("previous_id"); %>
                <input type="hidden" value="<%=previous_id%>" name="review_id">
                <% if (previous_id == 0) { %>
                    <input type="submit" class="button medium" value="Previous" name="submit" disabled style="margin-right: 10px;">
                <% } else { %>
                    <input type="submit" class="button medium" value="Previous" name="submit" style="margin-right: 10px;">
                <% } %>
            </form>

            <form method="GET" action="ViewReview">
                <% int next_id = (Integer)request.getAttribute("next_id"); %>
                <input type="hidden" value="<%=next_id%>" name="review_id">
                <% if (next_id == 0) { %>
                    <input type="submit" value="Next" class="button medium" name="submit" disabled>
                <% } else { %>
                    <input type="submit" value="Next" class="button medium" name="submit">
                <% } %>
            </form>
        </div>
    </body>
</html>
