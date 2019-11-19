package com.Ivey.crowdfunding.service;

import com.Ivey.crowdfunding.bean.User;

import java.util.List;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:32
 * @Version 1.0
 */
public interface UserService {
    List<User> queryAll();
}
