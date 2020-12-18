<%@ page import="Model.Teacher" %>
<%@ page import="Model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.CourseService" %>
<%@ page import="Model.Student" %>
<%@ page import="Service.GradeService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Teacher"/>
</jsp:include>

<h1>Teacher</h1><br>

<%
    if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Teacher))
    {
        response.sendRedirect(request.getContextPath());
        return;
    }

    Teacher me = (Teacher) session.getAttribute("profile");

    List<Course> courses = CourseService.filter(c -> c.getTeacherId() == me.getId());

    for (Course course : courses)
    {
%>
<div class="card card-body">
    <h2 class="card-title mb-0">
        <%=course.getCourseName()%>
        <form class="float-right" method="post" action="<%=request.getContextPath()%>/api/sc">
            <input type="hidden" value="<%=course.getId()%>" name="cid">
            <div class="form-group form-inline">
                <label class="mr-2" for="name-<%=course.getId()%>"></label>
                <input class="form-control" type="number" name="sid" placeholder="Student ID" id="name-<%=course.getId()%>">
                <button class="btn btn-primary ml-2" type="submit">Add</button>
            </div>
        </form>
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
                <th class="w-25">Name</th>
                <th class="w-25">Grade</th>
                <th class="w-50"></th>
            </tr>
            </thead>
            <tbody>
<%
        List<Student> students = CourseService.getStudentsOf(course.getId());
        for (Student student : students)
        {
%>
                <tr>
                    <td><%=student.getUserName()%></td>
                    <td><%=GradeService.getGrade(course.getId(), student.getId())%></td>
                    <td>
                        <form method="post" action="<%=request.getContextPath()%>/api/grade">
                            <input type="hidden" value="<%=course.getId()%>" name="cid">
                            <input type="hidden" value="<%=student.getId()%>" name="sid">
                            <div class="form-group form-inline">
                                <label class="mr-2" for="grade-<%=course.getId()%>-<%=student.getId()%>">Grade:</label>
                                <input class="form-control" type="number" name="value" id="grade-<%=course.getId()%>-<%=student.getId()%>">
                                <button class="btn btn-primary ml-2" type="submit">Save</button>
                            </div>
                        </form>
                    </td>
                </tr>
<%
        }
%>
            </tbody>
        </table>
    </div>
</div>
<%
    }
%>

<%@include file="component/footer.jsp"%>
