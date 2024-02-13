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
		<div class="message"></div>

		<div class="container-fluid">
			<div class="row info">
				<div class="nextcontainer col-4">
					<p>next:</p>
					<div class="next"></div>
				</div>
				<div class="score-container col-4">
					<p class="score"></p>
				</div>
				<div class="sinkacontainer col-4">
					<p>進化の輪</p>
					<img src="images/sikanowa02.png">
				</div>
			</div>
			<div class="row">
				<div class="ms-1">
					<div class="gamebox centertext"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
