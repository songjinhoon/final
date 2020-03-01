<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
    <c:if test="${userId eq null}">
        <a href="https://kauth.kakao.com/oauth/authorize?client_id=de621075efa65c9dc9ec223e759b1e6d&redirect_uri=http://localhost:8080/zSpringProject/user/loginForm&response_type=code">
            <img src="${pageContext.request.contextPath}/img/kakao_account_login_btn_medium_narrow.png">
        </a>
    </c:if>
    <c:if test="${userId ne null}">
        <h1>로그인 성공입니다</h1>
        <input type="button" value="로그아웃" onclick="location.href='${pageContext.request.contextPath}/user/logoutForm'">
    </c:if>
</body>
</html>