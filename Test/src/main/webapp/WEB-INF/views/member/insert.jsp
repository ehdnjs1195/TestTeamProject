<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
</head>
<body>
<div class="container">
	<h1>Alert</h1>
	<p>
		<strong>${dto.name }</strong> 님의 정보를 추가 했습니다.
		<!-- post 방식 전송이므로 인코딩 필터를 설정하지 않으면 글이 깨져서 출력된다.
			  스프링프레임 워크가 인코딩 필터를 지원하므로 우리가 직접 작성하지 않고 web.xml에서 추가하면 된다. -->
	</p>
</div>
</body>
</html>