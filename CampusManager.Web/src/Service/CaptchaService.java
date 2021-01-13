package Service;

import Model.Captcha;

import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.Hashtable;

public class CaptchaService
{
    private static final Dictionary<String, Captcha> captchaManager = new Hashtable<>();

    public static BufferedImage createCaptcha(String identity)
    {
        Captcha captcha = Captcha.generate();

        captchaManager.put(identity, captcha);

        return captcha.getCaptchaImage();
    }

    public static boolean checkCaptcha(String identity, int answer)
    {
        Captcha captcha = captchaManager.get(identity);
        if (captcha == null || captcha.isExpired())
        {
            return false;
        }

        captchaManager.remove(identity);

        return captcha.getAnswer() == answer;
    }
}
