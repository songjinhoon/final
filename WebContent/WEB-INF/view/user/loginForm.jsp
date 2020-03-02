<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<style>
		label{ color: white; }
	</style>
	<script>
		function checkIt(){
			var userinput = eval("document.userinput");
			
			if(!document.user.userId.value){ 
				alert("ID를 입력하세요"); 
				user.userId.focus();
				return false;
			}
			
			if(!document.user.userPasswd.value){
				alert("비밀번호를 입력하세요"); 
				user.userPasswd.focus();
				return false;
			}
		}
	</script>
</head>
<body>
	<div align="center">
		<p><br>
		<div class="w3-panel w3-card w3-round-xlarge" style="width: 40%; height: 60%; background-color: #745d46;">
			<form method="post" action="${pageContext.request.contextPath}/user/loginPro" name="user" onsubmit="return checkIt()">
				<p><br>
				<table style="color: white;">
					<tr>
						<td width="400"><label>ID</label> 
							<input class="w3-input w3-round" size="8" type="text" name="userId">
						</td>
					</tr>
					<tr>
						<td>
							<label>Password</label> 
							<input class="w3-input w3-round" size="15" type="password" name="userPasswd">
						</td>
					</tr>
				</table>
				<p><br> 
				<input class="w3-button w3-round-large"	style="background-color: #f0e68c; width: 100px;" type="submit" value="enter" />
		        <a href="https://kauth.kakao.com/oauth/authorize?client_id=de621075efa65c9dc9ec223e759b1e6d&redirect_uri=http://localhost:8080/zSpringProject/user/kakaoLoginForm&response_type=code">
            		<img src="${pageContext.request.contextPath}/img/kakao_account_login_btn_medium_narrow.png">
        		</a> 
				<br><br>
			</form>
		</div>
	</div>
</body>
</html>