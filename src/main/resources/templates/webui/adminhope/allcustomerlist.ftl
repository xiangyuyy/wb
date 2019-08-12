

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>一级代理列表</title>
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
		<div class="layui-card">
<!-- 			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">订单号</label>
						<div class="layui-input-inline">
							<input
								type="text" name="byordernum" placeholder="订单号" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">下单人卡号</label>
						<div class="layui-input-inline">
							<input type="text" name="bycustomerid" placeholder="下单人卡号"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-list" lay-submit
							lay-filter="LAY-app-contlist-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
					</div>
				</div>
			</div> -->

			<div class="layui-card-body">
				<div style="padding-bottom: 10px;">
					<!--           <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button> -->
				<!-- 	<button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
					<button class="layui-btn layuiadmin-btn-list" data-type="adds">批量添加</button> -->
				</div>
				<table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
												<script type="text/html" id="level">
          {{# if(d.level==0) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">普通会员</button>
{{# } else if(d.level==1) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">>黄金会员</button>
{{# } else if(d.level==2) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">>铂金会员</button>
{{# } else if(d.level==3) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">>钻石会员</button>
{{# } else{ }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">至尊会员</button>
{{# }}}
        </script>
				<script type="text/html" id="status">
          {{# if(d.status==0) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">待核实</button>
{{# } else if(d.status==1) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">正常</button>
{{# } else{ }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">冻结</button>
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
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edite">修改</a>
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="next">二级代理</a>
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
  }).use(['index', 'allcustomerlist', 'table'], function(){
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

  });
  </script>
</body>
</html>
