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
<h2>會 員 資 料</h2>
<table border="1">
<tr style="background-color:#a8fefa">
<th>使用者編號<th>使用者姓名<th>電子信箱<th>密碼<th>使用狀態<th>身分組<th>帳號更新時間<th>性別<th>生日<th>電話<th>身分證字號<th>國家<th>地址<th>護照號碼<th>註冊時間<th>資料更新時間</tr>
	<c:forEach items="${userData}" var="user">
  		<tr><td>${user.userId}
		<td>${user.userName}
		<td>${user.email}
		<td>${user.password}
		<td>${user.userStatus}
		<td>${user.userIdentity}
		<td>${user.updateTime}
	</c:forEach>
	<c:forEach items="${memberData}" var="member" varStatus="s" >
  		<td>${member.gender}
		<td>${member.birthday}
		<td>${member.phone}
		<td>${member.personalIdNo}
		<td>${member.country}
		<td>${member.address}
		<td>${member.passportNo}
		<td>${member.registerDate}
		<td>${member.updateTime}</tr>
		<c:set var="num" value="${s.count}"/>
	</c:forEach>
</table>
<h3>共${num}筆 會員資料</h3>
</div>
</body>
</html>