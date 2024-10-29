package com.example.bookonline.service;

import com.example.bookonline.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 用户登录功能
     *
     * @param account  账号
     * @param password 密码
     * @return 登录成功的用户对象，如果没有找到或密码错误则返回 null
     */
    User signIn(String account, String password);



}