

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>会员升级</title>
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
				<div class="layui-card-header">会员升级说明</div>
				<div class="layui-card-body">

					<fieldset class="layui-elem-field">
						<div class="layui-field-box"
							style="font-size: 16px; color: red; width: 50%; float: left">
							冻结好多升级 或者消费满好多升级<br>
							1.普通会员（无折扣）<br>
							2.黄金会员（${systempara.levelonediscount!}折）  &nbsp; &nbsp;需要消费${systempara.leveloneordermy!}  &nbsp; &nbsp;需要冻结${systempara.levelone!}<br>
							3.铂金会员（${systempara.leveltwodiscount!}折）  &nbsp; &nbsp;需要消费${systempara.leveltwoordermy!}  &nbsp; &nbsp;需要冻结${systempara.leveltwo!}<br>
							4.钻石会员（${systempara.levelthreediscount!}折）  &nbsp; &nbsp;需要消费${systempara.levelthreeordermy!}  &nbsp; &nbsp;需要冻结${systempara.levelthree!} <br>
							5.至尊会员（${systempara.levelfourdiscount!}折）  &nbsp; &nbsp;需要消费${systempara.levelfourordermy!}  &nbsp; &nbsp;需要冻结${systempara.levelfour!}<br></label>
						</div>
	<!-- 					<div class="layui-field-box"
							style="font-size: 16px; color: redwidth:40%; float: left">
							图片</div> -->
					</fieldset>

				</div>
			</div>
		</div>
		<div class="layui-card">
						<div class="layui-card-header">冻结升级</div>
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
									<input hidden id="customerid" value="${account.customerid!}"> 
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-list" data-type="add" data-stype="1">升级黄金会员</button>
							<button class="layui-btn layuiadmin-btn-list" data-type="add" data-stype="2" >铂金会员</button>
								<button class="layui-btn layuiadmin-btn-list" data-type="add" data-stype="3" >钻石会员</button>
									<button class="layui-btn layuiadmin-btn-list" data-type="add" data-stype="4" >至尊会员</button>
					</div>
				</div>
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
		    	  var stype = $(this).data('stype')
  	            var index1 = layer.load(1, {
	                shade: [0.5,'#000'] //0.1透明度的背景
	                });  
					layui.$.ajax({
						type : "post",
						url : "/showorder/ajaxcustomerupgrade?customerid="+customerid+"&stype="+stype,
						dataType : "json",
						success : function(res) {
							if (res.sucess) {
			                      layer.close(index1);    //返回数据关闭loading
			                      layer.msg("升级成功", {
			                        offset: '15px'
			                        ,icon: 1
			                        ,time: 1000
			                      }, function(){
			                       	  window.location.reload();
			                      });

							} else {
				                layer.close(index1);    //返回数据关闭loading
			                    layer.msg("升级失败", {
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
