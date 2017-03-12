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
<title>课堂练习</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    
    
    </script>
</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h3 class="text-info text-center">${test.pagerName}</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<h4>一、单选题：</h4>
			</div>
			<div class="col-sm-12">
				<table class="table ">
					<tbody>
						<c:forEach items="${test.singlesels}" var="item" varStatus="status">
							<tr>
								<td><table>
								<tr>
									<td>${status.index+1}.${item.question }</td>
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
						<c:forEach items="${test.judges}" var="judge" varStatus="status">
							<tr>
								<td>${status.index+1}. ${judge.question }（ ）
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "judge${status.index}" value ="0"/>√
								&nbsp;&nbsp;&nbsp;<input type="radio"  name = "judge${status.index}" value ="1"/>×</td>
							
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
						<c:forEach items="${test.subjective}" var="sub" varStatus="status">
							<tr>
								<td>${status.index+1}. ${sub.question }<br/>
								&nbsp;&nbsp;&nbsp;&nbsp;  
								<textarea class="form-control" rows="4" style="resize:none" name="sub${status.index}"></textarea> </td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
<button onclick="enter()">确认生成试卷</button>
<div><a href="javascript:delTest(${test.id })">弃用本次组卷</a></div>
	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	function delTest(testId){
		location.href="<%=basePath%>manage/autoPaper/delTest/"+testId+"";
	}
	function enter(){
		location.href="<%=basePath%>manage/autoPaper/autoTest";
	}
</script>

</body>
</html>
