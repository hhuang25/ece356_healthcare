<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/master.jsp" %>

<html>
    <body>
        <jsp:declaration>
            String errorMessage;
            String stackTrace;
        </jsp:declaration>

        <jsp:scriptlet>
            Exception myException = (Exception)request.getAttribute("exception");
            errorMessage = myException.getMessage();
            StringWriter errorWriter = new StringWriter();
            myException.printStackTrace(new PrintWriter(errorWriter));
            stackTrace = errorWriter.toString();
        </jsp:scriptlet>

        <div class="content" style="text-align: center;">
            <h2>Looks like something went wrong</h2>
            <p><button class="button large" onclick="window.location='index.jsp<%=query%>';">Return Home</button></p>
        </div>
        <%@ include file="/footer.jsp" %>
    </body>
</html>
