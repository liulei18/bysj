<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
<script>
	window.onload = function(){
		if("${message}"!=""){
		alert("${message}");
		}
	}


</script>
<body>

	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<h2>在此页面您将进行学生信息的录入</h2>
			</div>
			<div class="col-md-8">
				<h3>请按照以下的格式将学生信息录入Excel表中然后进行上传（样式没有限制，格式为）</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
				<img alt="图片加载失败" src="images/excle_style.png" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-8">
				<form method="post" action="<%=path %>/manage/mUpload/addStu" enctype="multipart/form-data">
					<br/>
					<h4>请选择文件：</h4><input type="file" name="file"  />
					<br/>
					<Button type="submit" class="btn btn-primary pull-right">提交</Button>
				</form>
			</div>
		</div>
		<script src="js/jquery-1.10.2.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
</body>
</html>
