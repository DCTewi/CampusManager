package Api;

import Service.CaptchaService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@WebServlet("/api/captcha")
public class CaptchaApi extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        BufferedImage captcha = CaptchaService.createCaptcha(session.getId());

        resp.setContentType("image/jpeg");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
//        encoder.encode(captcha);
        ImageIO.write(captcha, "jpg", stream);

        byte[] res = stream.toByteArray();
        resp.setContentLength(res.length);
        resp.getOutputStream().write(res);
        stream.close();
        resp.getOutputStream().close();
    }
}
