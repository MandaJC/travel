package cn.itcast.travel.domain;

import java.util.List;

public class PageBean<T> {
    private int totalCount;//总记录数??数据库查??dao
    private int totalPage;//总页数??计算：数据库查的总记录数÷客户端提交的每页记录数
    private int currentPage;//当前页码??客户端提交
    private int pageSize;//每页显示记录数??客户端提交
    private List<T> list;//每页显示的记录集合??数据库查??dao

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
