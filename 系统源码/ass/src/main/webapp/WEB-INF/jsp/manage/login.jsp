<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<base href="<%=basePath%>">
<title>ASS后台管理系统登录界面</title>

<link href="css/alogin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>

<script type="text/javascript">
	$(function() {

		$("#tname").focus(function() {
			$("#sp_msg").html("");
		});

	});
</script>
</head>

<body>
	<form:form modelAttribute="teacher" method="post">
		<div class="Main">
			<ul>
				<li class="top"></li>
				<li class="top2"></li>
				<li class="topA"></li>
				<li class="topB"><span> <img src="images/login/logo.gif"
						alt="" style="" />
				</span></li>
				<li class="topC"></li>
				<li class="topD">
					<ul class="login">
						<li><span class="left">账号：</span> <span style="left">
								<input id="tname" type="text" name="tname" class="txt"
								value="${name }admin" />

						</span></li>
						<li><span class="left">密 码：</span> <span style="left">
								<input id="tpwd" type="password" name="tpwd" class="txt" value="123456" />
						</span></li>


						<li><span class="left"> </span><span id="sp_msg">${message }</span></li>

					</ul>
				</li>
				<li class="topE"></li>
				<li class="middle_A"></li>
				<li class="middle_B"></li>
				<li class="middle_C"><span class="btn"> <input
						type="image" src="images/login/btnlogin.gif" />
				</span></li>
				<li class="middle_D"></li>
				<li class="bottom_A"></li>
				<li class="bottom_B"></li>
			</ul>
		</div>
	</form:form>
</body>
</html>