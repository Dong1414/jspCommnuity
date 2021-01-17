<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="com.sbs.example.jspCommunity.dto.Article"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title><%=article.getTitle()%></title>
</head>
<body>
	<h1>
		제목 :
		<%=article.getTitle()%></h1>

	<div>
		생성 날짜:
		<%=article.getRegDate()%>
		<br /> 수정 날짜:
		<%=article.getUpdateDate()%>
		<br /> 번호 :
		<%=article.getId()%>
		<br /> 제목 :
		<%=article.getTitle()%>
		<br /> 내용 :
		<%=article.getBody()%>
		<br /> 작성자 :
		<%=article.getExtra__writer()%>
		<br /> 게시판 :
		<%=article.getExtra__boardName()%>
		<br />
		<br /> <a href="http://localhost:8083/jspCommnuity/usr/article/list?boardId=<%=article.getBoardId()%>"  >게시물 리스트</a>		
		<hr />
	</div>
	<div>
		<a href="list?boardId=<%=article.getBoardId()%>">리스트로 이동</a>
		<a href="modify?id=<%=article.getId()%>">수정</a>
		<a onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }" href="doDelete?id=<%=article.getId()%>">삭제</a>
	</div>
</body>
</html>
