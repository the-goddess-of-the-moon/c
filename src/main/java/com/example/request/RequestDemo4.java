package com.example.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet("/RequestDemo4")
public class RequestDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //根据参数名获取参数值
        String username = req.getParameter("username");
        System.out.println("username" + username);

        //根据参数名获取参数值的数组
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println("hobby" + hobby);
        }

        //获取所有参数的参数名称
        System.out.println("===============");
        System.out.println("/获取所有参数的参数名称");
        Enumeration<String> parameterNames=req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            System.out.println("参数名称"+name);
            String value=req.getParameter(name);
            System.out.println("参数值"+value);
        }

        //获取所有参数的Map集合
        System.out.println("================");
        System.out.println("获取所有参数的Map集合");
        Map<String,String[]> paraeterMap=req.getParameterMap();
        //遍历获取
        Set<String> keySet=paraeterMap.keySet();
        for(String name:keySet){
            System.out.println("参数名称"+name);
            String[] values=paraeterMap.get(name);
            for(String value:values){
                System.out.println(value);
            }
        }
    }}