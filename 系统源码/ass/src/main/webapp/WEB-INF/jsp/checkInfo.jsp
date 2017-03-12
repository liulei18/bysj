<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>课堂练习统计</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h3 class="text-info text-center">${paper.pagerName}</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<p class="text-right">单元：${paper.unit.unitName}</p>
				<p class="text-right">
					截止时间：
					<fmt:formatDate value="${paper.answerDate}"
						pattern="yyyy-MM-dd HH:mm" />
				</p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
			<h4>选择班级：</h4>
				<c:forEach items="${classes }" var="clazz"	>
					<c:choose>
					<c:when test="${clazz.id == classid}">
					<a class="btn btn-primary" disabled="disabled" href ='<%=path %>/server/practice/info/${paper.id }/${teacherid }/${clazz.id}' >${clazz.name }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						<a class="btn btn-default" href ='<%=path %>/server/practice/info/${paper.id }/${teacherid }/${clazz.id}' >${clazz.name }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>		
				</c:forEach>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col-sm-12">
			<p >统计信息 : 已提交  ${answerNum }  人  ,未提交 ${stuNum } 人 </p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
			<h4>已提交：</h4>

			<c:forEach items="${answerlist }" var="answer"	varStatus="status">
				<p>${status.index+1 }、${answer.student.realName } 正确率：${answer.info}% </p>
			</c:forEach>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col-sm-12">
			<h4>未提交：</h4>
			<c:forEach items="${students }" var="student"	varStatus="status">
				<p>${status.index+1 }、${student.realName } </p>
			</c:forEach>
			</div>
		</div>
	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


</body>
</html>