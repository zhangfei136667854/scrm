<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" />
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label">父级字典</label>
			<div class="layui-input-inline" style="width: 100px;">
				<div class="layui-form-mid layui-word-aux">
					<span class="layui-badge layui-bg-cyan" id="parentName"><c:out value="${parentName}" default="一级字典"></c:out></span> 
					<input type="hidden" name="parentKey" id="parentKey" value="${parentKey}" />
				</div>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">字典编码</label>
			<div class="layui-input-block">
				<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
				<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
				<input name="dicCode" lay-verify="required|checkdiccode" id="dicCode" class="layui-input check-unique" placeholder="请输入字典编码" autocomplete="off">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">字典描述</label>
			<div class="layui-input-block">
				<input name="dicValue" lay-verify="required" id="dicValue" class="layui-input check-unique" placeholder="请输入字典描述" autocomplete="off">
			</div>
		</div>

	</div>


	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_resource_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script>
	layui.use([ 'iconPicker' ], function() {
		var iconPicker = layui.iconPicker;
		iconPicker.render({
			// 选择器，推荐使用input
			elem : '#rescIcon',
			// 数据类型：fontClass/unicode，推荐使用fontClass
			type : 'fontClass',
			// 是否开启搜索：true/false，默认true
			search : true,
			// 是否开启分页：true/false，默认true
			page : true,
			// 每页显示数量，默认12
			limit : 12,
			// 点击回调
			click : function(data) {
				//console.log(data);
			},
			// 渲染成功后的回调
			success : function(d) {
				// console.log(d);
			}
		});
	});
</script>