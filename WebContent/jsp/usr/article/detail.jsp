<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
	Map<String, Object> articleMap = (Map<String, Object>) request.getAttribute("articleMap");
%>
<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title><%=articleMap.get("title")%></title>
</head>
<body>
	<h1>
		제목 :
		<%=articleMap.get("title")%></h1>

	<div>
		생성 날짜:
		<%=articleMap.get("regDate")%>
		<br /> 수정 날짜:
		<%=articleMap.get("updateDate")%>
		<br /> 번호 :
		<%=articleMap.get("id")%>
		<br /> 제목 :
		<%=articleMap.get("title")%>
		<br /> 내용 :
		<%=articleMap.get("body")%>
		<br /> 작성자 :
		<%=articleMap.get("extra__writer")%>
		<br /> 게시판 :
		<%=articleMap.get("extra__boardName") %>
		<hr />
	</div>
</body>
</html>
