package com.qzh.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: qzh
 * @Date: 2020/12/24 23:59
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        Object attribute = request.getSession().getAttribute("user");

        if (attribute == null) {
            response.sendRedirect("/admin");
            return false;
        }else {
            return true;
        }

    }
}
