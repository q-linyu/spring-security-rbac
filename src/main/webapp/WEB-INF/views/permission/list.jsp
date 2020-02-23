<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限管理 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="权限管理 - RBAC">
    <meta name="description" content="权限管理 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
</head>
<body>
<div class="layui-fluid">
    <div class="layui-btn-group">
        <button class="layui-btn" id="btn-expand">全部展开</button>
        <button class="layui-btn" id="btn-fold">全部折叠</button>
    </div>
    <table id="menu_table" class="layui-table" lay-filter="menu-table"></table>

    <!-- 操作列 -->
    <script type="text/html" id="auth-state">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delete">删除</a>
    </script>
</div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript" src="${url}/static/js/lay-config.js"></script>
<script type="text/javascript">
    layui.use(['table', 'treetable', 'layer'], function () {
        var table = layui.table,
            treetable = layui.treetable,
            layer = layui.layer;

        layer.load(2);
        treetable.render({
            elem: '#menu_table',
            treeColIndex: 1,
            treeSpid: -1,
            treeIdName: 'menuId',
            treePidName: 'parentId',
            url: '${url}/menus',
            page: false,
            cols: [
                [
                    {type: 'numbers'},
                    {field: 'menuname',width:415, title: '权限名称'},
                    {field: 'identifier',width:415, title: '权限标识符'},
                    {field: 'menuUrl',width:415, title: '菜单url'},
                    {
                        field: 'isMenu', width: 130, align: 'center', title: '类型', templet: function (d) {
                            if (d.isMenu === 1) {
                                return '<span class="layui-badge layui-bg-blue">一级菜单</span>';
                            } else if (d.isMenu === 2) {
                                return '<span class="layui-badge-rim">二级菜单</span>'
                            }
                        }
                    },
                    {templet: '#auth-state', width: 120, align: 'center', title: '操作'}
                ]
            ],
            parseData: function (res) {
                if (res.status === 200) {
                    return {
                        "code": 0,
                        "msg": res.msg,
                        "data": res.data
                    }
                }
            },
            text: {
                none: "暂无数据"
            },
            done: function () {
                layer.closeAll('loading');
            }
        });

        // 全部展开
        $(document).on('click','#btn-expand',function () {
            treetable.expandAll('#menu_table');
        });

        // 全部折叠
        $(document).on('click','#btn-fold',function () {
            treetable.foldAll('#menu_table');
        });

        // 监听工具条
        table.on('tool(menu-table)',function (obj) {
            if (obj.event === 'edit'){
                var index = layer.open({
                    title: "编辑用户",
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['85%', '85%'],
                    content: "${url}/permission/edit.html",
                    success: function (layero,index) {
                        var iframe = window['layui-layer-iframe'+index];
                        iframe.child(obj.data);
                    }
                });
                $(window).on("resize",function () {
                    layer.full(index);
                });
                return false;
            }else if(obj.event === 'delete'){
                layer.confirm("真的要删除么",function () {
                    $.ajax({
                        url: "${url}/menu/" + obj.data.menuId,
                        type: 'DELETE',
                        async: false,
                        dataType: 'JSON',
                        success: function (res) {
                            if (res.status === 206){
                                layer.msg(res.msg,{time:3000});
                                // 删除当前行
                                obj.del();
                                table.reload('currentTableId');
                                // 关闭弹出框
                                layer.close();
                                return;
                            }
                            layer.msg(res.msg,{time:3000});
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
