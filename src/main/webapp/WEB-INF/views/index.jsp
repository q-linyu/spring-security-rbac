<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>首页 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="首页 - RBAC">
    <meta name="description" content="首页 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="${url}/index.html" class="rbac_text_center">rbac</a>
    </div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav right rbac_mr" lay-filter="">
        <li class="layui-nav-item">
            <input type="hidden" name="userId" id="userId_hidden">
            <a href="#" id="username-title"></a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd>
                    <a href="#">个人信息</a>
                </dd>
                <dd>
                    <a onclick="xadmin.add_tab('修改密码','${url}/system/password.html')">修改密码</a>
                </dd>
                <dd>
                    <a href="${url}/logout">退出</a>
                </dd>
            </dl>
        </li>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <%-- 动态渲染 --%>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${url}/home.html' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>

        <%-- 动态选项卡 --%>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript" src="${url}/static/js/common.js"></script>
<script type="text/javascript">

    var initMenu = function(data){
        $.each(data,function (parentKey,parent) {
            var $parent_li = $("<li></li>");
            var $parent_a = $("<a></a>");
            var $parent_i_top = $("<i></i>");
            var $parent_cite = $("<cite></cite>");
            var $parent_i_right = $("<i></i>");

            $parent_i_top.attr({"class":"iconfont left-nav-li", "lay-tips": parent.menuname}).html(parent.menuIcon);
            $parent_cite.html(parent.menuname);
            $parent_i_right.attr({"class":"iconfont nav_right"}).html("&#xe697;");
            $parent_a.append($parent_i_top).append($parent_cite).append($parent_i_right);
            $parent_li.append($parent_a);
            if (parent.childs !== null){
                var $child_ul = $("<ul class='sub-menu'></ul>");
                $.each(parent.childs,function (childKey,child) {

                    var $child_li = $("<li></li>");
                    var $child_a = $("<a></a>");
                    var $child_i = $("<i class='iconfont'></i>");
                    var $child_cite = $("<cite></cite>");


                    $child_i.html(child.menuIcon);
                    $child_cite.html(child.menuname);
                    $child_a.attr("onclick",onclick="xadmin.add_tab('"+child.menuname+"','"+child.menuUrl+"')");
                    $child_a.append($child_i).append($child_cite);
                    $child_li.append($child_a);

                    $child_ul.append($child_li);
                });
                $parent_li.append($child_ul);
            }

            $("#nav").append($parent_li);
        });
    };

    layui.use(['form','layer'],function () {
        $.ajax({
            url: '${url}/recursive',
            type: 'GET',
            data: {
                userId: $("#userId_hidden").val()
            },
            dataType: 'JSON',
            success: function (res) {
                if (res.status === 200){
                    initMenu(res.data);
                }
            }
        })
    });
</script>
</body>
</html>
