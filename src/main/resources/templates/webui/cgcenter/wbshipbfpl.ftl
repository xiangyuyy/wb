<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>微博视频播放批量下单</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="../../../layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
</head>
<body>
	<style>
.cz {
	width: 60% !important
}

.cs {
	width: 30% !important
}

.c {
	width: 80% !important
}

.need {
	padding:5px;
	right: -10px;
	top: 0;
	color: red;
}
</style>
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body" style="padding: 15px;">
				<div class="layui-form-item">
					<div class="layui-card">
						<div class="layui-card-header">商品描述</div>
						<div class="layui-card-body">

							<fieldset class="layui-elem-field">
								<div class="layui-field-box" style="font-size: 16px; color: red">
									${good.describe!}</div>
							</fieldset>

						</div>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-card">
						<div class="layui-card-header">下单说明</div>
						<div class="layui-card-body">

							<fieldset class="layui-elem-field">
								<div class="layui-field-box" style="font-size: 16px; color: red">
									${good.orderdescribe!}</div>
							</fieldset>

						</div>
					</div>
				</div>
				<form class="layui-form" action="" lay-filter="component-form-group">
					<div class="layui-card-header">下单</div>
					<div class="layui-form-item">
						<label class="layui-form-label">已选产品</label>
						<div class="layui-input-inline cz">
						<input name="fastprice" value="${good.fastprice?string(",##0.00000")}" hidden>
						<input name="goodid" value="${good.id!}" hidden>

							<div class="layui-form-mid" style="font-size: 16px; color: red">
								${good.goodname!}   <span id="price" style="padding: 10px">	${good.fastprice!*10000}</span>粉币/万
							</div>
						</div>
					</div>

					<div class="layui-form-item" id="rsd">
						<label class="layui-form-label">视频地址,数量<span class="need">*</span></label>
						<div class="layui-input-inline cs">
        <textarea name="url" lay-verify="required" placeholder=""  style="width: 400px; height: 150px;" autocomplete="off" class="layui-textarea"></textarea>
						</div>
						<div class="layui-form-mid layui-word-aux"><span>格式为：视频地址,刷粉数量</span></br>
<span>http://weibo.com/2401564465/yiPMG9eAg,1000</span></br>
<span>http://www.weibo.com/1768025224/yhFlWgLHL,2000</span></div>
					</div>								
					<div class="layui-form-item">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-inline cz">
							<textarea name=remark lay-verify="" placeholder="备注"
								style="width: 100%; height: 100px;" autocomplete="off"
								class="layui-textarea"></textarea>
						</div>
					</div>
					<div class="layui-form-item layui-layout-admin">
						<div class="layui-input-block">
							<div class="layui-footer" style="left: 0;">
								<button class="layui-btn" lay-submit=""
									lay-filter="component-form-demo1">提交</button>
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
  }).use(['index', 'form', 'table'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    form.on('radio(kind)', function (data) {
        var kind=data.value;
        if(kind == "3"){
        	$("#facekind").show();
        }else{
         	$("#facekind").hide();
        }
        if(kind == "2"){
        	$("#rid").show();
          	$("#rsd").hide();
        }else{
        	$("#rsd").show();
          	$("#rid").hide();
        }
    }); 

    
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	var wburl = obj.field.orderurl;
           var urlrid = obj.field.urlrid;
           var url = obj.field.url;
    	   var kind = $("input[name='kind']:checked").val();   	   
      	   //获取checkbox[name='facekind']的值
    	   var arr = new Array();
    	   $("input:checkbox[name='facekind']:checked").each(function(i){
    	    arr[i] = $(this).val();
    	   });
    	   obj.field.facekind = arr.join(",");//将数组合并成字符串
 	      var findex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/order/addswbshipbf",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){              
                  if(res.sucess){
                  layer.msg(res.msg, {
                    offset: '15px'
                    ,icon: 1
                    ,time: 1500
                  }, function(){
                      parent.layui.table.reload('LAY-app-content-list'); //重载表格
                      parent.layer.close(findex); //再执行关闭 
                  });
                }
                 else{
                	 layer.alert(res.msg)
                  }
                },
              error: function (XMLHttpRequest, textStatus, errorThrown) {
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

  });
  </script>
</body>
</html>