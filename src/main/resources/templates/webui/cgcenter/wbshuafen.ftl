<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>微博刷粉下单</title>
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
	padding: 5px;
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
							<input name="goodname" id="goodname" value="${good.goodname!}" hidden>
							
							<div class="layui-form-mid" style="font-size: 16px; color: red">
								${good.goodname!} <span id="price" style="padding: 10px">
									${good.fastprice!*10000}</span>粉币/万
															<span style="padding: 10px">会员折扣为          ${zhek!} </span>
							</div>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">微博地址或者ID<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="orderurl" value="" maxlength="100"
								lay-verify="required" placeholder="微博地址或者ID" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux">http://weibo.com/u/1742727537
							或者 微博ID：1742727537。</div>
																						<div class="layui-form-item" id="postdiv" hidden>
					<label class="layui-form-label"></label>
						<div class="layui-input-inline cz">
						<span id="namecontent" style="color:red"></span>     当前粉丝数:<span id="fsnum" style="color:red"></span>
						</div>
					</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">数量<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="allnum" value="500"
								lay-verify="required|number" placeholder="数量" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<#if good.goodname =="空闲粉" || good.goodname =="初级粉">
					<div class="layui-form-item">
						<label class="layui-form-label">刷粉计划</label>
						<div class="layui-input-inline cs">
							<input type="radio" name="iftiming" value="0" title="自动计划"
								checked lay-filter="plan">
						</div>
					</div>
					<#else>
					<div class="layui-form-item">
						<label class="layui-form-label">刷粉计划</label>
						<div class="layui-input-inline cs" disabled="disabled">
							<input type="radio" name="iftiming" value="0" title="自动计划"
								checked lay-filter="iftiming"> <input type="radio"
								name="iftiming" value="1" title="手动计划" lay-filter="iftiming">
						</div>
					</div>
					</#if>

					<div class="layui-form-item" id="planbyhand" hidden>
						<div class="layui-inline">
							<label class="layui-form-label">手动计划 从</label>
							<div class="layui-input-inline">
								<input type="text" name="startdate" class="layui-input"
									id="plantime" placeholder="yyyy-MM-dd">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">开始，每天</label>
							<div class="layui-input-inline">
								<select name="perhour" lay-verify="required">
									<option value="0">0</option>
									<option value="8" selected>8</option>
									<option value="10">10</option>
									<option value="12">12</option>
									<option value="18">18</option>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">点开始加</label>
							<div class="layui-input-inline">
								<input type="text" name="pernum" value="100" 
									lay-verify="required|number" placeholder="数量"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
					<#if good.goodname?index_of("超级粉")!=-1>
					<div class="layui-form-item">
						<label class="layui-form-label">执行速度</label>
						<div class="layui-input-inline cz">
							<select name="speed" lay-verify="required">
								<option value="1">慢速</option>
								<option value="2" selected>普速</option>
								<option value="3">快速</option>
								<option value="4">极速</option>
							</select>
						</div>
					</div>
					<#else>
					<div class="layui-form-item">
						<label class="layui-form-label">执行速度</label>
						<div class="layui-input-inline cz">
							<select name="speed" lay-verify="required" disabled="disabled">
								<option value="1">慢速</option>
								<option value="2" selected>普速</option>
								<option value="3">快速</option>
								<option value="4">极速</option>
							</select>
						</div>
					</div>
					</#if>
					<div class="layui-form-item">
						<label class="layui-form-label">费用总计</label>
						<div class="layui-input-inline cz">
							<input type="text" name="money" value="${good.fastprice!*500}"
								style="background-color: #f0f1f2" disabled="disabled"
								autocomplete="off" class="layui-input">
						</div>
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
  }).use(['index', 'form', 'table','laydate'], function(){
    var $ = layui.$
    ,admin = layui.admin
    ,element = layui.element
    ,layer = layui.layer
    ,laydate = layui.laydate
    ,form = layui.form;
    var righturl = 0;//标识正确的url
    //常规用法
    laydate.render({
      elem: '#plantime',
      min:minDate()
    });
 // 设置最小可选的日期
    function minDate(){
        var now = new Date();
        return now.getFullYear()+"-" + (now.getMonth()+1) + "-" + now.getDate();
     }
 //orderurl验证
    
    $("input[name='orderurl']").blur(function(){
    	var wburl = $(this).val();
        layui.$.ajax({
            type: "post",
            url: '/order/getwbuserInfor?wburl='+wburl,
            dataType: "json",
            success: function(res){              
      if(res.sucess){
           var data = res.data;
           var o = eval('(' + data + ')');
           var name = o.name;
           var followers_count  = o.followers_count;
           $("#postdiv").show();
           $("#namecontent").text(name);
           $("#fsnum").text(followers_count);
           righturl = 1;
    }
     else{
    	 righturl = 0
         layer.msg("微博 地址或者id不正确", {
             offset: '15px'
             ,icon: 2
             ,time:500
          });
         $("#postdiv").hide();
         return false;
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {
    	righturl = 0
        layer.msg("网络异常请联系管理员", {
            offset: '15px'
            ,icon: 2
         });
        $("#postdiv").hide();
        return false;
    }
    });
    	});
    form.on('radio(iftiming)', function (data) {
        var kind=data.value;
        if(kind == 1){
        	$("#planbyhand").show();
        }
        else{
        	$("#planbyhand").hide();
        }
    }); 
    $("input[name='allnum']").on('input propertychange',function(){
        var sum = parseInt($(this).val());
        if($(this).val() == ""){
      	   sum = 500;
      }
        var goodname = $("#goodname").val();
        if(goodname.indexOf("初级粉")>-1){
        	if(sum<500){
                layer.msg("初级粉最小数量为500", {
                    offset: '15px'
                    ,icon: 2
                 });
                //$("input[name='allnum']").val(500); 
                sum = 500;
        	}
        }
        if(goodname.indexOf("高级粉")>-1){
        	if(sum<200){
                layer.msg("高级粉最小数量为200", {
                    offset: '15px'
                    ,icon: 2
                 });
                //$("input[name='allnum']").val(200); 
                sum = 200;
        	}
        }
        if(goodname.indexOf("顶级粉")>-1){
        	if(sum<100){
                layer.msg("顶级粉最小数量为100", {
                    offset: '15px'
                    ,icon: 2
                 });
                //$("input[name='allnum']").val(100); 
                sum = 100;
        	}
        }
        if(goodname.indexOf("超级粉")>-1){
        	if(sum<100){
                layer.msg("超级粉最小数量为100", {
                    offset: '15px'
                    ,icon: 2
                 });
                //$("input[name='allnum']").val(100); 
                sum = 100;
        	}
        }
        var fastprice = $("input[name='fastprice']").val();
        $("input[name='money']").val((fastprice*10000 * sum)/10000);  

    });
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	if(righturl != 1){
            layer.msg("微博 地址或者id不正确", {
                offset: '15px'
                ,icon: 2
                ,time:500
             });
            return false;
    	};
    	   var iftiming = $("input[name='iftiming']:checked").val();
    	   var startdate = $("input[name='startdate']").val();
    	   var perhour = obj.field.perhour;
    	   var pernum = $("input[name='pernum']").val();
    	   if(iftiming == "1"){
    		   if(startdate == ""){
               layer.msg("手动计划开始时间不能为空", {
                   offset: '15px'
                   ,icon: 2
                });
               return false;
    		   }
    		   if(pernum == ""){
                   layer.msg("手动计划数量不能为空", {
                       offset: '15px'
                       ,icon: 2
                    });
                   return false;
        		   }
    	   }   	  
    	   var findex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    	            var index1 = layer.load(1, {
    	                shade: [0.5,'#000'] //0.1透明度的背景
    	                });   
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/order/addwbshuafen",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){      
    	                    layer.close(index1);    //返回数据关闭loading
                  if(res.sucess){
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

  });
  </script>
</body>
</html>