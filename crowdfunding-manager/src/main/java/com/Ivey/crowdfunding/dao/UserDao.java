package com.Ivey.crowdfunding.dao;

import com.Ivey.crowdfunding.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author IveyLv
 * @Date 2019/11/19 21:32
 * @Version 1.0
 */
public interface UserDao {

    @Select("select * from tb_user")
    List<User> queryAll();

    @Select("select * from tb_user where login_name = #{loginName} and password = #{password}")
    User queryForLogin(User user);

    List<User> queryPageData(Map map);

    int queryPageCount(Map<String, Object> map);
}
