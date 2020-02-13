package com.Ivey.crowdfunding.service;

import com.Ivey.crowdfunding.bean.Permission;

import java.util.List;

/**
 * @Description 许可业务接口层
 * @Author IveyLv
 * @Date 2020/2/13 15:38
 * @Version 1.0
 */
public interface PermissionService {

    Permission queryRootPermission();

    List<Permission> queryChildPermissions(Integer id);

    List<Permission> queryAll();

    void insertPermission(Permission permission);

    Permission queryById(Integer id);

    void updatePermission(Permission permission);

    void deletePermission(Integer id);
}
