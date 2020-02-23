<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员列表 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="管理员列表 - RBAC">
    <meta name="description" content="管理员列表 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <%-- 搜索框 --%>
            <fieldset class="table-search-fieldset">
                <legend>搜索信息</legend>
                <div class="" style="margin: 10px 10px 10px 10px">
                    <form method="get" class="layui-form layui-form-pane" id="searchForm">

                        <%-- 根据用户名查询 --%>
                        <div class="layui-form-item">

                            <div class="layui-inline">
                                <label for="username" class="layui-form-label">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="username" id="username" autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <%-- 根据创建的时间查询 --%>
                            <div class="layui-inline">
                                <label for="createTime" class="layui-form-label">创建的时间</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="createTime" id="createTime" placeholder="yyyy-MM-dd"
                                           autocomplete="off" class="layui-input"/>
                                </div>
                            </div>

                            <%-- 按钮 --%>
                            <div class="layui-inline">
                                <button type="submit" class="layui-btn" lay-submit="" lay-filter="searchForm">
                                    <i class="layui-icon">&#xe615;</i>搜 索
                                </button>
                            </div>

                        </div>

                    </form>
                </div>
            </fieldset>

            <%-- 自定义模板 --%>
            <script type="text/html" id="toolbar-top">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" id="open_add_window">
                        <i class="layui-icon">&#xe624;</i>添加用户
                    </button>
                </div>
            </script>

            <%-- 表格数据 --%>
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
            <%-- 自定义模板 --%>
            <script type="text/html" id="toolbar-right">
                <a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs" id="permission_dist" lay-event="dist">
                    <i class="layui-icon">&#xe60a;</i>权限分配
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-xs" id="edit-user" lay-event="edit">
                    <i class="layui-icon">&#xe642;</i>编辑
                </a>
                <a href="javascript:;" class="layui-btn layui-btn-xs layui-btn-danger" id="delete-User" lay-event="delete">
                    <i class="layui-icon">&#xe640;</i>删除
                </a>
            </script>
        </div>
    </div>
</div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript">
    layui.use(['form', "table", 'laydate', 'layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table,
            laydate = layui.laydate,
            layer = layui.layer;

        // 日期
        laydate.render({elem: "#createTime"});

        // 数据展示
        table.render({
            title: "系统用户表",
            elem: "#currentTableId",
            url: "${url}/user/page",
            method: "GET",
            dataType: "json",
            toolbar: "#toolbar-top",
            defaultToolbar: ["filter", "exports", "prints", {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [ // 设置表头
                [
                    {field: "userId", width: 80, title: "ID", sort: true},
                    {field: "username", width: 105, title: "用户名"},
                    {
                        field: "accountnonexpired", width: 130, title: "是否过期", templet: function (d) {
                            return d.accountnonexpired === true ? "否" : "是"
                        }
                    },
                    {
                        field: "accountnonlocked", width: 130, title: "是否锁定", templet: function (d) {
                            return d.accountnonlocked === true ? "否" : "是"
                        }
                    },
                    {
                        field: "credentialsnonexpired", width: 150, title: "证书是否过期", templet: function (d) {
                            return d.credentialsnonexpired === true ? "否" : "是"
                        }
                    },
                    {
                        field: "enabled", width: 130, title: "是否激活", templet: function (d) {
                            return d.enabled === true ? "是" : "否"
                        }
                    },
                    {
                        field: "roleName", width: 130, title: "角色名称", templet: function (d) {
                            return d.roles[0].roleName;
                        }
                    },
                    {field: "createTime", width: 200, title: "创建时间"},
                    {field: "lastTime", width: 200, title: "修改时间"},
                    {field: "操作", minWidth: 50, templet: "#toolbar-right", fixed: "right", align: "center"}
                ]
            ],
            loading: true,
            even: true,
            page: {
                limit: 10, // 每次显示10条
                groups: 5, // 显示5个连续码
                limits: [10, 20, 25, 50, 100],
            },
            parseData: function (res) {
                if (res.status === 200) {
                    return {
                        "code": 0,
                        "msg": res.msg,
                        "count": res.data.count,
                        "data": res.data.rows
                    }
                }
            },
            text: {
                none: "暂无数据"
            },
            done: function (res, curr, count) {
                // 如果当前页没有数据,就回退到前一页
                if (curr > 1 && res.data === undefined) {
                    var pageVal = curr - 1;
                    table.reload('currentTableId', {
                        page: {
                            curr: pageVal
                        }
                    });
                }
            }
        });

        // 搜索
        form.on('submit(searchForm)', function (data) {
            // 执行搜索重载
            table.reload('currentTableId', {
                page: {
                    curr: 1  // 重载后从第一页开始
                }
                , where: {
                    username: data.field['username'],
                    createTime: data.field['createTime']
                }
            });
            return false;
        });

        // 打开添加用户窗口
        $(document).on("click", "#open_add_window", function () {
            var index = layer.open({
                title: "添加用户",
                type: 2,
                shade: 0.2,
                maxmin: true,
                shadeClose: true,
                area: ['85%', '85%'],
                content: '${url}/system/add.html',
            });

            // 最大化并且填充整个页面
            $(window).on("resize", function () {
                layer.full(index);
            });
            return false;
        });

        table.on('tool(currentTableFilter)', function (obj) {
            if (obj.event === 'dist'){
                var index = layer.open({
                    title: "权限分配",
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['85%', '85%'],
                    content: "${url}/system/dist.html",
                    success: function (layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.child(obj.data);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'edit') {
                var index = layer.open({
                    title: "编辑用户",
                    type: 2,
                    shade: 0.2,
                    maxmin: true,
                    shadeClose: true,
                    area: ['85%', '85%'],
                    content: "${url}/system/edit.html",
                    success: function (layero, index) {
                        var iframe = window['layui-layer-iframe' + index];
                        iframe.child(obj.data);
                    }
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                layer.confirm("真的要删除么", function () {
                    $.ajax({
                        url: "${url}/user/" + obj.data.userId,
                        type: 'DELETE',
                        async: false,
                        dataType: 'JSON',
                        success: function (res) {
                            if (res.status === 206) {
                                layer.msg(res.msg, {time: 3000});
                                // 删除当前行
                                obj.del();
                                table.reload('currentTableId');
                                // 关闭弹出框
                                layer.close();
                                return;
                            }
                            layer.msg(res.msg, {time: 3000});
                        },
                        error: function (e) {
                            console.log(e);
                        }
                    });

                });
            }
        });

    });
</script>
</body>
</html>
