<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/matter-js/0.19.0/matter.min.js"></script>
<script defer type="module" src="main.js"></script>
<link rel="stylesheet" href="style.css" />
</head>
<body>
	<div class="main">
		<form action="ScoreServlet" method="post">
		<div class="message pb-3"></div>
		</form>

		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-2 col-12 info">
					<div class="row">
						<div class="nextcontainer col-4 col-lg-12 infop">
							<p class="my-1 centertext">next</p>
							<div class="next"></div>
						</div>
						<div class="score-container col-4 col-lg-12 infop">
							<p class="score my-1 centertext"></p>
						</div>
						<div class="col-4 col-lg-12 centertext infop">
							<p class="my-1">進化の輪</p>
							<div class="sinkacontainer mx-auto">
								<img src="images/sikanowa02.png" class="my-1">
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-7 mt-5">
					<div class="ms-1">
						<div class="gamebox centertext"></div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
