<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>记账本</title>
<!-- 引入Bootstrap核心样式文件 -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- 引入jQuery核心js文件 -->
<script src="./js/jquery-1.11.3.min.js"></script>
<!-- 引入BootStrap核心js文件 -->
<script src="./js/bootstrap.js"></script>
</head>
<body>
	<div class="row">
		<!-- 导航条部分 -->
		<div class="col-lg-11" style="margin-top: 15px;">
			<ul class="nav nav-pills">
				<li role="presentation" style="font-size: 20px;"><a href="${pageContext.request.contextPath}/showAllCost">开始记账</a></li>
				<li role="presentation" style="font-size: 20px"><a href="sum.jsp">账单汇总</a></li>
				<li role="presentation" style="font-size: 20px"><a href="#">图表数据</a></li>
				<li role="presentation" style="font-size: 20px"><a href="#">进入后台</a></li>
			</ul>
		</div>
		<div class="col-lg-1" style="padding-top: 15px; margin-top: 15px;">
			<a href="tencent://message/?uin=1098577802&Site=&Menu=yes"><font
				color="red";>联系我们</font></a>
		</div>
	</div>
</body>
</html>