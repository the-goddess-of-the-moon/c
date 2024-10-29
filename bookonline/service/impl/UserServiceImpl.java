package com.example.bookonline.service.impl;

import com.example.bookonline.dao.UserDao;
import com.example.bookonline.dao.impl.UserDaoImpl;
import com.example.bookonline.entity.User;
import com.example.bookonline.service.UserService;


public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();
    private static final String DEFAULT_AVATAR_PATH = "https://ts1.cn.mm.bing.net/th/id/R - C.67c70ed0eae200d69455a91b43a9f407?rik=JuGKKn2ExyU9PA&riu=http%3a%2f%2fwww.sucaijishi.com%2fuploadfile%2f2018%2f0508%2f20180508023717621.png&ehk=KU69IZrBC4o1Y88Iab8ZKx9FGLndJcignKsCkX31gds%3d&risl=&pid=ImgRaw&r=0&sres=1&sresct=1";

    @Override
    public User signIn(String account, String password) {
        User user = User.builder().account(account).password(password).build();
        User foundUser = userDao.findUser(user);
        if (foundUser!= null) {
            if (foundUser.getAvatar() == null) {
                // 如果用户头像为空，设置默认头像
                foundUser.setAvatar(DEFAULT_AVATAR_PATH);
            }
            return foundUser;
        }
        return null;
    }


}