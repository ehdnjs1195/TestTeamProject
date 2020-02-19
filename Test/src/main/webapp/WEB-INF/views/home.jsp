<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
<jsp:include page="include/resource.jsp"/>
</head>
<body>
<jsp:include page="include/navbar.jsp"/>
<div class="container">
	<h1>인덱스 페이지 입니다</h1>
	<ul>
		<li><a href="member/list.do">회원 목록 보기(member 테이블)</a></li>
	</ul>
</div>
</body>
</html>