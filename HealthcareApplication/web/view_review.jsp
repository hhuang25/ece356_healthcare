<%-- 
    Document   : view_review
    Created on : Mar 23, 2015, 3:36:20 AM
    Author     : Ajanthan Asogamoorth
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="composite.ReviewInfo"%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<!DOCTYPE html>
<html>
    <body>
        <div class="content">
            <% ReviewInfo review_info = (ReviewInfo) request.getAttribute("review_info"); %>
            <div style="text-align: center;">
                <h2><div class="page"></div><%=review_info.getDoctorName()%> Review</h2>
            </div>
            <table class="side-green-table view-review-table">
                <tr>
                    <td>
                        <b>Reviewed on <%=new SimpleDateFormat("MM/dd/yyyy | h:mm a").format(new Date(review_info.getReview().getReviewDate().getTime()))%></b><br/>
                        <div style="display: inline-block;">
                        <% 
                            double rating = review_info.getReview().getRating();
                            double c = 0;
                            if (rating == Math.floor(rating)) {
                                for (c = 0; c < rating; c++) {
                        %>
                            <span class="star-icon full">☆</span>
                        <% } %>
                        <%
                          for (double x = c; x < 5; x++) {
                        %>
                            <span class="star-icon">☆</span>
                        <%
                            }
                          } else {
                            rating = rating - 0.5;
                            for (c = 0; c < rating; c++) {
                        %>
                            <span class="star-icon full">☆</span>
                        <% } %>
                        <span class="star-icon half">☆</span>
                        <%
                          for (double x = c + 1; x < 5; x++) {
                        %>
                            <span class="star-icon">☆</span>
                        <%
                                }
                            }
                        %>
                        </div>
                    </td>
                    <td>
                        <div class="quote start-quote"></div>
                        <%=review_info.getReview().getReview()%>
                        <div class="quote end-quote"></div>
                    </td>
                </tr>
            </table>
            <br/>
            <form method="GET" action="ViewReview" style="display: inline-block; margin-left: 20px;">
                <% int previous_id = (Integer)request.getAttribute("previous_id"); %>
                <input type="hidden" value="<%=previous_id%>" name="review_id">
                <% if (previous_id == 0) { %>
                    <input type="submit" class="button medium disabled" value="‹   Previous" name="submit" disabled style="margin-right: 10px;">
                <% } else { %>
                    <input type="submit" class="button medium" value="‹   Previous" name="submit" style="margin-right: 10px;">
                <% } %>
            </form>
            <form method="GET" action="ViewReview" style="display: inline-block; float: right; margin-right: 20px;">
                <% int next_id = (Integer)request.getAttribute("next_id"); %>
                <input type="hidden" value="<%=next_id%>" name="review_id">
                <% if (next_id == 0) { %>
                    <input type="submit" value="Next   ›" class="button medium disabled" name="submit" disabled>
                <% } else { %>
                    <input type="submit" value="Next   ›" class="button medium" name="submit">
                <% } %>
            </form>
        </div>
    </body>
</html>
