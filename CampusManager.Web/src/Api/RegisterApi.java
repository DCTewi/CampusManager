package Api;

import Model.Administrator;
import Model.Student;
import Model.Teacher;
import Service.AccountService;
import Service.CaptchaService;
import Service.SecurityService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/register")
public class RegisterApi extends HttpServlet
{
    public static class PostModel
    {
        public Model.UserType UserType;
        public String UserName;
        public String Email;
        public String Password;
        public String Phone;
        public int CaptchaAnswer;

        public boolean Ok;

        public PostModel(HttpServletRequest req)
        {
            String i = req.getParameter("type");
            String s = req.getParameter("captcha");
            UserName = req.getParameter("username");
            Email = req.getParameter("email");
            Password = req.getParameter("password");
            Phone = req.getParameter("phone");

            try
            {
                UserType = Model.UserType.valueOf(i);
                CaptchaAnswer = Integer.parseInt(s);

                Ok = ValidationService.validateEmail(Email) &&
                        ValidationService.validatePhone(Phone) &&
                        ValidationService.validateUserName(UserName) &&
                        ValidationService.validatePassword(Password);
            }
            catch (Exception ignored)
            {
                Ok = false;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendRedirect(req.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        RegisterApi.PostModel model = new RegisterApi.PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "/register.jsp?error=400");
            return;
        }

        if (!CaptchaService.checkCaptcha(session.getId(), model.CaptchaAnswer))
        {
            resp.sendRedirect(req.getContextPath() + "/register.jsp?error=401");
            return;
        }

        byte[] salt = SecurityService.generateSalt();

        switch (model.UserType)
        {
            case Administrator:
                AccountService.registerAdmin(new Administrator()
                        .setEmail(model.Email)
                        .setUserName(model.UserName)
                        .setSecuritySalt(new String(salt))
                        .setPasswordHash(SecurityService.hashPassword(model.Password, salt))
                );
                break;
            case Teacher:
                AccountService.registerTeacher(new Teacher()
                        .setEmail(model.Email)
                        .setUserName(model.UserName)
                        .setPhone(model.Phone)
                        .setSecuritySalt(new String(salt))
                        .setPasswordHash(SecurityService.hashPassword(model.Password, salt))
                );
                break;
            case Student:
                AccountService.registerStudent(new Student()
                        .setEmail(model.Email)
                        .setUserName(model.UserName)
                        .setPhone(model.Phone)
                        .setSecuritySalt(new String(salt))
                        .setPasswordHash(SecurityService.hashPassword(model.Password, salt))
                );
                break;
        }

        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }
}
