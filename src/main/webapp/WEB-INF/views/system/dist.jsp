<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>权限分配 - RBAC</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="keywords" content="权限分配 - RBAC">
    <meta name="description" content="权限分配 - RBAC">
    <meta name="author" content="q-linyu">
    <link rel="stylesheet" href="${url}/static/css/font.css">
    <link rel="stylesheet" href="${url}/static/js/lay-module/eleTree/eleTree.css">
    <link rel="stylesheet" href="${url}/static/css/index.css">
    <style type="text/css">
        .eleTree {
            margin-left: 50px;
        }
    </style>
</head>
<body style="background-color: #fff">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend id="legendText"></legend>
            </fieldset>
            <div class="eleTree" id="menu_tree" lay-filter="tree_node"></div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${url}/static/assets/layui/layui.js"></script>
<script type="text/javascript" src="${url}/static/js/jquery.js"></script>
<script type="text/javascript" src="${url}/static/js/index.js"></script>
<script type="text/javascript" src="${url}/static/js/lay-config.js"></script>
<script type="text/javascript">

    // 数组去重
    function unique(arr) {
        if (!Array.isArray(arr)) {
            return;
        }
        var resultArr = [];
        for (var i = 0; i < arr.length; i++) {
            if (!resultArr.includes(arr[i])) {
                resultArr.push(arr[i]);
            }
        }
        return resultArr;
    }

    // 删除元素
    Array.prototype.remove = function (val) {
        var index = this.indexOf(val);
        if (index > -1) {
            this.splice(index, 1);
        }
    };

    function child(dataObj) {

        $("#legendText").text(dataObj.roles[0].roleName + "：" + dataObj.username);

        var data = [];

        var dataMenuIds = [];
        $.ajax({
            url: '${url}/recursive',
            type: 'GET',
            dataType: 'JSON',
            success: function (res) {
                if (res.status === 200) {
                    $.each(res.data, function (parentKey, parentItem) {
                        dataMenuIds.push(parentItem.menuId);
                        var dataChild = [];
                        if (parentItem.childs !== null) {
                            $.each(parentItem.childs, function (childKey, childItem) {
                                dataMenuIds.push(childItem.menuId);
                                dataChild.push({
                                    id: childItem.menuId,
                                    label: childItem.menuname
                                });
                            });
                        }
                        data.push({
                            id: parentItem.menuId,
                            label: parentItem.menuname,
                            children: dataChild
                        });
                    });
                }
            }
        });

        layui.use(['eleTree','layer'], function () {
            var eleTree = layui.eleTree,
                layer = layui.layer,
                menuIds = [];

            var el = eleTree.render({
                elem: '#menu_tree',
                data: data,
                showCheckbox: true,
                emptText: "暂无数据", // 内容为空的时候展示的文本
                showLine: true, // 是否显示连线
                defaultExpandAll: true, // 是否默认展开所有节点
                highlightCurrent: true, // 是否高亮当前选中节点，默认值是 false
                checkStrictly: true
            });

            // 数据回显
            $.ajax({
                url: '${url}/recursive',
                type: 'GET',
                data: {
                    userId: dataObj.userId
                },
                dataType: 'JSON',
                async: false,
                success: function (res) {
                    if (res.status === 200) {
                        $.each(res.data, function (index, item) {
                            menuIds.push(item.menuId);
                            $.each(item.childs, function (childrenIndex, childrenItem) {
                                menuIds.push(childrenItem.menuId);
                            });
                        });
                        el.setChecked(menuIds, false);
                        var newArr = dataMenuIds.filter(key => !menuIds.includes(key));
                        el.unCheckArrNodes(newArr);
                    }
                }
            });

            // 被选中触发
            eleTree.on('nodeChecked(tree_node)', function (d) {
                if (d.isChecked) {
                    menuIds.push(d.data.currentData.id, d.data.parentData.data.id);
                    // 执行修改操作
                    <%--$.ajax({--%>
                        <%--url: '${url}/rolemenu_add',--%>
                        <%--type: 'POST',--%>
                        <%--data: {--%>
                            <%--roleId: dataObj.roles[0].roleId,--%>
                            <%--menuIds: unique(menuIds)--%>
                        <%--},--%>
                        <%--dataType: 'JSON',--%>
                        <%--success: function (res) {--%>
                            <%--if (res.status === 204){--%>
                                <%--layer.msg("设置成功",{time: 3000});--%>
                            <%--}else{--%>
                                <%--layer.msg("设置失败",{time: 3000});--%>
                            <%--}--%>
                        <%--}--%>
                    <%--});--%>
                    console.log(unique(menuIds));
                } else {
                    menuIds.remove(d.data.currentData.id);
                    menuIds.remove(d.data.parentData.data.id);
                    var tempArr = [];
                    tempArr.push(d.data.currentData.id);
                }
                // 执行修改操作
                $.ajax({
                    url: '${url}/rolemenu',
                    type: 'POST',
                    data: {
                        roleId:dataObj.roles[0].roleId,
                        menuIds: unique(menuIds)
                    },
                    dataType: 'JSON',
                    success: function (res) {
                        if (res.status === 204){
                            layer.msg("设置成功",{time: 3000});
                        }else{
                            layer.msg("设置失败",{time: 3000});
                        }
                    }
                });
                console.log(unique(menuIds));
            });

        });

    }


</script>

</body>
</html>
