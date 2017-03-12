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
<title>软件工程双语课程</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading" style="margin-bottom:5px">试卷列表：</div>
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
						<td><a href='javascript:release(${item.id })'>发布 </a></td>
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

	
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
	function pageJump(currentPage) {
		location.href = "<%=basePath%>manage/add/listTestPaper/"+currentPage;
	}
	function release(testId) {
		location.href = "<%=basePath%>manage/add/release/"+testId;
	}
	</script>
</body>
</html>
