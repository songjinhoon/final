<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>카테고리폼</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>


<!-- 리스트 -->
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/categoryForm.css" />
</head>

<body>
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

	<!-- 검색창 -->
	<nav class="nav1">
		<div class="container">
			<div class="right">
				<form class="form-inline" action="/action_page.php">
					<input class="form-control mr-sm-2" type="text"
						placeholder="도토리 찾기">
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

			<!-- 리스트  -->

			<div class="col-sm-8">

				<div class="container">
					<div class="row">
						<div class="well">
							<div class="list-group">
								<a href="#" class="list-group-item">
									<div class="media col-md-3">
										<figure class="pull-left">
											<img class="media-object img-rounded img-responsive"
												src="http://placehold.it/350x250" alt="placehold.it/350x250">
										</figure>
									</div>
									<div class="col-md-6">
										<h4 class="list-group-item-heading">신발</h4>
										<p class="list-group-item-text">Qui </p>
									</div>
									<div class="col-md-3 text-center">
										<button type="button" class="btn btn-primary btn-lg btn-block">
											구매하기</button>
									</div>
								</a> 
								
								
								<a href="#" class="list-group-item">
									<div class="media col-md-3">
										<figure class="pull-left">
											<img class="media-object img-rounded img-responsive"
												src="http://placehold.it/350x250" alt="placehold.it/350x250">
										</figure>
									</div>
									<div class="col-md-6">
										<h4 class="list-group-item-heading">신발</h4>
										<p class="list-group-item-text">Eu </p>
									</div>
									<div class="col-md-3 text-center">
										<button type="button" class="btn btn-primary btn-lg btn-block">구매하기</button>
									</div>
								</a> 
								
								
								
								<a href="#" class="list-group-item">
									<div class="media col-md-3">
										<figure class="pull-left">
											<img class="media-object img-rounded img-responsive"
												src="http://placehold.it/350x250" alt="placehold.it/350x250">
										</figure>
									</div>
									<div class="col-md-6">
										<h4 class="list-group-item-heading">신발</h4>
										<p class="list-group-item-text">Ut </p>
									</div>
									<div class="col-md-3 text-center">
										<h2>
											13540 <small> votes </small>
										</h2>
										<button type="button" class="btn btn-primary btn-lg btn-block">구매하기</button>
										<div class="stars">
											<span class="glyphicon glyphicon-star"></span> <span
												class="glyphicon glyphicon-star"></span> <span
												class="glyphicon glyphicon-star"></span> <span
												class="glyphicon glyphicon-star"></span> <span
												class="glyphicon glyphicon-star-empty"></span>
										</div>
										<p>
											Average 4.1 <small> / </small> 5
										</p>
									</div>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
