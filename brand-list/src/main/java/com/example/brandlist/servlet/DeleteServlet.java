package com.example.brandlist.servlet;

import com.example.brandlist.entity.Brand;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/delBrand")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

        // 获取品牌 ID
        String idParam = req.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);

            // 从 ServletContext 获取品牌列表
            ServletContext servletContext = req.getServletContext();
            List<Brand> brandList = (List<Brand>) servletContext.getAttribute("brands");

            // 获取并删除指定 ID 的品牌
            boolean removed = brandList.removeIf(brand -> brand.getId() == id);

            // 更新 ServletContext 中的品牌列表
            servletContext.setAttribute("brands", brandList);

            // 返回相应结果 如果品牌被成功删除，则返回成功消息；否则设置HTTP状态码为404（Not Found），并返回品牌未找到的错误信息。
            if (removed) {
                resp.getWriter().write("{\"message\": \"Brand deleted successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\": \"Brand not found\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid Brand ID format\"}");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"Failed to delete brand: " + e.getMessage() + "\"}");
        }
    }


}