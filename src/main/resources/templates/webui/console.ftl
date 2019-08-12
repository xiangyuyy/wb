

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 控制台主页一</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/layuiadmin/style/admin.css" media="all">
</head>
<body>

    <div class="layui-fluid">
        <div class="layui-row layui-col-space15">
                    <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-header">后台说明
                        <i class="layui-icon layui-icon-tips" lay-tips="要支持的噢" lay-offset="5"></i>
                    </div>
                    <div class="layui-card-body layui-text layadmin-text" style="color:blue">
                        <p>一直以来，layui 秉承无偿开源的初心，虔诚致力于服务各层次前后端 Web 开发者，在商业横飞的当今时代，这一信念从未动摇。即便身单力薄，仍然重拾决心，埋头造轮，以尽可能地填补产品本身的缺口。驱蚊器翁群二群请问企鹅</p>

                    </div>
                </div>
            </div>
            <div class="layui-col-md12">
                <div class="layui-row layui-col-space15">
                                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">你的资料</div>
                            <div class="layui-card-body">

                                <div class="layui-carousel layadmin-carousel layadmin-backlog">
                                    <div carousel-item>
                                        <ul class="layui-row layui-col-space10">
                                         <li class="layui-col-xs4">
                                                <a class="layadmin-backlog-body">
                                                    <h3>登录账号</h3>
                                                    <p><cite>${account.account!}</cite></p>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>昵称 </h3>
                                                    <p><cite>${account.nickname!}</cite></p>
                                                </a>
                                            </li>
                                                                 <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>会员卡号</h3>
                                                    <p><cite>${account.customerid!}</cite></p>
                                                </a>
                                            </li>
                                                                                 <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>会员等级</h3>
                                                    <p><cite style="/* font-size:15px !important */">${levelstr!}</cite></p>
                                                </a>
                                          </li>      
                                            <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>余额 </h3>
                                                    <p><cite>${account.balance!}</cite></p>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>冻结金额</h3>
                                                    <p><cite>${account.levelmony!}</cite></p>
                                                </a>
                                            </li>       
                                            <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>总佣金</h3>
                                                    <p><cite>${account.commision!}</cite></p>
                                                </a>
                                            </li>
                                                     <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>已提现佣金</h3>
                                                    <p><cite>${account.cashcommision!}</cite></p>
                                                </a>
                                            </li>
                                                     <li class="layui-col-xs4">
                                                <a  class="layadmin-backlog-body">
                                                    <h3>待提现佣金</h3>
                                                    <p><cite>${account.nocashcommision!}</cite></p>
                                                </a>
                                            </li>                                        
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header">快捷方式</div>
                            <div class="layui-card-body">

                                <div class="layui-carousel layadmin-carousel layadmin-shortcut">
                                    <div carousel-item>
                                        <ul class="layui-row layui-col-space10">
                                            <li class="layui-col-xs4">
                                                <a href="/first" target="_blank" >
                                                    <i class="layui-icon layui-icon-console"></i>
                                                    <cite>主页</cite>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a lay-href="/showorder/onecommcustomerlist">
                                                    <i class="layui-icon layui-icon-chart"></i>
                                                    <cite>我的代理</cite>
                                                </a>
                                            </li>
											<li class="layui-col-xs4"><a
												lay-href="/showorder/addbalance"> <i
													class="layui-icon layui-icon-template-1"></i> <cite>充值</cite>
											</a></li>
											<li class="layui-col-xs4"><a lay-href="/showorder/customerupgrade">
													<i class="layui-icon layui-icon-chat"></i> <cite>会员升级</cite>
											</a></li>
											<li class="layui-col-xs4">
                                                <a href="javascript:void(0)" id="outcustomer">
                                                    <i class="layui-icon layui-icon-find-fill"></i>
                                                    <cite>申请退还保证金</cite>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a lay-href="user/user/list.html">
                                                    <i class="layui-icon layui-icon-user"></i>
                                                    <cite>我的订单</cite>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a lay-href="set/system/website.html">
                                                    <i class="layui-icon layui-icon-set"></i>
                                                    <cite>我的分销</cite>
                                                </a>
                                            </li>
                                            <li class="layui-col-xs4">
                                                <a href="/customerinfor" >
                                                    <i class="layui-icon layui-icon-username"></i>
                                                    <cite>账号信息</cite>
                                                </a>
                                            </li>
                                                          <li class="layui-col-xs4">
                                                <a href="/first" target="_blank" >
                                                    <i class="layui-icon layui-icon-survey"></i>
                                                    <cite>联系客服</cite>
                                                </a>
                                            </li>
                                        </ul>                                        

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="layui-col-md12">
                        <div class="layui-card">
                            <div class="layui-tab layui-tab-brief layadmin-latestData">
                                <ul class="layui-tab-title">
                                    <li class="layui-this">充值记录</li>
                             <!--        <li>消费记录</li> -->
                                </ul>
                                <div class="layui-tab-content">
                                    <div class="layui-tab-item layui-show">
                                        <table id="LAY-index-topSearch"></table>
                                    </div>
                                    <div class="layui-tab-item">
                                        <table id="LAY-index-topCard"></table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="../../layuiadmin/layui/layui.js?t=1"></script>
    				<!--时间格式化-->
				<script src="/layuiadmin/date-format.js" type="text/javascript"
					charset="utf-8"></script>
    				<script type="text/html" id="createtime">
    {{# 
    var date = new Date();
    return date.Format(d.createtime); 
    }} 
    </script>
    <script>
  layui.config({
    base: '../../layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'console'], function(){
	    var $ = layui.$, active = {
			      add: function(){
	  	            var index1 = layer.load(1, {
		                shade: [0.5,'#000'] //0.1透明度的背景
		                });  
						layui.$.ajax({
							type : "post",
							url : "/showorder/ajaxoutcustomerupgrade",
							dataType : "json",
							success : function(res) {
								if (res.sucess) {
				                      layer.close(index1);    //返回数据关闭loading
				                      layer.msg(res.msg, {
				                        offset: '15px'
				                        ,icon: 1
				                        ,time: 1000
				                      }, function(){
				                    	  window.location.reload();
				                      });

								} else {
					                layer.close(index1);    //返回数据关闭loading
				                    layer.msg(res.msg, {
				                        offset: '15px'
				                        ,icon: 2
				                        ,time: 1000
				                     });
				                    return false;
								}
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
					                layer.close(index1);    //返回数据关闭loading
				                    layer.msg("网络异常", {
				                        offset: '15px'
				                        ,icon: 2
				                        ,time: 1000
				                     });
				                    return false;
							}
						});
			      }

			    }; 
			    $("#outcustomer").on('click', function(){
			      var type = "add";
			      active[type] ? active[type].call(this) : '';
			    });
  });
    </script>
</body>
</html>

