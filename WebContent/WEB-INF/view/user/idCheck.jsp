<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>ID Check</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<c:if test="${userIdChecked == 1 }">
<div align="center">
	<table  class="w3-table" width="320" cellspading="0" cellpading="5">

		<tr>
			<td height="20" align="center">
				<div align="center">
					ID : ${userId }은(는) 사용중입니다.
				</div>
			</td>
		</tr>
	</table>

	<form name="checkForm" method="get" action="${pageContext.request.contextPath}/user/confirmId" autocomplete="off">
		<table  class="w3-table" width="320" cellspading="0" cellpading="5">
			<tr>
				<td align="center">
					<div align="center">
					다른 아이디를 선택하세요.
					<br>
						<label><input type="text" class="w3-input w3-round w3-border" size="10" maxlength="12" name="userId"> </label>
						<input type="submit" class="w3-button w3-round-large w3-small" style="background-color: #f0e68c;" value="ID중복 확인">
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>
</c:if>

<c:if test="${userIdChecked == 0 }">
	<table  class="w3-table" width="320" cellspading="0" cellpading="5">
		<tr>
			<td align="center">
				<div align="center">
					<p>
						입력하신 ID : ${userId }은(는) <br>사용하실 수 있는 ID입니다.
					</p> 
					<input type="button" class="w3-button w3-round-large w3-small" style="background-color: #f0e68c;" value="닫기" onclick="setid()">
				</div>
			</td>
		</tr>
	</table>

</c:if>
</body>
<script>
function setid(){					//setid를 사용해서 다시 지정한 id의 값을 저장
	opener.document.user.userId.value="${userId}";
		self.close();
	}
</script>
</html>