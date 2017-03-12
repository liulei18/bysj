<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>软件工程双语课程</title>
<link href="css/bootstrap.min.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->

<style>
</style>
<link href="css/index.css" rel="stylesheet">
</head>

<body>
	<!-- 顶部导航 -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation"
		id="menu-nav">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<%=path%>/index">软件工程双语课程</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<%=path%>/index">首页</a></li>
					
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">课程描述<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=path%>/target_content/50" data-tab="tab-learning">教学目标</a></li>
							<li><a href="<%=path%>/jxff_content/51" data-tab="tab-class">教学方法</a></li>
							<li><a href="<%=path%>/jxdg_content/52" data-tab="tab-library">教学大纲</a></li>
							<li><a href="<%=path%>/jxrl_content/53" data-tab="tab-library">教学日历</a></li>
							<li><a href="<%=path%>/khfs_content/54" data-tab="tab-library">考核方式</a></li>
							<li><a href="<%=path%>/jc_content/55" data-tab="tab-library">教材</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">教学团队<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=path%>/szll_content/56" data-tab="tab-learning">师资力量</a></li>
						</ul>
					</li>
					
					<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">教学资源<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=path%>/manager/word/all" data-tab="tab-learning">每日一词</a></li>
							<li><a href="<%=path%>/learning" data-tab="tab-learning">在线学习</a></li>
							<li><a href="<%=path%>/onlineStudy/class" data-tab="tab-class">课堂练习</a></li>
							<li><a href="<%=path%>/front/library" data-tab="tab-library">试题库</a></li>
						</ul>
					</li>
					
					<li><a href="#summary-container">简述</a></li>
					<!-- 
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">特点 <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="<%=path%>/learning" data-tab="tab-learning">在线学习</a></li>
							<li><a href="<%=path%>/onlineStudy/class"
								data-tab="tab-class">课堂练习</a></li>
							<li><a href="<%=path%>/front/library" data-tab="tab-library">试题库</a></li>
						</ul>
					</li>
					 -->
					<li><a href="#" data-toggle="modal" data-target="#about-modal">关于</a></li>
				</ul>
				<div class="nav navbar-right">
					<c:choose>
						<c:when test="${sessionScope.userName=='none'}">
							<button type="button" class="btn btn-inverse navbar-btn"
								data-toggle="modal" data-target="#exampleModal"
								data-whatever="@mdo">登录</button>
						</c:when>
						<c:otherwise>
            		${sessionScope.userName}<a href="<%=basePath%>/login/logout">【注销】</a>
            	</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<ul class="nav nav-tabs" role="tablist" id="feature-tab">
						<li class="active"><a href="#tab-student" role="tab"
							data-toggle="tab">学生</a></li>
					</ul>
				</div>
				<div class="tab-content">
					<div class="tab-pane active" id="tab-student">
						<div class="row feature">
							<div class="col-md-7">

								<div class="col-md-7">
									<form action="<%=path%>/login/login" method="post">
										<div class="modal-body">

											<div class="form-group">
												<label for="recipient-name" class="control-label">用户名:</label>
												<input type="text" name="name" class="form-control" id="recipient-name" value="201200814102">
													
											</div>
											<div class="form-group">
												<label for="message-text" class="control-label">密码:</label>
												<input type="password" name="pwd" class="form-control" id="message-text" value="201200814102"/>
												<input type="hidden" name="type" value="student" />
													
											</div>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">关闭</button>
											<input type="submit" class="btn btn-primary" value="登陆" />
										</div>
									</form>
								</div>
							</div>

						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>

	<!-- 广告轮播 -->
	<div id="ad-carousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#ad-carousel" data-slide-to="0" class="active"></li>
			<li data-target="#ad-carousel" data-slide-to="1"></li>
			<li data-target="#ad-carousel" data-slide-to="2"></li>
			<li data-target="#ad-carousel" data-slide-to="3"></li>
			<li data-target="#ad-carousel" data-slide-to="4"></li>
		</ol>
		<div class="carousel-inner">
			<div class="item active">
				<img src="images/bg.png" alt="1 slide">

				<div class="container">
					<div class="carousel-caption"></div>
				</div>
			</div>
			<div class="item">
				<img src="images/bg.png" alt="2 slide">

				<div class="container">
					<div class="carousel-caption">


						<p>
							<a class="btn btn-lg btn-primary" href="<%=path%>/course"
								target="_blank" role="button">点我进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="images/bg.png" alt="3 slide">

				<div class="container">
					<div class="carousel-caption">

						<p>
							<a class="btn btn-lg btn-primary"
								href="<%=path%>/course" target="_blank"
								role="button">点我进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="images/bg.png" alt="4 slide">

				<div class="container">
					<div class="carousel-caption">

						<p>
							<a class="btn btn-lg btn-primary"
								href="<%=path%>/course" target="_blank" role="button">点我进入</a>
						</p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="images/bg.png" alt="5 slide">

				<div class="container">
					<div class="carousel-caption">


						<p>
							<a class="btn btn-lg btn-primary" href="<%=path%>/paper/intoAnswer"
								target="_blank" role="button">查看我的练习</a>
						</p>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="#ad-carousel" data-slide="prev"><span
			class="glyphicon glyphicon-chevron-left"></span></a> <a
			class="right carousel-control" href="#ad-carousel" data-slide="next"><span
			class="glyphicon glyphicon-chevron-right"></span></a>
	</div>


	<!-- 主要内容 -->
	<div class="container summary">

		<!-- 简介 -->
		<div class="row" id="summary-container">
			<div class="col-md-4">
				<img class="img-circle" src="images/learning-small.jpg"
					alt="learning">

				<h2>在线学习</h2>

				<p>实时在线学习课程</p>

				<p>
					<a class="btn btn-default" href="<%=path%>/learning" role="button">点我进入</a>
				</p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="images/course-small.jpg" alt="class">

				<h2>课堂练习</h2>

				<p>课堂练习随时做</p>

				<p>
					<a class="btn btn-default" href="<%=path%>/onlineStudy/class"
						role="button">点我进入</a>
				</p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="images/library-small.jpg" alt="library">

				<h2>试题库</h2>

				<p>丰富的试题库</p>

				<p>
					<a class="btn btn-default" href="<%=path%>/front/library"
						role="button">点我进入</a>
				</p>
			</div>
		</div>

		<!-- 特性 -->

		<hr class="feature-divider">

		<ul class="nav nav-tabs" role="tablist" id="feature-tab">
			<li class="active"><a href="#tab-learning" role="tab"
				data-toggle="tab">在线学习</a></li>
			<li><a href="#tab-class" role="tab" data-toggle="tab">课堂练习</a></li>
			<li><a href="#tab-library" role="tab" data-toggle="tab">试题库</a></li>
		</ul>

		<div class="tab-content">
			<div class="tab-pane active" id="tab-learning">
				<div class="row feature">
					<div class="col-md-7">

						<h2 class="feature-heading">
							在线学习 <span class="text-muted">最新的网络课程</span>
						</h2>

						<p class="lead">在线学习让学生可以在线选择并学习需要的课程，并提供了双语支持，中英文的对照更加有助于学生的理解。
							教师的推荐可以使你更快的寻找到合适的内容。</p>
					</div>
					<div class="col-md-5">
						<img class="feature-image img-responsive"
							src="images/learning.jpg" alt="Learning">
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-class">
				<div class="row feature">
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="images/course.jpg"
							alt="Class">
					</div>
					<div class="col-md-7">

						<h2 class="feature-heading">
							课堂练习 <span class="text-muted">随时的练习</span>
						</h2>

						<p class="lead">课堂练习是保证学习效果的重要方式，老师布置的练习随时能够查看，自己也可以随时练习以巩固
							所学。了解自己的能力。</p>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-library">
				<div class="row feature">
					<div class="col-md-7">

						<h2 class="feature-heading">
							试题库 <span class="text-muted">丰富的试题选择</span>
						</h2>

						<p class="lead">试题库保证了课程的内容大大的丰富了。有学生和教师共同丰富的试题库将会节约大量的教学时间，
							客观题的自动处理也大大节约了教师的时间。</p>
					</div>
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="images/library.jpg"
							alt="Library">
					</div>
				</div>
			</div>

		</div>

		<!-- 关于 -->
		<div class="modal fade" id="about-modal" tabindex="-1" role="dialog"
			aria-labelledby="modal-label" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>
						</button>
						<h4 class="modal-title" id="modal-label">关于</h4>
					</div>
					<div class="modal-body">
						<p>基于Web的软件工程辅助学习系统。</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">知道了</button>
					</div>
				</div>
			</div>
		</div>


		<footer>

			
			
			<table border="0" cellpadding="0" cellspacing="0">
			    <tr>
			    	<td><a href="http://www.nsfc.gov.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12106)"><img src="images/link1.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="国家自科基金委" title="国家自科基金委"></a></td>
			        <td><a href="http://www.most.gov.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12107)"><img src="images/link2.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="国家科技部" title="国家科技部"></a></td>
			        <td><a href="http://www.moe.gov.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12108)"><img src="images/link3.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="国家教育部" title="国家教育部"></a></td>
			        <td><a href="http://www.hnkjt.gov.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12109)"><img src="images/link4.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="河南省科技厅" title="河南省科技厅"></a></td>
			        <td><a href="http://www.haedu.gov.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12110)"><img src="images/link5.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="河南省教育厅" title="河南省教育厅"></a></td>
			        <td><a href="http://www.zznet.com.cn" target="_blank" onclick="_addDynClicks('wbimage',1067777516,12111)"><img src="images/link6.jpg" width="150" height="34" hspace="2" vspace="0" border="0" alt="郑州市科技港" title="郑州市科技港"></a></td>
			    </tr>
        	</table>
        
        	<p class="pull-right">
				<a href="#top">回到顶部</a> <br>
				<a href="<%=path%>/manage/login">后台登陆</a>
			</p>

			<p>&copy; 2016 软件工程双语课程</p>
		</footer>

	</div>

	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
