<%@page import="com.situ.scrm.utils.AppConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%    if(request.getSession().getAttribute("login_user")==null){
	response.sendRedirect("go/login");
} 
 %>
<html>
<head>
<meta charset="utf-8">
<title>系统后台</title>
<jsp:include page="/base.jsp"></jsp:include>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<!-- 引入layui的样式表 -->
<link rel="stylesheet" href="assert/layui/css/layui.css" media="all">
<!-- 客户自定义的样式表 -->
<link rel="stylesheet" href="assert/pages/css/custom.css">
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">客户关系管理系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<!-- <ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul> -->

			<ul class="layui-nav layui-layout-right">
				<%
					if (session.getAttribute("login_user") != null) {
				%>
				<li class="layui-nav-item"><a href=""> <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img"> ${login_user.userName}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="go/loginout">退了</a></li>
				<%
					}
				%>
				<%
					if (session.getAttribute("login_user") == null) {
				%>
				<li class="layui-nav-item"><a href="login"> <img src="//tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" class="layui-nav-img"> 登入
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="loginout">退了</a></li>
				<%
					}
				%>
			</ul>

		</div>
		<!-- 左侧菜单 开始 -->
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test" id="left_nav_tree">
					<!-- <li class="layui-nav-item layui-nav-itemed"><a class="" href="javascript:;">所有商品</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="javascript:;">列表一</a>
							</dd>
							<dd>
								<a href="javascript:;">列表二</a>
							</dd>
							<dd>
								<a href="javascript:;">列表三</a>
							</dd>
							<dd>
								<a href="">超链接</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"> <i class="layui-icon layui-icon-home"></i> 系统管理
					</a>
						<dl class="layui-nav-child">
							<dd>
								<a href="user"><i class="layui-icon layui-icon-user"></i> 用户管理</a>
							</dd>
							<dd>
								<a href="role"><i class="layui-icon layui-icon-auz"></i> 角色管理</a>
							</dd>
							<dd>
								<a href="sysresource">资源管理</a>
							</dd>
							<dd>
								<a href="dictionary">字典管理</a>
							</dd>
							<dd>
								<a href="sysconfig">系统设置</a>
							</dd>
						</dl></li> -->
					<c:if test="${!empty resourceList}">
						<c:forEach items="${resourceList}" var="resource"
							varStatus="status">
							<c:if test="${resource.menuUrl eq 'controller'}"> 
								<li class="layui-nav-item"><a href="javascript:;"> <i
										class="layui-icon ${resource.rescIcon}"></i>
										${resource.rescName}
								</a> <c:set value="${resource.children}" var="childResourceList"></c:set>

									<dl class="layui-nav-child">
										<c:if test="${!empty childResourceList}">
											<c:forEach items="${childResourceList}" var="childResource">
												<dd>
													<a href="${childResource.menuUrl}"><i class="layui-icon ${childResource.rescIcon}"></i>
														${childResource.rescName}</a>
												</dd>
											</c:forEach>
										</c:if>
									</dl></li>
							</c:if> 
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- 左侧菜单 结束 -->
		<div class="layui-body" id="layui-body-main">
			<!-- 内容主体区域 -->
			<div class="layui-fluid">
				内容主体区域 <br> <br>

				<blockquote class="layui-elem-quote">
					注意：该页面只是简单的后台布局示例，并不是一整套后台系统方案，您可以关注 layui 官方提供后台模板解决方案： <a href="https://www.layui.com/admin/" target="_blank" class="layui-btn layui-btn-danger">layuiAdmin</a>
				</blockquote>

				<blockquote class="layui-elem-quote">
					layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。 <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
				</blockquote>

				<a href="/doc/element/layout.html#admin" target="_blank" class="layui-btn layui-btn-lg">获取该布局代码</a> <br> <br> <br> <br> 下面是充数内容，为的是出现滚动条<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>
			</div>
		</div>
	</div>
</body>
<!-- 引入layui的 开发脚本 -->
<script type="text/javascript" src="assert/layui/layui.js"></script>
<!-- 引入系统的重新的ajax脚本 -->
<script type="text/javascript" src="assert/pages/js/app_ajax.js"></script>
<!-- 引入系统的通用脚本  -->
<script type="text/javascript" src="assert/pages/js/app_core.js"></script>
<script>
	//此处引入treeTable的layui插件 次配置应该在项目中运行一次即可，不可以重复加载。
	layui.config({
		base : 'assert/layui/'//你存放新模块的目录，注意，不是layui的模块目录
	}).extend({
		treeTable : 'treeTable/treeTable',
		iconPicker : 'iconPicker/iconPicker'
	});
	//JavaScript代码区域
	layui.use([ 'element', 'form', 'layer' ], function() {
		var element = layui.element;
		var $ = layui.$;
		var form = layui.form;
		var layer = layui.layer;
		//绑定左侧表单二级目录的点击
		$('#left_nav_tree').find('.layui-nav-child').off('click', 'a').on(
				'click', 'a', function() {
					$.ajax({
						url : $(this).attr('href'),
						success : function(htmlData) {
							$('#layui-body-main').html(htmlData);
							//尝试解决搜索表单 select ,radio等不显示的问题
							if ($('#form_search')) {
								//尝试更新一下搜索表单
								form.render(null, 'form_search');
							}
						}
					});
					return false;
				});
	});
</script>
</html>
