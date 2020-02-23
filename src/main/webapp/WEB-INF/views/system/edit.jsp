<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改管理员 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="修改管理员 - RBAC">
    <meta name="description" content="修改管理员 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form method="post" class="layui-form rbac-form" lay-filter="FormVal">
            <%-- 用户名 --%>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label required">用户名</label>
                <div class="layui-input-block" style="max-width: 400px;">
                    <input
                            type="text"
                            name="username"
                            id="username"
                            disabled
                            lay-verify="required"
                            lay-reqtext="用户名不能为空"
                            placeholder="请输入用户名"
                            class="layui-input"
                    >
                    <tip>填写自己管理账号的名称</tip>
                </div>
            </div>

            <%-- 是否过期 --%>
            <div class="layui-form-item">
                <label for="accountnonexpired" class="layui-form-label">是否过期</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="accountnonexpired" lay-skin="switch" lay-filter="switchExpired"
                           id="accountnonexpired" lay-text="是|否">
                </div>
            </div>

            <%-- 是否锁定 --%>
            <div class="layui-form-item">
                <label for="accountnonlocked" class="layui-form-label">是否锁定</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="accountnonlocked" lay-skin="switch" lay-filter="switchLocked"
                           id="accountnonlocked" lay-text="是|否">
                </div>
            </div>

            <%-- 证书是否过期 --%>
            <div class="layui-form-item">
                <label for="credentialsnonexpired" class="layui-form-label">证书是否过期</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="credentialsnonexpired" lay-skin="switch"
                           lay-filter="switchCredentialsnonexpired" id="credentialsnonexpired"
                           lay-text="是|否">
                </div>
            </div>

            <%-- 是否激活 --%>
            <div class="layui-form-item">
                <label for="enabled" class="layui-form-label">是否激活</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="enabled" lay-skin="switch" lay-filter="switchEnabled" id="enabled"
                           lay-text="是|否">
                </div>
            </div>

            <%-- 角色名称 --%>
            <div class="layui-form-item">
                <label class="layui-form-label  required">角色名称</label>
                <div class="layui-input-block">
                    <input type="radio" name="roleId" value="1" title="管理员">
                    <input type="radio" name="roleId" value="2" title="普通用户" checked="">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="updateUserForm">
                        保 存
                    </button>
                </div>
            </div>

        </form>
    </div>
</div>

<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript">

    // 传值
    function child(dataObj) {
        // 数据回显
        layui.use(["form", "layedit", "laydate", 'layer'], function () {
            var form = layui.form,
                layer = layui.layer;

            form.render();

            // 数据回显
            form.val("FormVal", {
                "username": dataObj.username,
                "accountnonexpired": !dataObj.accountnonexpired,
                "accountnonlocked": !dataObj.accountnonlocked,
                "credentialsnonexpired": !dataObj.credentialsnonexpired,
                "enabled": dataObj.enabled,
                "roleId": dataObj.roles[0].roleId
            });

            // 监听是否过期
            form.on('switch(switchExpired)', function (data) {
                layer.msg("是否过期：" + (this.checked ? "是" : "否"), {offset: '6px'});
            });

            // 监听是否锁定
            form.on('switch(switchLocked)', function (data) {
                layer.msg("是否锁定：" + (this.checked ? "是" : "否"), {offset: '6px'});
            });

            // 监听证书是否过期
            form.on('switch(switchCredentialsnonexpired)', function (data) {
                layer.msg("证书是否过期：" + (this.checked ? "是" : "否"), {offset: '6px'});
            });

            // 监听是否激活
            form.on('switch(switchEnabled)', function (data) {
                layer.msg("是否激活：" + (this.checked ? "是" : "否"), {offset: '6px'});
            });

            // 保存按钮
            form.on("submit(updateUserForm)", function (data) {
                $.ajax({
                    url: "${url}/user/update",
                    type: "PUT",
                    async: false,
                    data: {
                        userId: dataObj.userId,
                        username: data.field["username"],
                        accountnonexpired: function () {
                            return data.field["accountnonexpired"] === "on" ? 0 : 1;
                        },
                        accountnonlocked: function () {
                            return data.field["accountnonlocked"] === "on" ? 0 : 1;
                        },
                        credentialsnonexpired: function () {
                            return data.field["credentialsnonexpired"] === "on" ? 0 : 1;
                        },
                        enabled: function () {
                            return data.field["enabled"] === "on" ? 1 : 0;
                        },
                        roleId: data.field["roleId"]
                    },
                    dataType: "JSON",
                    success: function (res) {
                        var iframeIndex = parent.layer.getFrameIndex(window.name);
                        if (res.status === 204) {
                            var index = layer.alert(res.msg, {title: "温馨提示"}, function () {
                                // 刷新页面
                                parent.layui.table.reload('currentTableId');
                                // 关闭弹出层
                                layer.close(index);
                                parent.layer.close(iframeIndex);
                            });
                        }else {
                            layer.msg(res.msg,{time:3000});
                        }
                    },
                    error: function (e) {
                        console.log(e);
                    }
                });
                return false;
            });
        });
    }


</script>
</body>
</html>
