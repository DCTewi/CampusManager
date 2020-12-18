package Api;

import Model.UserType;
import Service.AccountService;
import Service.CaptchaService;
import Service.SecurityService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/api/login")
public class LoginApi extends HttpServlet
{
    public static class PostModel
    {
        public UserType UserType;
        public String Email;
        public String Password;
        public int CaptchaAnswer;

        public boolean Ok = false;

        public PostModel(HttpServletRequest req)
        {
            String i = req.getParameter("type");
            Email = req.getParameter("email");
            Password = req.getParameter("password");
            String s = req.getParameter("captcha");
            try
            {
                UserType = Model.UserType.valueOf(i);
                CaptchaAnswer = Integer.parseInt(s);

                Ok = ValidationService.validateEmail(Email);
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

        PostModel model = new PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=400");
            return;
        }

        if (!CaptchaService.checkCaptcha(session.getId(), model.CaptchaAnswer))
        {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=401");
            return;
        }

        if (!SecurityService.checkPassword(model.UserType, model.Email ,model.Password))
        {
            resp.sendRedirect(req.getContextPath() + "/login.jsp?error=402");
            return;
        }

        session.setAttribute("logged", true);
        session.setAttribute("usertype", model.UserType);
        session.setAttribute("email", model.Email);
        session.setAttribute("profile", AccountService.getProfileByEmail(model.UserType, model.Email));

//        Cookie cookie = new Cookie("sid", session.getId());
//        cookie.setMaxAge(60);
//        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath());
    }
}
