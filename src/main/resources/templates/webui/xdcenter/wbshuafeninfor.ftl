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
  <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
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
        <input type="text" name="fastprice" value="${order.orderusername!}" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
      </div>
    </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">微博id</label>
      <div class="layui-input-inline cz">
        <input type="text" name="slowprice" value="${order.orderurl!}" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
      </div>
         </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">初始数</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdfastprice" value="${order.positionstart!}" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
      </div>
         </div>
                           <div class="layui-form-item">
      <label class="layui-form-label">已完成数</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdfastprice" value="${order.allok!}" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
      </div>
         </div>
                           <div class="layui-form-item">
      <label class="layui-form-label">刷粉总数</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdfastprice" value="${order.allnum!}" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
      </div>
         </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">当前状态</label>
      <div class="layui-input-inline">
      <#if order.status==-1>
               <input type="text" name="thirdslowprice" value="创建失败" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
<#elseif order.status==0>
                    <input type="text" name="thirdslowprice" value="审核中" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
<#elseif order.status==1>
                    <input type="text" name="thirdslowprice" value="队列中" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	<#elseif order.status==2>
  	                    <input type="text" name="thirdslowprice" value="执行中" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	<#elseif order.status==3>
  	                    <input type="text" name="thirdslowprice" value="有异常" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	<#elseif order.status==5>
  	      <input type="text" name="thirdslowprice" value="已暂停" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	  	<#elseif order.status==7>
  	  	                    <input type="text" name="thirdslowprice" value="今天已完成" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	  	  	<#elseif order.status==8>
  	  	  	  <input type="text" name="thirdslowprice" value="退款中" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
  	  	  	  	  	  	<#elseif order.status==9>
  	  	  	            <input type="text" name="thirdslowprice" value="已完毕" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
<#else>
                    <input type="text" name="thirdslowprice" value="已退款" lay-verify="required|littlenumber"  autocomplete="off" class="layui-input">
</#if>
      </div>
           </div>
                        <div class="layui-form-item">
      <label class="layui-form-label">费用</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdslowprice" value="${order.money!}" lay-verify="required|littlenumber" autocomplete="off" class="layui-input">
      </div>
    </div>
                        <div class="layui-form-item">
      <label class="layui-form-label">下单时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="thirdslowprice" value="${order.createtime?string('yyyy-MM-dd hh:mm:ss')}" lay-verify="required|littlenumber" autocomplete="off" class="layui-input">
      </div>
    </div>
                                    <div class="layui-form-item">
      <label class="layui-form-label">任务开始时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="startime" value="${(order.startime?string('yyyy-MM-dd hh:mm:ss'))!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                            <div class="layui-form-item">
      <label class="layui-form-label">任务结束时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="endtime" value="${(order.endtime?string('yyyy-MM-dd hh:mm:ss'))!}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>
                        <div class="layui-form-item">
      <label class="layui-form-label">备注</label>
      <div class="layui-input-inline cz">
        <input type="text" name="thirdslowprice" value="${order.remark!}" lay-verify="required|littlenumber" autocomplete="off" class="layui-input">
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
  })
  </script>
</body>
</html>