<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
String title = (String)request.getAttribute("title");
String body = (String)request.getAttribute("body");
String boardName = (String)request.getAttribute("boardName");
%>
<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title> 새 글 </title>
</head>
<body>
	<h1> 게시판 : <%= boardName %></h1>	
	
	<div>
		제목 : <%= title %>
		내용 : <%= body %>	
	</div>
	
</body>
</html> 