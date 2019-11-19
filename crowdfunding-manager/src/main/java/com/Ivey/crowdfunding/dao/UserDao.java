package com.Ivey.crowdfunding.dao;

import com.Ivey.crowdfunding.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:32
 * @Version 1.0
 */
public interface UserDao {

    @Select("select * from tb_user")
    List<User> queryAll();
}
