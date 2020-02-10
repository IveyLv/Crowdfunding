package com.Ivey.crowdfunding.service;

import com.Ivey.crowdfunding.bean.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 角色维护业务接口层
 * @Author IveyLv
 * @Date 2020/2/8 18:06
 * @Version 1.0
 */
public interface RoleService {

    List<Role> queryPageData(Map<String, Object> map);

    int queryPageCount(Map<String, Object> map);

    void insertRole(Role role);

    Role queryById(Integer id);

    void updateRole(Role role);

    void deleteRoleById(Integer id);

    void deleteRoles(Map<String, Object> map);
}
