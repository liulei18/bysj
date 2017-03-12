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
<base href="${pageContext.request.contextPath }/">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>添加试题模块</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->

<!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.div_bg {
	background-image: url(images/index-rightbg.jpg);
	background-repeat: no-repeat;
	width: 302px;
	height: 290px;
	margin-top: 10px;
	margin-right: auto;
	margin-bottom: 0px;
	margin-left: auto;
}

td {
	font-size: 14px;
	line-height: 20px;
	color: #414141;
}

.btn-group {
	font-size: 14px;
	color: blue;
	padding-top: 5px;
	cursor: pointer;
}

.btn-group:hover {
	font-size: 14px;
	color: red;
	padding-top: 5px;
	cursor: pointer;
}

a {
	color: #06329b;
	text-decoration: none;
	line-height: 24px;
}

a:hover {
	color: #cc0000;
	text-decoration: none;
	line-height: 24px;
}

.bg {
	background-repeat: no-repeat;
	height: 40px;
	width: 95px;
	text-align: center;
}

.nobg {
	background-repeat: no-repeat;
	height: 40px;
	width: 95px;
	text-align: center;
}
</style>
</head>
<script type="text/javascript">
	//设当前显示层
	function chgtt(d1) {
		var NowFrame;
		if (Number(d1)) {
			NowFrame = d1;
		} else {
			NowFrame = 1;
		}

		for (var i = 1; i <= 3; i++) {
			if (i == NowFrame) {
				document.getElementById("test" + NowFrame).style.display = "block"; //当前层
				document.getElementById("bg" + NowFrame).className = "bg";
			} else {
				document.getElementById("test" + i).style.display = "none"; //隐藏其他层
				document.getElementById("bg" + i).className = "nobg";
			}
		}

	}
	window.onLoad = chgtt();
</script>
<body>
	<div class="div_bg">
		<table width="1000" border="0" cellspacing="0" cellpadding="0"
			style=" margin:0px auto 0px auto;">
			<tr>
				<td style="height:35px;" colspan="5"></td>
			</tr>
			<tr>
				<td id="bg1"><a class="btn-group" role="group"
					onMouseOver="chgtt(1);">判断题</a></td>
				<td id="bg2"><a class="btn-group" onMouseOver="chgtt(2);">单选题</a></td>
				<td id="bg3"><a class="btn-group" onMouseOver="chgtt(3);">主观题</a></td>
			</tr>
			<tr>
				<td colspan="5"
					style="padding-top:10px; padding-left:5px; text-align:left;width: 100px;">
					<div id="test1" style="display:block;">
						<form:form modelAttribute="judge" method="post"
							class="form-horizontal">
							<span> 请选择单元：</span>
							<form:select path="TUnit.id">
								<option>请选择</option>
								<c:forEach items="${unit}" var="item">
									<form:option value="${item.id}">${item.unitName }</form:option>
								</c:forEach>
							</form:select>
							<input type="text" class="form-control" placeholder="请输入问题名称："
								name="question" aria-describedby="sizing-addon1">

							<br>

							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio1" value="0" checked> √
							</label>

							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio2" value="1"> ×
							</label>

							<br>

							<input type="text" class="form-control" placeholder="请输入关键字1："
								name="jkey1" aria-describedby="sizing-addon1">

							<br>

							<input type="text" class="form-control" placeholder="请输入关键字2："
								name="jkey2" aria-describedby="sizing-addon1">

							<br>

							<input type="text" class="form-control" placeholder="请输入关键字3："
								name="jkey3" aria-describedby="sizing-addon1">

							<br>

							<button type="submit" class="btn btn-primary pull-right">Submit</button>
						</form:form>
					</div>
					<div id="test2" style="display:none;">
						<form:form modelAttribute="singlesel" method="post"
							class="form-horizontal">
							<span> 请选择单元：</span>
							<form:select path="TUnit.id">
								<option>请选择</option>
								<c:forEach items="${unit}" var="item">
									<form:option value="${item.id}">${item.unitName }</form:option>
								</c:forEach>
							</form:select>
							<div class="form-group">
								<span>请分别输入选项ABCD的内容：</span> <input type="text" name="question"
									class="form-control" placeholder="请输入问题名称："
									aria-describedby="sizing-addon1"> <br> <span>请分别输入选项ABCD的内容：</span>
								<input type="text" class="form-control" placeholder="选项A:" name="choiceA"
									aria-describedby="sizing-addon1"> <br> <input
									type="text" class="form-control" placeholder="选项B：" name="choiceB"
									aria-describedby="sizing-addon1"> <br> <input
									type="text" class="form-control" placeholder="选项C：" name="choiceC" 
									aria-describedby="sizing-addon1"> <br> <input
									type="text" class="form-control" placeholder="选项D：" name="choiceD"
									aria-describedby="sizing-addon1">
							</div>

							<br>
							<span>请选择正确答案：</span>
							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio1" value="A" checked>A
							</label>
							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio2" value="B"> B
							</label>
							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio1" value="C">C
							</label>

							<label class="radio-inline"> <input type="radio"
								name="answer" id="inlineRadio2" value="D">D
							</label>

							<br>
							<br>
							<span>请输入关键字以便于查重：</span>
							<input type="text" class="form-control" placeholder="请输入关键字1："
								name="jkey1" aria-describedby="sizing-addon1">

							<br>
							<br>
							<input type="text" class="form-control" placeholder="请输入关键字2："
								name="jkey2" aria-describedby="sizing-addon1">

							<br>
							<br>
							<input type="text" class="form-control" placeholder="请输入关键字3："
								name="jkey3" aria-describedby="sizing-addon1">

							<br>
							<br>
							<button type="submit" class="btn btn-primary pull-right">Submit</button>
						</form:form>
					</div>
					<div id="test3" style="display:none;">
						<form:form modelAttribute="subjective" method="post"
							class="form-horizontal">
							<span> 请选择单元：</span>
							<form:select path="TUnit.id">
								<option>请选择</option>
								<c:forEach items="${unit}" var="item">
									<form:option value="${item.id}">${item.unitName }</form:option>
								</c:forEach>
							</form:select>
							<input type="text" class="form-control" placeholder="请输入问题名称："
								aria-describedby="sizing-addon1">
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio1" value="option1">
								正确
							</label>
							<label class="radio-inline"> <input type="radio"
								name="inlineRadioOptions" id="inlineRadio2" value="option2">
								错误
							</label>
							</br>
							<button type="submit" class="btn btn-primary pull-right">Submit</button>
						</form:form>
					</div>
				</td>
			</tr>
		</table>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>