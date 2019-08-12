<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8"> 
  <title>添加商品</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css" media="all">
</head>  
<body>
  <style>
  .layui-upload-img{width: 92px; height: 92px; margin: 0 10px 10px 0;}
  </style>
  <div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
            <div class="layui-form-item">
      <label class="layui-form-label">类型</label>
      <div class="layui-input-inline">
        <select name="type" lay-verify="required">
        <#list typeList as type>    
           <#if showphoto.type == type>
          <option value="${type}" selected="">${type}</option>
             <#else>  
          <option value="${type}">${type}</option>
           </#if>
</#list>
        </select>
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">图片</label>
      <div class="layui-input-inline">
              <div class="layui-upload-list">
                         <#if showphoto.url == "">
                <img class="layui-upload-img" id="test-upload-normal-img">
                  <#else>  
                <img class="layui-upload-img" id="test-upload-normal-img" src="..${showphoto.photourl!}">
              </#if>
                <p id="test-upload-demoText"></p>
              </div>
      </div>
      <input id="url" name="url" hidden>
      <button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传图片</button> 
    </div>
            <div class="layui-form-item">
      <label class="layui-form-label">描述</label>
      <div class="layui-input-inline">
        <input name="id" value="${showphoto.id!}" hidden>
        <textarea name=describe lay-verify="" placeholder="描述"  style="width: 400px; height: 150px;" autocomplete="off" class="layui-textarea">${showphoto.describe!}</textarea>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">状态</label>
      <div class="layui-input-inline">
      <#if showphoto.status ==1>
              <input  type="radio" name="status" value="1" title="上架" checked>
              <input  type="radio" name="status" value="0" title="下架">
              </#if>
                    <#if showphoto.status ==0>
              <input  type="radio" name="status" value="1" title="上架" >
              <input  type="radio" name="status" value="0" title="下架" checked>
              </#if>
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
    //普通图片上传
    var uploadInst = upload.render({
      elem: '#layuiadmin-upload-useradmin'
      ,url: '/good/upload/'
      ,before: function(obj){
        //预读本地文件示例，不支持ie8
        obj.preview(function(index, file, result){
          $('#test-upload-normal-img').attr('src', result); //图片链接（base64）
        });
      }
      ,done: function(res){
        //如果上传失败
        if(res.code > 0){
          return layer.msg('上传失败');
        }
        //上传成功
        $("#url").val(res.data);
        return layer.msg('上传成功');
      }
      ,error: function(){
        //演示失败状态，并实现重传
        var demoText = $('#test-upload-demoText');
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
        demoText.find('.demo-reload').on('click', function(){
          uploadInst.upload();
        });
      }
    });
    //监听提交
    form.on('submit(layuiadmin-app-form-submit)', function(data){
      var field = data.field; //获取提交的字段
      var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
        var index1 = layer.load(1, {
        shade: [0.5,'#000'] //0.1透明度的背景
        });                  
        layui.$.ajax({
            type: "post",
            url: "/showphoto/update",
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