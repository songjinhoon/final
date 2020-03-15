<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>myPage</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>

<body>
	<br>	<p>		<br>	<p>
	
	<div align="center">
		<div style="width: 80%;">
			<div style="float: left; width:25%;">
				<label>회원 이름</label><br> ${userId }
				<br>
				<input type="button" value="정보 수정" onclick="location='userModifyForm'" />
			</div>
			<div style="float: left; width:25%;">
				<label>나의 온도</label><br>
				<a href="${pageContext.request.contextPath}/user/saleList"><i class="fas fa-thermometer-empty  fa-4x"></i></a>
			</div>
			<div style="float: left; width:25%;">
				<label>찜 목록</label><br> 
				<a href="${pageContext.request.contextPath}/user/saleList"><i class="fas fa-heart fa-4x"></i></a>
			</div>
			<div style="float: left; width:25%;">
				<label>거래 내역</label><br>
				<a href="${pageContext.request.contextPath}/user/saleList"><i class="fas fa-clipboard-list  fa-4x"></i></a>
			</div>
		</div>
	</div>
	
	<br><p>	<br><p>		<br><p>	

	<br><p>
	<div align="center">
		<div style="width: 80%; height: 400px;">
			<div style="float: left; width: 30%;">
				<div style="border: 3px solid rgb(255, 194, 0); height: 200px;">
				<br><p></p><br>
					<a href="${pageContext.request.contextPath}/user/saleList">
						<i class="fas fa-comments  fa-4x"></i>
					</a> 
					<br>채팅
				</div>
				<div style="border: 3px solid pink; height: 200px; align: center;">
				<br><p></p><br>
					<a href="${pageContext.request.contextPath}/user/saleList">
						<i class="fas fa-user-edit  fa-4x"></i>
					</a> 
					<br>작성한 리뷰
				</div>
			</div>
			<div style="float: right; width: 70%;">
				<div style="border: 3px solid skyblue; height: 400px; ">
					내 주위에 있는 거래 보기<br> 
					<a href="${pageContext.request.contextPath}/user/saleList">
						<i class="fas fa-map fa-4x"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<br><p>	<br><p>	<br><p>	<br><p>
</body>
</html>