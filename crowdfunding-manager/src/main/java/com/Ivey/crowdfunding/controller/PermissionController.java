package com.Ivey.crowdfunding.controller;

import com.Ivey.crowdfunding.bean.AjaxResult;
import com.Ivey.crowdfunding.bean.Permission;
import com.Ivey.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 许可维护表现层
 * @Author IveyLv
 * @Date 2020/2/12 20:57
 * @Version 1.0
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index() {
        return "permission/index";
    }

    @RequestMapping("/loadData")
    @ResponseBody
    public Object loadData() {
        List<Permission> permissions = new ArrayList<>();

        /*// section 1: 模拟树节点数据显示
        Permission root = new Permission();
        root.setName("顶级节点");

        Permission child = new Permission();
        child.setName("子节点");

        root.getChildren().add(child);
        permissions.add(root);*/

        /*// section 2: 固定手动构建树节点信息
        // 获取根节点
        Permission root = permissionService.queryRootPermission();

        // 获取二级节点
        List<Permission> childPermissions = permissionService.queryChildPermissions(root.getId());
        // 获取二级节点的子节点
        for (Permission childPermission : childPermissions) {
            List<Permission> childChildPermissions = permissionService.queryChildPermissions(permission.getId());
            childPermission.setChildren(childChildPermissions);
        }

        root.setChildren(childPermissions);
        permissions.add(root);*/

        /*// section 3: 递归获取树节点信息
        Permission parent = new Permission();
        parent.setId(0);

        queryChildPermissions(parent);*/

        /*// section 4: 通过嵌套 for 循环获取节点数据信息
        List<Permission> permissionList = permissionService.queryAll();

        for (Permission permission : permissionList) {
            // 子节点
            Permission child = permission;
            if (child.getPid() == 0) {
                permissions.add(child);
            } else {
                for (Permission innerPermission : permissionList) {
                    if (child.getPid().equals(innerPermission.getId())) {
                        // 找到了当前节点的父节点
                        Permission parent = innerPermission;
                        // 确定父子节点关系
                        parent.getChildren().add(child);
                        break;
                    }
                }
            }
        }*/

        // section 5: 通过 map 集合方式获取许可数据
        List<Permission> permissionList = permissionService.queryAll();

        Map<Integer, Permission> permissionMap = new HashMap<>();
        for (Permission permission : permissionList) {
            permissionMap.put(permission.getId(), permission);
        }

        for (Permission permission : permissionList) {
            if (permission.getPid() == 0) {
                permissions.add(permission);
            } else {
                Permission parent = permissionMap.get(permission.getPid());
                parent.getChildren().add(permission);
            }
        }

        return permissions;
    }

    @RequestMapping("/add")
    public String add() {
        return "permission/add";
    }

    @RequestMapping("/insert")
    @ResponseBody
    public Object insert(Permission permission) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            permissionService.insertPermission(permission);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id, Model model) {
        Permission permission = permissionService.queryById(id);
        model.addAttribute("permission", permission);

        return "permission/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(Permission permission) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            permissionService.updatePermission(permission);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Integer id) {
        AjaxResult ajaxResult = new AjaxResult();

        try {
            permissionService.deletePermission(id);

            ajaxResult.setSuccess(true);
        } catch (Exception e) {
            ajaxResult.setSuccess(false);
        }

        return ajaxResult;
    }

    /**
     * 递归查询许可信息
     */
    private void queryChildPermissions(Permission parent) {
        List<Permission> childPermissions = permissionService.queryChildPermissions(parent.getId());

        for (Permission permission : childPermissions) {
            queryChildPermissions(permission);
        }

        parent.setChildren(childPermissions);
    }
}
