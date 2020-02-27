<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/auth-tags" %>
<div class="layui-fluid">
	<div class="layui-row layui-col-space15">
		<div class="layui-col-md12">
			<div class="layui-card">
				<div class="layui-card-header">系统设置</div>
				<div class="layui-card-body">
					<!-- 表单的开始 -->
					<form class="layui-form" lay-filter="form_sysconfig" id="form_sysconfig">
						<div class="layui-form-item">
							<label class="layui-form-label">公司名称</label>
							<div class="layui-input-inline" style="width: 280px;">
								<input type="text" name="config1" lay-verify="required" value="${sysConfig.config1}" placeholder="请输入公司名称" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">公海天数</label>
							<div class="layui-input-inline" style="width: 280px;">
								<input type="text" name="config2" lay-verify="number" value="${sysConfig.config2}" placeholder="请输入公海天数" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">最后的跟单超过这个天数后，客户转成公海状态，进入客户池</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">跟单提醒</label>
							<div class="layui-input-inline" style="width: 280px;">
								<input type="text" name="config3" lay-verify="number" value="${sysConfig.config3}" placeholder="请输入跟单提醒" autocomplete="off" class="layui-input">
							</div>
							<div class="layui-form-mid layui-word-aux">距离最后一次跟单后，到达这个天数进行提醒</div>
						</div>
						<div class="layui-form-item">
							<div class="layui-input-block">
								<auth:have url="sysconfig" method="put">
									<button class="layui-btn" lay-submit="" lay-filter="but_sysconfig_set">确认保存</button>
								</auth:have>
							</div>
						</div>

					</form>
					<!-- 表单的结束 -->
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="assert/pages/js/sys/sysconfig.js"></script>