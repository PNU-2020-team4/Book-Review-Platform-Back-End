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


<form action="/bookreview/reviewInsert" method="post" name="frm" style="width:470px;">
<h2>리뷰 테스트</h2>
<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="리뷰작성자" value="${id}"> <br>

<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="내용" value="${id}"> <br>

<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="별점" value="${id}"> <br>

<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="책아이디" value="${id}"> <br>

<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="날짜" value="${id}"> <br>

<input type="submit" value="입력" onclick="#"> <br>
</form>

<br>

<form action="/bookreview/reviewInsert" method="post" name="frm" style="width:470px;">
<h2>리뷰 테스트</h2>
<input type="text" name="id" id="id" class="w3-input w3-border" placeholder="책아이디" value="${id}"> <br>

</form>


</center>
</body>
</html>