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
      <label class="layui-form-label">商品名称</label>
      <div class="layui-input-inline">
      <input name="id" value="${good.id!}" hidden>
        <input type="text" name="goodname" value="${good.goodname!}" lay-verify="required" placeholder="商品名称" autocomplete="off" class="layui-input">
      </div>
    </div>
		<div class="layui-form-item">
			<label class="layui-form-label">商品平台</label>
			<div class="layui-input-inline">
				<select name="thirdname" lay-verify="required"> <#if good.thirdname=="慢速">
					<option value="慢速" selected="">慢速</option>
					<option value="顶点">顶点</option>
					<option value="现代">现代</option> <#elseif good.thirdname=="顶点">
					<option value="慢速">慢速</option>
					<option value="顶点" selected="">顶点</option>
					<option value="现代">现代</option> <#else>
					<option value="慢速">慢速</option>
					<option value="顶点">顶点</option>
					<option value="现代" selected="">现代</option> </#if>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
      <label class="layui-form-label">商品平台类型</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdtypename" value="${good.thirdtypename!}" lay-verify="" placeholder="第三方类型下的分类（顶点的 点赞 空闲赞 初级赞）" autocomplete="off" class="layui-input">
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">商品类别</label>
      <div class="layui-input-inline">
        <select name="goodtypename" lay-verify="required">
        <#list goodtypeNameList as goodtype>
           <#if good.goodtypename == goodtype>
          <option value="${goodtype}" selected="">${goodtype}</option>
                <#else>  
          <option value="${goodtype}">${goodtype}</option>
           </#if>
</#list>
        </select>
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">商品分类</label>
      <div class="layui-input-inline">
        <select name="goodclassname" lay-verify="required">
        <#list goodclassNameList as goodclass>        
           <#if good.goodclassname == goodclass>
          <option value="${goodclass}" selected="">${goodclass}</option>
              <#else>    
          <option value="${goodclass}">${goodclass}</option>
          </#if>
</#list>
        </select>
      </div>
    </div>
            <div class="layui-form-item">
      <label class="layui-form-label">商品标签</label>
      <div class="layui-input-inline">
        <select name="sign" lay-verify="required">
        <#list goodsignList as goodsign>    
           <#if good.sign == goodsign>
          <option value="${goodsign}" selected="">${goodsign}</option>
             <#else>  
          <option value="${goodsign}">${goodsign}</option>
           </#if>
</#list>
        </select>
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">价格</label>
      <div class="layui-input-inline">
        <input type="text" name="fastprice" value="${good.fastprice?string(",##0.00000")}" lay-verify="required|littlenumber" placeholder="快速的价格" autocomplete="off" class="layui-input">
      </div>
    </div>
                  <div class="layui-form-item">
      <label class="layui-form-label">拿货的价格</label>
      <div class="layui-input-inline">
        <input type="text" name="thirdfastprice" value="${good.thirdfastprice?string(",##0.00000")}" lay-verify="required|littlenumber" placeholder="拿货快速的价格" autocomplete="off" class="layui-input">
      </div>
         </div> 
                           <div class="layui-form-item">
      <label class="layui-form-label">最低拿货数量</label>
      <div class="layui-input-inline">
        <input type="text" name="minnumber" value="${good.minnumber!}" lay-verify="required" placeholder="最低拿货数量" autocomplete="off" class="layui-input">
      </div>
         </div> 
                        <div class="layui-form-item">
      <label class="layui-form-label">页面地址</label>
      <div class="layui-input-inline">
        <input type="text" name="hopeurl" value="${good.hopeurl!}" lay-verify="required" placeholder="页面地址" autocomplete="off" class="layui-input">
      </div>    
         </div>  
                              <div class="layui-form-item">
      <label class="layui-form-label">排序</label>
      <div class="layui-input-inline">
        <input type="text" name="rank" value="${good.rank!}" lay-verify="required" placeholder="页面显示位置" autocomplete="off" class="layui-input">
      </div>  
        <div class="layui-form-item">
      <label class="layui-form-label">图片</label>
      <div class="layui-input-inline">
              <div class="layui-upload-list">
                         <#if good.photourl == "">
                <img class="layui-upload-img" id="test-upload-normal-img">
                  <#else>  
                <img class="layui-upload-img" id="test-upload-normal-img" src="..${good.photourl!}">
              </#if>
                <p id="test-upload-demoText"></p>
              </div>
      </div>
      <input id="photourl" name="photourl" hidden>
      <button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传图片</button> 
            <button style="float: left;" type="button" class="layui-btn" id="uploadexcel">上传excel</button> 
                     <button style="float: left;" type="button" class="layui-btn" id="exportexcel">下载excel</button> 
    </div>
            <div class="layui-form-item">
      <label class="layui-form-label">描述</label>
      <div class="layui-input-inline">
        <textarea name=describe lay-verify="" placeholder="描述"  style="width: 400px; height: 150px;" autocomplete="off" class="layui-textarea">${good.describe!}</textarea>
      </div>
    </div>
        <div class="layui-form-item">
      <label class="layui-form-label">下单说明</label>
      <div class="layui-input-inline">
        <textarea name=orderdescribe lay-verify="" placeholder="下单说明"  style="width: 400px; height: 150px;" autocomplete="off" class="layui-textarea">${good.orderdescribe!}</textarea>
      </div>
    </div>
    <div class="layui-form-item">
      <label class="layui-form-label">状态</label>
      <div class="layui-input-inline">
      <#if good.status ==1>
              <input  type="radio" name="status" value="1" title="上架" checked >
              <input  type="radio" name="status" value="0" title="下架">
              </#if>
                    <#if good.status ==0>
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
        $("#photourl").val(res.data);
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
    
    //普通图片上传
    var uploadInst1 = upload.render({
      elem: '#uploadexcel'
      ,url: '/good/importexcel/'
      ,accept: 'file' //普通文件
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
        $("#photourl").val(res.data);
        return layer.msg('上传成功');
      }
      ,error: function(){
        //演示失败状态，并实现重传
        var demoText = $('#test-upload-demoText');
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
        demoText.find('.demo-reload').on('click', function(){
          uploadInst1.upload();
        });
      }
    });
    
    exportexcel
    
    $('#exportexcel').on('click', function(){
    	 layui.$.ajax({
        type: "get",
        url: "/good/exportexcel",
        dataType: "json",
        success: function(res){              
  if(res.sucess){

}
 else{

  }
},
error: function (XMLHttpRequest, textStatus, errorThrown) {
}
}); 
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
            url: "/good/update",
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