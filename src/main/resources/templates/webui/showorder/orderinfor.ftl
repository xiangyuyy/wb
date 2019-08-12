<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"> 
  <title>订单详情</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css" media="all">
</head>  
<body>
  <style>
  .layui-upload-img{width: 92px; height: 92px; margin: 0 10px 10px 0;}
  .cz {
	width: 60% !important
}
  </style>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body" style="padding: 15px;">
							<form class="layui-form" action="" lay-filter="component-form-group">
    <div class="layui-form-item">
      <label class="layui-form-label">商品名称</label>
      <div class="layui-input-inline cz">
      <input name="id" value="${order.id!}" hidden>
        <input type="text" name="goodname" value="${order.goodname!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">微博名称</label>
      <div class="layui-input-inline cz">
        <input type="text" name="orderusername" value="${order.orderusername!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
    </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">微博地址</label>
      <div class="layui-input-inline cz">
        <input type="text" name="orderurl" value="${order.orderurl!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
         </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">初始数</label>
      <div class="layui-input-inline">
        <input type="text" name="positionstart" value="${order.positionstart!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
         </div>
                           <div class="layui-form-item">
      <label class="layui-form-label">已完成数</label>
      <div class="layui-input-inline">
        <input type="text" name="allok" value="${order.allok!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
         </div>
                           <div class="layui-form-item">
      <label class="layui-form-label">总数</label>
      <div class="layui-input-inline">
        <input type="text" name="allnum" value="${order.allnum!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
         </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">当前状态</label>
      <div class="layui-input-inline">
      <#if order.status==-1>
               <input type="text"  value="创建失败" lay-verify=""  autocomplete="off" class="layui-input">
<#elseif order.status==0>
                    <input type="text"  value="审核中" lay-verify=""  autocomplete="off" class="layui-input">
<#elseif order.status==1>
                    <input type="text"  value="队列中" lay-verify=""  autocomplete="off" class="layui-input">
  	<#elseif order.status==2>
  	                    <input type="text"  value="执行中" lay-verify=""  autocomplete="off" class="layui-input">
  	<#elseif order.status==3>
  	                    <input type="text"  value="有异常" lay-verify=""  autocomplete="off" class="layui-input">
  	<#elseif order.status==5>
  	      <input type="text"  value="已暂停" lay-verify=""  autocomplete="off" class="layui-input">
  	  	<#elseif order.status==7>
  	  	                    <input type="text"  value="今天已完成" lay-verify=""  autocomplete="off" class="layui-input">
  	  	  	<#elseif order.status==8>
  	  	  	  <input type="text"  value="退款中" lay-verify=""  autocomplete="off" class="layui-input">
  	  	  	  	  	  	<#elseif order.status==9>
  	  	  	            <input type="text"  value="已完毕" lay-verify=""  autocomplete="off" class="layui-input">
<#else>
                    <input type="text"  value="已退款" lay-verify=""  autocomplete="off" class="layui-input">
</#if>
      </div>
      </div>
                              <div class="layui-form-item">
      <label class="layui-form-label">修改状态</label>
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
                        <div class="layui-form-item">
      <label class="layui-form-label">费用</label>
      <div class="layui-input-inline">
        <input type="text" name="money" value="${order.money!}" lay-verify="required|littlenumber" autocomplete="off" class="layui-input">
      </div>
    </div>
                            <div class="layui-form-item">
      <label class="layui-form-label">退款费用</label>
      <div class="layui-input-inline">
        <input type="text" name="returnmoney" value="" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                        <div class="layui-form-item">
      <label class="layui-form-label">下单时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="editecreatetime" value="${(order.createtime?string('yyyy-MM-dd hh:mm:ss'))!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                            <div class="layui-form-item">
      <label class="layui-form-label">任务开始时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="editestartime" value="${(order.startime?string('yyyy-MM-dd hh:mm:ss'))!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                            <div class="layui-form-item">
      <label class="layui-form-label">任务结束时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="editeendtime" value="${(order.endtime?string('yyyy-MM-dd hh:mm:ss'))!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                        <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline cz">
        <input type="text" name="remark" value="${order.remark!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
    					<div class="layui-form-item">
						<div class="layui-input-block">
							<div class="layui-footer" style="text-align: center">
								<button class="layui-btn" lay-submit=""
									lay-filter="component-form-demo1">修改</button>
						<!-- 		<button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
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
  }).use(['index', 'form', 'upload'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,upload = layui.upload
    ,form = layui.form;
    var layer = layui.layer; 
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){ 
    	   var findex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    	            var index1 = layer.load(1, {
    	                shade: [0.5,'#000'] //0.1透明度的背景
    	                });  
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/showorder/updateorder",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){              
                  if(res.sucess){
                      layer.close(index1);    //返回数据关闭loading
                  layer.msg(res.msg, {
                    offset: '15px'
                    ,icon: 1
                    ,time: 1000
                  }, function(){
                      parent.layui.table.reload('LAY-app-content-list'); //重载表格
                      parent.layer.close(findex); //再执行关闭 
                  });
                }
                 else{
                     layer.close(index1);    //返回数据关闭loading
                      layer.msg(res.msg, {
                          offset: '15px'
                          ,icon: 2
                          ,time: 1000
                       });
                  }
                },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
                  layer.close(index1);    //返回数据关闭loading
                    layer.msg("网络请求异常，请联系管理员", {
                        offset: '15px'
                        ,icon: 2
                        ,time: 1000
                     });
          }
              });
    	});
    	return false;//重点二！！！！  阻止提交行为！！
    });  
  })
  </script>
</body>
</html>