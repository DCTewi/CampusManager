<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Index"/>
</jsp:include>

<h1>Hello!</h1><br>
<%
    String userEmail;
    if (session.getAttribute("logged") != null)
    {
        userEmail = ((String) session.getAttribute("email"));
%>
        <h4>Hello <%=userEmail%>!</h4>
<%
    }
%>


<%@include file="component/footer.jsp"%>