<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>软件工程双语课程</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/theme.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<script type="text/javascript">
	function testPaper(unitId) {
		location.href = "onlineStudy/class/" + unitId;
	}
	function detail(Id) {
		location.href = "paper/intoPaper/" + Id;
	}
</script>
<body>
	<jsp:include page="menu.jsp" />
	<div class="container">
		<div class="row-fluid">
			<div class="span3" id="left-side">
				<h1 class="logo">随堂练习</h1>
				<div class="row">
					<div id="datepicker" class="span2"></div>
				</div>


				<h2>所有单元</h2>
				<c:forEach items="${units }" var="item" >
					<a href="javascript:testPaper(${item.id })">${item.unitName }</a>
					<br>
				</c:forEach>


<!-- 				<h2>标签</h2>
				<div class="row">
					<div class="span3">
						<a href="index-2.html" class="badge badge-info"><i
							class="icon-tag icon-white"></i> iPhone</a> <a href="index-2.html"
							class="badge badge-inverse"><i class="icon-tag icon-white"></i>
							iPad</a> <a href="index-2.html" class="badge badge-success"><i
							class="icon-tag icon-white"></i> hml5</a> <a href="index-2.html"
							class="badge badge-warning"><i class="icon-tag icon-white"></i>
							chrome</a> <a href="index-2.html" class="badge"><i
							class="icon-tag icon-white"></i> css</a> <a href="index-2.html"
							class="badge"><i class="icon-tag icon-white"></i> Bootstrap</a>
					</div>
				</div> -->
			</div>
			<div class="span9" id="main">

				<h1>
					<small>${unit.unitName }</small>
				</h1>
			<table class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>练习名</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${papers}" var="item" varStatus="status">
						<c:choose>
							<c:when test="${status.index % 2 == 0}">
								<tr class='success'>
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
						<td>${status.index+1}</td>
						<td>${item.pagerName }</td>
						<td>${item.createDate }</td>
						<td><a href='javascript:detail(${item.id })'>进入练习 </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
					

			</div>
		</div>
	</div>
</body>