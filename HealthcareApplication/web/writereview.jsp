<%-- 
    Document   : writereview
    Created on : 23-Mar-2015, 4:12:52 PM
    Author     : Tommy
--%>

<%@page import="bean.Doctor"%>
<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            if (_patientId == -1) {
                response.sendRedirect(request.getContextPath() + "/404Page.jsp");
            }
        %>
        <% Doctor doctor = (Doctor) request.getAttribute("doctor"); %>
        <div class="content default-padding">
            <% if (doctor != null) {
            %>
                <h2>Write a Review for <%=doctor.getFirstName()%> <%=doctor.getLastName()%></h2>
                <form action="WriteReview" method="POST">
                    <table class="write-review-table">
                        <tr>
                            <td>Rating</td>
                            <td>
                                <input name="rating" type="radio" value="0" class="radio-align" required> 0
                                <input name="rating" type="radio" value="0.5" class="radio-align" style="margin-left: 20px;"> 0.5
                                <input name="rating" type="radio" value="1" class="radio-align" style="margin-left: 20px;"> 1
                                <input name="rating" type="radio" value="1.5" class="radio-align" style="margin-left: 20px;"> 1.5
                                <input name="rating" type="radio" value="2" class="radio-align" style="margin-left: 20px;"> 2
                                <input name="rating" type="radio" value="2.5" class="radio-align" style="margin-left: 20px;"> 2.5
                                <br/>
                                <input name="rating" type="radio" value="3" class="radio-align"> 3
                                <input name="rating" type="radio" value="3.5" class="radio-align" style="margin-left: 20px;"> 3.5
                                <input name="rating" type="radio" value="4" class="radio-align" style="margin-left: 20px;"> 4
                                <input name="rating" type="radio" value="4.5" class="radio-align" style="margin-left: 20px;"> 4.5
                                <input name="rating" type="radio" value="5" class="radio-align" style="margin-left: 20px;"> 5
                                <br/><br/>
                            </td>
                        </tr>
                        <tr>
                            <td>Review<br/>(1000 character limit)</td>
                            <td><textarea name="review" cols="50" rows="15" class="review" required></textarea></td>
                        </tr>
                    </table>
                    <br/><br/>
                    <div style="text-align: center;">
                        <input type="hidden" name="patientId" value="<%=_patientId%>">
                        <input type="hidden" name="doctorId" value="<%=doctor.getId()%>">
                        <input type="submit" value="Submit" class="button medium">
                    </div>
                </form>
            <%
                } else if ((Boolean)request.getAttribute("sentResponse")) {
                    String error = (String)request.getAttribute("errorMessage");
                    if (error != null && error.length() > 0) {
            %>
                <div style="text-align: center;">
                    <h2>Your Review could not be Submitted</h2>
                    <p><%=error%></p>
                    <p><button class="button large" onclick="window.location='index.jsp<%=query%>';">Return</button></p>
                </div>
                    <%
                    } else {
                    %>
                    <div style="text-align: center;">
                       <h2>Your Review was successfully submitted</h2>
                       <p><button class="button large" onclick="window.location='index.jsp<%=query%>';">Return</button></p>
                   </div>
                  <%
                    }
                  %>
            <%
                } else {
                    response.sendRedirect(request.getContextPath() + "/404Page.jsp"); 
                }
            %>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
