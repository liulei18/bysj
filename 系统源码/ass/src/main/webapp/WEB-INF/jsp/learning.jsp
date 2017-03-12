<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>在线学习</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/side.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="css/menu_cornermorph.css" />
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
		<button class="menu-button-right" id="open-button">
			<i class="fa fa-fw fa-cog"></i><span>Open Menu</span>
		</button>
		<div class="st-pusher">
			<div class="container">
				<div id="st-trigger-effects">

					<button class="menu-button" id="open-button"
						data-effect="st-effect-11">
						<span>open</span>
					</button>
				</div>

				<div class="menu-wrap-right">
					<nav class="menu">

						<c:choose>
							<c:when test="${sessionScope.type=='teacher'}">
								<div class="profile">
									<span>教 师：</span><span>${sessionScope.userName }</span>
								</div>
								
							</c:when>
							<c:otherwise>
								<div class="profile">
									<span>学 生：</span><span>${sessionScope.userName }</span>
								</div>
								<div class="link-list">
									<span>学 号：</span><span>${stuId }</span><br><span>班 级：</span><span>${claName }</span></a>
								</div>
							</c:otherwise>
						</c:choose>
						<div class="icon-list">
							<a href="#"><i class="fa fa-fw fa-home"></i></a> <a href="#"><i
								class="fa fa-fw fa-question-circle"></i></a> <a href="#"><i
								class="fa fa-fw fa-power-off"></i></a>
						</div>
					</nav>
				</div>

				<div class="content-wrap">
					<div class="content">
						<header class="codrops-header">
							<h1>
								软件工程双语课程<span>software engineering</span>
							</h1>
							<nav class="codrops-demos">
								<c:forEach items="${units }" var="unit">
									
								<a href="<%=path%>/learning_content/${unit.id}">${unit.unitName }</a>
								</c:forEach>

							</nav>
						</header>
						<!-- Related demos -->
					</div>
				</div>
				<!-- /content-wrap -->
			</div>
			<!-- /container -->
		</div>
	</div>
	<script src="js/classie.js"></script>
	<script src="js/main.js"></script>
	<script src="js/sidebarEffects.js"></script>
</body>
</html>