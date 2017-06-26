<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<!--Import materialize.css-->
	<link type="text/css" rel="stylesheet"
		href="${appServer}/front/materialize/css/materialize.min.css" media="screen,projection" />
	<!--Let browser know website is optimized for mobile-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<body>
	<div>
		<ul id="slide-out" class="side-nav">
			<li>
				<div class="userView">
					<div class="background">
						<img src="${appServer}/front/img/nav_bg.png">
					</div>
					<a href="#!user">
						<img class="circle" src="">
					</a>
					<a href="#!name">
						<span class="white-text name">张三</span>
					</a>
					<a href="#!email">
						<span class="white-text email">jdandturk@sina.com</span>
					</a>
				</div>
			</li>
			<li>
				<a href="#!">
					<i data-activates="slide-out" name="xx" class="material-icons">cloud</i>带图标的第一链接
				</a>
			</li>
			<li>
				<a href="#!">第二链接</a>
			</li>
			<li>
				<div class="divider"></div>
			</li>
			<li>
				<a class="subheader">子标题</a>
			</li>
			<li>
				<a class="waves-effect" href="#!">带波纹效果的第三链接</a>
			</li>
		</ul>
	</div>
	<div>
		<nav>
		<div class="nav-wrapper">
			<a href="#" class="brand-logo center">Logo</a>
			<ul id="nav-mobile" class="right hide-on-med-and-down">
				<li>
					<a href="sass.html">Sass</a>
				</li>
				<li>
					<a href="badges.html">组件</a>
				</li>
				<li>
					<a href="collapsible.html">JavaScript</a>
				</li>
			</ul>
			<ul id="nav-mobile" class="left hide-on-med-and-down">
				<li>
					<a href="#" data-activates="slide-out" id="navside" class="">
						<i class="material-icons">menu</i>
					</a>
				</li>
			</ul>
		</div>
		</nav>
	</div>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script type="text/javascript" src="${appServer}/front/materialize/js/materialize.min.js"></script>
	<script type="text/javascript" src="${appServer}/front/encrypt/aes.js"></script>
	<script type="text/javascript" src="${appServer}/front/encrypt/rsa.js"></script>
	<script type="text/javascript" src="${appServer}/front/materialize/js/ActionCjh.js"></script>
	<script type="text/javascript">
		$("#navside").sideNav();
	</script>
</body>
</html>