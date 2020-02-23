package com.bbs.core.service;

import com.bbs.common.utils.PageHepler;
import com.bbs.common.utils.PageInfo;
import com.bbs.core.entity.UserInfo;

import java.util.List;

/**
 * 用户表(UserInfo)表服务接口
 *
 * @author makejava
 * @since 2020-02-17 17:43:18
 */
public interface UserService {

    /**
     * 修改密码
     * @param oldpassword
     * @param newPassword
     */
    void updatePassword(String oldpassword,String newPassword);

    /**
     * 查询全部系统用户带角色的分页信息
     * @param username
     * @param createTime
     * @param pageHepler 分页辅助对象
     * @return 分页集合对象
     */
    PageInfo<List<UserInfo>> page(String username,String createTime,PageHepler pageHepler);


    /**
     * 新增数据
     * @param userInfo 实例对象
     * @param roleId
     */
    void addUserInfo(UserInfo userInfo,Long roleId);

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    UserInfo get(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(int offset, int limit);

    /**
     * 修改数据
     * @param userInfo 实例对象
     * @param roleId
     * @return 实例对象
     */
    void update(UserInfo userInfo,Long roleId);

    /**
     * 通过主键删除数据
     * @param userId 主键
     * @return 是否成功
     */
    void deleteById(Long userId);

}