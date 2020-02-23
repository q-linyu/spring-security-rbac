package com.bbs.common.utils;

import java.io.Serializable;

/**
 * @className: PageInfo
 * @author: q-linyu
 * @description: 分页响应结果类
 * @date: 2020/02/19 13:36
 * @version: 1.0
 */
public class PageInfo<T> implements Serializable {

    /**
     * 总记录数
     */
    private Integer count;

    /**
     * 分页结果
     */
    private T rows;


    public PageInfo(Integer count, T rows) {
        this.count = count;
        this.rows = rows;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
