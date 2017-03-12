<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<div>
		
	<span>请输入试卷名称：</span><br>
	<input type="text" name ="paperName" placeholder="请输入试卷名称：" id="paperName"><br>
		<table border=1>
			<thead>
				<tr>
					<th colspan="2"><h4>一、单选题</h4></th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${singlesel}" var="item" varStatus="singlexu">
					<tr>
						<td>${singlexu.count }</td>
						<td>
							<table>
								<tr>
									<td>${item.question }</td>
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
						<td><a href="javascript:remove(${item.id },1)">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table>
			<tr>
				<th colspan="2"><h4>二、判断题</h4></th>
				<th>操作</th>
			</tr>
			<c:forEach var="item" items="${judge}" varStatus="judgexu">
				<tr>
					<td>${judgexu.count }</td>
					<td>${item.question }(&nbsp;&nbsp;&nbsp;&nbsp;)<br></td>
					<td><a href="javascript:remove(${item.id },0)">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<table>
			<tr>
				<th colspan="2"><h4>三、简答题</h4></th>
				<th>操作</th>
			</tr>
			<c:forEach var="item" items="${subjective}" varStatus="subxu">
				<tr>
					<td>${subxu.count }</td>
					<td>${item.question }<br></td>
					<td><a href="javascript:remove(${item.id },2)">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<a href="javascript:createTest()">【确认生成试卷】</a><a href="javascript:deleteSession()">【取消本次组卷】</a><br>
		<a href="javascript:addTest()">【继续添加试题】</a>
	</div>

</BODY>
<script type="text/javascript">

	function createTest(){
		if($.trim($("#paperName").val()).length<6||$("#paperName").val()==""||$.trim($("#paperName").val()).length>12){
			alert("试卷名称在6-12个字符之间");
		}else{
			var paperName = $("#paperName").val();
			paperName = encodeURI(encodeURI(paperName)); 
			location.href="<%=basePath%>manage/testPaper/createTest/"+paperName+"";
		}
	}
	function remove(id,type){
		location.href="<%=basePath%>manage/testPaper/basket/"+id+"/"+type+"";
	}
	function addTest(){
		location.href="<%=basePath%>manage/testPaper/library1";
	}
	function deleteSession(){
		if(window.confirm('你确定要取消本次组卷？')){
			
			location.href="<%=basePath%>manage/testPaper/removeTest";
         }else{
        	 location.href="<%=basePath%>manage/testPaper/basket";
        }
	}
	
</script>
</HTML>