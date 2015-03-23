<%-- 
    Document   : search_patient_form
    Created on : Mar 22, 2015, 9:24:36 PM
    Author     : Ajanthan Asogamoorth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="content default-padding">
            <h2>Find other patients </h2>
            <form method="post" action="FindPatientServlet">
                <fieldset>
                <legend> Search Parameters </legend>
                <table>
                <tr>
                    <td width="12%">Alias contains:</td><td><input type="text" name="alias"></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td><input type="text" name="city" style="margin-right: 20px;"></td>          
                    <td>Province</td>
                    <td>
                    <select name="province">
                        <option>Select One</option>
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
                    </td>
                </tr>
                </table>
                </fieldset><br/>
                <div style="text-align: center;">
                    <input type="submit" class="button small" value="Search">
                </div>
            </form>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
