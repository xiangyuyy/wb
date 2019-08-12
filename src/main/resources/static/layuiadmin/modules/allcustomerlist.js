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
        url: "/adminhope/ajaxallcustomerlist",
        cols: [[
//        	{
//            type: "checkbox",
//            fixed: "left"
//        },
        {
            field: "customerid",
            width: 150,
            title: "卡号",
            sort: !0
        },
        {
            field: "account",
            title: "登录名称",
            minWidth: 80
        },
        {
            field: "nickname",
            title: "昵称",
            minWidth: 80
        },
        
        {
            field: "balance",
            title: "余额",
            minWidth: 80
        },
        {
            field: "monetary",
            title: "消费总金额",
            minWidth: 80
        },
        {
            field: "commision",
            title: "总佣金",
            minWidth: 80
        },
        {
            field: "cashcommision",
            title: "已提现佣金",
            minWidth: 80
        },
        {
            field: "nocashcommision",
            title: "待提现佣金",
            minWidth: 80
        },
        {
            field: "customerid",
            title: "邀请码",
            minWidth: 80
        },
        {
            field: "byinvitatecod",
            title: "受邀请人码",
            minWidth: 80
        },
        {
            field: "level",
            title: "会员等级",
            templet:'#level',
            minWidth: 80
        },
        {
            field: "status",
            title: "状态",
            templet:'#status',
            minWidth: 80
        },
        {
            field: "createtime",
            title: "注册时间",
            templet:'#createtime',
            sort: !0
        },
        {
            title: "操作",
            minWidth: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-list"
        }
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
        }) : "next" === t.event && layer.open({
            type: 2,
            title: "二级代理",
            content: "/showorder/twocommcustomerlist?bytwocustomerid=" + e.customerid,
            maxmin: !0,
            area: ["90%", "90%"],
            btn: ["关闭"],
            yes: function(index) {
            	layer.close(index);
            }
        });
        if( "edite" === t.event){
        	layer.open({
                type: 2,
                title: "会员修改",
                content: "/adminhope/customerinfor?id=" + e.customerid,
                maxmin: !0,
                area: ["90%", "90%"],
                btn: ['确定', '取消']
            ,yes: function(index, layero){
              //点击确认触发 iframe 内容中的按钮提交
              var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
              submit.click();
            }
            });
         }
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
    t("allcustomerlist", {})
});