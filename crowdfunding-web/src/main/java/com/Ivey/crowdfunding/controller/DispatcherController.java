package com.Ivey.crowdfunding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/20 20:08
 * @Version 1.0
 */
@Controller
public class DispatcherController {

    @RequestMapping("login")
    public String login() {
        return "login";
    }
}
