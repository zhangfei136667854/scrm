<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form class="layui-form" lay-filter="form_add_edit" id="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId"/>
	<div class="layui-form-item">
		<label class="layui-form-label">用户角色</label>
		<div class="layui-input-block" lay-verify="onechecked">
		<select name="roleCode">
		<option value=""></option>
	
			<c:if test="${!empty roleList}">
			<c:forEach items="${roleList}" var="role">
		<option   value="${role.roleCode}"> ${role.roleName}</option>
			</c:forEach>
			</c:if>
				</select>
		</div>
	</div>
	<div class="layui-form-item">
	<div class="layui-inline">
		<label class="layui-form-label">员工级别</label>
		<div class="layui-input-block" >
		<select id="userLevel" lay-filter="userLevel" name="userLevel">
		<option value=""></option>
		<option value="1">一级</option>
		<option value="2">二级</option>
		<option value="3">三级</option>
				</select>
		</div>
		</div>
		<div class="layui-inline">
		<label class="layui-form-label">上级员工</label>
		<div class="layui-input-block" >
		<select name="parentCode" id="parentCode" ></select>
				
		</div>
		</div>
	</div>
	
	<div class="layui-form-item">
		<label class="layui-form-label">用户名称</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userName" lay-verify="required" placeholder="请输入用户名称" autocomplete="off" class="layui-input">
		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">登录账号</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<input name="userCode" lay-verify="required|checkusercode" id="userCode" placeholder="请输入登录账号" autocomplete="off" class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item layui-form-text" id="userPass">
		<label class="layui-form-label">登录密码</label>
		<div class="layui-input-block">
			<input type="password" name="userPass"  lay-verify="required" placeholder="请输入登录密码" autocomplete="off" class="layui-input">
		</div>
	</div>
	
	
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<!-- <script type="text/javascript" src="assert/pages/js/sys/user_add_edit.js"></script> -->
