<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${!empty user}">
<option value="${user.userCode}">${user.userName}</option>
</c:if>
<c:if test="${empty user}">
<option value="1">中享思途</option>
</c:if>