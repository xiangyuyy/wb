

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>佣金提现</title>
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
				<div class="layui-card-header">提现说明</div>
				<div class="layui-card-body">

					<fieldset class="layui-elem-field">
						<div class="layui-field-box"
							style="font-size: 16px; color: red; width: 50%; float: left">
							1.佣金提现到余额<br>
							2.佣金提现到余额<br>
							3.佣金提现到余额<br>
							4.佣金提现到余额 </label>
						</div>
	<!-- 					<div class="layui-field-box"
							style="font-size: 16px; color: redwidth:40%; float: left">
							图片</div> -->
					</fieldset>

				</div>
			</div>
		</div>
		<div class="layui-card">
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">佣金提现金额</label>
						<div class="layui-input-inline">
							<input hidden id="customerid" value="${account.customerid!}"> 
											<input hidden id="type" value="1"> 
							<input hidden id="type" value="2"><input
								type="text" name="money" id="money" placeholder="提现金额"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-list" data-type="add" id="add">提现</button>
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
				<script type="text/html" id="type"><!-- //0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4  -->
          {{# if(d.type==0) { }}
            充值
{{# } else if(d.type==1) { }}
            佣金提现
{{# } else if(d.type==2) { }}
            下单消费
{{# } else if(d.type==3) { }}
            升级冻结
{{# } else if(d.type==4) { }}
            冻结解除
{{# } else if(d.type==5) { }}
            提现申请
{{# } else if(d.type==6) { }}
            退单返回 
{{# } else{ }}
            管理员操作 
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
	
			</div>
		</div>
	</div>

	<script src="../../../layuiadmin/layui/layui.js"></script>
	<script>
	  layui.config({
		    base: '../../../layuiadmin/' //静态资源所在路径
		  }).extend({
		    index: 'lib/index' //主入口模块
		  }).use(['index', 'addcommcashlist', 'table'], function(){
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
		    	  var money = $("#money").val();
		    	  if(money == ""){
	                    layer.msg("提现金额不能为空", {
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
						url : "/showorder/ajaxaddcommcash?customerid="+customerid+"&money="+money,
						dataType : "json",
						success : function(res) {
							if (res.sucess) {
			                      layer.close(index1);    //返回数据关闭loading
			                      layer.msg("操作成功", {
			                        offset: '15px'
			                        ,icon: 1
			                        ,time: 1000
			                      }, function(){
							          table.reload('LAY-app-content-list');
			                      });

							} else {
				                layer.close(index1);    //返回数据关闭loading
			                    layer.msg("操作失败", {
			                        offset: '15px'
			                        ,icon: 2
			                        ,time: 1000
			                     });
			                    return false;
							}
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
				                layer.close(index1);    //返回数据关闭loading
			                    layer.msg("网络异常操作失败", {
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
