<%@ page language="java"
	contentType="application/vnd.ms-word; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	response.setHeader("Content-disposition",
			"attachment; filename=softWareTest.doc");
%>
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
<link href="css/theme.css" rel="stylesheet">
<link href="css/bootstrap-responsive.css" rel="stylesheet">
<script src="js/jquery-1.10.2.min.js"></script>
<style type="text/css">
</style>
<SCRIPT type="text/javascript">
	
</SCRIPT>
</HEAD>
<BODY>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h3 class="text-info text-center">${test.pagerName}</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<p class="text-right">单元：${test.unit.unitName}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h4>一、单选题：</h4>
			</div>
			<div class="col-sm-12">
				<table class="table ">
					<tbody>
						<c:forEach items="${test.singlesels}" var="item"
							varStatus="status">
							<tr>
						<td>
							<table>
								<tr>
									<td>${status.index+1 }.${item.question }</td>
								</tr>
								<tr>
									<td>A：${item.choiceA }</td>
									<td>B：${item.choiceB }</td>
								</tr>
								<tr>
									<td>C:${item.choiceC }</td>
									<td>D:${item.choiceD }</td>
								</tr>
							</table>
						</td>
					</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h4>二、判断题：</h4>
			</div>
			<div class="col-sm-12">
				<table class="table ">
					<tbody>
						<c:forEach items="${test.judges}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1 }.${item.question }(&nbsp;&nbsp;&nbsp;&nbsp;)<br></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h4>三、简答题：</h4>
			</div>
			<div class="col-sm-12">
				<table class="table ">
					<tbody>
						<c:forEach items="${test.subjective}" var="item"
							varStatus="status">
							<tr>
								<td>${status.index+1}.${item.question }<br />
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</BODY>
</HTML>