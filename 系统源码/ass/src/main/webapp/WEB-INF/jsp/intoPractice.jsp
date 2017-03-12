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
    function validateForm(){
    	var	result = true; 
    	for(var i = 0 ;i<${singNum};i++){
    		var val=$("input:radio[name=sing"+i+"]:checked").val();
    		console.log(val);
    		if(val==undefined){
    			result = false;
    		}
    	}
    	for(var i = 0 ;i<${judgeNum};i++){
    		var val=$("input:radio[name=judge"+i+"]:checked").val();
    		console.log(val);
    		if(val==undefined){
    			result = false;
    		}
    	}
    	if(result==false){
			$('#myModal').modal({
				show : true,
			})
    	}
    	return result;
    	
    }
    
    </script>
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
				<%-- <p class="text-right">
					截止时间：
					<fmt:formatDate value="${paper.answerDate}"
						pattern="yyyy-MM-dd HH:mm" />
				</p> --%>
			</div>
		</div>
		<form action="<%=path%>/server/practice/saveInfo" method="post" onsubmit="return validateForm();">
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
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="A"/>&nbsp;&nbsp;A. ${sing.choiceA }<br/>
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="B"/>&nbsp;&nbsp;B. ${sing.choiceB } <br/>
					   &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio"  name = "sing${status.index}" value ="C"/>&nbsp;&nbsp;C. ${sing.choiceC } <br/>
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
								&nbsp;&nbsp;&nbsp;&nbsp;  
								<textarea class="form-control" rows="4" style="resize:none" name="sub${status.index}"></textarea> 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<input type="hidden" name="paperid" value="${paper.id }" /> 
			<c:choose>
				<c:when test="${type=='student'}">
					<input type="hidden" name="stuid" value="${stuid }" /> 
						<input class="btn btn-primary pull-right" type="submit"  value="提  交">
					</c:when>
					<c:otherwise>
						<div class="col-sm-12">
						<h4>答案：</h4>
							<p>单选：${sings }</p>
							<p>判断：${judges }</p>
							<p>简答：${subs }</p>
						</div>
					</c:otherwise>
			</c:choose>
	
		</form>

		<br/><br/><br/>
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
					<p>请勾选完...</p>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	</div>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>


</body>
</html>
