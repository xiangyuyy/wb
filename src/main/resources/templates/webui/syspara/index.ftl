

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>系统设置</title>
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
			<div class="layui-card-header">系统设置</div>
			<div class="layui-card-body" style="padding: 15px;">
				<form class="layui-form" action="" lay-filter="component-form-group">

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">等级1需要冻结的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="levelone"
									value="${systempara.levelone!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级2需要冻结的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="leveltwo"
									value="${systempara.leveltwo!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级3需要冻结的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="levelthree"
									value="${systempara.levelthree!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级4需要冻结的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="levelfour"
									value="${systempara.levelfour!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">等级1需要消费的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="leveloneordermy"
									value="${systempara.leveloneordermy!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级2需要消费的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="leveltwoordermy"
									value="${systempara.leveltwoordermy!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级3需要消费的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="levelthreeordermy"
									value="${systempara.levelthreeordermy!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级4需要消费的钱</label>
							<div class="layui-input-inline">
								<input type="text" name="levelfourordermy"
									value="${systempara.levelfourordermy!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">等级1的折扣 </label>
							<div class="layui-input-inline">
								<input type="text" name="levelonediscount"
									value="${systempara.levelonediscount!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级2的折扣</label>
							<div class="layui-input-inline">
								<input type="text" name="leveltwodiscount"
									value="${systempara.leveltwodiscount!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级3的折扣</label>
							<div class="layui-input-inline">
								<input type="text" name="levelthreediscount"
									value="${systempara.levelthreediscount!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">等级4的折扣</label>
							<div class="layui-input-inline">
								<input type="text" name="levelfourdiscount"
									value="${systempara.levelfourdiscount!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">一级返佣比例</label>
							<div class="layui-input-inline">
								<input type="text" name="commisionone"
									value="${systempara.commisionone!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">二级返佣比例</label>
							<div class="layui-input-inline">
								<input type="text" name="commisiontwo"
									value="${systempara.commisiontwo!}"
									lay-verify="required|littlenumber" autocomplete="off"
									class="layui-input">
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">管理员账号</label>
							<div class="layui-input-inline">
								<input name="id" value="${systempara.id!}" hidden>
									<input name="oldPass" value="${systempara.adminpassword!}" hidden>
								 <input
									type="text" name="adminaccount"
									value="${systempara.adminaccount!}" lay-verify="required"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">管理员密码</label>
							<div class="layui-input-inline">
								<input type="text" name="adminpassword"
									value="${systempara.adminpassword!}" lay-verify="required"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">顶点token</label>
							<div class="layui-input-inline">
								<input type="text" name="token" value="${systempara.token!}"
									lay-verify="required" autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<div class="layui-form-item layui-layout-admin">
						<div class="layui-input-block">
							<div class="layui-footer" style="left: 0;">
								<button class="layui-btn" lay-submit=""
									lay-filter="component-form-demo1">立即提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


	<script src="../../../layuiadmin/layui/layui.js"></script>
	<script>
  layui.config({
    base: '../../../layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'form', 'laydate'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    
    form.render(null, 'component-form-group');

    laydate.render({
      elem: '#LAY-component-form-group-date'
    });
    
    /* 自定义验证规则 */
    form.verify({
      title: function(value){
        if(value.length < 5){
          return '标题至少得5个字符啊';
        }
      }
      ,pass: [/^(\w){6,12}$/, '密码必须由6-12位数字或者字母组成']
      ,content: function(value){
        layedit.sync(editIndex);
      }
    });
    
    /* 监听指定开关 */
    form.on('switch(component-form-switchTest)', function(data){
      layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
        offset: '6px'
      });
      layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
    });
    
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    		    admin.req({
                url:'/syspara/update' //实际使用请改成服务端真实接口
                ,data: obj.field
                ,done: function(res){                  
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
                }
              });
    	}/* , function(){
    		layer.msg('已取消..');
    	 return false;
    	} */);
    	return false;//重点二！！！！  阻止提交行为！！
    });
  });
  </script>
</body>
</html>
