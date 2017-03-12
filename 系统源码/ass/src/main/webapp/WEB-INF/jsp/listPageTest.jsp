<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<HTML>
<HEAD>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<base href="${pageContext.request.contextPath }/">
<title>软件工程辅助学习-试题库</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
li {
	list-style-type: none
}

/* .list {
	margin-left: 0px;
	width: 200px;
	height: 900px;
}

.shows,.container {
	width: 1100px;
	height: 900px;
}
 */
.list,.shows {
	float: left;
	border: 1px solid #ffffff;
}
</style>
<script type="text/javascript">
	function tchapter(unitId){
		var i = 0;
		$.ajax({url:"<%=basePath%>front/tunits/" + unitId + "",
					type : "get",
					dataType : 'json',
					success : function(data) {
						$(".tchapters").html("");
							for (i = 0; i < data[0].length; i++){
								var j = data[0][i].unitId;
									$("#tchapter"+j+"")
									.append(
											'<a href="javascript:tsector('+data[0][i].id+')">'+data[0][i].chaName+'</a><ul><li class="tsectors" id="tsector'+data[0][i].id+'"></li></ul>'); 
								}
					}
				});
	}
	function tsector(chapterId){
	var i = 0;
	$.ajax({url:"<%=basePath%>front/tchapters/" + chapterId + "",
			type : "get",
			dataType : 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				$(".tsectors").html("");
				var currentPage = 1;
				for (i = 0; i < data[0].length; i++) {
					var j = data[0][i].chaId;
					$("#tsector" + j + "").append(
							"<a href='javascript:allTest("+currentPage+","+data[0][i].id+")'>" + data[0][i].secName + "</a><br><br>");
				}

			}
		});
	}
	function allTest(currentPage,sectorId){
		$(".shows").show();
		var i = 0 ;
		var j = 0 ;
		var k = 0 ;
		var l = 0 ;
		var o = 0 ;
		var m = 0 ;
		$.ajax({url:"<%=basePath%>front/listPageTest/" + sectorId + "/" + currentPage + "",
					type : "POST",
					dataType : 'json',
					success : function(data) {
						$(".judges").html("");
						$(".singlesels").html("");
						$(".subjectives").html("");
						$(".fills").html("");
						for (i = 0; i < data[0].data.length; i++) {
							console.log(data[0].data[i]);
							var type = 0;
							m++;
							j++;
							$(".judges")
									.append(
											"<tr><td class='juquestion'>"
													+ j
													+ "."
													+ data[0].data[i].question
													+ "<br><label class='radio-inline'> <input type='radio' name='answer"+data[0].data[i].id+"' value='0' checked> √</label><label class='radio-inline'> <input type='radio' name='answer"+data[0].data[i].id+"'  value='1'>×</label></td></tr>");
							
						}
						for (i = 0; i < data[1].data.length; i++) {
							var type = 1;
							m++;
							k++;
							$(".singlesels")
									.append(
											"<tr><td id='selquestion"+data[1].data[i].id+"'><table><tr><td class='selxuhao'>"
													+ k
													+ "."
													+ data[1].data[i].question
													+ "</td></tr><tr><td>A:"
													+ data[1].data[i].choiceA
													+ "</td><td>B:"
													+ data[1].data[i].choiceB
													+ "</td></tr><tr><td>C:"
													+ data[1].data[i].choiceC
													+ "</td><td>D:"
													+ data[1].data[i].choiceD
													+ "</td></tr></table></td></tr>");
							
							
						}
						for (i = 0; i < data[2].data.length; i++) {
							var type=2;
							m++;
							l++;
							$(".subjectives")
									.append(
											"<tr><td class='subquestion'>"
													+ l
													+ "."
													+ data[2].data[i].question
													+ "</td></tr>");
						}
						for (i = 0; i < data[3].data.length; i++) {
							var type=3;
							m++;
							o++;
							$(".fills")
									.append(
											"<tr><td class='fillquestion'>"
													+ o
													+ "."
													+ data[3].data[i].question
													+ "</td></tr>");
						}
						/*
						判断题页面 分页栏
						 */
						$("#judgeCount").html(
								"共" + data[0].totalRecords + "条"
										+ data[0].currentPage + " / "
										+ data[0].totalPages + "");
						$("#judgeYe").html("");
						if (currentPage == 1) {
							$("#judgeYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>首页</a><a class='btn btn-info disabled' role='button'>上一页</a>");
						} else {
							currentPage--;
							$("#judgeYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest(1,"
													+ sectorId
													+ ","
													+ key
													+ ","
													+ diffcult
													+ ")' role='button'>首页</a><a class='btn btn-info' href='javascript:listTest("
													+ currentPage
													+ ","
													+ sectorId
													+ ","
													+ key
													+ ","
													+ diffcult
													+ ")' role='button'>上一页</a>");
						}
						if (data[0].currentPage >= data[0].totalPages) {
							$("#judgeYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>下一页</a><a class='btn btn-info disabled' role='button'>尾页</a>");
						} else {
							currentPage++;
							$("#judgeYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest("
													+ currentPage
													+ ","
													+ sectorId
													+ ","
													+ key
													+ ","
													+ diffcult
													+ ")' role='button'>下一页</a><a class='btn btn-info' href='javascript:pageJump("
													+ data[0].totalPages
													+ ")' role='button'>尾页</a>");
						}
						if(currentPage!=1){
							currentPage = 1;
						}
						/*
						单选题页面 分页栏
						 */
						$("#singleCount").html(
								"共" + data[1].totalRecords + "条"
										+ data[1].currentPage + " / "
										+ data[1].totalPages + "");
						$("#singleselYe").html("");
						if (data[1].currentPage == 1) {
							$("#singleselYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>首页</a><a class='btn btn-info disabled' role='button'>上一页</a>");
						} else {
							currentPage--;
							$("#singleselYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest(1,"
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>首页</a><a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>上一页</a>");
						}
						if (data[1].currentPage >= data[1].totalPages) {
							$("#singleselYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>下一页</a><a class='btn btn-info disabled' role='button'>尾页</a>");
						} else {
							currentPage++;
							$("#singleselYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>下一页</a><a class='btn btn-info' href='javascript:pageJump("
											+ data[1].totalPages
											+ ")' role='button'>尾页</a>");
						}
						if(currentPage!=1){
							currentPage = 1;
						}
						/*
						主观题页面 分页栏
						 */
						$("#subCount").html(
								"共" + data[2].totalRecords + "条"
										+ data[2].currentPage + " / "
										+ data[2].totalPages + "");
						$("#subjectiveYe").html("");
						if (data[2].currentPage == 1) {
							$("#subjectiveYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>首页</a><a class='btn btn-info disabled' role='button'>上一页</a>");
						} else {
							currentPage--;
							$("#subjectiveYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest(1,"
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>首页</a><a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>上一页</a>");
						}
						if (data[2].currentPage >= data[2].totalPages) {
							$("#subjectiveYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>下一页</a><a class='btn btn-info disabled' role='button'>尾页</a>");
						} else {
							currentPage++;
							$("#subjectiveYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>下一页</a><a class='btn btn-info' href='javascript:pageJump("
											+ data[2].totalPages
											+ ")' role='button'>尾页</a>");
						}

						if(currentPage!=1){
							currentPage = 1;
						}
						/*
						填空题页面 分页栏
						 */
						$("#fillCount").html(
								"共" + data[3].totalRecords + "条"
										+ data[3].currentPage + " / "
										+ data[3].totalPages + "");
						$("#fillYe").html("");
						if (data[3].currentPage == 1) {
							$("#fillYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>首页</a><a class='btn btn-info disabled' role='button'>上一页</a>");
						} else {
							currentPage--;
							$("#fillYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest(1,"
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>首页</a><a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>上一页</a>");
						}
						if (data[1].currentPage >= data[1].totalPages) {
							$("#fillYe")
									.append(
											"<a class='btn btn-info disabled' role='button'>下一页</a><a class='btn btn-info disabled' role='button'>尾页</a>");
						} else {
							currentPage++;
							$("#fillYe")
									.append(
											"<a class='btn btn-info' href='javascript:listTest("
											+ currentPage
											+ ","
											+ sectorId
											+ ","
											+ key
											+ ","
											+ diffcult
											+ ")' role='button'>下一页</a><a class='btn btn-info' href='javascript:pageJump("
											+ data[1].totalPages
											+ ")' role='button'>尾页</a>");
						}
					}
				});
	}
	
</script>
</head>
<BODY>
<jsp:include page="menu.jsp"></jsp:include>
	<div class="list">
		<ul>
			<c:forEach var="item" items="${units}">
				<li class="tunits"><a href="javascript:tchapter(${item.id})">${item.unitName }</a>
					<ul>
						<li class="tchapters" id="tchapter${item.id}"></li>
					</ul></li>
			</c:forEach>
		</ul>
	</div>
	<div class="shows" hidden="hidden">
		<ul class="nav nav-tabs" role="tablist" id="feature-tab">
			<li class="active"><a href="#tab-learning" role="tab"
				data-toggle="tab">判断题</a></li>
			<li><a href="#tab-class" role="tab" data-toggle="tab">单选题</a></li>
			<li><a href="#tab-library" role="tab" data-toggle="tab">简答题</a></li>
			<li><a href="#tab-fill" role="tab" data-toggle="tab">填空题</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab-learning">
				<div class="row feature">
					<div class="container">
						<div class="panel panel-default">
							<div class="panel-heading" style="margin-bottom:5px">判断题列表</div>

							<table class="table table-hover table-condensed">

								<thead>
									<tr>
										<th>试题</th>
									</tr>
								</thead>
								<tbody class="judges">


								</tbody>
							</table>
							<div class="row">
								<div class="col-md-6" id="judgeCount"></div>

								<div class="col-md-6 textRight" id="judgeYe"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-class">
				<div class="row feature">
					<div class="container">
						<div class="panel panel-default">
							<div class="panel-heading" style="margin-bottom:5px">单选题列表</div>
							<div class="row"></div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>单选题</th>
									</tr>
								</thead>
								<tbody class="singlesels">


								</tbody>
							</table>
							<div class="row">
								<div class="col-md-6" id="singleCount"></div>

								<div class="col-md-6 textRight" id="singleselYe"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-library">
				<div class="row feature">
					<div class="container">
						<div class="panel panel-default">
							<div class="panel-heading" style="margin-bottom:5px">简答题列表</div>
							<div class="row"></div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>简答题</th>
									</tr>
								</thead>
								<tbody class="subjectives">


								</tbody>
							</table>
							<div class="row">
								<div class="col-md-6" id="subCount"></div>

								<div class="col-md-6 textRight" id="subjectiveYe"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-fill">
				<div class="row feature">
					<div class="container">
						<div class="panel panel-default">
							<div class="panel-heading" style="margin-bottom:5px">填空题列表</div>
							<div class="row"></div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>填空题</th>
									</tr>
								</thead>
								<tbody class="fills">


								</tbody>
							</table>
							<div class="row">
								<div class="col-md-6" id="fillCount"></div>

								<div class="col-md-6 textRight" id="fillYe"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function pageJump(currentPage) {
			currentPage++;

		}
		
	</script>
</body>