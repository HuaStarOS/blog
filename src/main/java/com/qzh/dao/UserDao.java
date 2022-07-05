package com.qzh.dao;

import com.qzh.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: qzh
 * @Date: 2020/12/24 21:26
 */
public interface UserDao extends JpaRepository<User,Long > {

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);

}
