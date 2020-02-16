package com.Ivey.crowdfunding.dao;

import com.Ivey.crowdfunding.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * @Description 角色维护持久层
 * @Author IveyLv
 * @Date 2020/2/8 18:19
 * @Version 1.0
 */
public interface RoleDao {

    List<Role> queryPageData(Map<String, Object> map);

    int queryPageCount(Map<String, Object> map);

    void insertRole(Role role);

    Role queryById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);

    void deleteRoles(Map<String, Object> map);

    List<Role> queryAll();

    void insertRolePermission(Map<String, Object> map);

    void deleteRolePermissionById(Map<String, Object> map);
}
