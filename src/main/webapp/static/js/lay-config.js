// 自定义layui扩展

// 获取js的所在的路径路径
window.rootPath = (function (src) {
    src = document.scripts[document.scripts.length - 1].src;
    return src.substring(0, src.lastIndexOf("/") + 1);
})();

// 配置拓展模块
layui.config({
    base: rootPath + "lay-module/",
}).extend({
    treetable: 'treetable-lay/treetable', //table树形扩展
    eleTree: 'eleTree/eleTree' //table树形扩展
});