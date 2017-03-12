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
function autoTest() {
	var arr = new Array(); 
	if ($(".unit:selected").val() == undefined) {
		alert("请选择单元");
	} else{
		$("input[name=isSelect]:checked").each( 
		           function(a,c){ 
		              arr[a] = $(c).val();});
		if(arr.length==0){
			alert("请选择章");
		}else if($(".jEasy:selected").val()==undefined){
			alert("请将信息填写完整");
		}else{
			var chapterIds = arr.toString();
			$.post("<%=basePath%>manage/autoPaper/autoTest",{
				"chapterIds":chapterIds,
				"paperName":$("#paperName").val(),
				"jEasy":$(".jEasy:selected").val(),
				"jMid":$(".jMid:selected").val(),
				"jDif":$(".jDif:selected").val(),
				"siEasy":$(".siEasy:selected").val(),
				"siMid":$(".siMid:selected").val(),
				"siDif":$(".siDif:selected").val(),
				"suEasy":$(".suEasy:selected").val(),
				"suMid":$(".suMid:selected").val(),
				"suDif":$(".suDif:selected").val(),
				"fEasy":$(".fEasy:selected").val(),
				"fMid":$(".fMid:selected").val(),
				"fDif":$(".fDif:selected").val(),
				},function(data){
					location.href="<%=basePath%>manage/autoPaper/autoTestInfo/"+data+"";
					
				},"json");
		}
	}
}

$(function(){
	$("#units").change(function (){
		if($(".unit:selected")){
			var unitId = $(".unit:selected").val();
			$("#chapters").show();
			var i = 0;
			$.ajax({url:"<%=basePath%>manage/autoPaper/autoTest/"+ unitId + "",
											type : "get",
											dataType : 'json',
											success : function(data) {
												$("#chapters")
														.html(
																"<span>请选择章</span><br>");
												for (i = 0; i < data[0].length; i++) {
													$("#chapters")
															.append(
																	"<input type='checkbox' name='isSelect' class ='chapter' value='"+data[0][i].id+"'/>"
																			+ data[0][i].chaName
																			+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
												}
											}
										});
							}
						});
	});
</SCRIPT>
</HEAD>
<BODY>
	<div>
		<select id="units">
			<option>请选择单元：</option>
			<c:forEach items="${units }" var="item">
				<option value="${item.id}" class="unit">${item.unitName }</option>
			</c:forEach>
		</select>
		<div hidden="hidden" id="chapters">
			<input type="checkbox" value="1"> <input type="checkbox"
				name="isSelect" value="1" /> <input type="checkbox" value="1">
		</div>
		<br> <span>请输入试卷名称：</span><br> <input style="width: 300px;"
			type="text" name="paperName" placeholder="请输入试卷名称：" id="paperName"><br>
		<br> 判断题
		<ul>
			<li>简单：<select >
					<option class="jEasy" value="1">1</option>
					<option class="jEasy" value="2">2</option>
					<option class="jEasy" value="3" selected>3</option>
					<option class="jEasy" value="4">4</option>
					<option class="jEasy" value="5">5</option>
			</select></li>
			<li>中等：<select>
					<option class="jMid" value="1">1</option>
					<option class="jMid" value="2" selected>2</option>
					<option class="jMid" value="3">3</option>
					<option class="jMid" value="4">4</option>
			</select></li>
			<li>困难：<select>
					<option class="jDif" value="1" selected>1</option>
					<option class="jDif" value="2">2</option>
			</select></li>
		</ul>
		填空题
		<ul>
			<li>简单：<select >
					<option class="fEasy" value="1">1</option>
					<option class="fEasy" value="2">2</option>
					<option class="fEasy" value="3" selected>3</option>
					<option class="fEasy" value="4">4</option>
					<option class="fEasy" value="5">5</option>
			</select></li>
			<li>中等：<select>
					<option class="fMid" value="1">1</option>
					<option class="fMid" value="2" selected>2</option>
					<option class="fMid" value="3">3</option>
					<option class="fMid" value="4">4</option>
			</select></li>
			<li>困难：<select>
					<option class="fDif" value="1" selected>1</option>
					<option class="fDif" value="2">2</option>
			</select></li>
		</ul>
		单选题
		<ul>
			<li>简单：<select>
					<option class="siEasy"  value="1">1</option>
					<option class="siEasy"  value="2">2</option>
					<option class="siEasy"  value="3" selected>3</option>
					<option class="siEasy"  value="4">4</option>
					<option class="siEasy" value="5">5</option>
			</select></li>
			<li>中等：<select>
					<option class="siMid" value="1">1</option>
					<option class="siMid" value="2" selected>2</option>
					<option class="siMid" value="3">3</option>
			</select></li>
			<li>困难：<select>
					<option class="siDif" value="1" selected>1</option>
					<option class="siDif" value="2">2</option>
			</select></li>
		</ul>
		简答题
		<ul>
			<li>简单：<select>
					<option class="suEasy" value="1" selected>1</option>
					<option class="suEasy" value="2">2</option>
					<option class="suEasy" value="3">3</option>
			</select></li>
			<li>中等：<select>
					<option class="suMid" value="0" selected>0</option>
					<option class="suMid" value="1">1</option>
					<option class="suMid" value="2">2</option>
			</select></li>
			<li>困难：<select>
					<option class="suDif" value="0" selected>0</option>
					<option class="suDif" value="1">1</option>
			</select></li>
		</ul>
		<!-- <span>请输入知识点：</span><br>
		<input style="width: 300px;" type="text" name ="paperName" placeholder="请输入知识点 如：软件项目，软件开发等" id="paperName"><br>
		 -->
		<button type="button" onclick="autoTest()">【点击生成试卷】</button>
	</div>
</BODY>
</HTML>