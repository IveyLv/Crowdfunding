package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.AjaxResult;
import com.Ivey.crowdfunding.bean.Permission;
import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.service.PermissionService;
import com.Ivey.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/20 20:08
 * @Version 1.0
 */
@Controller
public class DispatcherController {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/error")
    public String error() {
        return "error";
    }

    /**
     * 执行登录
     * @return
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public String doLogin(User user, Model model) {
        User dbUser = userService.queryForLogin(user);

        if (dbUser != null) {
            // 登录成功
            return "main";
        } else {
            // 登录失败
            String errorMsg = "用户名或密码不正确，请重新输入";
            model.addAttribute("errorMsg", errorMsg);
            return "redirect:login";
        }
    }

    @RequestMapping("/doAjaxLogin")
    @ResponseBody
    public Object doAjaxLogin(User user, HttpSession session) {

        AjaxResult result = new AjaxResult();

        User dbUser = userService.queryForLogin(user);

        if (dbUser != null) {
            // 登录成功
            session.setAttribute("loginUser", dbUser);

            // 获取用户权限信息
            List<Permission> permissions = permissionService.queryPermissionsByUser(dbUser);
            Permission root = null;
            HashSet<String> uriSet = new HashSet<>();
            Map<Integer, Permission> permissionMap = new HashMap<>();
            for (Permission permission : permissions) {
                permissionMap.put(permission.getId(), permission);
                if (permission.getUrl() != null && !"".equals(permission.getUrl())) {
                    uriSet.add(session.getServletContext().getContextPath() + permission.getUrl());
                }
            }
            session.setAttribute("authUriSet", uriSet);

            for (Permission permission : permissions) {
                if (permission.getPid() == 0) {
                    root = permission;
                } else {
                    Permission parent = permissionMap.get(permission.getPid());
                    parent.getChildren().add(permission);
                }
            }
            session.setAttribute("rootPermission", root);
            result.setSuccess(true);
        } else {
            // 登录失败
            result.setSuccess(false);
        }
        return result;
    }
}
