

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>余额变更详情</title>
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
						<label class="layui-form-label">会员卡号</label>
						<div class="layui-input-inline">
							<input type="text" name="customerid" placeholder="会员卡号"
								autocomplete="off" class="layui-input">
						</div>
					</div>
										<div class="layui-inline">
						<label class="layui-form-label">类型</label>
						<div class="layui-input-inline">
							<select name="type" lay-verify="">
											<option value=""></option>
								<option value="0">充值</option>
								<option value="1">佣金提现</option>
								<option value="2">下单消费</option>
								<option value="3">升级冻结</option>
								<option value="4">冻结解除</option>
									<option value="6">退单返回</option>
										<option value="7">管理员操作</option>
							</select>
						</div>
					</div>
					<div class="layui-inline">
						<label class="layui-form-label">消费订单号</label>
						<div class="layui-input-inline">
							<input type="text" name="detailordernum" placeholder="名称"
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
			</div>

			<div class="layui-card-body">
				<div style="padding-bottom: 10px;">
					<!--           <button class="layui-btn layuiadmin-btn-list" data-type="batchdel">删除</button> -->
				<!-- 	<button class="layui-btn layuiadmin-btn-list" data-type="add">添加</button>
					<button class="layui-btn layuiadmin-btn-list" data-type="adds">批量添加</button> -->
				</div>
				<table id="LAY-app-content-list" lay-filter="LAY-app-content-list"></table>
				<script type="text/html" id="type"><!-- //0充值 1佣金提现 2下单消费 升级冻结3 冻结解除4  5 提现申请   6退单返回   7管理员操作 -->
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
  }).use(['index', 'allbalancerecordlist', 'table'], function(){
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
