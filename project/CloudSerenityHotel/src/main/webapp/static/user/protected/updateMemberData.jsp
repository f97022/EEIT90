<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改會員資料</title>
</head>
<body>
<div align="center">
<h2>修改管理員資料</h2>
<form method="post" action="/CloudSerenityHotel/user/updateData">
			<label for="name">使用者編號</label>
			 <input type="text" id="name" name="name" value="${userData.userId}" readonly required>
			  <p></p> 			
			<label for="name">使用者名稱</label>
			 <input type="text" id="name" name="name" value="${userData.userName}" readonly required>
			  <p></p> 			
			  <label for="email">電子信箱</label>
			 <input type="email" id="email" name="email" value="${userData.email}" required>
			  <p></p> 			
			  <label for="password">密碼</label>
			 <input type="password" id="password" name="password" value="${userData.password}" required>
			  <p></p> 
  			 <button type="submit">修改</button>
</form>
</body>
</html>