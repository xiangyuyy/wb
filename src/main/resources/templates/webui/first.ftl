<!DOCTYPE html>
<html class="fly-html-layui fly-html-store">
<head>
<meta charset="utf-8">
<title>首页</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="keywords" content="layui store,应用市场,模板市场,前端模板,UI模板">
<meta name="description"
	content="layuiStore 是 layui 官方推出的应用市场，主要提供各类前端 UI 模板及其它基于 layui 的高级应用，平台不仅提供 layui 自营应用，还面向开发者自主发布自己的 layui 应用。">
<link rel="stylesheet"
	href="./layuiadmin/store/font_first.css">
    <link rel="stylesheet" href="./layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet"
	href="./layuiadmin/store/fly/global.css?t=15483176633316"
	charset="utf-8">
<link rel="stylesheet"
	href="./layuiadmin/store/global.css?t=1548317663331"
	charset="utf-8">
<link rel="stylesheet"
	href="./layuiadmin/store/fly/store.css?t=1548317663331"
	charset="utf-8">
	<link rel="stylesheet"
	href="./layuiadmin/store/fly/font_.css?t=1548317663331"
	charset="utf-8">
</head>
<body>
	<div class="layui-header header header-store"
		style="background-color: #24262F;">
		<div class="layui-container">
			<a class="logo" href="https://www.layui.com/"> <img
				src="http://res.layui.com/static/images/layui/logo.png" alt="layui">
			</a>
			<div class="layui-form component"
				lay-filter="LAY-site-header-component"></div>
			<ul class="layui-nav">
				<li class="layui-nav-item layui-hide-xs"><a
					href="/first">首页</a></li>
				<li class="layui-nav-item"><a
					href="//www.layui.com/demo/">个人中心</a></li>
				<li class="layui-nav-item"><a href="/extend/">订单管理</a>
				</li>
				<li class="layui-nav-item layui-hide-xs"><a href="javascript:;">商品分类</a>
					<dl class="layui-nav-child">
						<dd lay-unselect>
							<a href="//www.layui.com/admin/" target="_blank">微博<span
								class="layui-badge-dot"></span></a>
						</dd>
						<dd lay-unselect>
							<a href="//www.layui.com/layim/" target="_blank">抖音</a>
<!-- 							<hr> -->
						</dd>
<!-- 						<dd lay-unselect>
							<a href="//www.layui.com/alone.html" target="_blank" lay-unselect>独立组件</a>
						</dd>
						<dd lay-unselect>
							<a href="https://fly.layui.com/jie/24209/" target="_blank">Axure
								组件</a>
						</dd> -->
					</dl></li>
									<li class="layui-nav-item layui-hide-xs layui-this"><a
					href="/store/">关于我们<span class="layui-badge-dot"></span></a></li>
				<li class="layui-nav-item"><a class="iconfont icon-touxiang"
					href="/cgcenter/wbdianzanlist""></a></li>
			</ul>
		</div>
	</div>
	<!--[if lt IE 9]>  <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>  <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script><![endif]-->
	<div class="shop-nav shop-index">
<!-- 		<div id="LAY-topbar">
			<form action="/store/search/">
				<div class="input-search">
					<div>
						<input type="text" placeholder="搜索你需要的模板应用" name="kws"
							autocomplete="off" value="">
						<button class="layui-btn layui-btn-shop">
							<i class="layui-icon layui-icon-search"></i>
						</button>
						<dl class="layui-hide-sm layui-show-md-inline-block">
							<dt>热搜：</dt>
							<dd>
								<a href="/store/search/后台/">后台</a>
							</dd>
							<dd>
								<a href="/store/search/IM/">IM</a>
							</dd>
							<dd>
								<a href="/store/search/商城/">商城</a>
							</dd>
							<dd>
								<a href="/store/search/博客/">博客</a>
							</dd>
							<dd>
								<a href="/store/search/新闻/">新闻</a>
							</dd>
							<dd>
								<a href="/store/search/企业/">企业</a>
							</dd>
							<dd>
								<a href="/store/search/社区/">社区</a>
							</dd>
							<dd>
								<a href="/store/search/" style="color: #FF5722;">全部</a>
							</dd>
						</dl>
					</div>
					<div class="layui-container layui-hide-xs">
						<a href="https://www.layui.com/" class="topbar-logo"> <img
							src="http://res.layui.com/static/images/layui/logo-1.png" alt="layui">
						</a>
					</div>
				</div>
			</form>
		</div> -->
		<div class="shop-banner">
