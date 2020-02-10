package com.Ivey.crowdfunding.service.impl;

import com.Ivey.crowdfunding.bean.Role;
import com.Ivey.crowdfunding.dao.RoleDao;
import com.Ivey.crowdfunding.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description 角色维护业务层实现
 * @Author IveyLv
 * @Date 2020/2/8 18:09
 * @Version 1.0
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> queryPageData(Map<String, Object> map) {
        return roleDao.queryPageData(map);
    }

    @Override
    public int queryPageCount(Map<String, Object> map) {
        return roleDao.queryPageCount(map);
    }

    @Override
    public void insertRole(Role role) {
        roleDao.insertRole(role);
    }

    @Override
    public Role queryById(Integer id) {
        return roleDao.queryById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleDao.deleteRoleById(id);
    }

    @Override
    public void deleteRoles(Map<String, Object> map) {
        roleDao.deleteRoles(map);
    }
}
