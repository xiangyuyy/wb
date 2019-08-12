

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>注册 - 福利狐</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="../../layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="../../layuiadmin/style/login.css" media="all">
</head>
<body>


  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">
    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>layuiAdmin</h2>
        <p>福利狐</p>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
<!--         <div class="layui-form-item">
          <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="验证码" class="layui-input">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="LAY-user-getsmscode">获取验证码</button>
              </div>
            </div>
          </div>
        </div> -->
        				<div class="layui-form-item">
					<label
						class="layadmin-user-login-icon layui-icon layui-icon-username"
						for="LAY-user-login-username"></label> <input type="text"
						name="account" id="LAY-user-login-username" lay-verify="nickname"
						placeholder="用户名" class="layui-input" maxlength="20" >
				</div>
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
          <label>找回密码密保手机</label> 
                   </div>
                    </div>
                      </div>
        </div>
               <div class="layui-form-item">
                                 <div class="layui-row">
             <div class="layui-col-xs7">
          <label class="layadmin-user-login-icon layui-icon layui-icon-login-qq" for="LAY-user-login-qq"></label>
          <input type="text" name="qq" maxlength="20"  id="LAY-user-login-qq" lay-verify="qq" placeholder="qq" class="layui-input">
              </div>
                          <div class="layui-col-xs5">
                   <div class="layui-text" style="margin-left: 10px;line-height: 36px;font-size: 16px;color: #ff5722;"> 
          <label>联系方式</label> 
                   </div>
                    </div>
                      </div>
        </div>
                        <div class="layui-form-item">
                                                         <div class="layui-row">
             <div class="layui-col-xs7">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-byinvitatecod"></label>
          <input type="text" name="byinvitatecod" id="LAY-user-login-byinvitatecod" lay-verify="" placeholder="邀请码" class="layui-input">
         </div>
                                   <div class="layui-col-xs5">
                   <div class="layui-text" style="margin-left: 10px;line-height: 36px;font-size: 16px;color: #ff5722;"> 
          <label>邀请码非必填</label> 
                   </div>
                    </div>
                      </div>
        </div>
         <div class="layui-form-item">
                  <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <img src="/valicode" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
              </div>
            </div>
          </div>
          </div>
<!--         <div class="layui-form-item">
          <input type="checkbox" name="agreement" lay-skin="primary" title="同意用户协议" checked>
        </div> -->
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="LAY-user-reg-submit">注 册</button>
        </div>
<!--         <div class="layui-trans layui-form-item layadmin-user-login-other">
          <label>社交账号注册</label>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
          
          <a href="login.html" class="layadmin-user-jump-change layadmin-link layui-hide-xs">用已有帐号登入</a>
          <a href="login.html" class="layadmin-user-jump-change layadmin-link layui-hide-sm layui-show-xs-inline-block">登入</a>
        </div> -->
      </div>
    </div>
    
<!--     <div class="layui-trans layadmin-user-login-footer">
      
      <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
      <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
      </p>
    </div> -->

  </div>

  <script src="../../layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '../../layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'user'], function(){
    var $ = layui.$
    ,setter = layui.setter
    ,admin = layui.admin
    ,form = layui.form
    ,router = layui.router();

    form.render();

    //提交
    form.on('submit(LAY-user-reg-submit)', function(obj){
        var field = obj.field; //获取提交的字段
      //确认密码
      if(field.password !== field.repass){
        return layer.msg('两次密码输入不一致');
      }
      
/*       //是否同意用户协议
      if(!field.agreement){
        return layer.msg('你必须同意用户协议才能注册');
      }
       */
      //请求接口
        var index1 = layer.load(1, {
        shade: [0.5,'#000'] //0.1透明度的背景
        });                  
        layui.$.ajax({
            type: "post",
            url: "/registerin",
            data: field,
            dataType: "json",
            success: function(res){
                if(res.sucess){
                layer.msg(res.msg, {
                  offset: '15px'
                  ,icon: 1
                  ,time: 500
                }, function(){
                  layer.close(index1);    //返回数据关闭loading
                  location.href = '../'; //后台主页
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
              },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("请求异常", {
                        offset: '15px'
                        ,icon: 2
                        ,time: 1000
                     });
                    layer.close(index1);    //返回数据关闭loading
          }
        });
      
      return false;
    });
  });
  </script>
</body>
</html>