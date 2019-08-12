<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>微博投票下单</title>
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
							<input name="goodname" id="goodname" value="${good.goodname!}" hidden> <input
								name="slowprice" value="${good.slowprice?string(",##0.00000")}" hidden>
							<div class="layui-form-mid" style="font-size: 16px; color: red">
								${good.goodname!} <span id="price" style="padding: 10px">
									${good.fastprice!*10000}</span>粉币/万
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">产品速度</label>
						<div class="layui-input-inline cs">
							<input type="radio" name="isfast" value="1" title="快速" checked
								lay-filter="isfast"> <input type="radio" name="isfast"
								value="0" title="慢速" lay-filter="isfast">
						</div>
						<div class="layui-form-mid layui-word-aux">快慢速说明。</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">投票地址或ID<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="orderurl" value="" maxlength="100"
								lay-verify="required" placeholder="微博地址或者ID" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux">http://vote.weibo.com/poll/432863
							或者 投票ID：432863</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">数量<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="allnum" value="500"
								lay-verify="required|number" placeholder="数量" autocomplete="off"
								class="layui-input">
						</div>
					</div>

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
    form.on('radio(iftiming)', function (data) {
        var kind=data.value;
        if(kind == 1){
        	$("#planbyhand").show();
        }
        else{
        	$("#planbyhand").hide();
        }
    }); 
    form.on('radio(isfast)', function (data) {
        var speed=data.value;
        var sum = parseInt($("input[name='allnum']").val());
        var fastprice = $("input[name='fastprice']").val()
        var slowprice = $("input[name='slowprice']").val()
        if(speed == "1"){
            $("#price").text(${good.fastprice * 10000});
            $("input[name='money']").val(fastprice * sum);  
        }else{
            $("#price").text(${good.slowprice * 10000});
            $("input[name='money']").val(slowprice * sum);  
        }
    }); 
    
    $("input[name='allnum']").on('input propertychange',function(){
        var sum = parseInt($(this).val());
        var goodname = $("#goodname").val();
        	if(sum<50){
                layer.msg("最小数量为500", {
                    offset: '15px'
                    ,icon: 2
                 });
                $("input[name='allnum']").val(50); 
                sum = 50;
        	}      
        var speed=$("input[name='isfast']:checked").val();
        var fastprice = $("input[name='fastprice']").val()
        var slowprice = $("input[name='slowprice']").val()
        if(speed == "1"){
            $("input[name='money']").val((fastprice*10000 * sum)/10000);  
        }else{
            $("input[name='money']").val((slowprice*10000 * sum)/10000);  
        }
    });
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	var wburl = obj.field.orderurl;
        if(!wburl.match(/weibo./))
        {
            layer.msg("微博 地址不正确", {
                offset: '15px'
                ,icon: 2
             });
            return false;
        };
        if(wburl.match(/\?/g))
        {
            wburl = wburl.replace(/\?(.*)/g, "");
        };
    	   var findex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引  
    	   layer.confirm('您确认要提交吗？', {
    	           btn: ['确认','取消'] //按钮
    	        }, function(index){
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/order/addwbtoup",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){              
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
                      layer.msg(res.msg, {
                          offset: '15px'
                          ,icon: 2
                          ,time: 1000
                       });
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