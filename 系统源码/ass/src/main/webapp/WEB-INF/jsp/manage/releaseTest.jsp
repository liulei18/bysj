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
<HTML>
<HEAD>
<TITLE>试题库</TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath }/">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.10.2.min.js"></script>
<style type="text/css">

</style>
<SCRIPT type="text/javascript">
	
</SCRIPT>
</HEAD>
<BODY>

	<div class="container">
		<div class="row-fluid">
			<div class="span3" id="left-side">
				<h1 class="logo">试题库</h1>
				点击进入<a href="javascript:allTest()">【试题展示页面】</a>
				<div class="span9" id="main">
					<div class="panel-heading" style="margin-bottom:5px">试卷列表：</div>

					<div class="row">
						<form class="form-inline" action="manage/lexicon/all"
							method="post" id="searchForm">
							<div class="col-md-8">
								<input type="hidden" name="currentPage" id="currentPage" />
								<div class="form-group">
									<label class="sr-only" for="exampleInputAmount"></label>
								</div>
							</div>
						</form>
					</div>
					<table class="table table-hover table-condensed">
						<thead>
							<tr>
								<th>序号</th>
								<th>试卷题目</th>
								<th>创建时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pageInfo.data}" var="item" varStatus="status">
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
								<td><a href='javascript:detail(${item.id })'>详细 </a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="row">
						<div class="col-md-6">共 ${pageInfo.totalRecords } 条
							${pageInfo.currentPage } / ${pageInfo.totalPages }</div>

						<div class="col-md-6 textRight">
							<c:choose>
								<c:when test="${pageInfo.currentPage == 1}">
									<a class="btn btn-info disabled " role="button">首页</a>
									<a class="btn btn-info disabled " role="button">上一页</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-info " href="javascript:pageJump(1)"
										role="button">首页</a>
									<a class="btn btn-info"
										href='javascript:pageJump(${pageInfo.currentPage-1 })'
										role="button">上一页</a>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${pageInfo.currentPage >= pageInfo.totalPages}">
									<a class="btn btn-info disabled" role="button">下一页</a>
									<a class="btn btn-info disabled" role="button">尾页</a>
								</c:when>
								<c:otherwise>
									<a class="btn btn-info"
										href='javascript:pageJump(${pageInfo.currentPage+1 })'
										role="button">下一页</a>
									<a class="btn btn-info"
										href='javascript:pageJump(${pageInfo.totalPages })'
										role="button">尾页</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</BODY>
</HTML>