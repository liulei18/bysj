<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">

<title>软件工程双语课程</title>
<link href="css/style.css" rel="stylesheet">
<link href="css/vendorstyle.css" rel="stylesheet">

<script src="js/video.js"></script>
<script src="js/vendorscript.js"></script>

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->



</head>
<body>
	<jsp:include page="menu.jsp" />

	<ol class="breadcrumb">
  <li><a href="<%=path%>/index">首页</a></li>
  <li><a href="<%=path%>/learning">在线学习</a></li>
  <li class="active">bootstrap</li>
</ol>
	<!-- 内容容器 -->
	<div class="container">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<!-- <div class="videoContainer"> -->
				<video id="myVideo" controls preload="auto"
					poster="images/poster.jpg" width="100%" height="100%">
					<source src="video/video.mp4" type="video/mp4" />
					<source src="http://demo.inwebson.com/html5-video/iceage4.webm"
						type="video/webM" />
					<source src="http://demo.inwebson.com/html5-video/iceage4.ogv"
						type="video/ogg" />
					<p>Your browser does not support the video tag.</p>
				</video>
				<!-- 		</div> -->
			</div>
		</div>
		
		
		
		
		<!--内容容器 END  -->
	</div>

	<div class="caption">This is HTML5 video with custom controls</div>
	<div class="control">
		<div class="progress">
			<span class="bufferBar"></span> <span class="timeBar"></span>
		</div>

		<div class="btmControl">

			<div class="mainControl">
				<a class="btnStop lvl3 btnmain" href="#" tabindex="0"
					title="Stop play"></a> <a class="btnBck lvl2 btnmain" href="#"
					tabindex="0" title="Rewind"></a> <a class="btnPlay lvl1 btnmain"
					href="#" tabindex="0" title="Play/Pause video"></a> <a
					class="btnFwd lvl2 btnmain" href="#" tabindex="0"
					title="Fast forward"></a> <a class="btnEnd lvl3 btnmain" href="#"
					tabindex="0" title="End video"></a>
			</div>

			<div class="volume">
				<div class="sound" title="Mute/Unmute sound"></div>
				<span class="volumeCover" title="Set volume"></span> <span
					class="volumeBar"></span>
			</div>
		</div>

	</div>
	<div class="loading"></div>

	</div>



</body>
</html>