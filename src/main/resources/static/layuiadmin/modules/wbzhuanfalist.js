/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
    var e = layui.$,
    i = layui.table,
    admin = layui.admin,
    n = layui.form;
    var layer = layui.layer;
    var goodid = e("#goodid").val();
    i.render({
        elem: "#LAY-app-content-list",
        url: "/cgcenter/ajaxwbdianzanlist?goodid="+goodid+"",
        cols: [[
//        	{
//            type: "checkbox",
//            fixed: "left"
//        },
            {
                field: "id",
                width: 150,
                title: "id",
                sort: !0,
                hide:true
            },
        {
            field: "ordernum",
            width: 150,
            title: "订单号",
            sort: !0
        },
        {
            field: "goodname",
            title: "商品名称",
            minWidth: 80
        },

        {
            field: "orderusername",
            title: "微博名称",
            minWidth: 80
        },
        {
            field: "orderurl",
            title: "微博url",
            minWidth: 80
        },
        {
            field: "positionstart",
            title: "初始数量",
            minWidth: 80
        },
        {
            field: "allok",
            title: "已完成数量",
            minWidth: 50
        },
        {
            field: "allnum",
            title: "订单数量",
            minWidth: 50
        },
        {
            field: "money",
            title: "价格",
            minWidth: 50
        },  
        {
            field: "wbcontent",
            title: "内容摘要",
            minWidth: 50
        },
        {
            field: "createtime",
            title: "创建时间",
            templet:'#createtime',
            sort: !0
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
        }) : "infor" === t.event && layer.open({
            type: 2,
            title: "订单详情",
            content: "/cgcenter/wbzhuanfainfor?id=" + e.id,
            maxmin: !0,
            area: ["90%", "90%"],
            btn: ["关闭"],
            yes: function(index) {
            	layer.close(index);
            }
        });
        if( "stop" === t.event){
     	   layer.confirm('您确认要提交吗？', {
	           btn: ['确认','取消'] //按钮
	        }, function(index){
	            layui.$.ajax({
	                type: "post",
	                url: "/order/synStopOrder?id=" + e.id,
	                dataType: "json",
	                success: function(res){              
              if(res.sucess){
              layer.msg(res.msg, {
                offset: '15px'
                ,icon: 1
                ,time: 1000
              }, function(){
          		layer.close(index);
             window.location.reload();
              });
            }
             else{
                  layer.msg(res.msg, {
                      offset: '15px'
                      ,icon: 2
                      ,time: 1000
                   });
              }
            },
          error: function (XMLHttpRequest, textStatus, errorThrown) {
                layer.msg("网络请求异常，请联系管理员", {
                    offset: '15px'
                    ,icon: 2
                    ,time: 1000
                 });
      }
          });
	});
        }
        if( "run" === t.event){
      	   layer.confirm('您确认要提交吗？', {
 	           btn: ['确认','取消'] //按钮
 	        }, function(index){
 	            layui.$.ajax({
 	                type: "post",
 	                url: "/order/synStartOrder?id=" + e.id,
 	                dataType: "json",
 	                success: function(res){              
               if(res.sucess){
               layer.msg(res.msg, {
                 offset: '15px'
                 ,icon: 1
                 ,time: 1000
               }, function(){
           		layer.close(index);
              window.location.reload();
               });
             }
              else{
                   layer.msg(res.msg, {
                       offset: '15px'
                       ,icon: 2
                       ,time: 1000
                    });
               }
             },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
                 layer.msg("网络请求异常，请联系管理员", {
                     offset: '15px'
                     ,icon: 2
                     ,time: 1000
                  });
       }
           });
 	});
         }
        if( "return" === t.event){
      	   layer.confirm('您确认要提交吗？', {
 	           btn: ['确认','取消'] //按钮
 	        }, function(index){
 	            layui.$.ajax({
 	                type: "post",
 	                url: "/order/synRefundOrder?id=" + e.id,
 	                dataType: "json",
 	                success: function(res){              
               if(res.sucess){
               layer.msg(res.msg, {
                 offset: '15px'
                 ,icon: 1
                 ,time: 1000
               }, function(){
           		layer.close(index);
              window.location.reload();
               });
             }
              else{
                   layer.msg(res.msg, {
                       offset: '15px'
                       ,icon: 2
                       ,time: 1000
                    });
               }
             },
           error: function (XMLHttpRequest, textStatus, errorThrown) {
                 layer.msg("网络请求异常，请联系管理员", {
                     offset: '15px'
                     ,icon: 2
                     ,time: 1000
                  });
       }
           });
 	});
         }
    }),
    t("wbzhuanfalist", {})
});