package com.bbs.web.controller;

import com.bbs.common.enums.HttpState;
import com.bbs.common.utils.PageHepler;
import com.bbs.common.utils.PageInfo;
import com.bbs.common.utils.ResponseData;
import com.bbs.core.entity.UserInfo;
import com.bbs.core.service.UserService;
import com.bbs.security.utils.SecurityUtils;
import com.bbs.security.utils.VerifyCodeUitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * 用户表(UserInfo)表控制层
 * @author makejava
 * @since 2020-02-17 17:43:18
 */
@Controller
public class UserController {

    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    /**
     * 修改密码
     * @author q-linyu
     * @date 2020/2/16 16:25
     * @param oldpassword
     * @param newpassword
     * @return void
     */
    @RequestMapping(value = "/user/updatePassword")
    @ResponseBody
    public ResponseData<Void> updatePassword(@RequestParam(value = "oldpassword")String oldpassword,@RequestParam(value = "newpassword")String newpassword){
        userService.updatePassword(oldpassword,newpassword);
        return ResponseData.success(HttpState.PASSWORD_UPDATE_SUCCESS);
    }

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/verifyCode")
    @ResponseBody
    public void verifyCode(HttpServletRequest request, HttpServletResponse response){
        VerifyCodeUitls.getVerifyCode(request,response);
    }

    /**
     * 查询用户信息
     */
    @RequestMapping(value = "/user/getUserInfo")
    @ResponseBody
    public ResponseData<UserInfo> getUserInfo(){
        UserInfo userInfo = SecurityUtils.getUserInfo();
        userInfo.setLastTime(new Date());
        return ResponseData.success(HttpState.SUCCESS,userInfo);
    }

    /**
     * 查询全部系统用户带角色的分页信息
     * @author q-linyu
     * @date 2020/2/19 0019 14:50
     * @param page 偏移量
     * @param limit 每页显示的条数
     * @return com.bbs.common.utils.ResponseData<com.bbs.common.utils.PageInfo<java.util.List<com.bbs.core.entity.UserInfo>>>
     */
    @RequestMapping(value = "/user/page")
    @ResponseBody
    public ResponseData<PageInfo<List<UserInfo>>> page(@RequestParam(value = "username",required = false)String username,
                                                       @RequestParam(value = "createTime",required = false)String createTime,
                                                       @RequestParam(value = "page")Integer page,
                                                       @RequestParam(value = "limit")Integer limit){
        return ResponseData.success(HttpState.SUCCESS,userService.page(username,createTime,new PageHepler(page,limit)));
    }

    /**
     * 新增
     * @author q-linyu
     * @date 2020/2/19 17:16
     * @param userInfo
     * @param roleId
     * @return com.bbs.common.utils.ResponseData<java.lang.Void>
     */
    @RequestMapping(value = "/user/addUserInfo")
    @ResponseBody
    public ResponseData<Void> addUserInfo(UserInfo userInfo,@RequestParam(value = "roleId")Long roleId){
        userService.addUserInfo(userInfo,roleId);
        return ResponseData.success(HttpState.CREATE_SUCCESS);
    }

    /**
     * @author q-linyu
     * @description 通过用户ID查询单条数据
     * @date 2020/2/20 15:41
     * @Param userId 用户ID
     * @return com.bbs.common.utils.ResponseData<com.bbs.core.entity.UserInfo>
     **/
    @RequestMapping(value = "/user")
    @ResponseBody
    public ResponseData<UserInfo> get(@RequestParam(value = "userId")Long userId){
        return ResponseData.success(HttpState.SUCCESS,userService.get(userId));
    }

    /**
     * 通过主键删除数据
     * @author q-linyu
     * @description
     * @date 2020/2/20 16:54
     * @Param userId 用户ID
     * @return com.bbs.common.utils.ResponseData<java.lang.Void>
     **/
    @ResponseBody
    @RequestMapping(value = "/user/{userId}")
    public ResponseData<Void> delete(@PathVariable(value = "userId")Long userId){
        userService.deleteById(userId);
        return ResponseData.success(HttpState.DELETE_SUCCESS);
    }

    /**
     * 修改数据
     * @author q-linyu
     * @description
     * @date 2020/2/20 17:27
     * @Param userId 用户ID
     * @return com.bbs.common.utils.ResponseData<java.lang.Void>
     **/
    @ResponseBody
    @RequestMapping(value = "/user/update")
    public ResponseData<Void> update(UserInfo userInfo,@RequestParam(value = "roleId")Long roleId){
        userService.update(userInfo,roleId);
        return ResponseData.success(HttpState.UPDATE_SUCCESS);
    }

}