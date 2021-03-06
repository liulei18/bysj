<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    
    <script >
	
	$(function() {
		$("#word").blur(function() { 
			if($("#word").val().trim()!=""){
				$.post("<%=path%>/manage/lexicon/find",
					{ "word": $("#word").val().trim(),"type":"server" },
					 function(data){
						$("#info").html(data.info).addClass("text-info");
					}, "json");
				};
				});
			
	});
	
	
		function validateForm() {
			var result = true;
			if ($("#word").val().trim() == ""||$("#info").html().trim() !="此词条可以录入"
					|| $("#enMean").val().trim() == ""
					|| $("#zhMean").val().trim() == "") {
				$('#myModal').modal({
					show : true,
				});
				result = false;
			}
			return result;
		};

	</script>
</head>
	
<body>


	<div class="container">
		<div class="row">

			<form:form modelAttribute="lexicon" method="post"
				onsubmit="return validateForm();">
				<div class="row" style="margin-top: 10px">
					<div >
						<input type="hidden" id="id" name="id" value="${id}" /> 标题：<input
							type="text" id="title" name="title" class="form-control"
							placeholder="请输入标题" value="${bilingual.title}" /><span id="info"></span>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div >
						英文文献：
						<textarea  id="enContent" name="enContent">${bilingual.enContent}</textarea>
							
						中文文献：
						<textarea  id="zhContent" name="zhContent">${bilingual.zhContent}</textarea>
							
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div class="col-md-6 col-md-offset-2">
						<c:choose>
							<c:when test="${detail==1 }">
								<a href="manage/bilingual/all" class="btn btn-primary pull-right  ">返回</a>
							</c:when>
							<c:otherwise>
								<Button type="submit" class="btn btn-primary pull-right">提交</Button>
							</c:otherwise>
						</c:choose>

					</div>
				</div>
			</form:form>


		</div>
	</div>

	<div class="modal fade" id="myModal" style="margin-top: 100px">
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
					<p>请确认信息...</p>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

</body>

<script src="<%=path %>/js/jquery-1.10.2.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/fckeditor/fckeditor.js"></script>
<script src="<%=path %>/fckeditor/js/jquery-fckeditor.js"></script>
<script src="${pageContext.request.contextPath}/fckeditor/js/department_add.js"></script>
</html>
