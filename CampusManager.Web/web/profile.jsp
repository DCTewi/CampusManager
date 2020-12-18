<%@ page import="Model.UserType" %>
<%@ page import="Model.Administrator" %>
<%@ page import="Model.Teacher" %>
<%@ page import="Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Profile"/>
</jsp:include>

<h1>Profile</h1><br>

<div class="card card-body">
<%
    Object logged = session.getAttribute("logged");
    Object profile = session.getAttribute("profile");
    if (logged != null && profile != null)
    {
        switch ((UserType) session.getAttribute("usertype"))
        {
            case Administrator:
                Administrator admin = (Administrator) profile;
%>
    <h5 class="card-title">Admin</h5>
    <div class="card-text">
        <table class="table table-hover mb-1">
            <tbody>
                <tr>
                    <th scope="row">Username</th>
                    <td><%=admin.getUserName()%></td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td><%=admin.getEmail()%></td>
                </tr>
            </tbody>
        </table>
<%
                break;
            case Teacher:
                Teacher teacher = (Teacher) profile;
%>
    <h5 class="card-title">Teacher</h5>
    <div class="card-text">
        <table class="table table-hover mb-1">
            <tbody>
            <tr>
                <th scope="row">Avatar</th>
                <td><img src="<%=teacher.getAvatarUrl()%>" height="120px"></td>
            </tr>
            <tr>
                <th scope="row">Username</th>
                <td><%=teacher.getUserName()%></td>
            </tr>
            <tr>
                <th scope="row">Email</th>
                <td><%=teacher.getEmail()%></td>
            </tr>
            <tr>
                <th scope="row">Phone</th>
                <td><%=teacher.getPhone()%></td>
            </tr>
            </tbody>
        </table>
<%
                break;
            case Student:
                Student student = (Student) profile;
%>
    <h5 class="card-title">Student</h5>
    <div class="card-text">
        <table class="table table-hover mb-1">
            <tbody>
            <tr>
                <th scope="row">Avatar</th>
                <td><% if (student.getAvatarUrl() != null) { %>
                    <img src="<%=student.getAvatarUrl()%>" height="120px">
                    <% } %></td>
            </tr>
            <tr>
                <th scope="row">Username</th>
                <td><%=student.getUserName()%></td>
            </tr>
            <tr>
                <th scope="row">Email</th>
                <td><%=student.getEmail()%></td>
            </tr>
            <tr>
                <th scope="row">Phone</th>
                <td><%=student.getPhone()%></td>
            </tr>
            </tbody>
        </table>
<%
                break;
        }
    } else {
        response.sendRedirect(request.getContextPath());
        return;
    }
%>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editModal">
            Edit Profile
        </button>
        <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editModalLabel">Edit Profile</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <script src="js/validator.js"></script>
                        <form method="post" action="${pageContext.request.contextPath}/api/profile"
                              name="profileForm" id="profileForm"
                              onchange="checkFormProfileAdmin()" onsubmit="checkFormProfileAdmin()">
                            <input type="hidden" name="type" value="<%=
                                session.getAttribute("profile") instanceof Administrator ? "Administrator" :
                                session.getAttribute("profile") instanceof Teacher ? "Teacher" : "Student"
                            %>">
                            <div class="form-group">
                                <label for="userName">Username</label>
                                <input class="form-control" type="text" name="username" id="userName" />
                            </div>
                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <input class="form-control" type="text" name="phone" id="phone" />
                            </div>
                            <div class="form-group">
                                <label for="avatarUrl">Avatar URL</label>
                                <input class="form-control" type="text" name="avatarUrl" id="avatarUrl" />
                            </div>
                            <div class="form-group">
                                <label for="password">Password *</label>
                                <input class="form-control" type="password" name="password" id="password" />
                            </div>
                            <div class="form-group">
                                <label for="newPassword">New Password</label>
                                <input class="form-control" type="password" name="newPassword" id="newPassword" />
                            </div>
                            <div class="form-group">
                                <label for="newPasswordConfirm">New Password Confirm</label>
                                <input class="form-control" type="password" name="newPasswordConfirm" id="newPasswordConfirm" />
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
    </div>
</div>
<%@include file="component/footer.jsp"%>