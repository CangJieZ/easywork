<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${appServer}/front/materialize/css/materialize.min.css"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body style="background:url(${appServer}/front/img/login_bg.jpg) no-repeat center center;">
	<form id="login" class="container" action="${appServer}/login/toLogin.action">
		<div class="row center">
			<div class="input-field col s3">
				<input placeholder="请输入账号" name="userAccount" id="userAccount" type="text" class="validate">
				<label for="first_name">账号</label>
			</div>
		</div>
		<div class="row center">
			<div class="input-field col s3">
				<input id="userPwd" name="userPwd" type="text" class="validate">
					<label for="last_name">密码</label>
			</div>
		</div>
		<div class="row center">
			<div class="input-field col s3">
				<input id="checkCode" name="checkCode" type="text" class="validate">
					<label for="last_name">验证码</label>
			</div>
			<div>
				<img id="authCode_img" style="width: 80px;height: 35px" alt="验证码"
					src="${appServer}/login/checkCode.action" onclick="changeAdImage()" align="top" />&nbsp;&nbsp;
				<a href="#" onclick="changeAdImage()" style="">
					<label style="font-size: 18px;">刷新</label>
				</a>
			</div>
		</div>
		<div class="row center">
			<a class="waves-effect waves-light btn" onclick="login()">login</a>
		</div>
	</form>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${appServer}/front/materialize/js/materialize.min.js"></script>
	<script type="text/javascript" src="${appServer}/front/encrypt/aes.js"></script>
	<script type="text/javascript" src="${appServer}/front/encrypt/rsa.js"></script>
	<script type="text/javascript" src="${appServer}/front/materialize/js/ActionCjh.js"></script>
	<script type="text/javascript">
		function login() {
			var account = $("#userAccount").val();
			var pwd=$("#userPwd").val();
			$("#userAccount").val(RSA(account));
			$("#userPwd").val(RSA(pwd));
			var form=$( "#login");
			form.submit();
		}
	
		//刷新以验证码 function
		changeAdImage() {
			var ccImg=document.getElementById( "authCode_img");
			var ccImgSrc="";
			if (ccImg) {
				nowTime=new Date();
				ccImgSrc="${appServer}/login/checkCode.action?r="+ nowTime.getTime();
				ccImg.src=ccImgSrc;
				}
			}
	</script>
</body>
</html>
