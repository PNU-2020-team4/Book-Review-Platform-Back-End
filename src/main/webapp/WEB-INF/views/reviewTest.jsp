<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>REVIEW TEST</title>
</head>
<body>
<h1>Review Test</h1>
<hr>
<br>
<center>


<form action="/bookreview/reviewInsert" method="post" name="ifrm" style="width:470px;">
<h2>삽입 테스트</h2>
<input type="text" name="writer" id="writer" class="w3-input w3-border" placeholder="리뷰작성자" > <br>

<input type="text" name="content" id="content" class="w3-input w3-border" placeholder="내용" > <br>

<input type="text" name="star" id="star" class="w3-input w3-border" placeholder="별점" > <br>

<input type="text" name="book" id="book" class="w3-input w3-border" placeholder="책아이디" > <br>

<input type="text" name="date" id="date" class="w3-input w3-border" placeholder="날짜" > <br>

<input type="submit" value="입력" onclick="#"> <br>
</form>

<br>


<form action="/bookreview/reviewUpdate" method="post" name="ufrm" style="width:470px;">
<h2>수정 테스트</h2>
<input type="text" name="idx" id="idx" class="w3-input w3-border" placeholder="리뷰 인덱스" > <br>

<input type="hidden" name="writer" id="writer" class="w3-input w3-border" placeholder="리뷰작성자" value ="3"> <br>

<input type="text" name="content" id="content" class="w3-input w3-border" placeholder="내용" > <br>

<input type="text" name="star" id="star" class="w3-input w3-border" placeholder="별점" > <br>

<input type="hidden" name="book" id="book" class="w3-input w3-border" placeholder="책아이디" value = "0"> <br>

<input type="text" name="date" id="date" class="w3-input w3-border" placeholder="날짜" > <br>

<input type="submit" value="입력" onclick="#"> <br>
</form>

<br>


<form action="/bookreview/reviewSelect" method="post" name="sfrm" style="width:470px;">
<h2>조회 테스트</h2>
<div> 작성자 아이디 입력 </div>
<input type="text" name="writer" id="writer" class="w3-input w3-border" placeholder="작성자아이디"> <br>
<input type="submit" value="입력" onclick="#"> <br>
</form>

<form action="/bookreview/reviewDelete" method="post" name="dfrm" style="width:470px;">
<h2>삭제 테스트</h2>
<div> 리뷰 인덱스 입력 </div>
<input type="text" name="idx" id="idx" class="w3-input w3-border" placeholder="리뷰인덱스"> <br>
<input type="submit" value="입력" onclick="#"> <br>
</form>



</center>
</body>
</html>