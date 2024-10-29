package com.example.bookonline.verify;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 160;
        int height = 45;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        String str = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHGDSAZXCVBNM";
        Random random = new Random();
        StringBuilder captchaBuilder = new StringBuilder();
        for (int i = 0; i < 6; i++) { // 生成6位验证码
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            captchaBuilder.append(ch);
            Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            g.setColor(color);
            Font font = new Font("Dialog", Font.BOLD, 20);
            g.setFont(font);
            g.drawString(ch + " ", width / 6 * i, height / 2);
        }

        String captcha = captchaBuilder.toString();
        HttpSession session = req.getSession();
        session.setAttribute("captcha", captcha); // 将验证码存储在 session 中

        resp.setContentType("image/jpeg");
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}