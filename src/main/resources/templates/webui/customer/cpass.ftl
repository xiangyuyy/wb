

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>修改密码</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css" media="all">
      <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="../../layuiadmin/style/login.css" media="all">
</head>
<body>
  <div class="layadmin-user-login layadmin-user-display-show" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input type="password" name="password" id="LAY-user-login-password" lay-verify="pass" placeholder="密码" class="layui-input">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-repass"></label>
          <input type="password" name="repass" id="LAY-user-login-repass" lay-verify="required" placeholder="确认密码" class="layui-input">
        </div>
                <div class="layui-form-item">
                  <div class="layui-row">
             <div class="layui-col-xs7">
          <label class="layadmin-user-login-icon layui-icon layui-icon-cellphone" for="LAY-user-login-cellphone"></label>
          <input type="text" maxlength="20"  name="phone" id="LAY-user-login-cellphone" lay-verify="phone" placeholder="手机" class="layui-input">
         </div>
                  <div class="layui-col-xs5">
                   <div class="layui-text" style="margin-left: 10px;line-height: 36px;font-size: 16px;color: #ff5722;" > 
          <label>密保手机</label> 
                   </div>
                    </div>
                      </div>
        </div>
            <div class="layui-form-item layui-hide">
      <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
      <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
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
  }).use(['index', 'user'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,form = layui.form;   
    form.render();
    //监听提交
    form.on('submit(layuiadmin-app-form-submit)', function(data){
      var field = data.field; //获取提交的字段
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
      //确认密码
      if(field.password !== field.repass){
        return layer.msg('两次密码输入不一致');
      }
      //提交 Ajax 成功后，关闭当前弹层并重载表格
      //$.ajax({});
      var index1 = layer.load(1, {
          shade: [0.5,'#000'] //0.1透明度的背景
          });   
       layui.$.ajax({
          type: "post",
          url: "/customer/updatepass",
          data: field,
          dataType: "json",
          success: function(res){
              if(res.sucess){
              layer.msg(res.msg, {
                offset: '15px'
                ,icon: 1
                ,time: 1000
              }, function(){
                  parent.layer.close(index); //再执行关闭 
                  layer.close(index1);    //返回数据关闭loading
              });
            }
             else{
                  layer.msg(res.msg, {
                      offset: '15px'
                      ,icon: 2
                      ,time: 1000
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