<%@ page import="Model.Administrator" %>
<%@ page import="Model.Teacher" %>
<%@ page import="Service.AccountService" %>
<%@ page import="Model.UserType" %>
<%@ page import="Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>

<h1>Dashboard</h1><br>

<%
    if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Administrator))
    {
        response.sendRedirect(request.getContextPath());
        return;
    }

    Object[] teachers = AccountService.getProfileOf(UserType.Teacher);
    Object[] students = AccountService.getProfileOf(UserType.Student);

    assert teachers != null;
    assert students != null;
%>
<table class="table table-hover">
    <thead>
        <tr>
            <th scope="col" class="w-25">Teacher</th>
            <th scope="col" class="w-25">Username</th>
            <th scope="col" class="w-25">Email</th>
            <th scope="col" class="w-25">Phone</th>
        </tr>
    </thead>
    <tbody>
<%
    for (Object teacherObject : teachers)
    {
        Teacher teacher = (Teacher) teacherObject;
%>
        <tr>
            <th scope="row"><%=teacher.getId()%></th>
            <td><%=teacher.getUserName()%></td>
            <td><%=teacher.getEmail()%></td>
            <td><%=teacher.getPhone()%></td>
        </tr>
<%
    }
%>
    </tbody>
</table>
<br>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col" class="w-25">Student</th>
        <th scope="col" class="w-25">Username</th>
        <th scope="col" class="w-25">Email</th>
        <th scope="col" class="w-25">Phone</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Object studentObject : students)
        {
            Student student = (Student) studentObject;
    %>
    <tr>
        <th scope="row"><%=student.getId()%></th>
        <td><%=student.getUserName()%></td>
        <td><%=student.getEmail()%></td>
        <td><%=student.getPhone()%></td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<%@include file="component/footer.jsp"%>
