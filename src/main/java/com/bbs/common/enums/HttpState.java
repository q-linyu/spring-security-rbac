package com.bbs.common.enums;

/**
 * @className: HttpState
 * @author: q-linyu
 * @description: 状态码
 * @date: 2020/02/17 22:04
 * @version: 1.0
 */
public enum  HttpState {

    /**
     * 请求成功
     */
    SUCCESS(200,"success"),

    /**
     * 系统内部错误,请联系管理员
     */
    ERROR(500,"系统内部错误,请联系管理员"),

    /**
     * 验证码不能为空
     */
    VERIFY_CODE_IS_NULL(400,"验证码不能为空"),

    /**
     * 验证码不一致
     */
    VERIFY_CODE_FAILURE(403,"验证码不一致"),

    /**
     * 旧密码错误
     */
    OLD_PASSWORD_FAILURE(403,"旧密码错误"),

    /**
     * 密码修改失败
     */
    PASSWORD_UPDATE_FAILURE(403,"密码修改失败"),

    /**
     * 密码修改失败
     */
    PASSWORD_UPDATE_SUCCESS(204,"密码修改成功"),

    /**
     * 暂无数据
     */
    NOT_FONT(404,"暂无数据"),

    /**
     * 新增失败
     */
    CREATE_FAILURE(403,"新增失败"),

    /**
     * 新增成功
     */
    CREATE_SUCCESS(201,"新增成功"),

    /**
     * 删除失败
     */
    DELETE_FAILURE(403,"删除失败"),

    /**
     * 删除成功
     */
    DELETE_SUCCESS(206,"删除成功"),

    /**
     * 修改失败
     */
    UPDATE_FAILURE(403,"修改失败"),

    /**
     * 修改成功
     */
    UPDATE_SUCCESS(204,"修改成功"),

    ;


    /**
     * 状态码
     */
    private final Integer status;

    /**
     * 消息
     */
    private final String msg;


    HttpState(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
