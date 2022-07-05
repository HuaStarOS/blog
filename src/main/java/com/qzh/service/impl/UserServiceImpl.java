package com.qzh.service.impl;

import com.qzh.dao.UserDao;
import com.qzh.pojo.User;
import com.qzh.service.UserService;
import com.qzh.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: qzh
 * @Date: 2020/12/24 21:24
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
