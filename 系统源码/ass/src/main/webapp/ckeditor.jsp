<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/js/jquery-1.4.2.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/js/jquery-fckeditor.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/js/department_add.js"></script>
<html>
<head>
	<title>部门设置</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<!-- 标题显示 --> 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/> 部门信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    
    <textarea name="description" rows="3" cols="20" ></textarea>
    
</div>
</body>
</html>
