<%-- 
    Document   : index
    Created on : 21-Mar-2015, 10:22:24 PM
    Author     : huangster
--%>
<%@ include file="/master.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <%
            String param = request.getParameter("redirect");
            if (param != null) {
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            }
        %>
        <div class="content">
            <div class="passage pentagon">
                <h1>Find the best doctor for you</h1>
                <p>Your all-in-one solution for finding the right doctor in your area</p>
                <div class="steth horizontal-center"></div>
            </div>
            <div class="horizontal-center features">
                <h2 style="color: #00CC99;">Features</h2>
                <ul class="checkmark">
                    <li>&nbsp;&nbsp;Find Doctors of various Specializations</li>
                    <li>&nbsp;&nbsp;Read comprehensive Doctor Profiles</li>
                    <li>&nbsp;&nbsp;Connect with other Patients in a Social Network</li>
                    <li>&nbsp;&nbsp;Read Reviews on Doctors written by other Patients</li>
                </ul>
                <p>... and more</p>
            </div>
            <div style="text-align: center; margin-top: 20px;">
                <button class="button large" onclick="window.location='login.jsp';">Sign In</button>
            </div>
            <br/><br/>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>