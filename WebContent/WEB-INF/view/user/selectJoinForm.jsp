<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.jumbotron {
			border: solid 10px #745d46; 
			background-color: #ffffff;
		}
		p { 
			color: black; 
			font-size: 20px;
			 font-weight: bold; 
		}
		.normal {
			text-align: center;
			background-color: #745d46;
			border-radius: 5px;
			height: 60px;
			line-height: 60px;
		}
		.normal:hover { cursor: pointer; }
		.normal span { color: #ffffff; }
		.section { padding: 10px 0; }
		.kakao, .naver { padding: 0; }
		.naver img { width: 100%; height: 60px; }
		.kakao img { width: 100%; height: 60px; }
	</style>
</head>
<body>
<p><br><p><br>
	<div class="row">
		<div class="col-lg-4"></div>
		<div class="col-lg-4 jumbotron">
			<p class="text-center">SELECT JOIN TYPE</p>
			<div class="row section">
				<div class="col-lg-3"></div>
				<div class="col-lg-6 normal" onclick="location.href='${pageContext.request.contextPath}/user/joinForm'">
					<span>일반 회원 가입</span>
				</div>
				<div class="col-lg-3"></div>
			</div>
			<div class="row section">
				<div class="col-lg-3"></div>
				<div class="col-lg-6 kakao">
					<a href="${kakaoApiUrl}">
        				<img src="${pageContext.request.contextPath}/img/kakao_account_login_btn_large_narrow.png">
       				</a>
				</div>
				<div class="col-lg-3"></div>
			</div>
			<div class="row section">
				<div class="col-lg-3"></div>
				<div class="col-lg-6 naver">
		       		<a href="${naverApiUrl}">
						<img src="${pageContext.request.contextPath}/img/Log in with NAVER_Official_Green.PNG">				            	
      				</a> 
				</div>
				<div class="col-lg-3"></div>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
</body>
</html>