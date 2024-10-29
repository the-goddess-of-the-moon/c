package com.example.bookonline.servlet;

import com.example.bookonline.dao.UserDao;
import com.example.bookonline.dao.impl.UserDaoImpl;
import com.example.bookonline.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String account = req.getParameter("account");
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");

        // 验证账户格式
        if (!isValidAccount(account)) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('账号格式不正确，请填写手机号或邮箱');window.history.back();</script>");
            return;
        }

        if (nickname == null || nickname.isEmpty()) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('昵称不能为空');window.history.back();</script>");
            return;
        }

        // 检查账号是否已存在
        User existingUser = User.builder().account(account).build();
        if (userDao.findUser(existingUser)!= null) {
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('该账号已存在，请更换账号');window.history.back();</script>");
            return;
        }

        // 创建User对象
        User user = new User();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPassword(password);

        // 调用UserDao的insertUser方法保存用户信息到数据库
        int result = userDao.insertUser(user);
        if (result > 0) {
            // 注册成功，重定向到登录页面
            resp.sendRedirect(req.getContextPath() + "/login-page");
        } else {
            // 注册失败，给出提示信息
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write("<script>alert('注册失败，请检查输入的信息');window.history.back();</script>");
        }
    }

    private boolean isValidAccount(String account) {
        if (account == null) {
            return false;
        }
        String phoneRegex = "^\\d{11}$";
        String emailRegex = "^[A Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(phoneRegex, account) || Pattern.matches(emailRegex, account);
    }
}