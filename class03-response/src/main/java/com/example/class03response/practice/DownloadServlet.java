package com.example.class03response.practice;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //获取文件名
     String filename=req.getParameter("filename");
     //获取servletContext 对象
        ServletContext servletContext=req.getServletContext();
        String realPath=servletContext.getRealPath("/img/"+filename);
        //用字节流关联
        FileInputStream fileInputStream=new FileInputStream(realPath);

        //获取问价的媒体类型
        String mimeType=servletContext.getMimeType(filename);
      //设置响应的Content-Type
        resp.setContentType(mimeType);
        //设置响应头的打开方式
        resp.setHeader("content-disposition", "attachment; filename="+filename);
        //将输入的数据写道输出流中
        ServletOutputStream outputStream=resp.getOutputStream();
        byte[] buffer=new byte[1024*8];
        int len=0;
        while((len=fileInputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        fileInputStream.close();
    }
}
