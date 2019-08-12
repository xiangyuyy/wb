<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"> 
  <title>会员详情</title>
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
      <label class="layui-form-label">会员卡号</label>
      <div class="layui-input-inline cz">
        <input type="text" name="customerid" value="${customer.customerid!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
    </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">登录账号</label>
      <div class="layui-input-inline cz">
        <input type="text" name="account" value="${customer.account!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">昵称</label>
      <div class="layui-input-inline">
        <input type="text" name="nickname" value="${customer.nickname!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                           <div class="layui-form-item">
      <label class="layui-form-label">qq</label>
      <div class="layui-input-inline">
        <input type="text" name="qq" value="${customer.qq!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                                                   <div class="layui-form-item">
      <label class="layui-form-label">手机号码</label>
      <div class="layui-input-inline">
        <input type="text" name="phone" value="${customer.phone!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
                                    <div class="layui-form-item">
      <label class="layui-form-label">注册ip</label>
      <div class="layui-input-inline">
        <input type="text" name="ip" value="${customer.ip!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                                                <div class="layui-form-item">
      <label class="layui-form-label">冻结资金</label>
      <div class="layui-input-inline">
        <input type="text" name="levelmony" value="${customer.levelmony!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
      
                                                <div class="layui-form-item">
      <label class="layui-form-label">余额</label>
      <div class="layui-input-inline">
        <input type="text" name="balance" value="${customer.balance!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
      
                                                <div class="layui-form-item">
      <label class="layui-form-label">消费总金额</label>
      <div class="layui-input-inline">
        <input type="text" name="monetary" value="${customer.monetary!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
      
                                                <div class="layui-form-item">
      <label class="layui-form-label">总佣金</label>
      <div class="layui-input-inline">
        <input type="text" name="commision" value="${customer.commision!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                                                         <div class="layui-form-item">
      <label class="layui-form-label">已提现佣金</label>
      <div class="layui-input-inline">
        <input type="text" name="cashcommision" value="${customer.cashcommision!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
      
                                                               <div class="layui-form-item">
      <label class="layui-form-label">待提现佣金</label>
      <div class="layui-input-inline">
        <input type="text" name="nocashcommision" value="${customer.nocashcommision!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                                                                        <div class="layui-form-item">
      <label class="layui-form-label"> 邀请码</label>
      <div class="layui-input-inline">
        <input type="text" name="" value="${customer.customerid!}" lay-verify="required"  autocomplete="off" class="layui-input">
      </div>
         </div>
                                                                        <div class="layui-form-item">
      <label class="layui-form-label">受邀请人码 </label>
      <div class="layui-input-inline">
        <input type="text" name="byinvitatecod" value="${customer.byinvitatecod!}" lay-verify=""  autocomplete="off" class="layui-input">
      </div>
         </div>  
                                 <div class="layui-form-item">
      <label class="layui-form-label">注册时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="" value="${customer.createtime?string('yyyy-MM-dd hh:mm:ss')}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>     
                                     <div class="layui-form-item">
      <label class="layui-form-label">最后修改时间</label>
      <div class="layui-input-inline"> 
        <input type="text" name="" value="${customer.updatetime?string('yyyy-MM-dd hh:mm:ss')}" lay-verify="" autocomplete="off" class="layui-input">
      </div>
    </div>    
                           <div class="layui-form-item">
      <label class="layui-form-label">当前等级</label>
      <div class="layui-input-inline">
      <#if customer.level==0>
               <input type="text" name="" value="普通会员" lay-verify=""  autocomplete="off" class="layui-input">
<#elseif customer.level==1>
                    <input type="text" name="" value="黄金会员" lay-verify=""  autocomplete="off" class="layui-input">
                    <#elseif customer.level==2>
                    <input type="text" name="" value="铂金会员" lay-verify=""  autocomplete="off" class="layui-input">
                    <#elseif customer.level==3>
                    <input type="text" name="" value="钻石会员" lay-verify=""  autocomplete="off" class="layui-input">
<#else>
                    <input type="text" name="" value="至尊会员" lay-verify=""  autocomplete="off" class="layui-input">
</#if>
      </div>
           </div>
                                               <div class="layui-form-item">
      <label class="layui-form-label">修改状态</label>
      <div class="layui-input-inline">
							<select name="level" lay-verify="">
								<option value="-1">请选择</option>
								<option value="0">普通会员</option>
								<option value="1">黄金会员</option>
								<option value="2">铂金会员</option>
								<option value="3">钻石会员</option>
								<option value="4">至尊会员</option>
							</select>
      </div>
         </div>                                                                       <div class="layui-form-item">        
         
                  <div class="layui-form-item">
      <label class="layui-form-label">当前状态</label>
      <div class="layui-input-inline">
      <#if customer.status==0>
               <input type="text" name="" value="待核实" lay-verify=""  autocomplete="off" class="layui-input">
<#elseif customer.status==1>
                    <input type="text" name="" value="正常" lay-verify=""  autocomplete="off" class="layui-input">
<#else>
                    <input type="text" name="" value="冻结" lay-verify=""  autocomplete="off" class="layui-input">
</#if>
      </div>
           </div>
                                               <div class="layui-form-item">
      <label class="layui-form-label">修改状态</label>
      <div class="layui-input-inline">
							<select name="status" lay-verify="">
								<option value="-1">请选择</option>
								<option value="0">待核实</option>
								<option value="1">正常</option>
								<option value="2">冻结</option>
							</select>
      </div>
         </div>

    </div>
        <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
      <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
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
    //监听提交
    form.on('submit(layuiadmin-app-form-submit)', function(data){
      var field = data.field; //获取提交的字段
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
        var index1 = layer.load(1, {
        shade: [0.5,'#000'] //0.1透明度的背景
        });                  
        layui.$.ajax({
            type: "post",
            url: "/adminhope/ajaxupdatecustomer",
            data: field,
            dataType: "json",
            success: function(res){
                if(res.sucess){
                layer.msg(res.msg, {
                  offset: '15px'
                  ,icon: 1
                  ,time: 500
                }, function(){
                  form.render(),
                  parent.layui.table.reload('LAY-app-content-list'); //重载表格
                  parent.layer.close(index); //再执行关闭 
                  layer.close(index1);    //返回数据关闭loading
                });
              }
               else{
                    layer.msg(res.msg, {
                        offset: '15px'
                        ,icon: 2
                        ,time: 500
                     });
                    layer.close(index1);    //返回数据关闭loading
                }
              }
        });
    });
  })
  </script>
</body>
</html>