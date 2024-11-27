<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢結果</title>
</head>
<body>
<div align="center">
<h2>管 理 員 資 料</h2>
<table border="1">
<tr style="background-color:#a8fefa">
<th>使用者編號<th>使用者姓名<th>電子信箱<th>密碼<th>使用狀態<th>身分組<th>帳號更新時間</tr>
	<c:forEach items="${userData}" var="user" varStatus="s" >
  		<tr><td>${user.userId}
		<td>${user.userName}
		<td>${user.email}
		<td>${user.password}
		<td>${user.userStatus}
		<td>${user.userIdentity}
		<td>${user.updateTime}</tr>
		<c:set var="num" value="${s.count}"/>
	</c:forEach>
</table>
<h3>共${num}筆 管理員資料</h3>
</div>
</body>
</html>