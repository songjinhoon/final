<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<style>
		label { color: white; }
	</style>	
	<script>
		function checkIt(){
			var userinput = eval("document.userinput");
			
			if(!document.user.userPasswd.value){ 
				alert("비밀번호를 입력하세요"); 
				user.userPasswd.focus();
				return false;
			}
			
			if(!document.user.userEmail.value){ 
				alert("이메일을 입력하세요"); 
				user.email.focus();
				return false;
			}
			
			if(!document.user.phone1.value){ 
				alert("핸드폰번호를 입력하세요"); 
				user.phone1.focus();
				return false;
			}
			
			if(!document.user.phone2.value){ 
				alert("핸드폰번호를 입력하세요"); 
				user.phone2.focus();
				return false;
			}
			
			if(!document.user.phone3.value){ 
				alert("핸드폰번호를 입력하세요"); 
				user.phone3.focus();
			return false;
			}
		}
	</script>
</head>
<body>
	<div align="center">
		<p>
		<br>
		<div class="w3-panel w3-card w3-round-xlarge" style="width: 40%; height: 60%; background-color: #745d46;">
			<form method="post" action="${pageContext.request.contextPath}/user/joinPro" name="user" onsubmit="return checkIt()">
				<p>
				<br>
				<table style="color: white;">
					<tr>
						<td><p style="color: white;">안녕하세요. 카카오에서 사용하시는 아이디와 닉네임은 그대로 활용하시면되요.<br>추가적으로 아래에 정보를 입력하시고 회원가입을 완료하세요.</p></td>
						<td><input type="hidden" name="userId" value="${userId}"></td>
						<td><input type="hidden" name="userName" value="${userName}"></td>
					</tr>
					<tr>
						<td>
							<label>Password</label> 
							<input class="w3-input w3-round" size="15" type="password" name="userPasswd">
						</td>
					</tr>
					<tr>
						<td>
							<label>Email</label> 
							<input class="w3-input w3-round" size="25" type="text" name="userEmail">
						</td>
					</tr>
					<tr>
						<td>
							<label>Phone</label><br>
							<input class="w3-round" size="10" type="text" name="phone1"> - 
							<input class="w3-round" size="12" type="text" name="phone2"> - 
							<input class="w3-round" size="12" type="text" name="phone3"> 
						</td>
					</tr>
					<tr>
						<td>
							<label>Address</label> <input class="w3-input w3-round" size="30" type="text" name="userAddress">
						</td>
					</tr>
				</table>
				<p>
				<br> 
				<input class="w3-button w3-round-large"	style="background-color: #f0e68c; width: 100px;" type="submit" value="join" /> 
				<input class="w3-button w3-round-large" style="background-color: #f0e68c; width: 100px;" type="reset" value="reset" /> 
				<br>
				<br>
			</form>
		</div>
	</div>
</body>
</html>