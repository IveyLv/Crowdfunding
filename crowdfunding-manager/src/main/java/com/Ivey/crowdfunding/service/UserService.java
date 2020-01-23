package com.Ivey.crowdfunding.service;

import com.Ivey.crowdfunding.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:32
 * @Version 1.0
 */
public interface UserService {
    List<User> queryAll();

    User queryForLogin(User user);

    List<User> queryPageData(Map map);

    int queryPageCount(Map<String, Object> map);
}
