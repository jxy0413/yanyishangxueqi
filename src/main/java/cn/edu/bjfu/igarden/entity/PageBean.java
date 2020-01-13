package cn.edu.bjfu.igarden.entity;

import com.github.pagehelper.PageInfo;

import java.util.List;
/**
 * Created by Cookie on 2019/2/26.
 */


/**
 * 分页bean
 */

public class PageBean<T> {
    // 当前页
    private Integer currentPage ;
    // 每页显示的总条数
    private Integer pageSize;
    // 是否有下一页
    private Integer isMore;
    // 总页数
    private Number total;
    private Integer totalPage;
    // 开始索引
    private Integer startIndex;
    // 分页结果
    private List<T> items;

    public PageBean() {
        super();
    }

    public PageBean(PageInfo<T> pageInfo) {
        //this.pageInfo = pageInfo;
        //设置当前页
        this.setCurrentPage(pageInfo.getPageNum());
        //每页条数
        this.setPageSize(pageInfo.getPageSize());
        //总条数
        this.setTotal(pageInfo.getTotal());
        //总页数
        this.setPageSize(pageInfo.getPages());
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    public Integer getIsMore() {
        return isMore;
    }

    public void setIsMore(Integer isMore) {
        this.isMore = isMore;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}