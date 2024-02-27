<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>
	<c:set var="count" value="1"/>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-6 loginitem p-3">
				<a href="game.jsp" class="small d-block mb-2">←戻る</a>
				<c:forEach var="rank" items="${sortedEntries}">
					<div class="row">
						<p class="col-3 ps-4">
							
							<c:out value="${count}" />
							位
						</p>
						<p class="col-5">
							<c:out value="${rank.key}" />
							さん
						</p>
						<p class="col-3">
							<c:out value="${rank.value.max}" />
							点
							<c:set var="score" value="${rank.value.max}" />
							<c:if test="${score ne prescore}">
								<c:set var="count" value="${count+1}"/>
							</c:if>
							<c:set var="prescore" value="${rank.value.max}" />
						</p>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>