<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>微博转发评论下单</title>
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
						<label class="layui-form-label">博文地址<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="orderurl" value="" maxlength="100"
								lay-verify="required" placeholder="博文地址" autocomplete="off"
								class="layui-input">
						</div>
						<div class="layui-form-mid layui-word-aux">http://weibo.com/u/1742727537
							</div>
															<div class="layui-form-item" id="postdiv" hidden>
					<label class="layui-form-label"></label>
						<div class="layui-input-inline cz">
						<span id="pcontent"></span>当前转发数:<span id="zfnum" style="color:red"></span>当前评论数:<span id="plnum" style="color:red"></span>当前赞数:<span id="pnum" style="color:red"></span>
						</div>
					</div>
					</div>
										<div class="layui-form-item">
						<label class="layui-form-label">数量<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="allnum" value="500"
								lay-verify="required|number" placeholder="数量" autocomplete="off"
								class="layui-input" oninput="value=value.replace(/[^\d]/g,'')">
						</div>
					</div>
										<div class="layui-form-item">
						<label class="layui-form-label">业务类型</label>
						<div class="layui-input-inline c">
							<input type="radio" name="plzftype" value="0" title="转发" checked lay-filter="plzftype"> 
							<input type="radio" name="plzftype" value="1" title="评论" lay-filter="plzftype">
					</div>
					</div>
						<div class="layui-form-item" id="zfy">
						<label class="layui-form-label"><span id="zhuanfayu">转发语</spa></label>
						<div class="layui-input-inline cs">
							<input type="radio" name="iscomment" value="0" title="自动内容" checked lay-filter="iscomment">
						    <input type="radio" name="iscomment" value="1" title="关闭内容" lay-filter="iscomment">
						    <input type="radio" name="iscomment" value="3" title="指定内容（一行一个）" lay-filter="iscomment">
						        <input type="radio" name="iscomment" value="5" title="按关键词" lay-filter="iscomment">
					    </div>
					    </div>
					<div id="zdnr" hidden>
											<div class="layui-form-item">
						<label class="layui-form-label">评论数<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="countnumber" value=""
								lay-verify="" readonly placeholder="下面指定内容填写后自动计算" autocomplete="off"
								class="layui-input">
						</div>
						</div>
					
				 <div class="layui-form-item" id="comments">
						<label class="layui-form-label">指定内容</label>
						<div class="layui-input-inline cz">
							<textarea name="comments" lay-verify="" placeholder="备注"
								style="width: 100%; height: 100px;" autocomplete="off"
								class="layui-textarea"></textarea>
						</div>
								<div class="layui-form-mid layui-word-aux">格式一行一个，已输入<span style="color:red" id="countnumber1">0</span>条，内容中不能带连接，不能带@，最大数量1000条，超过后将自动截取或指定内容条数大于转发数量也将自动截取
							</div>
				 </div>
					</div>
					
					  <div id="gjnr" hidden>
						<div class="layui-form-item">
						<label class="layui-form-label">关键词<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="comment_keyword" value="" maxlength="100"
								lay-verify="" placeholder="关键词" autocomplete="off"
								class="layui-input">
						</div>
					     </div>
										<div class="layui-form-item">
						<label class="layui-form-label">最大评论数量<span class="need">*</span></label>
						<div class="layui-input-inline">
									<select name="comment_keyword_nums" lay-verify="required">
										<option value="10">10%</option>
										<option value="20">20%</option>
										<option value="30">30%</option>
										<option value="40">40%</option>
										<option value="50">50%</option>
										<option value="60">60%</option>
										<option value="70">70%</option>
										<option value="80">80%</option>
										<option value="90">90%</option>
										<option value="100">100%</option>
									</select>
								</div>
					</div>
					</div>
					<div class="layui-form-item" id="isftspl">
						<label class="layui-form-label">是否同时评论</label>
						<div class="layui-input-inline cs">
							<input type="radio" name="iscomment_onoff" value="0" title="否" checked lay-filter="iscomment_onoff"> 
							<input type="radio" name="iscomment_onoff" value="1" title="是" lay-filter="iscomment_onoff">
					</div>
											<div class="layui-form-mid layui-word-aux" id="xzonoff" hidden>选择是 我们将为您生成一个“${good.goodname!}”单独评论订单 价格为<span style="color:red" id="zhuanfaprice">${good.fastprice * 10000}</span>粉币/万
					</div>
						</div>
						
						             <div class="layui-form-item" id="zfply" hidden>
						<label class="layui-form-label">评论语</label>
						<div class="layui-input-inline cz">
							<input type="radio" name="zfiscomment" id="tsyy" value="0" title="同转发语" lay-filter="zfiscomment"> 
							<input type="radio" name="zfiscomment" value="1" title="指定内容（一行一个）" checked lay-filter="zfiscomment">
							
					</div>
						</div>
					<div id="zftspy" hidden>
															<div class="layui-form-item">
						<label class="layui-form-label">评论数<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="zftspycurrentnumber" value=""
								lay-verify="" placeholder="上面转发语指定内容条数" autocomplete="off"
								class="layui-input" readonly>
						</div>
				<div class="layui-form-mid layui-word-aux">数量最小100，不足按100计算</div>
					</div>
					</div>					
					<div id="zfzdnr" hidden>
						<div class="layui-form-item">
						<label class="layui-form-label">评论数<span class="need">*</span></label>
						<div class="layui-input-inline cz">
							<input type="text" name="zfcurrentnumber" readonly value=""
								lay-verify="" placeholder="下面指定内容填写后自动计算" autocomplete="off"
								class="layui-input">
						</div>
					</div>
										<div class="layui-form-item" id="comments">
						<label class="layui-form-label">指定内容</label>
						<div class="layui-input-inline cz">
							<textarea name="zfcomments" lay-verify="" placeholder="备注"
								style="width: 100%; height: 100px;" autocomplete="off"
								class="layui-textarea"></textarea>
						</div>
								<div class="layui-form-mid layui-word-aux">格式一行一个，已输入<span style="color:red" id="zfcurrentnumber1">0</span>条，内容中不能带连接，不能带@，最小100，不足按100，计算最大数量1000条，超过后将自动截取或指定内容条数大于转发数量也将自动截取
							</div>
				 </div>
				 </div>
										<div class="layui-form-item">
						<label class="layui-form-label">刷赞类型</label>
						<div class="layui-input-inline cs">
							<input type="radio" name="sztype" value="0" title="关闭" checked lay-filter="sztype"> 
							<input type="radio" name="sztype" value="1" title="空闲赞" lay-filter="sztype">
									<input type="radio" name="sztype" value="2" title="初级赞" lay-filter="sztype">
											<input type="radio" name="sztype" value="3" title="高速赞" lay-filter="sztype">
					</div>
											<div class="layui-form-mid layui-word-aux">价格为<span style="color:red" id="dianzanprice">0</span>粉币/万</div>
					</div>
											<div class="layui-form-item" id="sznum" hidden>
						<label class="layui-form-label">刷赞数量<span class="need">*</span></label>
						<div class="layui-input-inline cs">
							<input type="text" name="szallnum" value="100"
								lay-verify="" placeholder="" autocomplete="off"
								class="layui-input" oninput="value=value.replace(/[^\d]/g,'')">
						</div>
								<div class="layui-form-mid layui-word-aux">数量最小100，不足按100计算</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">费用总计</label>
						<div class="layui-input-inline cs">
							<input type="text" name="money" value="${good.fastprice!*500}"
								style="background-color: #f0f1f2" disabled="disabled"
								autocomplete="off" class="layui-input">
						</div>
					<div class="layui-form-mid layui-word-aux" id="allpricediv">粉币
					<span id="pzhuanfadiv" hidden>转发费<span style="color:red" id="pzhuanfa">0</span>粉币</span>
					<span id="ppinglundiv" hidden>评论费<span style="color:red" id="ppinglun">0</span>粉币</span>
					<span id="pdianzandiv" hidden>点赞费<span style="color:red" id="pdianzan">0</span>粉币</span>
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
           var start_num  = o.start_num;
           var pl_num = o.pl_num;
           var content = o.content;
           var wbname= o.name;
           $("#postdiv").show();
           $("#pcontent").text(content);
           $("#pnum").text(num);
           $("#plnum").text(pl_num);
           $("#zfnum").text(start_num);
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
 //计算总价
    function price(){
        var sztype=$("input[name='sztype']:checked").val();//刷赞类型
        var plzftype=$("input[name='plzftype']:checked").val();//类型
        var iscomment_onoff=$("input[name='iscomment_onoff']:checked").val();//是否同时评论
        var num = parseInt($("input[name='allnum']").val()); 
        var zfiscomment = $("input[name='zfiscomment']:checked").val();//转发评论语类型（同时发语 指定内容）
        var zfcurrentnumber = parseInt($("input[name='zfcurrentnumber']").val()); //指定内容评论数
        var zftspycurrentnumber = parseInt($("input[name='zftspycurrentnumber']").val()); //同时发语评论数
        var szallnum = parseInt($("input[name='szallnum']").val()); //刷赞数 ${good.fastprice}
        if(num < 100){
        	num = 100;
        }

        	if(plzftype == 0){//转发
        		if(iscomment_onoff == 1){ //同时评论
        			if(zfiscomment == 0){ //同时发语
        				zfcurrentnumber = zftspycurrentnumber;	
        			}
        	        if(zfcurrentnumber < 100){
        	        	zfcurrentnumber = 100;
        	        }
        	        var zhuanfa = num * ${good.fastprice * 10000}/10000;
        	        var pinglun = zfcurrentnumber * ${good.fastprice  * 10000}/10000;
    	            var dianzan = 0;
        	        if(sztype == 0){//刷赞
        	        	$("#pdianzandiv").hide();
        	        }
        	        else{
        	            if(szallnum < 100){
        	            	szallnum = 100;
            	        }
                        if(sztype == 1){
                        	dianzan = szallnum * ${kxzgood.fastprice  * 10000}/10000;
                        }
                        if(sztype == 2){
                          	dianzan = szallnum * ${cjzgood.fastprice  * 10000}/10000;
                        }
                        if(sztype == 3){
                           	dianzan = szallnum * ${gszgood.fastprice  * 10000}/10000;
                        }
                        $("#pdianzandiv").show();
                        $("#pdianzan").text(dianzan);
        	        }
                    $("#ppinglundiv").show();
                    $("#ppinglun").text(pinglun);
                    $("#pzhuanfadiv").show();
                    $("#pzhuanfa ").text(zhuanfa);
                    var sumprice = zhuanfa + pinglun + dianzan;
                    sumprice =  sumprice *10000/10000;
                    $("input[name='money']").val(sumprice);  
        		}
        		else{
        		     var zhuanfa = num * ${good.fastprice  * 10000}/10000;
     	            var dianzan = 0;
         	        if(sztype == 0){//刷赞
        	        	$("#pdianzandiv").hide();
        	        }
        	        else{
        	            if(szallnum < 100){
        	            	szallnum = 100;
            	        }
                        if(sztype == 1){
                        	dianzan = szallnum * ${kxzgood.fastprice * 10000}/10000;
                        }
                        if(sztype == 2){
                          	dianzan = szallnum * ${cjzgood.fastprice  * 10000}/10000;
                        }
                        if(sztype == 3){
                           	dianzan = szallnum * ${gszgood.fastprice  * 10000}/10000;
                        }
                        $("#pdianzandiv").show();
                        $("#pdianzan").text(dianzan);
                        $("#ppinglundiv").hide();
                        $("#pzhuanfadiv").show();
                        $("#pzhuanfa ").text(zhuanfa);
        	        }
                    var sumprice = zhuanfa + dianzan;
                    sumprice =  sumprice *10000/10000;
                    $("input[name='money']").val(sumprice);  
        		}
        	} 
        	else{ //评论
   		        var pinglun = num * ${good.fastprice  * 10000}/10000;
	            var dianzan = 0;
  	        if(sztype == 0){//刷赞
 	        	$("#pdianzandiv").hide();
 	        }
 	        else{
 	            if(szallnum < 100){
 	            	szallnum = 100;
     	        }
                 if(sztype == 1){
                 	dianzan = szallnum * ${kxzgood.fastprice  * 10000}/10000;
                 }
                 if(sztype == 2){
                   	dianzan = szallnum * ${cjzgood.fastprice  * 10000}/10000;
                 }
                 if(sztype == 3){
                   dianzan = szallnum * ${gszgood.fastprice  * 10000}/10000;
                 }
                 $("#ppinglundiv").show();
                 $("#ppinglun").text(pinglun);
                 $("#pzhuanfadiv").hide();
                 $("#pdianzandiv").show();
                 $("#pdianzan").text(dianzan);
 	        }
             var sumprice = pinglun + dianzan;
             sumprice =  sumprice *10000/10000;
             $("input[name='money']").val(sumprice);  
        	}        
     }
    form.on('radio(iscomment)', function (data) {
        var old = $("input[name='iscomment']:checked").val()
        var kind=data.value;
        var plzftype = $("input[name='plzftype']:checked").val()
        if(kind == 3){
        	$("#gjnr").hide();
        	$("#zdnr").show();
        }
        else if(kind == 5){
         	$("#zdnr").hide();
        	$("#gjnr").show();
        }
        else{
        	$("#zdnr").hide();
        	$("#gjnr").hide();
        }
        if(plzftype == 1){
        	if(kind == 1 || kind == 5 ){
                layer.msg("不能选择", {
                    offset: '15px'
                    ,icon: 2
                    ,time:500
                 });
                $('input:radio[name=iscomment]')[0].checked = true;
                form.render('radio');
            	$("#gjnr").hide();
                return false;
        	}
        }
    }); 
    
    form.on('radio(iscomment_onoff)', function (data) {
        var kind=data.value;
        if(kind == 1){
        	$("#zfply").show();
        	$("#zfzdnr").show();
        	}
        else{
        	$("#zfply").hide();
         	$("#zfzdnr").hide();
        }
        price();
    });     
    
    form.on('radio(plzftype)', function (data) {
        var kind=data.value;
        if(kind == 0){
        	$("#isftspl").show();
        	$("#zhuanfayu").text("转发语")
        }
        else{
          	$("#zhuanfayu").text("评论语")
        	$("#isftspl").hide();
          	$("#zfply").hide();
          	$("#zftspy").hide();
          	$("#zftspy").hide();
        }
        
    });
    form.on('radio(sztype)', function (data) {
        var kind=data.value;
        if(kind == 0){
        	$("#sznum").hide();
            $("#dianzanprice").text(0);
          	price();
        }
        else{
        	
                if(kind == 1){
                    $("#dianzanprice").text(${kxzgood.fastprice * 10000});
                }
                if(kind == 2){
                    $("#dianzanprice").text(${cjzgood.fastprice * 10000});
                }
                if(kind == 3){
                    $("#dianzanprice").text(${gszgood.fastprice * 10000});
                }
                
          	$("#sznum").show();
          	price();
        }
        
    });
    form.on('radio(zfiscomment)', function (data) {
        var kind=data.value;
        var iscomment = $("input[name='iscomment']:checked").val()
        if(kind == 0){
        	if(iscomment != 3){
                layer.msg("不能选择", {
                    offset: '15px'
                    ,icon: 2
                    ,time:500
                 });
                $('input:radio[name=zfiscomment]')[1].checked = true;
                form.render('radio');
                return false;
        	}
        	$("#zftspy").show();
        	$("#zfzdnr").hide();
        }
        else{
           	$("#zftspy").hide();
        	$("#zfzdnr").show();
        }
        price();
    });  
    
    $("[name='comments']").blur(function(){
 	   var content = $(this).val().replace(/\ +/g,"");;
    	   if(content ==""){
               layer.msg("内容不能为空", {
                   offset: '15px'
                   ,icon: 2
                   ,time:500
                });
               return false;
    	   }
    	   var arry = content.split(/[\r\n]/g);
    	   arry = skipEmptyElementForArray(arry);
    	   $("#countnumber1").text(arry.length);
           $("input[name='countnumber']").val(arry.length);  
           $("input[name='zftspycurrentnumber']").val(arry.length);  
           price();
    	})  
    $("[name='zfcomments']").blur(function(){
  	   var content = $(this).val().replace(/\ +/g,"");;
    	   if(content ==""){
               layer.msg("内容不能为空", {
                   offset: '15px'
                   ,icon: 2
                   ,time:500
                });
               return false;
    	   }
    	   var arry = content.split(/[\r\n]/g);
    	   arry = skipEmptyElementForArray(arry);
    	   $("#zfcurrentnumber1").text(arry.length);
           $("input[name='zfcurrentnumber']").val(arry.length);  
           price();
    	})
    	  function skipEmptyElementForArray(arr){  
        var a = [];  
        $.each(arr,function(i,v){  
            var data = $.trim(v);//$.trim()函数来自jQuery  
            if('' != data){  
                a.push(data);  
            }  
        });  
        return a;  
    } 
    $("input[name='allnum']").blur(function(){
        var sum = $(this).val();
        if($(this).val() == ""){
     	   sum = 100;
     }
        var goodname = $("#goodname").val();
        if(sum<100){
                layer.msg("最小数量为100", {
                    offset: '15px'
                    ,icon: 2
                    ,time:500
                 });
                //$("input[name='allnum']").val(100); 
                sum = 100;
        }

        var fastprice = $("input[name='fastprice']").val()
        price();
    });
    $("input[name='szallnum']").blur(function(){
        var sum = $(this).val();
        if($(this).val() == ""){
      	   sum = 100;
      }
        if(sum<100){
                layer.msg("最小数量为100", {
                    offset: '15px'
                    ,icon: 2
                    ,time:500
                 });
               // $("input[name='szallnum']").val(100); 
                sum = 100;
        }
        price();
    });
    /* 监听提交 */
    form.on('submit(component-form-demo1)', function(obj){
    	if(righturl != 1){
            layer.msg("微博 地址不正确", {
                offset: '15px'
                ,icon: 2
                ,time:500
             });
            return false;
    	};
    	var wburl = obj.field.orderurl;
        if(!wburl.match(/weibo./))
        {
            layer.msg("微博 地址不正确", {
                offset: '15px'
                ,icon: 2
                ,time:500
             });
            return false;
        }
        if(wburl.match(/\?/g))
        {
            wburl = wburl.replace(/\?(.*)/g, "");
        }    	
 	     obj.field.orderurl = wburl;//地址处理
         var sztype=$("input[name='iscomment']:checked").val();
         if(sztype == 3){//指定内容
        	 if(obj.field.countnumber == "" || obj.field.countnumber == 0){
                 layer.msg("指定内容不能为空", {
                     offset: '15px'
                     ,icon: 2
                     ,time:500
                  });
                 return false;
        	 }
         } 	     
         if(sztype == 5){//关键词
        	 if(obj.field.comment_keyword == ""){
                 layer.msg("关键词不能为空", {
                     offset: '15px'
                     ,icon: 2
                     ,time:500
                  });
                 return false;
        	 }
         }
         
         var zfiscomment=$("input[name='zfiscomment']:checked").val();
         var iscomment_onoff=$("input[name='iscomment_onoff']:checked").val();
         if(zfiscomment == 1 && iscomment_onoff == 1 ){//转发评论指定内容
        	 if(obj.field.zfcurrentnumber == "" || obj.field.zfcurrentnumber == 0){
                 layer.msg("转发评论指定内容不能为空", {
                     offset: '15px'
                     ,icon: 2
                     ,time:500
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
    	                url: "/order/addwbzhuanfa",
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