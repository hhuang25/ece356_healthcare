<%-- 
    Document   : profile
    Created on : 22-Mar-2015, 4:28:19 PM
    Author     : Tommy
--%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="bean.Review"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Specialization"%>
<%@page import="bean.Address"%>
<%@page import="composite.ViewProfileResult"%>
<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <% ViewProfileResult result = (ViewProfileResult) request.getAttribute("ViewProfileResult"); %>
        <% Boolean isViewingOwn = false; %>
        <div class="content default-padding">
            <div style="text-align: center;">
                <h2 style="margin-top: 0;"><div class="icon" style="top: 20px;">
		<div class="user"></div>
		<div class="shoulder"></div></div>
            <%
                if (result != null) {
            %>
            <%
                if (result.getDoctor().getId() == _docId) {
                    isViewingOwn = true;
            %>
                Your Profile
            <%
                }
                else {
            %>
                <%=result.getDoctor().getFirstName()%> <%=result.getDoctor().getLastName()%>'s Profile
            <%
                }
            %>
            <%
               if (_docId != -1 && !isViewingOwn) {
                   response.sendRedirect(request.getContextPath() + "/404Page.jsp");
               }
            %>
            </h2>
            </div>
            <table width="100%" colspan="2">
                <thead>
                    <tr>
                        <th><h2>◆ Information</h2></th>
                        <th><h2>✍ Reviews</h2></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td width="50%" style="vertical-align: top;">
                            <table width="100%" class="side-green-table">
                                <tbody>
                                    <tr>
                                        <td width="50%">First Name</td><td><%=result.getDoctor().getFirstName()%></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Last Name</td><td><%=result.getDoctor().getLastName()%></td>
                                    </tr>
                                    <% 
                                        if (isViewingOwn) {
                                    %>
                                        <tr>
                                            <td width="50%">E-mail</td><td><%=result.getEmail()%></td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                    <tr>
                                        <td width="50%">Gender</td><td><%=result.getDoctor().getGender()%></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Years Licensed</td><td><%=result.getYearsLicensed()%></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Average Rating</td>
                                        <td>
                                            <%=result.getAverageRating()%>/5.0
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Work Address(es)</td>
                                        <td>
                                            <%
                                                int count = 0;
                                                ArrayList<Address> addresses = result.getAddresses();
                                                for(Address address: addresses) {
                                                    count++;
                                            %>
                                                <%=address.getStreetNumber()%> <%=address.getStreet()%><br/>
                                                <%=address.getRegion().getCity()%>, <%=address.getRegion().getProvince()%><br/>
                                                <%=address.getPostalCode()%><br/>
                                                <%
                                                    if (count != addresses.size()) {
                                                %>
                                                    <br/>
                                                <%
                                                }
                                                %>
                                            <%
                                              }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Specialization(s)</td>
                                        <td>
                                            <%
                                                int counter = 0;
                                            %>
                                            <%
                                                for(Specialization s: result.getSpecializations()) {
                                                    counter++;
                                            %>
                                                <%=s.getSpecialization()%><br/>
                                            <%
                                              }
                                            %>
                                            <%
                                                if (counter == 0) {
                                            %>
                                                None
                                            <%
                                                }
                                            %>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td width="50%" style="vertical-align: top;">
                            <table width="100%" class="review-table">
                                <tr>
                                    <td width="50%"><b><%=result.getNumOfReviews()%></b> Review(s) Written</td>
                                    <td width="50%" style="text-align: right;">
                                    <%
                                        if (_patientId != -1) {
                                    %>
                                        <button class="button medium" 
                                         onclick="window.location.href='WriteReview?docId=<%=result.getDoctor().getId()%>';">
                                        Write Review</button>
                                    <%
                                        }
                                    %>
                                    </td>
                                </tr>
                                <%
                                  for (Review review: result.getReviews()) {
                                %>
                                    <tr>
                                        <td style="width: 80%;">
                                            <b>Reviewed on <%=new SimpleDateFormat("MM/dd/yyyy | h:mm a").format(new Date(review.getReviewDate().getTime()))%></b>
                                            <br/>Rated
                                            <div style="display: inline-block; margin-left: 15px;">
                                            <% 
                                                double rating = review.getRating();
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
                                        <td style="text-align: right;">
                                            <button class="button small" onclick="window.location.href='ViewReview?review_id=<%=review.getId()%>';">View</button>
                                        </td>
                                    </tr>
                                <%
                                  }
                                %>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <%
            }
            else {
               response.sendRedirect(request.getContextPath() + "/404Page.jsp"); 
            }
            %>     
        </div> 
        <%@ include file="/footer.jsp" %>
    </body>
</html>
