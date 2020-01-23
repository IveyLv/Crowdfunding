package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.AjaxResult;
import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
            result.setSuccess(true);
        } else {
            // 登录失败
            result.setSuccess(false);
        }
        return result;
    }
}
