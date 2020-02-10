package com.Ivey.crowdfunding.bean;

/**
 * @Description 角色实体类
 * @Author IveyLv
 * @Date 2020/2/8 18:15
 * @Version 1.0
 */
public class Role {

    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
