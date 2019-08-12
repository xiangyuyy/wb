

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>充值</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="../../../layuiadmin/style/admin.css"
	media="all">
</head>
<body>

	<div class="layui-fluid">
		<div class="layui-form-item">
			<div class="layui-card">
				<div class="layui-card-header">充值说明</div>
				<div class="layui-card-body">

					<fieldset class="layui-elem-field">
						<div class="layui-field-box"
							style="font-size: 16px; color: red; width: 50%; float: left">
							1.支付宝账号：2817689833@qq.com 收款人：杨昆 （也可直接扫描左侧二维码）<br>
							2.用支付宝进行转账，核对好支付宝账号与收款人名称 是否与上面一致<br> （转账时备注里输入平台用户名 可自动充值
							无需3 4两步骤）<br> 3.付款成功后 请复制 此订单的支付宝 交易号/流水号<br>
							4.回到充值页面，填上 支付宝交易号/流水号 ， 点充值 即可完成 </label>
						</div>
						<div class="layui-field-box"
							style="font-size: 16px; color: redwidth:40%; float: left">
							图片</div>
					</fieldset>

				</div>
			</div>
		</div>
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">交易流水号</label>
						<div class="layui-input-inline">
							<input hidden id="customerid" value="${customerid!}"> <input
								type="text" name="thirdnumber" id="thirdnumber" placeholder="交易流水"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-list" data-type="add" id="add">充值</button>
					</div>
				</div>
			</div>

			<div class="layui-card-body">
				<div style="padding-bottom: 10px;">
					<!--           <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button> -->
					<!-- 	<button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
					<button class="layui-btn layuiadmin-btn-list" data-type="adds">批量添加</button> -->
				</div>
				<table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
				<script type="text/html" id="buttonTpl"><!-- //交易状态（0创建失败，1） -->
          {{# if(d.status==0) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">失败</button>
{{# } else{ }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">成功</button>
{{# }}}
        </script>
				<!--时间格式化-->
				<script src="/layuiadmin/date-format.js" type="text/javascript"
					charset="utf-8"></script>
				<script type="text/html" id="createtime">
    {{# 
    var date = new Date();
    return date.Format(d.createtime); 
    }} 
    </script>
				</script>
				<script type="text/html" id="table-content-list">
                    {{# if(d.status==0 || d.status==1 || d.status==2 || d.status==7) { }}
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="stop">停止</a>
{{# } else if(d.status==5) { }}
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="run">启动</a>
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="return">退单</a>
{{# } else{ }}
{{# }}}
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="infor">详情</a>
        </script>
			</div>
		</div>
	</div>

	<script src="../../../layuiadmin/layui/layui.js"></script>
	<script>
	  layui.config({
		    base: '../../../layuiadmin/' //静态资源所在路径
		  }).extend({
		    index: 'lib/index' //主入口模块
		  }).use(['index', 'addbalancelist', 'table'], function(){
		    var table = layui.table
		    ,form = layui.form;
		    //监听搜索
		    form.on('submit(LAY-app-contlist-search)', function(data){
		      var field = data.field;     
		      //执行重载
		      table.reload('LAY-app-content-list', {
		        where: field
		      });
		    });		  
		    var $ = layui.$, active = {
		      add: function(){
		    	  var customerid = $("#customerid").val();
		    	  var thirdnumber = $("#thirdnumber").val();
		    	  if(thirdnumber == ""){
	                    layer.msg("交易流水号不能为空", {
	                        offset: '15px'
	                        ,icon: 2
	                        ,time: 1000
	                     });
	                    return false;
		    	  }
  	            var index1 = layer.load(1, {
	                shade: [0.5,'#000'] //0.1透明度的背景
	                });  
					layui.$.ajax({
						type : "post",
						url : "/showorder/ajaxaddbalance?customerid="+customerid+"&thirdnumber="+thirdnumber,
						dataType : "json",
						success : function(res) {
							if (res.sucess) {
			                      layer.close(index1);    //返回数据关闭loading
			                      layer.msg("充值成功", {
			                        offset: '15px'
			                        ,icon: 1
			                        ,time: 1000
			                      }, function(){
							          table.reload('LAY-app-content-list');
			                      });

							} else {
				                layer.close(index1);    //返回数据关闭loading
			                    layer.msg("充值失败", {
			                        offset: '15px'
			                        ,icon: 2
			                        ,time: 1000
			                     });
			                    return false;
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
				                layer.close(index1);    //返回数据关闭loading
			                    layer.msg("网络异常充值失败", {
			                        offset: '15px'
			                        ,icon: 2
			                        ,time: 1000
			                     });
			                    return false;
						}
					});
		      }

		    }; 
		    $('.layui-btn.layuiadmin-btn-list').on('click', function(){
		      var type = $(this).data('type');
		      active[type] ? active[type].call(this) : '';
		    });

		  });
		  </script>
</body>
</html>
