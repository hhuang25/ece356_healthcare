<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>

<%@ include file="/master.jsp" %>
<%@ include file="/auth.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<title>Error Page</title>
</head>
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

<h2>Oops!</h2>
<pre style="color: red;">
    <%=errorMessage%> Please remove stack trace
</pre>
<h3>Exception stack trace</h3>
<pre>
    <%= stackTrace %>
</pre>

</body>
</html>