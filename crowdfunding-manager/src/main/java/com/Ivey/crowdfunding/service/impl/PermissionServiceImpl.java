package com.Ivey.crowdfunding.service.impl;

import com.Ivey.crowdfunding.bean.Permission;
import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.dao.PermissionDao;
import com.Ivey.crowdfunding.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 许可业务实现层
 * @Author IveyLv
 * @Date 2020/2/13 15:38
 * @Version 1.0
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Permission queryRootPermission() {
        return permissionDao.queryRootPermission();
    }

    @Override
    public List<Permission> queryChildPermissions(Integer id) {
        return permissionDao.queryChildPermissions(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionDao.queryAll();
    }

    @Override
    public void insertPermission(Permission permission) {
        permissionDao.insertPermission(permission);
    }

    @Override
    public Permission queryById(Integer id) {
        return permissionDao.queryById(id);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionDao.updatePermission(permission);
    }

    @Override
    public void deletePermission(Integer id) {
        permissionDao.deletePermission(id);
    }

    @Override
    public List<Integer> queryPermissionIdsByRoleId(Integer roleId) {
        return permissionDao.queryPermissionIdsByRoleId(roleId);
    }

    @Override
    public List<Permission> queryPermissionsByUser(User dbUser) {
        return permissionDao.queryPermissionByUser(dbUser);
    }
}
