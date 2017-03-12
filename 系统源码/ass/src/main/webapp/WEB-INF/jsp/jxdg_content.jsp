<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="zh-cn">
<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<base href="${pageContext.request.contextPath }/">
		<title>在线学习</title>
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/side.css" />
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/component.css" />
		
	</head>
	<body>
	<div id="st-container" class="st-container">
	
	<nav class="st-menu st-effect-11" id="menu-11">
				<h2 class="icon icon-lab">软件工程</h2>
				<ul>
					<li><a class="icon icon-data" href="<%=path%>/index">首页</a></li>
					<li><a class="icon icon-location" href="<%=path%>/learning">在线学习</a></li>
					<li><a class="icon icon-study" href="<%=path%>/onlineStudy/class">课堂练习</a></li>
					<li><a class="icon icon-photo" href="<%=path%>/library">试题库</a></li>
					<li><a class="icon icon-wallet" href="<%=path%>/index">关于</a></li>
				</ul>
			</nav>
	
	<div class="st-pusher">
		<div class="container">
			<div id="st-trigger-effects" >
								
				<button  class="menu-button" id="open-button" data-effect="st-effect-11"><span>open</span></button>
			</div>
			<div class="content-wrap">
				<div class="content">
					<header class="codrops-header" style="width:90%;height:500px;">
						
						<h1>教学大纲<span>${unit }</span></h1>
						<div class="containter">
		<div class="row">
			<ul class="nav nav-tabs" role="tablist" id="feature-tab">
				<li class="active"><a class="icon icon-data" href="<%=path%>/index"><u><font color="green">首页</font></u></a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab-ppt">
					<div class="row feature">


						<embed style="width: 100% ;height:100% " name="plugin" wmode=opaque
							src="${target.url}" type="application/pdf">
						</embed>

					</div>
				</div>

		</div>
	</div>
			</nav>
				</header>	
					<!-- Related demos -->
				</div>
			</div><!-- /content-wrap -->
		</div><!-- /container -->
		<script src="js/classie.js"></script>
		<script src="js/main.js"></script>
		<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/sidebarEffects.js"></script>
	
	</body>
</html>