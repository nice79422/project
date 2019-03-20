<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SY</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/scripts/css/login.css"
	type="text/css" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
</head>
<body>

	<div id="container">
		<div class="login-box">
			<div><img id="a" src="${pageContext.request.contextPath}/scripts/images/123.jpg" ></div>
				<form "form-container" method="post" action="${pageContext.request.contextPath}/base/login.do?method=login">
				<div>
					<h1  style="font-size:4em;">SY<span>석재</span></h1>
					<input type="text" placeholder="Username" name="empCode">
				</div>
				<div>
					<input type="password" placeholder="Password" name="pw">
				</div>
				<div>
					<input type="submit" value="Login">
				</div>
				<a href="#" alt="Registro" onclick="register()"> Forgot your username?</a>
				<a href="#" alt="Esqueceu a senha ?" onclick="ForgotP()">Forgot your password?</a>
			</form>
			<form class="regist" method="POST">

				<div>
					<input type="email" placeholder="Email " name="user">
				</div>
				<div>
					<input type="password" placeholder="Senha" name="password">
				</div>
		    </form>

		</div>
	</div>
	<script src="http://loadez2000.ezyro.com/www/js/jquery.js"></script>
	<script src="http://loadez2000.ezyro.com/www/js/main.js"></script>

</body>
</html>
