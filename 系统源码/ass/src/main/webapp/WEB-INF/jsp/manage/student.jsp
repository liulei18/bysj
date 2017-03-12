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

<body>
	<div>
		<table>
			<c:forEach items="${tclass}" var="item">
				<tr>
					<td><a href="javascript:queryStu(${item.id })">${item.name }</a></td>
				</tr>
				<br>
			</c:forEach>
		</table>
	</div>
	<div class="container">
		<table class="table table-hover table-condensed">
			<thead>
				<tr>
					<th>学号</th>
					<th>姓名</th>
				</tr>
			</thead>
			<tbody class="stu">
				
			</tbody>
		</table>


	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function queryStu(classId) {
			var i=0;
			$.ajax({url:"<%=basePath%>manage/teacher/queryStu/" + classId + "",
				type : "post",
				dataType : 'json',
				success : function(data) {
					$(".stu").html("");
						for (i = 0; i < data[0].length; i++){
							console.log(data[0][i]);
								$(".stu")
								.append("<tr><td>"+data[0][i].id+"</td><td>"+data[0][i].realName+"</td></tr>"); 
							}
				}
			});
		}
	</script>
</body>
</html>
