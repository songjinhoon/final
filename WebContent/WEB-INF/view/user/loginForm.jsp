<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script src="${pageContext.request.contextPath}/js/sweetalert/sweetalert.js"></script>
	<style>
		label{ 
			color: white; 
		}
		
		.jumbotron {
			border: solid 10px #745d46; 
			background-color: #ffffff;
		}
	</style>
	<script>
		function checkIt(){
			var userinput = eval("document.userinput");
			
			if(!document.user.userId.value){ 
				swal("ID를 입력하세요"); 
				user.userId.focus();
				return false;
			}
			
			if(!document.user.userPasswd.value){
				swal("비밀번호를 입력하세요"); 
				user.userPasswd.focus();
				return false;
			}
		}
	</script>
</head>
<body>
	<div align="center">
		<p><br><p><br>
		<div class="col-lg-4 jumbotron w3-round-xlarge">
			<form method="post" action="${pageContext.request.contextPath}/user/loginPro" name="user" onsubmit="return checkIt()">
				<table style="color: white;">
					<tr>
						<td width="400"><label>ID</label> 
							<input class="w3-input w3-round" size="8" type="text" name="userId" placeholder="ID">
						</td>
						<td rowspan="2" width="85">
						<br> 
							<div align="center"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
								<input class="w3-button w3-round-large w3-large w3-margin-left" 
										style="background-color: #745d46; width: 100px;" type="submit" value="Login" />
							</div>
						</td>
					</tr>
					
					<tr>
						<td>
							<label>Password</label> 
							<input class="w3-input w3-round" size="15" type="password" name="userPasswd" placeholder="Password">
						</td>
					</tr>
				</table>
				<p><br> 
		        <a href="${kakaoApiUrl}">
            		<img src="${pageContext.request.contextPath}/img/kakao_account_login_btn_large_narrow.png" width="200"/>
        		</a> 
    	        <a href="${naverApiUrl}">
    	        	<img src="${pageContext.request.contextPath}/img/Log in with NAVER_Official_Green.PNG" width="200"/>
        		</a> 
			</form>
			<br>
		</div>
	</div>
</body>
</html>