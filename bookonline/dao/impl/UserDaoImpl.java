package com.example.bookonline.dao.impl;

import com.example.bookonline.dao.UserDao;
import com.example.bookonline.entity.User;
import com.example.bookonline.util.JdbcUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;



public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtil.getDataSource());


    @Override
    public int insertUser(User user) {
        // 1、编写 SQL
        String sql = "INSERT INTO t_user(account,password,nickname,avatar) VALUES (?,?,?,?)";
        return jdbcTemplate.update(sql, user.getAccount(), user.getPassword(), user.getNickname(), user.getAvatar());


    }
    @Override
        public User findUser(User userDto) {
        try {
            String sql="SELECT * FROM t_user WHERE account = ? AND password = ?";
            //调用query方法，比对账号和加密后的密码
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>
                    (User.class),userDto.getAccount(),userDto.getPassword());
        }catch(DataAccessException e){
            e.printStackTrace();
            return null;
        }
    }
}

