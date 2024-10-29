package com.example.class03response.response;

//description：设置

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/responseDemo01")
public class responseDemo01 extends HttpServlet {

    public responseDemo01() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置响应码
        resp.setStatus(302);
        //2.设置响应头 location
       // resp.setHeader("location","/responseDemo02");
        //3.设置编码
        resp.setContentType("text/html;charset=UTF-8");
        //4.设置响应结果
        resp.getWriter().write("您好 ResponseDemo01");
    }
}
