package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:02
 * @Version 1.0
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public Object getAll() {
        List<User> users = userService.queryAll();

        return users;
    }
}
