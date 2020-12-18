<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<jsp:include page="component/header.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>

<h1>Login</h1>
<br>

<script src="js/validator.js"></script>
<div class="card card-body col-8">
    <form method="post" action="${pageContext.request.contextPath}/api/login" name="loginForm" onchange="checkFormLogin()" onsubmit="return checkFormLogin()">
        <div class="form-group">
            <label>Role</label><br>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="roleRadioAdmin" name="type" class="custom-control-input" value="Administrator" checked>
                <label class="custom-control-label" for="roleRadioAdmin">Admin</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="roleRadioTeacher" name="type" class="custom-control-input" value="Teacher">
                <label class="custom-control-label" for="roleRadioTeacher">Teacher</label>
            </div>
            <div class="custom-control custom-radio custom-control-inline">
                <input type="radio" id="roleRadioStudent" name="type" class="custom-control-input" value="Student">
                <label class="custom-control-label" for="roleRadioStudent">Student</label>
            </div>
        </div>
        <div class="form-group">
            <label for="loginEmail">Email</label>
            <input class="form-control" type="text" name="email" id="loginEmail" />
        </div>
        <div class="form-group">
            <label for="loginPass">Password</label>
            <input class="form-control" type="password" name="password" id="loginPass" />
        </div>
        <div class="form-group">
            <label for="loginCaptcha">Captcha</label>
            <img src="${pageContext.request.contextPath}/api/captcha" class="figure-img img-fluid rounded" alt="captcha" />
            <input class="form-control" type="text" name="captcha" id="loginCaptcha" />
        </div>
        <button class="btn btn-outline-primary" type="submit">Login</button>
    </form>
    <br>
    <a href="register.jsp">Register a new account</a>
</div>


<%@include file="component/footer.jsp"%>