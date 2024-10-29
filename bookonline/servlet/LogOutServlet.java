package com.example.bookonline.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取当前会话
        HttpSession session = req.getSession(false);

        if (session != null) {
            // 从会话中移除用户信息
            session.removeAttribute("user");

            // 使会话失效
            session.invalidate();
        }

        // 重定向到登录页面
        resp.sendRedirect(req.getContextPath() + "/login-page");
    }
}
