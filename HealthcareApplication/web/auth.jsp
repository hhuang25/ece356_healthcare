<% 
    if (session.getAttribute("UserSession") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
