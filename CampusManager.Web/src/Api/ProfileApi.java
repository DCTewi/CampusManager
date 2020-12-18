package Api;

import Model.Administrator;
import Model.Student;
import Model.Teacher;
import Service.AccountService;
import Service.SecurityService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/profile")
public class ProfileApi extends HttpServlet
{
    public static class PostModel
    {
        public Model.UserType UserType;
        public String UserName;
        public String Password;
        public String NewPassword;
        public String NewPasswordConfirm;
        public String Phone;
        public String AvatarUrl;

        public boolean Ok;

        public PostModel(HttpServletRequest req)
        {
            String i = req.getParameter("type");
            UserName = req.getParameter("username");
            Password = req.getParameter("password");
            NewPassword = req.getParameter("newPassword");
            NewPasswordConfirm = req.getParameter("newPasswordConfirm");
            Phone = req.getParameter("phone");
            AvatarUrl = req.getParameter("avatarUrl");

            try
            {
                UserType = Model.UserType.valueOf(i);

                Ok = (Phone == null || Phone.equals("") || ValidationService.validatePhone(Phone)) &&
                    (AvatarUrl == null || AvatarUrl.equals("") || ValidationService.validateUrl(AvatarUrl)) &&
                        ValidationService.validateUserName(UserName) &&
                        ValidationService.validatePassword(Password);

                if (NewPassword != null && !NewPassword.equals(""))
                {
                    Ok &= ValidationService.validatePassword(NewPasswordConfirm) &&
                            ValidationService.validatePassword(NewPassword) &&
                            NewPassword.equals(NewPasswordConfirm);
                }
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
        resp.sendRedirect(req.getContextPath() + "/profile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();

        if (session.getAttribute("logged") == null)
        {
            resp.sendRedirect(req.getContextPath() + "/profile.jsp?error=400");
            return;
        }

        ProfileApi.PostModel model = new ProfileApi.PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "/profile.jsp?error=401");
            return;
        }

        if (!SecurityService.checkPassword(model.UserType, (String) session.getAttribute("email") ,model.Password))
        {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=402");
            return;
        }

        switch (model.UserType)
        {
            case Administrator:
                Administrator admin = (Administrator) session.getAttribute("profile");
                AccountService.updateProfile(admin
                        .setUserName(model.UserName)
                );
                break;
            case Teacher:
                Teacher teacher = (Teacher) session.getAttribute("profile");
                AccountService.updateProfile(teacher
                        .setUserName(model.UserName)
                        .setPhone(model.Phone)
                        .setAvatarUrl(model.AvatarUrl)
                );
                break;
            case Student:
                Student student = (Student) session.getAttribute("profile");
                AccountService.updateProfile(student
                        .setUserName(model.UserName)
                        .setPhone(model.Phone)
                        .setAvatarUrl(model.AvatarUrl)
                );
                break;
        }

        if (model.NewPassword != null && !model.NewPassword.equals("") && model.NewPassword.equals(model.NewPasswordConfirm))
        {
            byte[] salt = SecurityService.generateSalt();
            switch (model.UserType)
            {
                case Administrator:
                    Administrator admin = (Administrator) session.getAttribute("profile");
                    AccountService.updateProfile(admin
                            .setSecuritySalt(new String(salt))
                            .setPasswordHash(SecurityService.hashPassword(model.NewPassword, salt))
                    );
                    break;
                case Teacher:
                    Teacher teacher = (Teacher) session.getAttribute("profile");
                    AccountService.registerTeacher(teacher
                            .setSecuritySalt(new String(salt))
                            .setPasswordHash(SecurityService.hashPassword(model.NewPassword, salt))
                    );
                    break;
                case Student:
                    Student student = (Student) session.getAttribute("profile");
                    AccountService.registerStudent(student
                            .setSecuritySalt(new String(salt))
                            .setPasswordHash(SecurityService.hashPassword(model.NewPassword, salt))
                    );
                    break;
            }
        }

        resp.sendRedirect(req.getContextPath() + "/profile.jsp");
    }
}
