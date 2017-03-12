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
<title>我的练习</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<jsp:include page="menu.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<h3 class="text-info text-center">${paper.pagerName}</h3>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-12">
				<p class="text-right">单元：${paper.unit.unitName}</p>
			
			</div>
		</div>
	
		<div class="row">
			<div class="col-sm-12">
				<h4>一、单选题：</h4>
			</div>
			<div class="col-sm-12">
				<table class="table ">
					<tbody>
						<c:forEach items="${paper.singlesels}" var="sing" varStatus="status">
							<tr>
								<td>${status.index+1}. ${sing.question }<br/>
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="A"/>&nbsp;&nbsp;A. ${sing.choiceA }
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="B"/>&nbsp;&nbsp;B. ${sing.choiceB } 
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="C"/>&nbsp;&nbsp;C. ${sing.choiceC }
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="D"/>&nbsp;&nbsp;D. ${sing.choiceD }
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
						<c:forEach items="${paper.judges}" var="judge" varStatus="status">
							<tr>
								<td>${status.index+1}. ${judge.question }（ ）
								&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "judge${status.index}" value ="0"/>√
								&nbsp;&nbsp;&nbsp;<input type="radio"  name = "judge${status.index}" value ="1"/>×
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
						<c:forEach items="${paper.subjective}" var="sub" varStatus="status">
							<tr>
								<td>${status.index+1}. ${sub.question }<br/>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col-sm-12">
				<h4>我的答案：</h4>
					<p>单选：${mysings }</p>
					<p>判断：${myjudges }</p>
					<p>简答：${mysubs }</p>
			</div>
		</div>
		<hr/>
		<div class="row">
			<div class="col-sm-12">
			<h4>正确答案：</h4>
				<p>单选：${sings }</p>
				<p>判断：${judges }</p>
				<p>简答：${subs }</p>
			</div>
		</div>
	


		<br/><br/><br/>

	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


</body>
</html>