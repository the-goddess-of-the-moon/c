package com.example.class04cookiesession.cookie;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookieDemo01")
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.创建Cookie对象
        Cookie cookie = new Cookie("username", "zhangsan");
        //2.通过response发送Cookie
        resp.addCookie(cookie);
        //3. cookie存活时间
        cookie.setMaxAge(60*60*24*7);




    }
}
