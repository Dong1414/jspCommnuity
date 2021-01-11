<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%String memberId =  (String)request.getAttribute("memberId");
  String articleId =  (String)request.getAttribute("articleId");
%>

<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title>글쓰기 폼</title>
</head>
<body>
	<form action="/usr/article/doModify" method="POST"
		target="_blank">
		<input type="hidden" name="memberId" value=<%=memberId %>>
		<hr>
		<input type="hidden" name="id" value=<%=articleId %>>
		<hr>		
		<input type="text" name="title" placeholder="수정 할 제목을 입력해주세요."
			maxlength="100">
		<hr>
		<textarea name="body" placeholder="수정 할 내용을 입력해주세요." maxlength="1000"></textarea>
		<hr>
		<input type="submit" value="수정">
</body>
</form>