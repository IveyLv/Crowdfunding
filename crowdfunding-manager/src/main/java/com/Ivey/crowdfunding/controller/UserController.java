package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.AjaxResult;
import com.Ivey.crowdfunding.bean.Page;
import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户表现层
 * @Author IveyLv
 * @Date 2020/1/22 13:10
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "user/index";
    }

    @RequestMapping("/index1")
    public String index1(@RequestParam(required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                        Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<User> users = userService.queryPageData(map);
        model.addAttribute("users", users);

        // 当前页
        model.addAttribute("currentPageNo", pageNo);
        // 总的数据条数
        int totalSize = userService.queryPageCount(map);
        // 最大页码
        int totalNo = 0;
        if (totalSize % pageSize == 0) {
            totalNo = totalSize / pageSize;
        } else {
            totalNo = totalSize / pageSize + 1;
        }
        model.addAttribute("totalNo", totalNo);

        return "user/index1";
    }

    @RequestMapping("/pageQuery")
    @ResponseBody
    public Object pageQuery(Integer pageNo, Integer pageSize, String queryText) {

        AjaxResult ajaxResult = new AjaxResult();

        try {
            // 分页查询
            Map<String, Object> map = new HashMap<>();
            map.put("pageNo", (pageNo - 1) * pageSize);
            map.put("pageSize", pageSize);
            map.put("queryText", queryText);

            List<User> users = userService.queryPageData(map);

            // 总的数据条数
            int totalSize = userService.queryPageCount(map);
            // 最大页码
            int totalNo = 0;
            if (totalSize % pageSize == 0) {
                totalNo = totalSize / pageSize;
            } else {
                totalNo = totalSize / pageSize + 1;
            }

            Page<User> userPage = new Page<>();
            userPage.setDatas(users);
            userPage.setCurrentPageNo(pageNo);
            userPage.setTotalNo(totalNo);
            userPage.setTotalSize(totalSize);

            ajaxResult.setData(userPage);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/add")
    public String add() {
        return "user/add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(User user) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            user.setCreateTime(format.format(new Date()));
            user.setPassword("123456");
            userService.insertUser(user);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        User user = userService.queryById(id);
        model.addAttribute("user", user);

        return "user/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(User user) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            userService.updateUser(user);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            userService.deleteUserById(id);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public Object deletes(Integer[] userId) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("userIds", userId);
            userService.deleteUsers(map);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }
}
