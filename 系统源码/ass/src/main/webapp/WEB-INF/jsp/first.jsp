<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="zh-cn">
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Template</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="containter">
		<div class="row">
			<ul class="nav nav-tabs" role="tablist" id="feature-tab">
				<li class="active"><a href="#tab-ppt" role="tab"
					data-toggle="tab">ppt在线</a></li>
				<li><a href="#tab-video" role="tab" data-toggle="tab">视频在线</a></li>
				<li><a href="#tab-document" role="tab" data-toggle="tab">双语文献</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="tab-ppt">
					<div class="row feature" style="width: 100% ;height:100% ">


						<embed style="width: 100% ;height:100% " name="plugin"
							src="video/first.swf" type="application/x-shockwave-flash">
						</embed>

					</div>
				</div>
				<div class="tab-pane" id="tab-video">
					<div class="row feature">
							<video id="myVideo" controls preload="auto"
					poster="images/poster.jpg" width="100%" height="100%">
					<source src="video/video.mp4" type="video/mp4" />
					<source src="http://demo.inwebson.com/html5-video/iceage4.webm"
						type="video/webM" />
					<source src="http://demo.inwebson.com/html5-video/iceage4.ogv"
						type="video/ogg" />
					<p>Your browser does not support the video tag.</p>
				</video>
					</div>
				</div>
				<div class="tab-pane" id="tab-document">
					<div class="row feature">
						<embed style="width: 100% ;height:100% " name="plugin"
							src="video/first.pdf" type="application/pdf">
						</embed>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
