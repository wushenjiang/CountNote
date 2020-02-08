<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<title>记账本</title>
<script>
		function countcost(){
			var date1=$("input[name='date1']").val();
			var date2=$("#date2").val();
			location.href="${pageContext.request.contextPath}/countCost?date1="+date1+"&date2="+date2;
		}
</script>
</head>
<body>
	<!-- 引入top.jsp -->
	<jsp:include page="/top.jsp"></jsp:include>
	<!-- 查询栏 -->
	<div class="container-fluid">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group">
					<label>选择开始时间：</label>
					<!--指定 date标记-->
					<div class='input-group date' id='datetimepicker1'>
						<input type='date' class="form-control" name="date1" id="date1" style="width:700px;" > 
					</div>
				</div>
			</div>
			<div class='col-sm-5'>
				<div class="form-group">
					<label>选择结束时间：</label>
					<!--指定 date标记-->
					<div class='input-group date' id='datetimepicker2'>
						<input type='date' class="form-control" name="date2" id="date2" style="width:600px;">
					</div>
				</div>
			</div>
			<div class="col-sm-1" style="margin-top: 25px;">
				<button type="button" class="btn btn-primary" onclick="countcost()">计算总额</button>
			</div>
		</div>
	</div>
	<div class="container-fluid">
			<label style="font-size:20px;color:#46B8DA">总额:</label>
			<label style="font-size:20px;color:red;">${sum}</label>
			<table class="table table-hover">
			<thead style="font-size: 20px; color: #46B8DA">
				<tr>
					<td>编号</td>
					<td>时间</td>
					<td>种类</td>
					<td>金额</td>
			</thead>
			<tbody style="font-size: 20px; color: #FF0033">
					<c:forEach items="${countCostList}" var="ccl" varStatus="vs">
						<tr>
						<td>${vs.count}</td>
						<td>${ccl.cdate}</td>
						<td>${ccl.ctype}</td>
						<td>${ccl.ccost}</td>
						</tr>
				   </c:forEach>
			</tbody>
			</table>
	</div>
</body>
</html>