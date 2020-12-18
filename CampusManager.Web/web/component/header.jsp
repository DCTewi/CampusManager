<%@ page import="Model.Administrator" %>
<%@ page import="Model.Teacher" %>
<%@ page import="Model.Student" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>${param.title} - Campus Manager</title>

    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/site.css" rel="stylesheet" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <a class="navbar-brand" href="#">Campus Manager</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <%
                if (session.getAttribute("logged") != null)
                {
            %>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile.jsp">Profile</a>
            </li>
            <%
                    if (session.getAttribute("profile") instanceof Administrator)
                    {
            %>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/dashboard.jsp">Dashboard</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/courses.jsp">Courses</a>
            </li>
            <%
                    }
                    if (session.getAttribute("profile") instanceof Teacher)
                    {
            %>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/teacher.jsp">Teacher</a>
            </li>
            <%
                    }
                    if (session.getAttribute("profile") instanceof Student)
                    {
            %>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/student.jsp">Student</a>
            </li>
            <%
                    }
                }
            %>
        </ul>
    </div>
    <%
        if (session.getAttribute("logged") == null)
        {
    %>
    <div class="form-inline">
        <a class="btn btn-outline-primary" href="login.jsp">Login</a>
    </div>
    <%
        } else {
    %>
    <div class="form-inline">
        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/api/logout">Logout</a>
    </div>
    <%
        }
    %>
</nav>
    <div class="container">