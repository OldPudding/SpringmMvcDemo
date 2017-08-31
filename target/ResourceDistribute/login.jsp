<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>稿件编辑分发平台</title>
<%@ include file="admin/common.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/commons/css/loginstyle.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/loginjs.js"></script>

</head>
<body>
	
	
<div class="container">
	<p class="systitle">稿件编辑分发平台</p>
	<section id="content">
		<form action="${pageContext.request.contextPath}/user/login.do"
		method="post" onsubmit="return checkForm()">
			<h1>Login</h1>
			<div>
				<input type="text" placeholder="用户名" required="" id="username" name="username" value="${user.username }"/>
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="password" name="password" value="${user.password }"/>
			</div>
			
			<div class="sure">
				<input type="text" placeholder="验证码" required="" id="verificationcode" name="verificationcode"/>
				<button type="button" class="btn btn-md btn-warning change" onclick="changeCode()">换一张</button>
				<img src="${pageContext.request.contextPath}/user/captcha-image.do" id="kaptchaImage" class="img-sty"/>
			</div>
			
			<div>
				<input type="submit" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" style="width: 92%;"/>
				
			</div>
		</form><!-- form -->
		<div class="button">
			<span id='copy'></span>
		</div><!-- button -->
		<input type="hidden" value="${errorInfo }" id="error" name="error"></input>
	</section><!-- content -->
</div><!-- container -->
</body>
</html>