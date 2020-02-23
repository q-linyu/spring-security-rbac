<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改权限 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="修改权限 - RBAC">
    <meta name="description" content="修改权限 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form method="post" class="layui-form" lay-filter="perssionForm">
            <%-- 菜单名称 --%>
            <div class="layui-form-item">
                <label for="menuname" class="layui-form-label">权限名称</label>
                <div class="layui-input-block" style="max-width: 400px;">
                    <input type="text" class="layui-input" id="menuname" name="menuname" disabled>
                </div>
            </div>

            <%-- 父级菜单 --%>
            <div class="layui-form-item">
                <label for="menuId" class="layui-form-label">父级菜单</label>
                <div class="layui-input-inline">
                    <select name="menuId" id="menuId">
                        <option value="-1">无</option>
                        <option value="1">管理员管理</option>
                        <option value="2">商品管理</option>
                        <option value="3">订单管理</option>
                        <option value="4">系统设置</option>
                    </select>
                </div>
            </div>


            <%-- 菜单标识符 --%>
            <div class="layui-form-item">
                <label for="identifier" class="layui-form-label">权限标识符</label>
                <div class="layui-input-block" style="max-width: 400px;">
                    <input type="text" class="layui-input" id="identifier" name="identifier">
                </div>
            </div>
            <%-- 菜单图标 --%>
            <div class="layui-form-item">
                <label for="menuIcon" class="layui-form-label">权限图标</label>
                <div class="layui-input-block" style="max-width: 400px;">
                    <input type="text" id="menuIcon" name="menuIcon" class="layui-input">
                </div>
            </div>
            <%-- 菜单url --%>
            <div class="layui-form-item">
                <label for="menuUrl" class="layui-form-label">权限url</label>
                <div class="layui-input-block" style="max-width: 400px;">
                    <input type="text" class="layui-input" id="menuUrl" name="menuUrl">
                </div>
            </div>

            <%-- 按钮 --%>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="perssionForm">保 存</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript">

    function child(dataObj) {
        layui.use(['form', 'layer', 'laytpl'], function () {
            var form = layui.form,
                layer = layui.layer,
                laytpl = layui.laytpl;

            form.render();

            // 数据回显
            form.val("perssionForm", {
                "menuname": dataObj.menuname,
                "identifier": dataObj.identifier,
                "menuIcon": dataObj.menuIcon,
                "menuUrl": dataObj.menuUrl,
                "menuId": dataObj.parentId
            });

            form.on('submit(perssionForm)',function () {
               console.log('执行修改操作');
            });

        });
    }


</script>
</body>
</html>
