package Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Random;

public class Captcha
{
    private BufferedImage CaptchaImage;
    private int Answer;
    private final Calendar ExpirationTime;

    private static final int ExpirationMinutes = 10;
    private static final int width = 220;
    private static final int height = 50;
    private static final int paddingY = 5;
    private static final int paddingX = 10;
    private static final int fontsize = height - paddingY * 2;

    public static Captcha generate()
    {
        Captcha captcha = new Captcha();

        captcha.CaptchaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = captcha.CaptchaImage.getGraphics();

        graphics.setColor(Color.getHSBColor(0, 0, 0.988f));
        graphics.fillRect(0, 0, width, height);

        Random random = new Random();

        captcha.Answer = random.nextInt(50) * 2;
        int a = random.nextInt(captcha.Answer / 2);
        int b = captcha.Answer - a;

        StringBuilder builder = new StringBuilder()
                .append(a)
                .append("+")
                .append(b)
                .append("=");

        for (int i = 0; i < builder.length(); i++)
        {
            Font font = new Font("monospace",
                    random.nextBoolean() ? Font.BOLD : random.nextBoolean() ? Font.ITALIC : Font.PLAIN,
                    random.nextInt(fontsize / 3) + fontsize / 3 * 2);
                    //fontsize);
            graphics.setFont(font);
            graphics.setColor(Color.BLACK);
            graphics.drawString(""+builder.charAt(i), i * fontsize / 5 * 3 + paddingX, height - paddingY * 2);

            graphics.setColor(Color.DARK_GRAY);
            graphics.drawLine(random.nextInt(width), random.nextInt(height),
                    random.nextInt(width), random.nextInt(height));
        }

        return captcha;
    }

    private Captcha()
    {
        ExpirationTime = Calendar.getInstance();
        ExpirationTime.add(Calendar.MINUTE, ExpirationMinutes);
    }

    public BufferedImage getCaptchaImage()
    {
        return CaptchaImage;
    }

    public int getAnswer()
    {
        return Answer;
    }

    public boolean isExpired()
    {
        return Calendar.getInstance().after(ExpirationTime);
    }
}
