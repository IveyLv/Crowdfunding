package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.AjaxResult;
import com.Ivey.crowdfunding.bean.Page;
import com.Ivey.crowdfunding.bean.Role;
import com.Ivey.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 角色维护表现层
 * @Author IveyLv
 * @Date 2020/2/8 17:35
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/index")
    public String index() {
        return "role/index";
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

            List<Role> roles = roleService.queryPageData(map);

            // 总的数据条数
            int totalSize = roleService.queryPageCount(map);
            // 最大页码
            int totalNo = 0;
            if (totalSize % pageSize == 0) {
                totalNo = totalSize / pageSize;
            } else {
                totalNo = totalSize / pageSize + 1;
            }

            Page<Role> rolePage = new Page<>();
            rolePage.setDatas(roles);
            rolePage.setCurrentPageNo(pageNo);
            rolePage.setTotalNo(totalNo);
            rolePage.setTotalSize(totalSize);

            ajaxResult.setData(rolePage);
            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/add")
    public String add() {
        return "role/add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(Role role) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            roleService.insertRole(role);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Role role = roleService.queryById(id);
        model.addAttribute("role", role);

        return "role/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Role role) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            roleService.updateRole(role);

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

        roleService.deleteRoleById(id);

        try {

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/deletes")
    @ResponseBody
    public Object deletes(Integer[] roleId) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("roleIds", roleId);

            roleService.deleteRoles(map);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/assign")
    public String assign() {
        return "role/assign";
    }

    @RequestMapping("/doAssign")
    @ResponseBody
    public Object doAssign(Integer roleId, Integer[] permissionIds) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", roleId);
            map.put("permissionIds", permissionIds);

            roleService.insertRolePermission(map);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }
}
