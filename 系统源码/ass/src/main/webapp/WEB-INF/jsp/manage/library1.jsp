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

.list {
	margin-left: 0px;
	width: 200px;
	height: 900px;
}

.shows,.container {
	width: 900px;
	height: 900px;
}

.list,.shows {
	float: left;
	border: 1px solid #ffffff;
}
</style>
<script type="text/javascript">
	function tchapter(unitId){
		var i = 0;
		$.ajax({url:"<%=basePath%>manage/testPaper/tunits/" + unitId + "",
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
	$.ajax({url:"<%=basePath%>manage/testPaper/tchapters/" + chapterId + "",
			type : "get",
			dataType : 'json',
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success : function(data) {
				$(".tsectors").html("");
				var currentPage = 1;
				for (i = 0; i < data[0].length; i++) {
					if(document.getElementById("key").value.length==0){
						 key = null;
					}
					var j = data[0][i].chaId;
					$("#tsector" + j + "").append(
							"<a href='javascript:listTest("+currentPage+","+data[0][i].id+","+key+",0)'>" + data[0][i].secName + "</a><br><br>");
				}

			}
		});
	}
	function listTest(currentPage,sectorId,key,diffcult){
		$(".shows").show();
		$("#searchJudge").html("<button type='submit' class='btn btn-primary' onclick='listTest("+currentPage+","+sectorId+","+key+","+diffcult+")'>搜索</button>");
		$("#diffcult")
		.html(
				"<a href='javascript:listTest("+currentPage+","+sectorId+","+key+",0)'>易</a> &nbsp;&nbsp;&nbsp;<a href='javascript:listTest("+currentPage+","+sectorId+","+key+",1)'>中</a>&nbsp;&nbsp;&nbsp;<a href='javascript:listTest("+currentPage+","+sectorId+","+key+",2)'>难</a>");
		if(document.getElementById("key").value.length==0){
			 key = null;
		}else{
			key =$("#key").val();
			key = encodeURI(encodeURI(key)); 
		}
		$("#key").val("");
		var i = 0 ;
		var j = 0 ;
		var k = 0 ; 
		var l = 0 ;
		var o = 0 ;
		var m = 0 ;
		$.ajax({url:"<%=basePath%>manage/testPaper/listTest/" + sectorId + "/" + key
							+ "/" + diffcult + "/" + currentPage + "",
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
													+ "<br><label class='radio-inline'> <input type='radio' name='answer"+data[0].data[i].id+"' value='0' checked> √</label><label class='radio-inline'> <input type='radio' name='answer"+data[0].data[i].id+"'  value='1'>×</label></td>"
													+"<td id='add"+m+"'><a href='javascript:add("
													+ data[0].data[i].id
														+ ","+m+","+type+")'>加入试题篮</a></td></tr>");
							if(data[0].data[i].flag==1){
										$("#add"+m+"").html("<a href='javascript:cancel("
												+ data[0].data[i].id
												+ ","+m+","+type+")'>取消</a>");
									}else if(data[0].data[i].flag==0){
										$("#add"+m+"").html("<a href='javascript:add("
												+ data[0].data[i].id
												+ ","+m+","+type+")'>加入试题篮</a>");
									}
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
													+ "</td></tr></table></td><td id='add"+m+"'>"
													+ "<a href='javascript:add("
													+ data[1].data[i].id
													+ ","+m+","+type+")'>加入试题篮</a></td></tr>");
							if(data[1].data[i].flag==1){
								$("#add"+m+"").html("<a href='javascript:cancel("
										+ data[1].data[i].id
										+ ","+m+","+type+")'>取消</a>");
							}else if(data[1].data[i].flag==0){
								$("#add"+m+"").html("<a href='javascript:add("
										+ data[1].data[i].id
										+ ","+m+","+type+")'>加入试题篮</a>");
							}
							
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
													+ "</td><td id='add"+m+"'><a href='javascript:add("
													+ data[2].data[i].id
													+ ","+m+","+type+")'>加入试题篮</a></td></tr>");
							
							if(data[2].data[i].flag==1){
								$("#add"+m+"").html("<a href='javascript:cancel("
										+ data[2].data[i].id
										+ ","+m+","+type+")'>取消</a>");
							}else if(data[2].data[i].flag==0){
								$("#add"+m+"").html("<a href='javascript:add("
										+ data[2].data[i].id
										+ ","+m+","+type+")'>加入试题篮</a>");
							}
						}
						
						for (i = 0; i < data[3].data.length; i++) {
							var type=3;
							m++;
							l++;
							$(".fills")
									.append(
											"<tr><td class='fillquestion'>"
													+ l
													+ "."
													+ data[3].data[i].question
													+ "</td><td id='add"+m+"'><a href='javascript:add("
													+ data[3].data[i].id
													+ ","+m+","+type+")'>加入试题篮</a></td></tr>");
							
							if(data[3].data[i].flag==1){
								$("#add"+m+"").html("<a href='javascript:cancel("
										+ data[3].data[i].id
										+ ","+m+","+type+")'>取消</a>");
							}else if(data[3].data[i].flag==0){
								$("#add"+m+"").html("<a href='javascript:add("
										+ data[3].data[i].id
										+ ","+m+","+type+")'>加入试题篮</a>");
							}
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
						if (data[3].currentPage >= data[3].totalPages) {
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
											+ data[3].totalPages
											+ ")' role='button'>尾页</a>");
						}
					}
				});
	}
	function add(Id,m,type) {
		$.ajax({url:"<%=basePath%>manage/testPaper/addSession/" + Id + "/"+type+"",
			type : "POST",
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$("#count").html(data);
			}
		});

		$("#add" + m + "").html(
				"<a href='javascript:cancel(" + Id + "," + m + ","+type+")'>取消</a>");
	}
	function cancel(Id, m,type) {
		$.ajax({url:"<%=basePath%>manage/testPaper/removeSession/" + Id + "/"+type+"",
			type : "POST",
			dataType : 'json',
			success : function(data) {
				console.log(data);
				$("#count").html(data);
			}
		});


		$("#add" + m + "").html(
				"<a href='javascript:add(" + Id + "," + m + ","+type+")'>加入试题篮</a>");
	}
	function basket(){
		location.href="<%=basePath%>manage/testPaper/basket";
	}