<!-- 			<div class="layui-container layui-hide-xs">
				<div class="product-list">
					<dl>
						<dt>
							<a href="/store/cat/0/">模板分类</a>
						</dt>
						<dd>
							<a href="/store/cat/1/">后台管理</a>
						</dd>
						<dd>
							<a href="/store/cat/2/">商城购物</a>
						</dd>
						<dd>
							<a href="/store/cat/3/">社区论坛</a>
						</dd>
						<dd>
							<a href="/store/cat/4/">主页博客</a>
						</dd>
						<dd>
							<a href="/store/cat/5/">企业门户</a>
						</dd>
						<dd>
							<a href="/store/cat/6/">资讯头条</a>
						</dd>
						<dd>
							<a href="/store/cat/7/">其它模板</a>
						</dd>
					</dl>
				</div>
			</div> -->
			<div class="layui-carousel" lay-filter="LAY-store-banner"
				id="LAY-store-banner" style="background: rgb(242, 230, 214)">
				<div carousel-item>
					<div style="background: rgb(242, 230, 214)">
						<div class="layui-container">
							<a href="/store/layuiHomeMall/" target="_blank"> <img
								src="../one.jpg"
								alt="简约家居商城模板">
							</a>
						</div>
					</div>
					<div style="background: rgb(242, 230, 214)">
						<div class="layui-container">
							<a href="/store/layuiSimpleNews/" target="_blank"> <img
						src="../two.jpg"
								alt="极简新闻资讯模板">
							</a>
						</div>
					</div>
					<div style="background: rgb(242, 230, 214)">
						<div class="layui-container">
							<a href="/store/layuiQuietBlog/" target="_blank"> <img
									src="../two.jpg"
								alt="静谧风格个人博客模板">
							</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div class="shop-temp">
		<div class="temp-hot">
			<div class="layui-container">
				<p class="temp-title-cn">
					<span></span>热门商品<span></span>
				</p>
				<div class="layui-row layui-col-space20">
					<div class="layui-col-xs6 layui-col-md3">
						<a class="template store-list-box" href="/store/layuiAdmin/">
							<img
							src="../pone.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">layuiAdmin 通用后台管理模板</h2>
							<p class="price">
								<span title="金额"> 收费 </span>
							</p>
						</a>
					</div>
					<div class="layui-col-xs6 layui-col-md3">
						<a class="template store-list-box" href="/store/layuiQuietBlog/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1541591846215_76528.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">静谧风格个人博客模板</h2>
							<p class="price">
								<span title="金额"> ￥68 </span>
							</p>
						</a>
					</div>
					<div class="layui-col-xs6 layui-col-md3">
						<a class="template store-list-box" href="/store/layim/"> <img
							src="http://cdn.layui.com/upload/2018_11/168_1541544049294_47382.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">LayIM 网页即时通讯前端解决方案</h2>
							<p class="price">
								<span title="金额"> ￥600 </span>
							</p>
						</a>
					</div>
					<div class="layui-col-xs6 layui-col-md3">
						<a class="template store-list-box" href="/store/layuiSimpleNews/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1543493090773_84195.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">极简自媒体新闻资讯模板</h2>
							<p class="price">
								<span title="金额"> ￥150 </span>
							</p>
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="temp-normal">
			<div class="layui-container">
				<p class="temp-title-cn">
					<span></span> <a href="/store/cat/0/">最新上架</a> <span></span>
				</p>
				<div class="layui-row layui-col-space20 shoplist">
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiWeNews/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1543499206847_90773.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">微新闻模板</h2>
							<div>
								<label class="layui-badge-rim store-list-pay"> ￥38 </label>
								<div class="store-list-colorbar" title="蓝色调">
									<span class="store-color-bar" style="background: #1E9FFF"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiCateCompany/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1541609075171_4192.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">餐饮企业网站模版</h2>
							<div>
								<label class="layui-badge-rim store-list-pay"> ￥58 </label>
								<div class="store-list-colorbar" title="黑色调">
									<span class="store-color-bar" style="background: #000"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box"
							href="/store/layuiUniversalCompany/"> <img
							src="http://cdn.layui.com/upload/2018_11/168_1541607570391_86468.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">通用企业网站模板</h2>
							<div>
								<label class="layui-badge-rim">免费</label>
								<div class="store-list-colorbar" title="白色调">
									<span class="store-color-bar" style="background: #f8f8f8"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiNetCompany/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1541606785720_43588.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">网络公司企业网站模版</h2>
							<div>
								<label class="layui-badge-rim">免费</label>
								<div class="store-list-colorbar" title="墨绿色调">
									<span class="store-color-bar" style="background: #39c2d2"></span>
								</div>
							</div>
						</a>
					</div>
					
				</div>
