package Service;

import javax.servlet.http.HttpServletResponse;

public class ResponseService
{
    public static void setGeneralHeader(HttpServletResponse resp)
    {
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
    }
}
