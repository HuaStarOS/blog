package com.qzh.service;

import com.qzh.pojo.User;

/**
 * @Author: qzh
 * @Date: 2020/12/24 21:22
 */
public interface UserService {

    /**
     * 通过用户名和密码检查用户
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username,String password);

}
