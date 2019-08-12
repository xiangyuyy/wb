

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>订单列表</title>
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
			<div class="layui-form layui-card-header layuiadmin-card-header-auto">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">订单号</label>
						<div class="layui-input-inline">
							<input type="text" name="ordernum" placeholder="订单号"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">会员卡号</label>
						<div class="layui-input-inline">
							<input type="text" name="customerid" placeholder="会员卡号"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">名称</label>
						<div class="layui-input-inline">
							<input type="text" name="orderusername" placeholder="名称"
								autocomplete="off" class="layui-input">
						</div>
					</div>
										<div class="layui-inline">
						<label class="layui-form-label">地址</label>
						<div class="layui-input-inline">
							<input type="text" name="orderurl" placeholder="地址"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">订单类型</label>
						<div class="layui-input-inline">
							<select name="isfast" lay-verify="">
								<option value=""></option>
								<option value="0">慢速订单</option>
								<option value="1">快速订单</option>
							</select>
						</div>
					</div>
										<div class="layui-inline">
						<label class="layui-form-label">订单状态</label>
						<div class="layui-input-inline">
							<select name="status" lay-verify="">
								<option value=""></option>
								<option value="-1">创建失败</option>
								<option value="0">审核中</option>
								<option value="1">队列中</option>
								<option value="2">执行中</option>
								<option value="3">有异常</option>
								<option value="5">已暂停</option>
								<option value="7">今天已完成</option>
								<option value="8">退款中</option>
								<option value="9">已完毕</option>
								<option value="10">已退款</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<button class="layui-btn layuiadmin-btn-list" lay-submit
							lay-filter="LAY-app-contlist-search">
							<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
						</button>
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
				<script type="text/html" id="buttonTpl"><!-- //交易状态（-1创建失败，0 审核中,1 队列中,2 执行中,3 有异常,5 已暂停,7 今天已完成,8 退款中,9 已完毕,10 已退款） -->
          {{# if(d.status==-1) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">创建失败</button>
{{# } else if(d.status==0) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">审核中</button>
{{# } else if(d.status==1) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">队列中</button>
{{# } else if(d.status==2) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">执行中</button>
{{# } else if(d.status==3) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">有异常</button>
{{# } else if(d.status==5) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">已暂停</button>
{{# } else if(d.status==7) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">今天已完成</button>
{{# } else if(d.status==8) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">退款中</button>
{{# } else if(d.status==9) { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">已完毕</button>
{{# } else{ }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">已退款</button>
{{# }}}
        </script>
				<script type="text/html" id="isfast">
          {{# if(d.isfast==0) { }}
            慢速订单
{{# } else{ }}
             快速订单
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
				<!--时间格式化-->
				<script src="/layuiadmin/date-format.js" type="text/javascript"
					charset="utf-8"></script>
				<script type="text/html" id="endtime">
    {{# 
    var date = new Date();
    return date.Format(d.endtime); 
    }} 
    </script>
				<script type="text/html" id="table-content-list">
 <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="eidte">编辑</a>
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
  }).use(['index', 'allorderlist', 'table'], function(){
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
