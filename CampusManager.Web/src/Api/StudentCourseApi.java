package Api;

import Model.Teacher;
import Service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/sc")
public class StudentCourseApi extends HttpServlet
{
    public static class PostModel
    {
        public int StudentId;
        public int CourseId;

        public boolean Ok;

        public PostModel(HttpServletRequest req)
        {
            String sid = req.getParameter("sid");
            String cid = req.getParameter("cid");

            try
            {
                StudentId = Integer.parseInt(sid);
                CourseId = Integer.parseInt(cid);

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

        PostModel model = new PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "teacher.jsp?error=401");
            return;
        }

        CourseService.addStudentTo(model.StudentId, model.CourseId);

        resp.sendRedirect(req.getContextPath() + "/teacher.jsp");
    }
}
