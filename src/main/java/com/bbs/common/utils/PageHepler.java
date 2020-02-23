package com.bbs.common.utils;

import java.io.Serializable;

/**
 * @className: PageHepler
 * @author: q-linyu
 * @description: 分页辅助类
 * @date: 2020/02/19 14:29
 * @version: 1.0
 */
public class PageHepler implements Serializable {

    /**
     * 偏移量
     */
    private Integer page;

    /**
     * 每页显示的条数
     */
    private Integer limit;


    public PageHepler() {
        super();
    }

    public PageHepler(Integer page, Integer limit) {
        this.page = (page - 1) * limit;
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
