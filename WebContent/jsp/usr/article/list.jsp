<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
List<Map<String, Object>> articleMapList = (List<Map<String, Object>>) request.getAttribute("articleMapList");
Map<String, Object> boardMap = (Map<String, Object>) request.getAttribute("boardMap");
%>
<!doctype html>
<html lang="ko">
<head>

<meta charset="UTF-8" />

<title> <%=boardMap.get("name") %>
		 리스트</title>
</head>
<body>
	<h1> <%=boardMap.get("name") %>리스트</h1>
	
	<div>
	<%
	for (Map<String, Object> articleMap : articleMapList) {
	%>
		번호 :
		<%=articleMap.get("id")%>
		<br />
		제목 :
		<%=articleMap.get("title")%>
		<br />
		내용 :
		<%=articleMap.get("body")%>
		<br />
		작성자 :
		<%=articleMap.get("memberId")%>
		<hr />
	</div>
	<%
	}
	%>
</body>
</html> 