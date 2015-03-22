<% 
    Object authenticated = session.getAttribute("UserSession");
    if (authenticated == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
