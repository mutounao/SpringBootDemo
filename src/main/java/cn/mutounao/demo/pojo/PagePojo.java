package cn.mutounao.demo.pojo;

import javax.persistence.Transient;

public class PagePojo {
    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    @Transient
    private String search = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
