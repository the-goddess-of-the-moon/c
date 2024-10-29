package com.example.class03response.response;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

@WebServlet("/responseDemo03")
public class responseDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String type=req.getParameter("type");
       switch (type) {
           case "img":
               getImage(req, resp);
               break;
               case "pdf":
                   getPDF(req, resp);
                   break;
           case "html":
               getHTML(req, resp);
               break;
               case "txt":
                   getTxt(req, resp);
                   break;
               default:
                   break;
       }
    }


    private  void getImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpg");
        String realPath = req.getServletContext().getRealPath("/");
        File file = new File(realPath+"/img/OIP-C.jpg");
        InputStream in  = new FileInputStream(file);
        int read=0;
        ServletOutputStream outputStream= resp.getOutputStream();
        while((read =in.read() )!=-1){
            outputStream.write(read);
        }
        outputStream.flush();
        outputStream.close();
    }
    private void getPDF(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/pdf");
        String realPath = req.getServletContext().getRealPath("/");
        File file = new File(realPath+"/pdf/软件2341史轩如的JAVAWEB.pdf");
        InputStream in  = new FileInputStream(file);
        int read=0;
        ServletOutputStream outputStream= resp.getOutputStream();
        while((read =in.read() )!=-1){
            outputStream.write(read);
        }
        outputStream.flush();
        outputStream.close();
    }
    private void getHTML(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String realPath = req.getServletContext().getRealPath("/");
         File file =new File(realPath+"/html/用户注册.html");
         InputStream in  = new FileInputStream(file);
         int read=0;
         ServletOutputStream outputStream= resp.getOutputStream();
        resp.setContentType("text/html;charset=utf-8");
         while((read =in.read() )!=-1){
             outputStream.write(read);
         }
         outputStream.flush();
         outputStream.close();
    }
    private void getTxt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/txt");
        String realPath = req.getServletContext().getRealPath("/");
        File file = new File(realPath+"/text/SHIXUANRU.txt");
        InputStream in  = new FileInputStream(file);
        resp.setContentType("text/html;charset=utf-8");
        int read=0;
        ServletOutputStream outputStream= resp.getOutputStream();
        while((read =in.read() )!=-1){
            outputStream.write(read);
        }
        outputStream.flush();
        outputStream.close();
    }

}
