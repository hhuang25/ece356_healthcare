<%-- 
    Document   : register
    Created on : 22-Mar-2015, 4:42:12 AM
    Author     : Tommy
--%>

<%@ include file="/master.jsp" %>

<% response.sendRedirect(request.getContextPath() + "/index.jsp"); %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content">
            <div class="horizontal-center">
                <h2 style="text-align: center;">
                    Create your free account in seconds
                </h2>
                <div class="horizontal-center" style="width: 25%;">
                    <form action="RegisterServlet" method=post>
                        <p>Username</p>
                        <input type="text" name="username" size="45">
                        <p>Password</p>
                        <input type="password" size="45" name="password"><br/>
                        <p>E-mail Address</p>
                        <input type="text" name="email" size="45">
                        <p>I am a...</p>
                        <select>
                            <option value="">Select One</option>
                            <option value="doctor">Doctor</option>
                            <option value="patient">Patient</option>
                        </select> 
                        <br/><br/>
                        <div style="text-align: center;">
                            <input type="submit" value="Submit" class="button small">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>