package Api;

import Model.Teacher;
import Service.GradeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/grade")
public class GradeApi extends HttpServlet
{
    public static class PostModel
    {
        public int StudentId;
        public int CourseId;
        public int Value;

        public boolean Ok;

        public PostModel(HttpServletRequest req)
        {
            String sid = req.getParameter("sid");
            String cid = req.getParameter("cid");
            String value = req.getParameter("value");

            try
            {
                StudentId = Integer.parseInt(sid);
                CourseId = Integer.parseInt(cid);
                Value = Integer.parseInt(value);

                Ok = true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
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

        if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Teacher))
        {
            resp.sendRedirect(req.getContextPath() + "teacher.jsp?error=400");
            return;
        }

        GradeApi.PostModel model = new GradeApi.PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "teacher.jsp?error=401");
            return;
        }

        GradeService.setGrade(model.CourseId, model.StudentId, model.Value);

        resp.sendRedirect(req.getContextPath() + "/teacher.jsp");
    }
}
