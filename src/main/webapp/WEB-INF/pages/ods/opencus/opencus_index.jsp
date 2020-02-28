<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">
					公海客户管理
				</div>
				<div class="layui-card-body">
					<!-- 搜索表单 开始  -->
					<form class="layui-form" id="form_search">
						<div class="layui-search-form">
						<input type="hidden" name="isPublic" value="1">
							<div class="layui-inline">
								<input name="cusName" placeholder="客户名称" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<input name="linkName" placeholder="联系人" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-inline">
								<!-- 搜索按钮 -开始 -->
								<button class="layui-btn layui-btn-primary layui-btn-sm layui-tips" title="搜索" lay-submit lay-filter="btn_search">
									<i class="layui-icon layui-icon-search"></i>
								</button>
								<!-- 搜索按钮 -结束 -->
								<!-- 重置按钮 - 开始 -->
								<button type="reset" title="重置" class="layui-btn layui-btn-primary layui-btn-sm layui-tips">
									<i class="layui-icon layui-icon-refresh"></i>
								</button>
								<!-- 重置按钮 - 结束 -->
							</div>
						</div>
					</form>
					<!-- 搜索表单 结束  -->
					<!-- 页面表格 开始  -->
					<table id="list_table" lay-filter="filter_table"></table>
					<!-- 页面表格 结束  -->
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 页面上的隐藏域，提供信息给通用的JS脚本使用 -->
<input type="hidden" id="hideURL" value="opencus" />
<input type="hidden" id="hideTitle" value="公海客户" />

<script type="text/html" id="customerBtnTpl">

	<a class="layui-btn layui-btn-xs" lay-event="edit" data-callback="handerEdit()">跟单</a>


</script>


<!-- 引入自定义的JS脚本 -->
<script src="assert/pages/js/ods/opencus.js"></script>
