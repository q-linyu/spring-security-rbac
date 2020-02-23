<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="登录 - RBAC">
    <meta name="description" content="登录 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/assets/layui/css/layui.css">
    <!-- 引入系统样式 -->
    <link rel="stylesheet" href="${url}/static/css/login.css">
    <style type="text/css">
        /* 覆盖原框架样式 */
        .layui-input,
        .layui-select,
        .layui-textarea {
            padding-left: 35px;
            color: #666;
        }
    </style>
</head>
<body>
<div class="login-page">
    <!-- 头部 start -->
    <div class="header"></div>
    <!-- 头部 end -->

    <!-- 中部 start -->
    <div class="main">
        <div class="login-wrapper">
            <h1>RBAC</h1>
            <form method="post" id="loginForm" class="layui-form">
                <!-- 账号 -->
                <div class="layui-col-sm12 layui-col-md12">
                    <div class="layui-form-item">
                        <input
                                type="text"
                                name="username"
                                id="username"
                                lay-verify="required|username"
                                lay-reqtext="用户名不能为空"
                                autocomplete="off"
                                placeholder="用户名"
                                class="layui-input"
                        >
                        <i class="layui-icon layui-icon-username rbac_login_input_icon"></i>
                    </div>
                </div>

                <!-- 密码 -->
                <div class="layui-col-sm12 layui-col-md12">
                    <div class="layui-form-item">
                        <input
                                type="password"
                                name="password"
                                id="password"
                                lay-verify="required|password"
                                lay-reqtext="密码不能为空"
                                autocomplete="off"
                                placeholder="密码"
                                class="layui-input"
                        >
                        <i class="layui-icon layui-icon-password rbac_login_input_icon"></i>
                    </div>
                </div>

                <!-- 验证码 -->
                <div class="layui-form-item">
                    <div class="layui-row">
                        <div class="layui-col-xs7">
                            <input
                                    type="text"
                                    name="verifyCode"
                                    id="verifyCode"
                                    maxlength="4"
                                    placeholder="验证码"
                                    class="layui-input"
                            >
                            <i class="layui-icon layui-icon-vercode rbac_login_input_icon"></i>
                        </div>
                        <div class="layui-col-xs5">
                            <div style="margin-left: 10px;">
                                <img src="${url}/verifyCode" class="codeimg "
                                     onclick="this.src = this.src + '?' + Math.random()">
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 记住我 -->
                <div class="layui-col-sm12 layui-col-md12">
                    <div class="layui-form-item">
                        <input type="checkbox" name="remember-me" value="true" title="记住密码" lay-skin="primary">
                    </div>
                </div>

                <!-- 提交按钮 -->
                <div class="layui-col-sm12 layui-col-md12">
                    <button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="loginForm">登 录</button>
                </div>
            </form>
        </div>
    </div>
    <!-- 中部 end -->

    <!-- 底部 start -->
    <div class="footer rbac_text_center">
        <p>@ 2020 <span>q-linyu</span></p>
    </div>
    <!-- 底部 end -->
</div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript">

    layui.use(['form', 'layer'], function () {
        var form = layui.form,
            layer = layui.layer;
        $ = layui.$;

        // 自定义验证规则
        form.verify({
            verifyCode: function (value) {
                if ("" === value && value.length <= 0) {
                    return "验证码不能为空";
                }

                // 从后端获取到的验证码
                var key = "${key}";
                if (value.toLowerCase() !== key.toLowerCase()) {
                    return "验证码不一致";
                }
            }
        });


        // 监听提交
        form.on('submit(loginForm)', function (data) {

            $.ajax({
                url: "${url}/login",
                type: "post",
                async: false,
                dataType: "JSON",
                data: data.field,
                success: function (res) {
                    if (res.status === 200) {
                        window.location.href = "${url}/index.html";
                    } else {
                        console.log(res.msg);
                        layer.msg(res.msg, {icon: 5});
                    }
                }
            });
            return false;
        });

    });
</script>
</body>
</html>

