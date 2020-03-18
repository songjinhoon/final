<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>리스트입니다</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/list.css" />
</head>

<body>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->
	
	<!-- 검색창 -->
	<nav class="nav1">
		<div class="container">
		<div class="right">
			<form class="form-inline" action="/action_page.php">
				<input class="form-control mr-sm-2" type="text" placeholder="도토리 찾기">
				<button class="btn btn-outline-warning" type="submit"
					style="height: 40px;">
					<i class="fa fa-search"></i>
				</button>
			</form>
		</div>
		</div>
	</nav>
	<br>

	<!-- 카테고리창 -->
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<!-- Category -->
				<div class="single category">
					<h3 class="side-title">카테고리</h3>
					<ul class="list-unstyled">
						<li><a href="" title="">디지털/가전</a></li>
						<li><a href="" title="">가구/인테리어</a></li>
						<li><a href="" title="">여성패션/잡화 </a></li>
						<li><a href="" title="">남성패션/잡화 </a></li>
						<li><a href="" title="">뷰티/미용</a></li>
						<li><a href="" title="">반려동물용품</a></li>
						<li><a href="" title="">기타중고물품</a></li>
					</ul>
				</div>
			</div>

			<!-- 캐러셀 -->
			<div class="col-sm-9 ">
			<div id="demo" class="carousel slide" data-ride="carousel">
				<ul class="carousel-indicators">
					<li data-target="#demo" data-slide-to="0" class="active"></li>
					<li data-target="#demo" data-slide-to="1"></li>
					<li data-target="#demo" data-slide-to="2"></li>
				</ul>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="./img/list/purple.png" alt="Los Angeles" width="800" height="300">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="carousel-item">
						<img src="./img/list/event.png" alt="Chicago" width="800" height="300">
						<div class="carousel-caption">
						</div>
					</div>
					<div class="carousel-item">
						<img src="./img/list/ebichu.png" alt="New York" width="800" height="300">
						<div class="carousel-caption">
						</div>
					</div>
				</div>
				<a class="carousel-control-prev" href="#demo" data-slide="prev">
					<span class="carousel-control-prev-icon"></span>
				</a> <a class="carousel-control-next" href="#demo" data-slide="next">
					<span class="carousel-control-next-icon"></span>
				</a>
			</div>

			<!-- 리스트  -->
			<!-- <div class="col-sm-8"> -->
				<h3 class="h3">오늘의 중고거래</h3>
				<div class="row">
					<div class="col-md-4 col-sm-6">
						<div class="product-grid8">
							<div class="product-image8">
								<a href="#"> <img class="pic-1"
									src="./img/list/phonecase.JPG"> <img class="pic-2"
									src="./img/list/phonecase.JPG">
								</a>
								<ul class="social">
									<li><a href="" class="fa fa-heart" style="text-decoration:none"></a></li>
								</ul>
							</div>
							<div class="product-content">
								<div class="price">£ 8.00</div>
								<span class="product-shipping">Free Shipping</span>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="product-grid8">
							<div class="product-image8">
								<a href="#"> <img class="pic-1" src="./img/list/airpods.JPG">
									<img class="pic-2" src="./img/list/airpods.JPG">
								</a>
								<ul class="social">
									<li><a href="" class="fa fa-heart" style="text-decoration:none"></a></li>
								</ul>
							</div>
							<div class="product-content">
								<div class="price">£ 7.00</div>
								<span class="product-shipping">Free Shipping</span>
							</div>
						</div>
					</div>
					<div class="col-md-4 col-sm-6">
						<div class="product-grid8">
							<div class="product-image8">
								<a href="#"> <img class="pic-1" src="./img/list/wallet.JPG">
									<img class="pic-2" src="./img/list/wallet.JPG">
								</a>
								<ul class="social">
									<li><a href="" class="fa fa-heart" style="text-decoration:none"></a></li>
								</ul>
							</div>
							<div class="product-content">
								<div class="price">닥스 남자 반지갑</div>
								<span class="product-shipping">50,000원</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
