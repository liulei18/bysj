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

$(document).ready(function(){
	$("#units").change(function(){
		if($(".unit:selected")){
			var unitId = $(".unit:selected").val();
			var i = 0;
			$.ajax({url:"<%=basePath%>manage/frontJudge/checkChapter/" + unitId + "",
					type : "get",
					dataType : 'json',
					success : function(data) {
						$("#chapters").html("<option>--请选择章--</option>");
						for (i = 0; i < data[0].length; i++) {
							$("#chapters").append('<option class="chapter" value="'+data[0][i].id+'">'+data[0][i].chaName+'</option>');
						}
					}
				});
			}
		$("#chapters").change();
		});
	$("#chapters").change(function(){
		$("#sectors").empty();
		if($(".chapter:selected")){
			var chapterId = $(".chapter:selected").val();
			var j = 0;
			$.ajax({url:"<%=basePath%>manage/frontJudge/checkSector/" + chapterId + "",
					type : "get",
					dataType : 'json',
					success : function(data) {
						$("#sectors").html("<option>--请选择节--</option>");					
						for (j = 0; j < data[0].length; j++) {
							 $("#sectors").append('<option name ="sectorId" value="'+data[0][j].id+'">'+data[0][j].secName+'</option>'); 							
						}
					}
				});
			}
		});
	});
$("#units").change();


</script>
</head>
<body>
	<form:form modelAttribute="Tsinglesel" method="post"
		class="form-horizontal">
		<select id="units">
		<option>--请选择单元--</option>
			<c:forEach items="${units}" var="item">
				<option value="${item.id}" class="unit" onlick="javascript()">${item.unitName }</option>
			</c:forEach>
		</select>
		
		<select id="chapters">
		
		</select>
		<select id="sectors" name ="sectors">
		
		</select>
		<br>
		<div class="form-group">
			<span>请输入问题名称：</span> <br><input type="text" name="question"
				class="form-control" placeholder="请输入问题名称："
				aria-describedby="sizing-addon1"> <br> <span>请分别输入选项ABCD的内容：</span>
			<input type="text" class="form-control" placeholder="选项A:"
				name="choiceA" aria-describedby="sizing-addon1"> <br> <input
				type="text" class="form-control" placeholder="选项B：" name="choiceB"
				aria-describedby="sizing-addon1"> <br> <input
				type="text" class="form-control" placeholder="选项C：" name="choiceC"
				aria-describedby="sizing-addon1"> <br> <input
				type="text" class="form-control" placeholder="选项D：" name="choiceD"
				aria-describedby="sizing-addon1">
		</div>

		<br>
		<span>请选择正确答案：</span>
		<label class="radio-inline"> <input type="radio" name="answer"
			id="inlineRadio1" value="A" checked>A
		</label>
		<label class="radio-inline"> <input type="radio" name="answer"
			id="inlineRadio2" value="B"> B
		</label>
		<label class="radio-inline"> <input type="radio" name="answer"
			id="inlineRadio1" value="C">C
		</label>

		<label class="radio-inline"> <input type="radio" name="answer"
			id="inlineRadio2" value="D">D
		</label>

		<br>
		<br>
		<span>请输入知识点以便于查重：</span>
		<input type="text" class="form-control" placeholder="请输入知识点："
			name="skey1" aria-describedby="sizing-addon1">

		<br>
		<br>
		
		<button type="submit" class="btn btn-primary pull-right">Submit</button>
	</form:form>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>