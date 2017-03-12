<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>添加教师用户</title>


<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<form:form modelAttribute="teacher" method="post" onsubmit="return volidateForm();">
				
					<div class="form-group">
						<label for="exampleInputEmail1">用户名:</label> <input type="text"
							class="form-control" id="username" name="tname" placeholder="用户名"><span id="info"></span>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">密码:</label> <input
							type="password" class="form-control" id="password" name="tpwd"
							placeholder="密码">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">姓名:</label> <input type="text"
							class="form-control" id="realname" name="realName"
							placeholder="姓名">
					</div>
					
					<button type="submit" class="btn btn-primary pull-right">提交</button>
				</form:form>
			</div>
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
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	$(function() {
		$("#username").blur(function() { 
			if($("#username").val().trim()!=""){
				$.post("<%=path%>/manage/teacher/checkName",
					{ "username": $("#username").val().trim() },
					 function(data){
						$("#info").html(data.info).addClass("text-info");
					}, "json");
				};
				});
			
	});
	function volidateForm() {
		var result = true;
		if ($("#username").val().trim() == ""||$("#info").html().trim() !="可以使用"
				|| $("#password").val().trim() == ""
				|| $("#realname").val().trim() == "") {
			$('#myModal').modal({
				show : true,
			});
			result = false;
		}
		return result;
	};
	</script>
</body>
</html>