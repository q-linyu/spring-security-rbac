package com.bbs.common.utils;

import com.bbs.common.enums.HttpState;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @className: ResponseData
 * @author: q-linyu
 * @description: 响应结果类
 * @date: 2020/02/17 23:17
 * @version: 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {

    /**
     * 返回的状态码
     */
    private Integer status;

    /**
     * 返回的消息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 返回结果不带数据
     * @param httpState
     */
    public ResponseData(HttpState httpState) {
        this.status = httpState.getStatus();
        this.msg = httpState.getMsg();
    }

    /**
     * 只返回消息
     * @param msg
     */
    public ResponseData(String msg) {
        this.msg = msg;
    }

    /**
     * 返回结果带数据
     * @param httpState
     * @param data
     */
    public ResponseData(HttpState httpState,T data) {
        this.status = httpState.getStatus();
        this.msg = httpState.getMsg();
        this.data = data;
    }

    /**
     * 成功后返回的结果,不带数据
     * @param httpState
     * @return
     */
    public static <T> ResponseData<T> success(HttpState httpState){
        return new ResponseData<>(httpState);
    }


    /**
     * 成功后返回的结果，带数据
     * @param httpState
     * @return
     */
    public static <T> ResponseData<T> success(HttpState httpState, T data){
        return new ResponseData<>(httpState,data);
    }


    /**
     * 错误后返回的结果
     * @param msg
     * @return
     */
    public static <T> ResponseData<T> error(String msg){
        return new ResponseData<>(msg);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
