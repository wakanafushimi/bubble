<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>バブルゲーム</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="style.css" />
</head>
<body class="bg">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-4 col-11 loginitem justify-content-center">
				<div class="imgcontainer">
					<img src="images/logo.jpg">
				</div>
				<a class="centertext mt-2 d-block" href="signin.jsp">初めての方はこちら</a>
				<form action="LoginServlet" method="get" class="m-3">
					<input type="text" class="form-control" placeholder="ユーザネーム"
						name="username">
					<div class="centertext">
						<button type="submit" class="btn btn-warning my-2">START</button>
					</div>
				</form>
				
			</div>
		</div>
	</div>
</body>
</html>