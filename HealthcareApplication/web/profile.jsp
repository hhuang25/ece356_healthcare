<%-- 
    Document   : profile
    Created on : 22-Mar-2015, 4:28:19 PM
    Author     : Tommy
--%>
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
            <%
                if (result != null) {
            %>
            <%
                if (result != null && result.getDoctor().getId() == _docId) {
                    isViewingOwn = true;
            %>
                    <h2>Your Profile</h2>
            <%
                }
                else {
            %>
                    <h2><%=result.getDoctor().getFirstName()%> <%=result.getDoctor().getLastName()%>'s Profile</h2>
            <%
                }
            %>
            </div>
            <table width="100%" colspan="2">
                <thead>
                    <tr>
                        <th><h2>Information</h2></th>
                        <th><h2>Reviews</h2></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td width="50%">
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
                                        <td width="50%">Years Specialized</td><td><%=result.getYearsLicensed()%></td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Address(es)</td>
                                        <td>
                                            <%
                                                for(Address address: result.getAddresses()) {
                                            %>
                                                <%=address.getStreetNumber()%> <%=address.getStreet()%><br/>
                                                <%=address.getRegion().getCity()%>, <%=address.getRegion().getProvince()%><br/>
                                                <%=address.getPostalCode()%><br/>
                                                <br/>
                                            <%
                                              }
                                            %>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="50%">Specialization(s)</td>
                                        <td>
                                            <%
                                                for(Specialization s: result.getSpecializations()) {
                                            %>
                                                <%=s.getSpecialization()%><br/>
                                            <%
                                              }
                                            %>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td width="50%">
                            <table width="100%">
                                <tr>
                                    <td></td><td></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
            <%
            }
            else {
               response.sendRedirect(request.getContextPath() + "/main.jsp"); 
            }
            %>     
        </div> 
        <%@ include file="/footer.jsp" %>
    </body>
</html>
