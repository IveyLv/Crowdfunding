package com.Ivey.crowdfunding.dao;

import com.Ivey.crowdfunding.bean.Permission;
import com.Ivey.crowdfunding.bean.User;

import java.util.List;

/**
 * @Description 许可持久层
 * @Author IveyLv
 * @Date 2020/2/13 15:39
 * @Version 1.0
 */
public interface PermissionDao {

    Permission queryRootPermission();

    List<Permission> queryChildPermissions(Integer id);

    List<Permission> queryAll();

    void insertPermission(Permission permission);

    Permission queryById(Integer id);

    void updatePermission(Permission permission);

    void deletePermission(Integer id);

    List<Integer> queryPermissionIdsByRoleId(Integer roleId);

    List<Permission> queryPermissionByUser(User dbUser);
}
