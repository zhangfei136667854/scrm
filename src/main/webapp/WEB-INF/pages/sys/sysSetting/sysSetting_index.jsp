<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 引入自定义的权限管理标签 -->
<%@ taglib prefix="auth" uri="/auth-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="layui-fluid" style="background-color: #c2c2c2;">
	<div class="layui-card-header">系统设置</div>
	<form class="layui-form" lay-filter="form_sysSetting">
		<input type="hidden" value="${sysSetting.rowId}">
		<div class="layui-form-item">
			<label class="layui-form-label">公司名称</label>
			<div class="layui-input-inline">
				<input type="text" name="companyName" placeholder="請輸入。。。"
					autocomplete="off" class="layui-input"
					value="${sysSetting.companyName}">
			</div>
			
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">公海天数</label>
			<div class="layui-input-inline">
				<input lay-verify="required|number" type="text" name="nationWaterDay" placeholder="請輸入。。。"
					autocomplete="off" class="layui-input"
					value="${sysSetting.nationWaterDay}">
			</div>
			<div class="layui-form-label" style="width:40px;padding-left:0px;">天数</div>
			
			<div class="layui-form-mid layui-word-aux">最后的跟单超过这个天数后，客户转成公海状态，进入客户池。</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">跟单提醒</label>
			<div class="layui-input-inline">
				<input lay-verify="required|number" type="text" name="docRemind" placeholder="請輸入。。。"
					autocomplete="off" class="layui-input"
					value="${sysSetting.docRemind}">
			</div>
			<div class="layui-form-label" style="width:40px;padding-left:0px;">天数</div>
			<div class="layui-form-mid layui-word-aux">距离最后一次跟单后，到达这个天数进行提醒</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit
					lay-filter="but_resource_submit">确定保存</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="assert/pages/js/sys/sysSetting.js"></script>