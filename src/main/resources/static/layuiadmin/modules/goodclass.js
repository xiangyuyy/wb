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
        url: "/goodclass/getlist",
        cols: [[
//        	{
//            type: "checkbox",
//            fixed: "left"
//        },
        {
            field: "id",
            width: 120,
            title: "商品分类ID",
            sort: !0
        },
        {
            field: "name",
            title: "商品分类名称",
            minWidth: 100
        },
        {
            field: "describe",
            title: "商品分类描述"
        },
        {
            field: "rank",
            title: "排序",
            width: 100,
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
            title: "编辑商品分类",
            content: "/goodclass/listform?id=" + e.id,
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
                        url: "/goodclass/update",
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
    i.render({
        elem: "#LAY-app-content-tags",
        url: layui.setter.base + "json/content/tags.js",
        cols: [[{
            type: "numbers",
            fixed: "left"
        },
        {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        },
        {
            field: "tags",
            title: "分类名",
            minWidth: 100
        },
        {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#layuiadmin-app-cont-tagsbar"
        }]],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-content-tags)",
    function(t) {
        var i = t.data;
        if ("del" === t.event) layer.confirm("确定删除此分类？",
        function(e) {
            t.del(),
            layer.close(e)
        });
        else if ("edit" === t.event) {
            e(t.tr);
            layer.open({
                type: 2,
                title: "编辑分类",
                content: "../../../views/app/content/tagsform.html?id=" + i.id,
                area: ["450px", "200px"],
                btn: ["确定", "取消"],
                yes: function(e, i) {
                    var n = i.find("iframe").contents().find("#layuiadmin-app-form-tags"),
                    l = n.find('input[name="tags"]').val();
                    l.replace(/\s/g, "") && (t.update({
                        tags: l
                    }), layer.close(e))
                },
                success: function(t, e) {
                    var n = t.find("iframe").contents().find("#layuiadmin-app-form-tags").click();
                    n.find('input[name="tags"]').val(i.tags)
                }
            })
        }
    }),
    i.render({
        elem: "#LAY-app-content-comm",
        url: layui.setter.base + "json/content/comment.js",
        cols: [[{
            type: "checkbox",
            fixed: "left"
        },
        {
            field: "id",
            width: 100,
            title: "ID",
            sort: !0
        },
        {
            field: "reviewers",
            title: "评论者",
            minWidth: 100
        },
        {
            field: "content",
            title: "评论内容",
            minWidth: 100
        },
        {
            field: "commtime",
            title: "评论时间",
            minWidth: 100,
            sort: !0
        },
        {
            title: "操作",
            width: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-com"
        }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！"
    }),
    i.on("tool(LAY-app-content-comm)",
    function(t) {
        t.data;
        "del" === t.event ? layer.confirm("确定删除此条评论？",
        function(e) {
            t.del(),
            layer.close(e)
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑评论",
            content: "../../../views/app/content/contform.html",
            area: ["450px", "300px"],
            btn: ["确定", "取消"],
            yes: function(t, e) {
                var n = window["layui-layer-iframe" + t],
                l = "layuiadmin-app-comm-submit",
                a = e.find("iframe").contents().find("#" + l);
                n.layui.form.on("submit(" + l + ")",
                function(e) {
                    e.field;
                    i.reload("LAY-app-content-comm"),
                    layer.close(t)
                }),
                a.trigger("click")
            },
            success: function(t, e) {}
        })
    }),
    t("goodclass", {})
});