package com.atguigu.domain;

import java.util.List;

/*
 * @Description : Page是分页的模型对象
 * @param <T> 是具体的模块的 JavaBean类
*/
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;

    // 当前页码
    private Integer pageNo;
    // 总页码
    private Integer pageTotal;
    // 当前页显示数量
    private Integer pageSize = PAGE_SIZE;
    // 总记录数
    private Integer pageTotalcount;
    // 当前页数据
    private List<T> Items;
    // 分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        // 实现数据边界有效检查
        if(pageNo < 1){
            pageNo = 1;
        }
        if(pageNo > pageTotal){
            pageNo = pageTotal;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalcount() {
        return pageTotalcount;
    }

    public void setPageTotalcount(Integer pageTotalcount) {
        this.pageTotalcount = pageTotalcount;
    }

    public List<T> getItems() {
        return Items;
    }

    public void setItems(List<T> items) {
        Items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalcount=" + pageTotalcount +
                ", Items=" + Items +
                ", url='" + url + '\'' +
                '}';
    }
}
