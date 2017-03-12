<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<div class="panel-heading" style="margin-bottom:5px">访问统计列表</div>
			<!-- <div class="panel-body">
				<p></p>
			</div> -->
			<div class="row">
				<form class="form-inline" action="manage/lexicon/all" method="post" id="searchForm">
					<div class="col-md-8">
					</div>
					<div class="col-md-4">
						<a class="btn btn-default" href="<%=path%>/pages/Jfree/showPie3D"role="button">查看统计图</a>
							
					</div>
				</form>
			</div>
			<table class="table table-hover table-condensed">
				<thead>
					<tr>
						<th>序号</th>
						<th>月份</th>
						<th>访问人数</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${mapInfo}" var="item" varStatus="status">
						<c:choose>
							<c:when test="${status.index % 2 == 0}">
								<tr class='success'>
							</c:when>
							<c:otherwise>
								<tr>
							</c:otherwise>
						</c:choose>
						<td>${status.index+1}</td>
						
						<td>${fn:substring(item.sj, 0, 7)}</td>
						<td>${item.sl }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			

	<div class="modal fade" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">提示</h4>
				</div>
				<div class="modal-body">
					<p>你确定要删除本条记录吗？</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" id="sureBtn" class="btn btn-primary">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript">
		function pageJump(page) {
			$('#currentPage').val(page);
			$('#searchForm').submit();
		}
		function deleteLexicon(id) {
			$('#myModal').modal({
				show : true,
			})
			$('#sureBtn').data('value', id);
		}
		
		function updateLexicon(id){
			location.href = "manage/lexicon/update/"
				+ id;
			
		}
		function detail(id){
			location.href = "manage/lexicon/detail/"
				+ id;
			
		}
		
		$(function() {
			$('#sureBtn').click(
					function() {
						location.href = "manage/lexicon/delete/"
								+ $(this).data('value');
					});
		});
	</script>
</body>
</html>
