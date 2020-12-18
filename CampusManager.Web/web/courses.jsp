<%@ page import="Model.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="Service.CourseService" %>
<%@ page import="Service.AccountService" %>
<%@ page import="Model.UserType" %>
<%@ page import="Model.Teacher" %>
<%@ page import="java.util.Objects" %>
<%@ page import="Model.Administrator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Courses"/>
</jsp:include>

<h1>Courses</h1><br>

<%
    if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Administrator))
    {
        response.sendRedirect(request.getContextPath());
        return;
    }
%>

<button type="button" class="btn btn-primary mb-3" data-toggle="modal" data-target="#editModal">
    Create
</button>
<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Create Course</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/api/course"
                      name="profileForm" id="profileForm">
                    <div class="form-group">
                        <label for="name">Course Name</label>
                        <input class="form-control" type="text" name="name" id="name"/>
                    </div>
                    <div class="form-group">
                        <label for="time">Course Time</label>
                        <input class="form-control" type="text" name="time" id="time"/>
                    </div>
                    <div class="form-group">
                        <label for="classroom">Classroom</label>
                        <input class="form-control" type="text" name="classroom" id="classroom"/>
                    </div>
                    <div class="form-group">
                        <label for="teacherId">Teacher</label>
                        <select class="custom-select" name="teacherId" id="teacherId">
                            <%
                                for (Object tObject : Objects.requireNonNull(AccountService.getProfileOf(UserType.Teacher)))
                                {
                                    Teacher teacher = (Teacher) tObject;
                            %>
                            <option value="<%=teacher.getId()%>"><%=teacher.getUserName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" form="profileForm" class="btn btn-primary">Save</button>
            </div>
        </div>
    </div>
</div>

<table class="table table-hover">
    <thead>
    <tr>
        <th>Id</th>
        <th>Teacher</th>
        <th>Time</th>
        <th>Classroom</th>
    </tr>
    </thead>
    <tbody>
<%
    List<Course> courses = CourseService.getAllCourses();

    for (Course course : courses)
    {
%>
        <tr>
            <th><%=course.getId()%></th>
            <td><%=((Teacher) Objects.requireNonNull(AccountService.getProfileById(UserType.Teacher, course.getTeacherId()))).getUserName()%></td>
            <td><%=course.getCourseTime()%></td>
            <td><%=course.getClassroom()%></td>
        </tr>
<%
    }
%>
    </tbody>
</table>

<%@include file="component/footer.jsp"%>