package Model;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaTest
{

    @Test
    void generate()
    {
        Captcha captcha = Captcha.generate();

        File file = new File("captcha.jpg");
        try
        {
            ImageIO.write(captcha.getCaptchaImage(), "jpeg", file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println(captcha.getAnswer());
        System.out.println(captcha.isExpired());
    }
}