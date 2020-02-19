<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<c:choose>
		<c:when test="${not empty dto.title && not empty dto. content }">
			<p><strong>${dto.writer }</strong>님이 작성하신 글이 추가되었습니다</p>
		</c:when>
		<c:otherwise>
			<p>새 글 추가를 실패하였습니다</p>
		</c:otherwise>
	</c:choose>
	<button><a href="list.do">목록보러가기</a></button>
	</div>
</body>
</html>