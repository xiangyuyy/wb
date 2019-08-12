/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
    var e = layui.$,
    i = layui.table,
    admin = layui.admin,
    n = layui.form;
    var layer = layui.layer;
    i.render({
        elem: "#LAY-app-content-list",
        url: "/good/getlist",
        cols: [[
//        	{
//            type: "checkbox",
//            fixed: "left"
//        },
        {
            field: "id",
            width: 120,
            title: "商品ID",
            sort: !0
        },
        {
            field: "goodname",
            title: "商品名称",
            minWidth: 100
        },
        {
            field: "goodtypename",
            title: "商品类型名称",
            minWidth: 100
        },
        {
            field: "goodclassname",
            title: "商品分类名称",
            minWidth: 100
        },
        {
            field: "thirdname",
            title: "商品平台",
            minWidth: 100
        },
        {
            field: "thirdtypename",
            title: "商品平台类型名称",
            minWidth: 100
        },
        {
            field: "sign",
            title: "商品标签",
            minWidth: 100
        },
        {
            field: "rank",
            title: "排序",
            width: 100,
        },
        {
            field: "photourl",
            title: "图片",
            width: 100,
            templet: "#imgTpl"
        },
        {
            field: "createtime",
            title: "创建时间",
            templet:'#createtime',
            sort: !0
        },
        {
            field: "updatetime",
            title: "更新时间",
            sort: true,fixed:false,templet:'#updatetime',
        },
        {
            field: "status",
            title: "状态",
            templet: "#buttonTpl",
            minWidth: 80,
            align: "center"
        },
        {
            title: "操作",
            minWidth: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-list"
        }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！",
        text: {none: '一条数据也没有^_^'}
    }),
    i.on("tool(LAY-app-content-list)",
    function(t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定下架此商品类型？",
        function(e) {
            t.del(),
            layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑商品",
            content: "/good/listform?id=" + e.id,
            maxmin: !0,
            area: ["550px", "550px"],
            btn: ["确定", "取消"],
            yes: function(e, i) {
                var l = window["layui-layer-iframe" + e],
                a = i.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)",
                function(i) {
                    var index = layer.load(1, {
                        shade: [0.5,'#000'] //0.1透明度的背景
                        });                     
                    layui.$.ajax({
                        type: "post",
                        url: "/good/update",
                        data: i.field,
                        dataType: "json",
                        success: function(res){
                            if(res.sucess){
                            layer.msg(res.msg, {
                              offset: '15px'
                              ,icon: 1
                              ,time: 500
                            }, function(){
                                n.render(),
                                layui.table.reload('LAY-app-content-list');
                                layer.close(e)
                                layer.close(index);    //返回数据关闭loading
                            });
                          }
                           else{
                                layer.msg(res.msg, {
                                    offset: '15px'
                                    ,icon: 2
                                    ,time: 500
                                 });
                                layer.close(index);    //返回数据关闭loading
                            }
                          }
                    });
                }),
                a.trigger("click")
            }
        })
    }),
    t("good", {})
});