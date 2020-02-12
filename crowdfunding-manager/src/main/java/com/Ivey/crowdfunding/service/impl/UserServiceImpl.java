package com.Ivey.crowdfunding.service.impl;

import com.Ivey.crowdfunding.bean.User;
import com.Ivey.crowdfunding.dao.UserDao;
import com.Ivey.crowdfunding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:32
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryAll() {
        return userDao.queryAll();
    }

    @Override
    public User queryForLogin(User user) {
        return userDao.queryForLogin(user);
    }

    @Override
    public List<User> queryPageData(Map map) {
        return userDao.queryPageData(map);
    }

    @Override
    public int queryPageCount(Map<String, Object> map) {
        return userDao.queryPageCount(map);
    }

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }

    @Override
    public void deleteUsers(Map<String, Object> map) {
        userDao.deleteUsers(map);
    }

    @Override
    public void insertUserRoles(Map<String, Object> map) {
        userDao.insertUserRoles(map);
    }

    @Override
    public void deleteUserRoles(Map<String, Object> map) {
        userDao.deleteUserRoles(map);
    }

    @Override
    public List<Integer> queryRoleIdByUserId(Integer id) {
        return userDao.queryRoleIdByUserId(id);
    }
}
