<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="${pageContext.request.contextPath }/">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>添加试题模块</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.10.2.min.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script type="text/javascript">
/* $(document).ready(function(){
	
}); */
/* $(function(){
	if($("#choiceA").val()=="A"){
		alert($("#choiceA").val());
	};
	re = new RegExp("^[a-z,A-Z]+$");
	 if(answer==$("#choiceA").val()){
		 $("#choiceA").checked=true;
	 }
	 if(answer==$("#choiceB").val()){

			alert("123");
		 $("#choiceB").checked=true;
	 }
	 if(answer==$("#choiceC").val()){
		 $("#choiceC").checked=true;
	 }
	 if(answer==$("#choiceD").val()){
		 $("#choiceD").checked=true;
	 }
}); */


</script>
</head>
<body>
<div>
	<form:form modelAttribute="tSinglesel" method="post"
		class="form-horizontal">
		<br>
		<div >
			<span>请输入问题名称：</span> <br><input type="text" name="question"
				class="form-control" placeholder="请输入问题名称："
				aria-describedby="sizing-addon1" value="${tSinglesel.question }"> <br> <span>请分别输入选项ABCD的内容：</span><br>
			<span>选项A：</span><br><input type="text" class="form-control" placeholder="选项A:"
				name="choiceA" aria-describedby="sizing-addon1" value="${tSinglesel.choiceA }"> <br><span>选项B：</span><br> <input
				type="text" class="form-control" placeholder="选项B：" name="choiceB"
				aria-describedby="sizing-addon1" value="${tSinglesel.choiceB }"> <br><span>选项C：</span><br> <input
				type="text" class="form-control" placeholder="选项C：" name="choiceC"
				aria-describedby="sizing-addon1" value="${tSinglesel.choiceC }"> <br><span>选项D：</span><br> <input
				type="text" class="form-control" placeholder="选项D：" name="choiceD"
				aria-describedby="sizing-addon1" value="${tSinglesel.choiceD }">
		</div>

		<br>
		<span>正确答案：</span>
		<input type="text" class="form-control" placeholder="请输入知识点："
			name="answer" aria-describedby="sizing-addon1" value="${tSinglesel.answer }">
		
		<br>
		<span>知识点：</span>
		<input type="text" class="form-control" placeholder="请输入知识点："
			name="skey1" aria-describedby="sizing-addon1" value="${tSinglesel.skey1 }">

		<br>
		<span>请设置问题难度：</span>
		<select id="quesDifficult" name="quesDifficult" >
		<option>请选择试题难度</option>
			<option   value="0">简单</option>
			<option  value="1">中等</option>
			<option  value="2">困难</option>
		</select>
		<br>
		
		<button type="submit" class="btn btn-primary pull-right">提交</button>
	</form:form>
</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>