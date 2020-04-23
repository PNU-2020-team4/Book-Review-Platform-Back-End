<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>AUTH TEST</title>
</head>
<body>
<h1>Auth test</h1>
<hr>
<br>
<center>
<c:choose>
<c:when test="${id != null}">
<h2> 아이디 확인</h2>
<h3>'${id}'  </h3>
</c:when>
</c:choose>
<form action="/bookreview/BrAuth" method="post" name="frm" style="width:470px;">
<h2>인증</h2>
<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="아이디" value="${id}"> <br>
<input type="submit" value="확인" onclick="#"> <br>
</form>
<br>

</center>
</body>
</html>