<!-- 				<div class="shop-more">
					<a href="/store/cat/0/"><i
						class="layui-icon layui-icon-shop-more"></i></a>
				</div> -->
			</div>
		</div>
				<div class="temp-normal">
			<div class="layui-container">
				<p class="temp-title-cn">
					<span></span> <a href="/store/cat/0/">精选商品</a> <span></span>
				</p>
				<div class="layui-row layui-col-space20 shoplist">
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiWeNews/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1543499206847_90773.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">微新闻模板</h2>
							<div>
								<label class="layui-badge-rim store-list-pay"> ￥38 </label>
								<div class="store-list-colorbar" title="蓝色调">
									<span class="store-color-bar" style="background: #1E9FFF"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiCateCompany/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1541609075171_4192.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">餐饮企业网站模版</h2>
							<div>
								<label class="layui-badge-rim store-list-pay"> ￥58 </label>
								<div class="store-list-colorbar" title="黑色调">
									<span class="store-color-bar" style="background: #000"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box"
							href="/store/layuiUniversalCompany/"> <img
							src="http://cdn.layui.com/upload/2018_11/168_1541607570391_86468.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">通用企业网站模板</h2>
							<div>
								<label class="layui-badge-rim">免费</label>
								<div class="store-list-colorbar" title="白色调">
									<span class="store-color-bar" style="background: #f8f8f8"></span>
								</div>
							</div>
						</a>
					</div>
					<div
						class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg3">
						<a class="template store-list-box" href="/store/layuiNetCompany/">
							<img
							src="http://cdn.layui.com/upload/2018_11/168_1541606785720_43588.jpg"
							class="store-list-cover">
							<h2 class="layui-elip">网络公司企业网站模版</h2>
							<div>
								<label class="layui-badge-rim">免费</label>
								<div class="store-list-colorbar" title="墨绿色调">
									<span class="store-color-bar" style="background: #39c2d2"></span>
								</div>
							</div>
						</a>
					</div>
					
				</div>
				<div class="shop-more">
					<a href="/store/cat/0/"><i
						class="layui-icon layui-icon-shop-more"></i></a>
				</div>
			</div>
		</div>
	</div>
	<div class="fly-footer">
		<p>
			<a href="http://fly.layui.com/">Fly 社区</a> 2019 &copy; <a
				href="http://www.layui.com/">layui.com</a>
		</p>
		<p>
			<a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a> <a
				href="/extend/" target="_blank">组件平台</a> <a href="/store/"
				target="_blank">模板市场</a> <a href="/case/2019/" target="_blank">年度案例</a>
			<a href="http://fly.layui.com/jie/2461/" target="_blank">公众号</a>
		</p>
		<p class="fly-union">
			<a href="https://www.upyun.com?from=layui" target="_blank"
				rel="nofollow" upyun><img
				src="http://res.layui.com/static/images/other/upyun.png?t=1"></a> <span>提供
				CDN 赞助</span>
		</p>
	</div>
    <script src="./layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
    base: '../../../layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'store', 'table'], function(){
  });
  </script>
</body>
</html>