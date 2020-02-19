<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/updateform.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>업데이트 폼 입니다.</h1>
	<form action="update.do" method="post">
		<!-- disabled 해놓으면 파라미터를 못 가져가므로 input type="hidden" 해서 전달해준다. -->
		<input type="hidden" name="num" value="${dto.num }" />
		<div class="form-group">
			<label for="num">번호</label>
			<input type="text" id="num" value="${dto.num }" disabled="disabled" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="name">이름</label>
			<input type="text" name="name" id="name" value="${dto.name }" class="form-control"/>
		</div>
		<div class="form-group">
			<label for="addr">주소</label>
			<input type="text" name="addr" id="addr" value="${dto.addr }" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary">수정확인</button>
		<button type="reset" class="btn btn-warning">취소</button>
	</form>
</div>
</body>
</html>