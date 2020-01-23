package com.Ivey.crowdfunding.bean;

import java.util.List;

/**
 * @Description 分页信息
 * @Author IveyLv
 * @Date 2020/1/23 16:50
 * @Version 1.0
 */
public class Page<T> {

    private List<T> datas;
    private int currentPageNo;
    private int totalNo;
    private int totalSize;

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }

    public int getTotalNo() {
        return totalNo;
    }

    public void setTotalNo(int totalNo) {
        this.totalNo = totalNo;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "datas=" + datas +
                ", currentPageNo=" + currentPageNo +
                ", totalNo=" + totalNo +
                ", totalSize=" + totalSize +
                '}';
    }
}
