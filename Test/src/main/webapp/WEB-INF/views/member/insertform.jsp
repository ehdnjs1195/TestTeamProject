<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/inertform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>회원정보 추가 폼</h1>
	<form action="insert.do" method="post">
		<div class="form-group">
			<label for="name">이름</label>
			<input type="text" name="name" id="name" class="form-control"/>
			<p class="help-block">반드시 입력하세요.</p>
		</div>
		<div class="form-group">
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary">추가</button>
	</form>
</div>
</body>
</html>