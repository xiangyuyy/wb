/** layuiAdmin.std-v1.2.1 LPPL License By http://www.layui.com/admin/ */
;
layui.define(["table", "form"],
function(t) {
    var e = layui.$,
    i = layui.table,
    admin = layui.admin,
    n = layui.form;
    var layer = layui.layer;
    var customerid = e("#customerid").val();
    i.render({
        elem: "#LAY-app-content-list",
        url: "/showorder/ajaxaddbalancelist?customerid="+customerid+"",
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
            field: "balanceorderid",
            width: 150,
            title: "订单号",
            sort: !0
        },
        {
            field: "balance",
            title: "充值金额",
            minWidth: 80
        },
        {
            field: "thirdnumber",
            title: "交易流水号",
            minWidth: 150
        },
        {
            field: "beforbalance",
            title: "充值前余额",
            minWidth: 80
        },
        {
            field: "afterbalance",
            title: "充值后余额",
            minWidth: 80
        },

        {
            field: "status",
            title: "状态",
            templet: "#buttonTpl",
            minWidth: 80,
            align: "center"
        },
        {
            field: "createtime",
            title: "创建时间",
            templet:'#createtime',
            sort: !0
        },
/*        {
            title: "操作",
            minWidth: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-list"
        }*/
        ]],
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
            content: "/cgcenter/wbshouyydinfor?id=" + e.id,
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
    t("addbalancelist", {})
});