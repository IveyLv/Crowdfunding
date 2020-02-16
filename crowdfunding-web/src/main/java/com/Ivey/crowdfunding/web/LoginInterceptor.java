package com.Ivey.crowdfunding.web;

import com.Ivey.crowdfunding.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description 登录拦截器
 * @Author IveyLv
 * @Date 2020/2/16 16:23
 * @Version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在控制器执行之前完成业务逻辑操作
     * 方法的返回值决定逻辑是否继续执行，true：表示继续执行，false：表示不再继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 判断当前用户是否已经登录
        HttpSession session = httpServletRequest.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            String path = session.getServletContext().getContextPath();
            httpServletResponse.sendRedirect(path + "/login");
            return false;
        } else {
            return true;
        }
    }

    /**
     * 在控制器执行完毕之后进行的逻辑操作
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在完成视图渲染之后，执行此方法
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
