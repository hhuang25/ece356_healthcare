<%-- 
    Document   : search_doctor_form
    Created on : Mar 22, 2015, 5:08:59 AM
    Author     : Ajanthan Asogamoorthy
--%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Huang Healthcare</title>
        <link rel="shortcut icon" href="src/img/logo.ico">
        <link rel="stylesheet" href="src/css/main.css" />
    </head>
    <body>
        <div class="content">
            <form method="post" action="FindDoctorServlet">
                <fieldset>
                <legend> Personal Information </legend>
                First Name: <input type="text" name="firstname">
                Last Name: <input type="text" name="lastname">
                Gender: 
                <input type="radio" value="male"> Male
                <input type="radio" value="female"> Female
                <br>
                City: <input type="text" name="city">          
                <br>
                Province: 
                <select name="province"> 
                    <option>Ontario</option>
                    <option>Quebec</option>
                    <option>British Columbia</option>
                    <option>Alberta</option>
                    <option>Saskatchewan</option>
                    <option>Winnipeg</option>
                    <option>Manitoba</option>
                    <option>Nova Scotia</option>
                    <option>New Brunswick</option>
                    <option>Newfoundland and Labrador</option>
                    <option>North West Territories</option>
                    <option>Yukon</option>
                    <option>Nunavut</option>
                </select>
                <fieldset>
                    <legend> Address </legend>
                    Street Number: <input type="number" name="street_number" min="1">
                    Street <input type="text" name="street">
                    Postal Code <input type="text" name="postal_code">
                </fieldset>
                <fieldset>
                    <legend> Doctor Attributes </legend>
                    Year License Obtained: 
                    <input type="number" name="year_license_obtained" min="1800">
                    <br>
                    Specialization: 
                    <select name="specialization">
                        <%! ArrayList<String> specializations;%>
                        <%
                            specializations = (ArrayList<String>) request.getAttribute("specializations");
                            for (String specialization : specializations) {
                        %>
                        <option value="<%=specialization%>"><%=specialization%></option>
                        <%
                            }
                        %>
                    </select>
                    <br>
                    Average Rating Threshold:
                    <input type="number" name="rating_threshold" min="1" max="5">
                    <br>
                    Friend Reviewed
                    <input type="text" name="friend_reviewed">
                    <br>
                </fieldset>
                <fieldset>
                    <legend> Review Characteristics </legend>
                    Keyword in Review: 
                    <input type="text" name="keyword">
                </fieldset>
                <input type="submit" value="submit_doctor_search">
            </form>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
