<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!doctype html>

<html>
<title>JoinSendEmail</title>
<body>
	<div class="container">
		<div class="alert alert-success mt-4" role="alert">
		이메일 주소 인증 메일이 전송되었습니다.<br> 
		이메일에 들어가셔서 인증해주세요.</div>
	</div>
	
	<meta http-equiv="Refresh" content="5;url=${pageContext.request.contextPath}/user/logoutForm" />
</body>
</html>