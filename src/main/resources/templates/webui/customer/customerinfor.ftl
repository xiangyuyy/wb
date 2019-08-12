<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"> 
  <title>基本信息</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
</head>  
<body>
  <style>
  .cz{width: 60% !important}
  </style>
  <div class="layui-fluid">
    <div class="layui-card">
      <div class="layui-card-body" style="padding: 15px;">
        <div class="layui-form-item">
        <div class="layui-form-mid layui-word-aux" style="padding:0px 20px !important"><button id="changepass" data-type="add" class="layui-btn" >修改密码</button></div>
        </div>
        <form class="layui-form" action="" lay-filter="component-form-group">
                 <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline cz">
              <input type="text" name=account value="${customer.account!}" style="background-color:#f0f1f2" disabled="disabled" autocomplete="off" class="layui-input">
            </div>
          </div>
         <div class="layui-form-item">
            <label class="layui-form-label">用户昵称</label>
            <div class="layui-input-inline cz">
              <input type="text" name="nickname" value="${customer.nickname!}" maxlength="20"  lay-verify="nickname" placeholder="用户昵称" autocomplete="off" class="layui-input">
            </div>
          </div>
                   <div class="layui-form-item">
            <label class="layui-form-label">qq</label>
            <div class="layui-input-inline cz">
              <input type="text" name="qq" value="${customer.qq!}" lay-verify="qq" maxlength="20"  placeholder="qq" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">联系方式</div>
          </div>
                           <div class="layui-form-item">
            <label class="layui-form-label">邀请码</label>
            <div class="layui-input-inline cz">
              <input type="text" name=customerid value="${customer.customerid!}" style="background-color:#f0f1f2" disabled="disabled" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">我的邀请码</div>
          </div>
                                     <div class="layui-form-item">
            <label class="layui-form-label">受邀请码</label>
            <div class="layui-input-inline cz">
              <input type="text" name=byinvitatecod value="${customer.byinvitatecod!}" style="background-color:#f0f1f2" disabled="disabled" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">注册填入的邀请人的邀请码</div>
          </div>
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="component-form-demo1">提交修改</button>
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
  }).use(['index', 'form', 'table'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    
    
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    	            var index1 = layer.load(1, {
    	                shade: [0.5,'#000'] //0.1透明度的背景
    	                });
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/customer/update",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){              
                  if(res.sucess){
                  layer.msg(res.msg, {
                    offset: '15px'
                    ,icon: 1
                    ,time: 1000
                  }, function(){
              		layer.close(index);
                    layer.close(index1);    //返回数据关闭loading
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
                  layer.close(index1);    //返回数据关闭loading
                }
              });
    	}/* , function(){
    		layer.msg('已取消..');
    	 return false;
    	} */);
    	return false;//重点二！！！！  阻止提交行为！！
    });   

    	    $('#changepass').on('click', function(){
    	        layer.open({
      	          type: 2
      	          ,title: '修改密码'
      	          ,content: '/customer/cpass'
      	          ,maxmin: true
      	          ,area: ['450px', '450px']
      	          ,btn: ['确定', '取消']
      	          ,yes: function(index, layero){
      	            //点击确认触发 iframe 内容中的按钮提交
       	            var submit = layero.find('iframe').contents().find("#layuiadmin-app-form-submit");
       	            submit.click();
      	          }
      	        }); 
    	    });

  });
  </script>
</body>
</html>