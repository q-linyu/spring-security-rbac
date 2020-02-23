<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="修改密码 - RBAC">
    <meta name="description" content="修改密码 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body>
<div>
    <div class="layui-fluid">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md12">
                <form method="post" class="layui-form rbac-form" id="passwordForm">
                    <%-- 旧密码 --%>
                    <div class="layui-form-item">
                        <label for="oldpassword" class="layui-form-label required">旧密码</label>
                        <div class="layui-input-block">
                            <input
                                    type="password"
                                    name="oldpassword"
                                    id="oldpassword"
                                    lay-verify="required|oldpassword"
                                    lay-reqtext="旧密码不能为空"
                                    autocomplete="off"
                                    placeholder="请输入旧密码"
                                    class="layui-input"
                            >
                        </div>
                    </div>

                    <%-- 新密码 --%>
                    <div class="layui-form-item">
                        <label for="newpassword" class="layui-form-label required">新密码</label>
                        <div class="layui-input-block">
                            <input
                                    type="password"
                                    name="newpassword"
                                    lay-verify="required|newpassword"
                                    lay-reqtext="新密码不能为空"
                                    autocomplete="off"
                                    id="newpassword"
                                    placeholder="请输入新密码"
                                    class="layui-input"
                            >
                        </div>
                    </div>

                    <%-- 确认密码 --%>
                    <div class="layui-form-item">
                        <label for="querypassword" class="layui-form-label required">确认密码</label>
                        <div class="layui-input-block">
                            <input
                                    type="password"
                                    name="querypassword"
                                    lay-verify="required|querypassword"
                                    lay-reqtext="确认密码不能为空"
                                    autocomplete="off"
                                    id="querypassword"
                                    placeholder="请输入确认密码"
                                    class="layui-input"
                            >
                        </div>
                    </div>

                    <%-- 保存按钮 --%>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit="" lay-filter="passwordForm">保 存</button>
                        </div>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript">
    layui.use(["form", "layer"], function () {
        var form = layui.form,
            layer = layui.layer;

        // 自定义验证规则
        form.verify({
            newpassword: function (value) {
                if (value !== $("#querypassword").val()) {
                    return "两次密码不一致"
                }
            }
        });

        // 监听提交
        form.on('submit(passwordForm)', function (data) {
            $.ajax({
                url: "${url}/user/updatePassword",
                type: "PUT",
                async: false,
                data: {
                    oldpassword: data.field["oldpassword"],
                    newpassword: data.field["newpassword"]
                },
                dataType: "JSON",
                success: function (ret) {
                    if (ret.status === 204) {
                        layer.msg(ret.msg, {time: 3000});
                        $("#passwordForm")[0].reset();
                        layui.form.render();
                    }else {
                        layer.msg(ret.msg, {time: 3000});
                    }
                },
                error: function (e) {
                    console.log(e);
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
