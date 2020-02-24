<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/base.jsp"></jsp:include>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="assert/layui/css/layui.css" media="all">
</head>
<body>
<div class="layui-form-item" style="width:250px; padding-left:510px;padding-top:150px;">
<form class="layui-form" lay-filter="form_add_edit">
<h1>客户关系管理系统</h1>
					<div class=" layui-word-aux" style="padding-left:50px;">中享思途-官方出品</div>
				


		<div class="">
		<div class="" style=""></div>
		<div class="">	<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
	<i class="layui-icon layui-icon-username" ></i>		<input name="userCode" lay-verify="required"  autocomplete="off" class="layui-input">
		</div>
		<div class="">
		<i class="layui-icon layui-icon-password"></i>
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input type="password" name="userPass" lay-verify="required"  autocomplete="off" class="layui-input">
		</div>
		
		
		 <div class=""> 
			<button class="layui-btn" lay-submit lay-filter="but_submit"  style="width:250px;">登陆</button>
	</div>
	
	
</form>
</div>
</body>
<script type="text/javascript" src="assert/layui/layui.js"></script>
<!-- 引入系统的重新的ajax脚本 -->
<!-- <script type="text/javascript" src="assert/pages/js/app_ajax.js"></script> -->
<script type="text/javascript" src="assert/pages/js/sys/user_login.js"></script>

</html>