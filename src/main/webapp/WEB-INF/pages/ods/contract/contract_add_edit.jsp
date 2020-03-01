<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form class="layui-form" lay-filter="form_add_edit">
	<!-- layui的隐藏域  -->
	<input class="layui-hide" name="rowId" id="hideRowId" />
<input class="layui-hide" name="cusCode" id="cusCode">
	<div class="layui-form-item">
		<label class="layui-form-label">客户名称</label>
		<div class="layui-input-block">
			<!-- lay-verify 这个layui的自定义属性 ，设置表单的校验，多个校验 用 ‘|’ 隔开  -->
			<!-- class ='check-unique' 放置在此处用于通用JS脚本修改的时候使用-->
			<input name="cusName" lay-verify="required|checkCustomerName"
				id="cusName" class="layui-input check-unique" placeholder="请输入客户名称"
				autocomplete="off">
		</div>
	</div>
	<div class="layui-form-item layui-form-text">
		<c:if test="${!empty dictionaryList}">
			<c:forEach items="${dictionaryList}" var="dictionary">
				<div class="layui-inline">
					<c:if test="${dictionary.parentKey eq 'K000'&& dictionary.dicOrder>3}">
						<c:set value="${dictionary.children}" var="childDictionaryList"></c:set>
						<label class="layui-form-label">${dictionary.dicValue}</label>
						<div class="layui-input-inline">
							<select name="${dictionary.dicCode}" lay-verify="required" >
								<option value="">请选择</option>
								<c:if test="${!empty childDictionaryList}">
									<c:forEach items="${childDictionaryList}" var="childDictionary">
										<option value="${childDictionary.dicKey}">${childDictionary.dicValue}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</c:if>
				</div>
			</c:forEach>
		</c:if>

	</div>
	<div class="layui-form-item layui-form-text">
		<%-- <div class="layui-inline">
			<label class="layui-form-label">下次联系</label>
			<div class="layui-input-inline">
				<input name="nextContDate" type="date" lay-verify="required"  value="${nextContDate}"
					class="layui-input">
			</div>
		</div> --%>
		<label class="layui-form-label">跟单附件</label>
		<div class="layui-input-inline">
			<button type="button"   class="layui-btn" id="contUpload">
				<i class="layui-icon">&#xe67c;</i>上传附件
			</button>
			<input type="hidden" lay-verify="required" name="contFile" id="contFile" />
		</div>
	</div>
      <div class="layui-form-item layui-form-text">
    <label class="layui-form-label">详细信息</label>
    <div class="layui-input-block">
      <textarea name="contInfo" placeholder="请输入内容" class="layui-textarea"></textarea>
    </div>
  </div>
	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="but_submit">提交</button>
			<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="assert/pages/js/ods/contract.js"></script>