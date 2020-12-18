<%@ page import="Model.Teacher" %>
<%@ page import="Model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.CourseService" %>
<%@ page import="Model.Student" %>
<%@ page import="Service.GradeService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Student"/>
</jsp:include>

<h1>Student</h1><br>

<%
    if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Student))
    {
        response.sendRedirect(request.getContextPath());
        return;
    }

    Student me = (Student) session.getAttribute("profile");

    List<Course> courses = CourseService.getCourseOf(me.getId());

    for (Course course : courses)
    {
%>
<div class="card card-body">
    <h2 class="card-title mb-0">
        <%=course.getCourseName()%>
    </h2>
    <div class="card-text mb-4">
        <small class="text-muted text-monospace">
            <strong>Classroom: </strong><%=course.getClassroom()%> |
            <strong>Time:</strong><%=course.getCourseTime()%>
        </small>
    </div>
    <div class="card-text">
        <h5>Student List</h5>
        <table class="table table-hover">
            <thead>
            <tr>
                <th class="w-50">Name</th>
                <th class="w-50">Grade</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%=me.getUserName()%></td>
                <td><%=GradeService.getGrade(course.getId(), me.getId())%></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<%
    }
%>

<%@include file="component/footer.jsp"%>
