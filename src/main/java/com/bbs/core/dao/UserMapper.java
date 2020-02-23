package com.bbs.core.dao;

import com.bbs.common.utils.PageHepler;
import com.bbs.core.entity.Menu;
import com.bbs.core.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 用户表(UserInfo)表数据库访问层
 * @author q-linyu
 * @since 2020-02-17 17:43:18
 */
public interface UserMapper {

    /**
     * 根据用户名查询该用户是否存在
     * @param username  用户名
     * @return
     */
    UserInfo queryByUsername(@Param(value = "username")String username);

    /**
     * 根据用户名查询所有该用户的权限
     * @param username  用户名
     * @return
     */
    List<Menu> queryMenuByUsername(@Param(value = "username")String username);


    /**
     * 查询全部系统用户带角色的信息
     * @param username
     * @param createTime
     * @param pageHepler
     * @return
     */
    List<UserInfo> queryByPage(@Param(value = "username")String username,
                               @Param(value = "createTime")String createTime,
                               @Param(value = "pageHepler")PageHepler pageHepler);

    /**
     * 总记录数
     * @param username
     * @param createTime
     * @return
     */
    Integer count(@Param(value = "username")String username, @Param(value = "createTime")String createTime);


    /**
     * 修改密码
     * @author q-linyu
     * @date 2020/2/16 16:14
     * @param userId 用户ID
     * @param newpassword 新密码
     * @return void
     */
    Integer updatePassword(@Param(value = "userId")Long userId,@Param(value = "newpassword")String newpassword);



    /**
     * 通过ID查询单条数据
     * @param userId 主键
     * @return 实例对象
     */
    UserInfo queryById(@Param(value = "userId")Long userId);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userInfo 实例对象
     * @return 对象列表
     */
    List<UserInfo> queryAll(UserInfo userInfo);

    /**
     * 新增数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    Integer insert(UserInfo userInfo);


    /**
     * 修改数据
     *
     * @param userInfo 实例对象
     * @return 影响行数
     */
    Integer update(UserInfo userInfo);

    /**
     * 通过主键删除数据
     * @param userId 主键
     * @return 影响行数
     */
    Integer deleteById(@Param(value = "userId")Long userId);

}