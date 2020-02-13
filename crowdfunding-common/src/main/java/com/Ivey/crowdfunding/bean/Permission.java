package com.Ivey.crowdfunding.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 许可实体类
 * @Author IveyLv
 * @Date 2020/2/12 21:34
 * @Version 1.0
 */
public class Permission {

    private Integer id;
    private String name;
    private String url;
    private Integer pid;
    private boolean open = true;
    private List<Permission> children = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", pid=" + pid +
                ", open=" + open +
                ", children=" + children +
                '}';
    }
}
