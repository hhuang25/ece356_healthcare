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
    <body>
        <%
            if (_patientId == -1) {
                response.sendRedirect(request.getContextPath() + "/404Page.jsp");
            }
        %>
        <div class="content default-padding">
            <%! ArrayList<DoctorReview> doctorReviews;%>
            <% doctorReviews = (ArrayList<DoctorReview>) request.getAttribute(
                    "doctor_reviews"
            );%>
            <h2>Doctor Search Results (<%=doctorReviews.size()%>)</h2>
        <%
            if (doctorReviews.size() > 0) {
        %>
            <table width="80%" class="list-table">
                <thead>
                    <tr>
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Gender</th>
                        <th>Average Rating</th>
                        <th>Number of Reviews</th>
                        <th>Profile</th>
                    </tr>
                </thead>
            <%
                for (DoctorReview ds : doctorReviews) {
            %>
                <% 
                if(ds.getDoctor() != null){   
                %>
                <form method="post" action="Profile">
                        <tr>
                            <td>
                                <%=ds.getDoctor().getFirstName()%>
                            </td>
                                <%
                                if(ds.getDoctor().getMiddleName() != null){
                                %>
                            <td>
                                <%=ds.getDoctor().getMiddleName()%>
                            </td>
                                <%
                                }else{
                                %>
                                    <td>
                                    N/A
                                    </td>
                                <%
                                }
                                %>
                            <td>
                                <%=ds.getDoctor().getLastName()%>
                            </td>
                            <td>
                                <%=ds.getDoctor().getGender()%>
                            </td>
                            <td>
                                <%=ds.getAverageRating()%>
                            </td>
                            <td>
                                <%=ds.getNumberOfReviews()%>
                            </td>
                            <td style="text-align: left;">
                                <input type="submit" class="button small" value="View">
                            </td>
                            <input type="hidden" value="<%=ds.getDoctor().getId()%>" name="docId">
                            <input type="hidden" value="<%=ds.getDoctor().getUserId()%>" name="user_id">
                        </tr>

                </form>
                    <%        
                    }
                %>
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
