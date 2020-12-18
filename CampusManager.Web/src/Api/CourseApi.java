package Api;

import Model.Administrator;
import Model.Course;
import Model.UserType;
import Service.AccountService;
import Service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/api/course")
public class CourseApi extends HttpServlet
{
    public static class PostModel
    {
        public String CourseName;
        public String Classroom;
        public String CourseTime;
        public int TeacherId;

        public boolean Ok;

        public PostModel(HttpServletRequest req)
        {
            String i = req.getParameter("teacherId");
            CourseName = req.getParameter("name");
            CourseTime = req.getParameter("time");
            Classroom = req.getParameter("classroom");

            try
            {
                TeacherId = Integer.parseInt(i);

                Ok = AccountService.getProfileById(UserType.Teacher, TeacherId) != null;
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

        if (session.getAttribute("logged") == null || !(session.getAttribute("profile") instanceof Administrator))
        {
            resp.sendRedirect(req.getContextPath() + "/courses.jsp?error=400");
            return;
        }

        CourseApi.PostModel model = new CourseApi.PostModel(req);

        if (!model.Ok)
        {
            resp.sendRedirect(req.getContextPath() + "/courses.jsp?error=401");
            return;
        }

        CourseService.insertCourse(new Course()
                .setCourseName(model.CourseName)
                .setCourseTime(model.CourseTime)
                .setClassroom(model.Classroom)
                .setTeacherId(model.TeacherId)
        );

        resp.sendRedirect(req.getContextPath() + "/courses.jsp");
    }
}
