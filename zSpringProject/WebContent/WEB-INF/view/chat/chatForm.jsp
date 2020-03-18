<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String name = request.getParameter("name");
	if(name == null){
		name = "무명";
	}	
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		.chattingform { background-color: #F0E4C8; height: 450px; border: 10px solid #745d46; padding: 2%; }
		/* .container { border: 1px solid red; } */
		#section1 { margin-bottom: 50px; }
		.chattinglist { background-color: #F2F2F2; border: 1px solid #9B6E04; height: 300px; overflow-y: scroll; }
		.chattinglist .p { text-align: center;  }
		.contentform { background-color: #F2F2F2; border: 1px solid #9B6E04;  height: 300px; overflow-y: scroll; }
		.contentform .col-sm-6 { margin-bottom: 10px; }
		#inputMessage { width: 100%; }
		.me { background-color: yellow; border-radius: 10px; }
		.you { background-color: #ACFA58; border-radius: 10px; }
		.button { width: 100%; background-color: #FACC2E;}
	</style>
</head>
<body>
	<div class="container chattingform">
		<div class="container" id="section1">
			<div class="row">
				<div class="col-sm-2 chattinglist">
					<div class="row">
						<p>채팅 접속자</p>					
					</div>
				</div>
				<div class="col-sm-1">
				</div>
				<div class="col-sm-9 contentform">
					<div class="row" id="messageWindow">
					</div>			
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
						<input type="text" id="inputMessage"></input>
					</div>
				</div>
				<div class="col-sm-3"></div>
				<div class="col-sm-2">
					<div class="row">
						<input class="button" type="button" value="글 입력" onclick="send()"></input>
					</div>
				</div>
			</div>		
		</div>
	</div>
</body>
	<script type="text/javascript">
		var textarea = document.getElementById('messageWindow');
		var webSocket = new WebSocket('ws://localhost:8080<%= request.getContextPath() %>/weball');
		var inputMessage = document.getElementById('inputMessage');
		
		webSocket.onerror = function(event){ onError(event) };
		webSocket.onopen = function(event){ onOpen(event) };
		webSocket.onmessage = function(event){ onMessage(event) };
		
		function onMessage(event){
			var check = event.data;
			if(check.startsWith('[djalwjc1542]')){
				check = check.split(']');
				for(var i in check){
					if(i == 1){
						textarea.innerHTML += "<div class='container-fluid' style='padding: 0;'>"+ check[i] +"</div>";
						textarea.scrollTop = textarea.scrollHeight;						
					}
				}
			}else{
				textarea.innerHTML += "<div class='col-sm-6 you'>" + event.data + "</div>";
				textarea.innerHTML += "<div class='col-sm-6'>";
				textarea.scrollTop = textarea.scrollHeight;
			}
		}
		function opClose(event){
			textarea.innerHTML += "<div class='container-fluid' style='padding: 0;'><%= name %>님이 퇴장 하셨습니다.</div>";
			webSocket.send("<%= name %>:퇴장 하였습니다.");
		}
		function onOpen(event){
			textarea.innerHTML += "<div class='container-fluid' style='padding: 0;'>채팅방에 입장하셨습니다.</div>";
			webSocket.send("[djalwjc1542]<%= name %>님이 입장하셨습니다.");
		}
		function onError(event){
			alert(event.data);
		}
		function send(){
			textarea.innerHTML += "<div class='col-sm-6'>";
			textarea.innerHTML += "<div class='col-sm-6 me'>" + inputMessage.value + "</div>";
			textarea.scrollTop = textarea.scrollHeight;
			webSocket.send("<%= name %>:" + inputMessage.value);
			inputMessage.value = "";
		}
	</script>
</html>