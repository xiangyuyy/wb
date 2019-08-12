<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${good.goodname!}</title>
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
						<div class="layui-card-header">下单说明</div>
						<div class="layui-card-body">

							<fieldset class="layui-elem-field">
								<div class="layui-field-box" style="font-size: 16px; color: red">
									${good.orderdescribe!}
								</div>
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
						<input name="minnumber" id="minnumber" value="${good.minnumber!}" hidden>
							<div class="layui-form-mid" style="font-size: 16px; color: red">
								${good.goodname!}   <span id="price" style="padding: 10px">	${good.fastprice!*10000}</span>粉币/万
								<span style="padding: 10px">会员折扣为          ${zhek!} </span>
							</div>
						</div>
					</div>

					<div class="layui-form-item" id="facekind">
						<label class="layui-form-label">选择表情</label>
						<div class="layui-input-inline c">
							<input type="checkbox" name="facekind" title="大拇指" value="1" lay-skin="primary" checked>
							<input type="checkbox" name="facekind" title="高兴" value="2" lay-skin="primary">
							<input type="checkbox" name="facekind" title="惊讶" value="3" lay-skin="primary">
							<input type="checkbox" name="facekind" title="难受" value="4" lay-skin="primary">
							<input type="checkbox" name="facekind" title="发怒"  value="5" lay-skin="primary">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">微博地址<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="kind" value="3"  hidden>
							<input type="text" name="orderurl" value="" maxlength="100"
								lay-verify="required|url" placeholder="微博地址" autocomplete="off"
								class="layui-input">
						</div>
					</div>
								<div class="layui-form-item" id="postdiv" hidden>
					<label class="layui-form-label"></label>
						<div class="layui-input-inline cz">
						<span id="pcontent"></span>当前赞数:<span id="pnum" style="color:red"></span>
						</div>
					</div>
								
					<div class="layui-form-item">
						<label class="layui-form-label">数量<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="allnum" value="10000" maxlength="100"
								lay-verify="required|number" placeholder="数量" autocomplete="off"
								class="layui-input">
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">费用总计</label>
						<div class="layui-input-inline cz">
							<input type="text" name="money" value="${good.fastprice!*10000}"
								style="background-color: #f0f1f2" disabled="disabled"
								autocomplete="off" class="layui-input">
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">备注</label>
						<div class="layui-input-inline cz">
							<textarea name="remark" lay-verify="" placeholder="备注"
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
    var righturl = 0;//标识正确的url

    
    $("input[name='orderurl']").blur(function(){
    	var wburl = $(this).val();
        if(!wburl.match(/weibo./))
        {
            layer.msg("微博 地址不正确", {
                offset: '15px'
                ,icon: 2
                ,time:500
             });
            return false;
        }
        layui.$.ajax({
            type: "post",
            url: '/order/getwbInfor?wburl='+wburl,
            dataType: "json",
            success: function(res){              
      if(res.sucess){
           var data = res.data;
           var o = eval('(' + data + ')');
           var num = o.zan_num;
           var content = o.content;
           var wbname= o.name;
           $("#postdiv").show();
           $("#pcontent").text(content);
           $("#pnum").text(num);
           righturl = 1;
    }
     else{
    	 righturl = 0
         layer.msg("微博 地址不正确", {
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
       
    $("input[name='allnum']").on('input propertychange',function(){
        var sum = parseInt($(this).val());
        var minnumber = $("#minnumber").val();
        if($(this).val() == ""){
      	   sum = minnumber;
      }
       if(sum<minnumber){
                layer.msg("赞最小数量为"+ minnumber , {
                    offset: '15px'
                    ,icon: 2
                 });
                //$("input[name='allnum']").val(100); 
         sum = minnumber;        	
        }
        var fastprice = $("input[name='fastprice']").val()
        $("input[name='money']").val((fastprice*10000 * sum)/10000);  
    });
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	if(righturl != 1){
            layer.msg("微博 地址不正确", {
                offset: '15px'
                ,icon: 2
             });
            return false;
    	};
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
 	   obj.field.orderurl = wburl;//地址处理
  
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
    	            var index1 = layer.load(1, {
    	                shade: [0.5,'#000'] //0.1透明度的背景
    	                });  
    	            layui.$.ajax({
    	                type: "post",
    	                url: "/order/addwbdianzan",
    	                data: obj.field,
    	                dataType: "json",
    	                success: function(res){              
                  if(res.sucess){
                      layer.close(index1);    //返回数据关闭loading
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