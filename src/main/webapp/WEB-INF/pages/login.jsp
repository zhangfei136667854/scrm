<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<jsp:include page="/base.jsp"></jsp:include>
<link rel="stylesheet" href="assert/layui/css/layui.css">
<link rel="stylesheet" href="assert/pages/css/login.css">
</head>
<body>
	<form class="layadmin-user-login layadmin-user-display-show layui-form" id="LAY-user-login" >

		<div class="layadmin-user-login-main">
			<div class="layadmin-user-login-box layadmin-user-login-header">
				<h2>
					<font style="vertical-align: inherit;"><font style="vertical-align: inherit;">客户关系管理系统</font></font>
				</h2>
				<p>中享思途-官方出品</p>
			</div>
			<div class="layadmin-user-login-box layadmin-user-login-body layui-form">
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label> <input type="text" name="userCode" id="userCode" lay-verify="required" placeholder="用户名称" class="layui-input layui-form-danger">
				</div>
				<div class="layui-form-item">
					<label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label> <input type="password" name="userPass" id="userPass" lay-verify="required" placeholder="用户密码" class="layui-input">
				</div>
				<!-- <div class="layui-form-item">
					<div class="layui-row">
						<div class="layui-col-xs7">
							<label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label> <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
						</div>
						<div class="layui-col-xs5">
							<div style="margin-left: 10px;">
								<img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
							</div>
						</div>
					</div>
				</div> -->
				<!-- <div class="layui-form-item" style="margin-bottom: 20px;">
					<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
					<div class="layui-unselect layui-form-checkbox" lay-skin="primary">
						<span>记住密码</span><i class="layui-icon layui-icon-ok"></i>
					</div>
					<a lay-href="/user/forget" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
				</div> -->
				<div class="layui-form-item">
					<button class="layui-btn layui-btn-fluid" lay-submit="" lay-filter="btn_login" id="btn_login">
						<font style="vertical-align: inherit;"><font style="vertical-align: inherit;">立即登录</font></font>
					</button>
				</div>
				<!-- <div class="layui-trans layui-form-item layadmin-user-login-other">
					<label>社交账号登入</label> <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a> <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a> <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a> <a lay-href="/user/reg" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
				</div> -->
			</div>
		</div>

		<!-- <div class="layui-trans layadmin-user-login-footer">

			<p>
				© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a>
			</p>
			<p>
				<span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span> <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span> <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
			</p>
		</div> -->

		<div class="ladmin-user-login-theme">
    <script type="text/html" template>
      <ul>
        <li data-theme=""><img src="{{ layui.setter.base }}style/res/bg-none.jpg"></li>
        <li data-theme="#03152A" style="background-color: #03152A;"></li>
        <li data-theme="#2E241B" style="background-color: #2E241B;"></li>
        <li data-theme="#50314F" style="background-color: #50314F;"></li>
        <li data-theme="#344058" style="background-color: #344058;"></li>
        <li data-theme="#20222A" style="background-color: #20222A;"></li>
      </ul>
    </script>
  </div>

	</form>
</body>
<script type="text/javascript" src="assert/layui/layui.js"></script>
<script type="text/javascript" src="assert/pages/js/sys/userLogin.js"></script>
</html>