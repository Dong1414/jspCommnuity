<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%String memberId =  (String)request.getAttribute("memberId");
  String boardId =  (String)request.getAttribute("boardId");
%>

<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title>글쓰기 폼</title>
</head>
<body>
	<form action="/usr/article/doWrite" method="POST"
		target="_blank">
		<input type="hidden" name="memberId" value=<%=memberId %>>
		<hr>
		<input type="hidden" name="boardId" value=<%=boardId %>>
		<hr>
		<input type="text" name="title" placeholder="제목을 입력해주세요."
			maxlength="100">
		<hr>
		<textarea name="body" placeholder="내용을 입력해주세요." maxlength="1000"></textarea>
		<hr>
		<input type="submit" value="등록">
</body>
</form>