</script>
</head>
<BODY>	
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
		<a href="javascript:basket()">试题篮</a><span id="count">${count }</span>
		<div class="parameter">
			<table>
				<tr>
					<td>难度：</td>
					<td id="diffcult"><a href='javascript:listTest(0)'>简单</a>
						&nbsp;&nbsp;&nbsp;<a href=''>中等</a>&nbsp;&nbsp;&nbsp;<a href=''>困难</a>
						<input id="flag" value="1" hidden="hidden" /></td>
				</tr>
			</table>
		</div>
		<hr class="feature-divider">
		<ul class="nav nav-tabs" role="tablist" id="feature-tab">
			<li class="active"><a href="#tab-learning" role="tab"
				data-toggle="tab">判断题</a></li>
			<li><a href="#tab-class" role="tab" data-toggle="tab">单选题</a></li>
			<li><a href="#tab-library" role="tab" data-toggle="tab">主观题</a></li>
			<li><a href="#tab-fill" role="tab" data-toggle="tab">填空题</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab-learning">
				<div class="row feature">
					<div class="container">
						<div class="panel panel-default">
							<div class="panel-heading" style="margin-bottom:5px">判断题列表</div>
							<div class="row">
								<div class="col-md-8">
									<input type="hidden" name="currentPage" id="currentPage" />
									<div class="form-group">
										<label class="sr-only" for="exampleInputAmount"></label>
										<div class="input-group">
											<div class="input-group-addon">知识点：</div>
											<input type="text" class="form-control" name="jkey" id="key" />
										</div>
									</div>
								</div>
								<div class="col-md-4" id="searchJudge"></div>
							</div>
							<table class="table table-hover table-condensed">

								<thead>
									<tr>
										<th>试题</th>
										<th>操作</th>
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
							<div class="row">
								<form class="form-inline" action="manage/lexicon/all"
									method="post" id="searchForm">
									<div class="col-md-8">
										<input type="hidden" name="currentPage" id="currentPage" />
										<div class="form-group">
											<label class="sr-only" for="exampleInputAmount"></label>
											<div class="input-group">
												<div class="input-group-addon">知识点：</div>
												<input type="text" class="form-control" name="skey1"
													id="skey1" />
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<button type="submit" class="btn btn-primary">搜索</button>
										
									</div>
								</form>
							</div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>试题名称</th>
										<th>操作</th>
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
							<div class="panel-heading" style="margin-bottom:5px">主观题列表</div>
							<div class="row">
								<form class="form-inline" action="manage/lexicon/all"
									method="post" id="searchForm">
									<div class="col-md-8">
										<input type="hidden" name="currentPage" id="currentPage" />
										<div class="form-group">
											<label class="sr-only" for="exampleInputAmount"></label>
											<div class="input-group">
												<div class="input-group-addon">知识点：</div>
											<input type="text" class="form-control" name="skey1" id="skey1" />
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<button type="submit" class="btn btn-primary">搜索</button>
										
									</div>
								</form>
							</div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>试题名称</th>
										<th>操作</th>
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
							<div class="row">
								<form class="form-inline" action="manage/lexicon/all"
									method="post" id="searchForm">
									<div class="col-md-8">
										<input type="hidden" name="currentPage" id="currentPage" />
										<div class="form-group">
											<label class="sr-only" for="exampleInputAmount"></label>
											<div class="input-group">
												<div class="input-group-addon">知识点：</div>
												<input type="text" class="form-control" name="fkey" id="fkey" />
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<button type="submit" class="btn btn-primary">搜索</button>
										
									</div>
								</form>
							</div>
							<table class="table table-hover table-condensed">
								<thead>
									<tr>
										<th>试题名称</th>
										<th>操作</th>
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
		function deleteUser(id) {
			$('#myModal').modal({
				show : true,
			});
			$('#sureBtn').data('value', id);
		}
		$(function() {
			$('#sureBtn').click(function() {
				location.href = "" + $(this).data('value');
			});
		});
		/*
		encodeURI 进行加密
		 */
		function queryJudge(id, jkey1) {
			var jkey = encodeURI(encodeURI(jkey1));
			location.href = "manage/tJudge/judgeCheck/" + id + "/" + jkey + "";
		}
	</script>
</body>