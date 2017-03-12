<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>软件工程双语课程管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="js/themes/icon.css" />
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.js"></script>

<script type="text/javascript" src='js/outlook2.js'>
	
</script>

<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "首页导航内容管理",
			"menus" : [  {
				"menuname" : "教学目标",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/targetManagement/1"
			}, {
				"menuname" : "教学方法",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/jxffManagement/1"
			}, {
				"menuname" : "教学大纲",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/jxdgManagement/1"
			}, {
				"menuname" : "教学日历",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/jxrlManagement/1"
			}, {
				"menuname" : "考核方式",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/khfsManagement/1"
			} , {
				"menuname" : "教材",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/jcManagement/1"
			}, {
				"menuname" : "师资力量",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/szllManagement/1"
			}]
		}, {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "教学内容管理",
			"menus" : [  {
				"menuname" : "ppt管理",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/pptManagement/1"
			}, {
				"menuname" : "视频管理",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/videoManagement/1"
			}, {
				"menuname" : "文献管理",
				"icon" : "icon-add",
				"url" : "<%=path%>/manage/file/documentManagement/1"
			}, {
				"menuname" : "双语词库管理",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/lexicon/all"
			}, {
				"menuname" : "双语文献管理",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/bilingual/all"
			}  ]
		}, {
			"menuid" : "1",
			"icon" : "icon-sys",
			"menuname" : "统计管理",
			"menus" : [  {
				"menuname" : "访问统计",
				"icon" : "icon-add",
				"url" : "<%=path%>/stat/all"
			} ,{
				"menuname" : "学习记录",
				"icon" : "icon-add",
				"url" : "<%=path%>/learnrecord/all"
			}]
		},{
			"menuid" : "8",
			"icon" : "icon-sys",
			"menuname" : "在线学习管理",
			"menus" : [ {
				"menuname" : "增加测试",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/add/listTestPaper"
			}]
		}, {
			"menuid" : "28",
			"icon" : "icon-sys",
			"menuname" : "试题管理",
			"menus" : [ {
				"menuname" : "判断题审核",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/tJudge/listTJudgeTemp"
			}, 	{
				"menuname" : "单选题审核",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/tSinglesel/listTSingleselTemp"
			},{
				"menuname" : "简答题审核",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/tSubjective/listTSubjectiveTemp"
			}, {
				"menuname" : "填空题审核",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/tFill/listTFillTemp"
			},{
				"menuname" : "人工组卷",
				"icon" : "icon-nav",
				"url" : "<%=basePath%>manage/testPaper/library1"
			}, {
				"menuname" : "自动组卷",
				"icon" : "icon-nav",
				"url" : "<%=basePath%>manage/autoPaper/autoTest"
			} , {
				"menuname" : "上传判断题",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/frontJudge/addTjudge"
			} , {
				"menuname" : "上传单选题",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/frontSinglesel/addTSinglesel"
			} , {
				"menuname" : "上传简答题",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/frontSubjective/addTSubjective"
			} , {
				"menuname" : "上传填空题",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/frontFill/addTFill"
			}]
		}, {
			"menuid" : "39",
			"icon" : "icon-sys",
			"menuname" : "系统管理",
			"menus" : [ {
				"menuname" : "学生列表查看",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/teacher/student"
			}, {
				"menuname" : "添加教师",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/teacher/addTeacher"
			}, {
				"menuname" : "添加学生",
				"icon" : "icon-nav",
				"url" : "<%=path%>/manage/mUpload/addStu"
			} ]
		} ]
	};
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 300,
			modal : true,
			shadow : true,
			closed : true,
			height : 160,
			resizable : false
		});
	}
	//关闭登录窗口
	function close() {
		$('#w').window('close');
	}

	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('系统提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('系统提示', '请再一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}
		var pwd= $newpass.val();
		$.post('<%=basePath%>/manage/teacher/update/'+pwd, function(
				msg) {
			msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		});

	}
	function closeLogin(){
		$('#w').window('close');
	}
	$(function() {

		openPwd();
		//
		$('#editpass').click(function() {
			$('#w').window('open');
		});

		$('#btnEp').click(function() {
			serverLogin();
		});
		
		
		$('#loginOut').click(function() {
			$.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

				if (r) {
					location.href = '<%=basePath%>/manage/login';
				}
			});

		});

	});
	
</script>

</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float:right; padding-right:20px;" class="head">ASS后台管理 <span>${userInfo.tname }</span><button id="editpass">修改密码</button> <a id="loginOut">安全退出</a>
		</span> <span style="padding-left:10px; font-size: 16px; "><img
			src="images/blocks.gif" width="20" height="20" align="absmiddle" />
			ASS后台管理系统</span>
	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2; ">
		<div class="footer">By ASS小组</div>
	</div>
	<div region="west" split="true" title="导航菜单" style="width:180px;"
		id="west">
		<div class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y:hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">

				<h1>${userInfo.tname } Welcome to ASS后台管理系统!</h1>

			</div>
		</div>
	</div>


	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="Password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="Password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"
					href="javascript:void(0)"> 确定</a> <a class="easyui-linkbutton"
					icon="icon-cancel" href="javascript:void(0)" onclick="closeLogin()">取消</a>
			</div>
		</div>
	</div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>


</body>
</html>