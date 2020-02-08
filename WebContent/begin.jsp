<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<script>
	function delCost(cid) {
		var isDel = confirm("您确认要删除吗？");
		//确认要删除
		if (isDel) {
			location.href = "${pageContext.request.contextPath}/delCost?cid="
					+ cid;
		}
	}
	function searchCost() {
		var $select = $("#select").val();
		var $selectVal = $("#selectVal").val();
		location.href = "${pageContext.request.contextPath}/searchCost?select="
				+ $select + "&selectVal=" + $selectVal;
	}
	$(function() {
		$("#select").change(function() {
			var selectEle = $("#select").val();
			if (selectEle == "消费时间") {
				$("#selectVal").attr("type", "date");
			} else {
				$("#selectVal").attr("type", "text");
			}
		})
	})
</script>
<title>记账本</title>
</head>
<body>
	<!-- 引入top.jsp -->
	<jsp:include page="/top.jsp"></jsp:include>
	<!-- 添加以及查询模块-->
	<div class="container-fluid">
		<div class="row">
			<button type="button" class="btn btn-default btn-lg"
				data-toggle="modal" data-target="#myModal">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label style="font-size: 20px">查询条件:</label>&nbsp;&nbsp;
			<select name="select" style="font-size: 20px" id="select">
				<option value="消费种类">消费种类</option>
				<option value="消费时间">消费时间</option>
			</select> &nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="selectVal"
				style="font-size: 20px; width: 150px" id="selectVal">&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-default btn-lg"
				onclick="searchCost()">
				<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
			</button>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">新增消费项</h4>
					</div>
					<div class="modal-body">
						<form action="${pageContext.request.contextPath}/addCost"
							method="post" id="addForm">
							<div class="form-group">
								<label class="label label-danger" style="font-size: 13px;">消费时间</label>
								<input type="date" class="form-control" id="cdate"
									placeholder="Time" name="cdate">
							</div>
							<div class="form-group">
								<label class="label label-warning" style="font-size: 13px;">种类</label>
								<input type="text" class="form-control" id="ctype"
									placeholder="Type" name="ctype">
							</div>
							<div class="form-group">
								<label class="label label-info" style="font-size: 13px;">金额</label>
								<input type="text" class="form-control" id="ccost"
									placeholder="Cost" name="ccost">
							</div>
							<label style="color: red;">${costinfo}</label>
							<button type="submit" class="btn btn-primary"
								style="margin-left: 520px;">添加</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 显示部分 -->
	<div class="container-fluid">
		<label style="font-size: 20px; color: #46B8DA">所有消费额:</label> <label
			style="font-size: 20px; color: red;">${sum}</label>
		<table class="table table-hover">
			<thead style="font-size: 20px; color: #46B8DA">
				<tr>
					<td>编号</td>
					<td>时间</td>
					<td>种类</td>
					<td>金额</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody style="font-size: 20px; color: #FF0033">
				<c:forEach items="${pageBean.costList}" var="cost" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${cost.cdate}</td>
						<td>${cost.ctype}</td>
						<td>${cost.ccost}</td>
						<td>
							<button type="button" class="btn btn-default btn-lg">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"
									data-toggle="modal" data-target="#myModal${vs.count}"></span>
							</button>&nbsp;&nbsp;&nbsp;
							<div class="modal fade" id="myModal${vs.count}" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">修改消费项</h4>
										</div>
										<div class="modal-body">
											<form
												action="${pageContext.request.contextPath}/updateCost?cid=${cost.cid}"
												method="post" id="addForm">
												<div class="form-group">
													<label class="label label-danger" style="font-size: 13px;">消费时间</label>
													<input type="date" class="form-control" id="cdate"
														placeholder="Time" name="cdate" value="${cost.cdate}">
												</div>
												<div class="form-group">
													<label class="label label-warning" style="font-size: 13px;">种类</label>
													<input type="text" class="form-control" id="ctype"
														placeholder="Type" name="ctype" value="${cost.ctype}">
												</div>
												<div class="form-group">
													<label class="label label-info" style="font-size: 13px;">金额</label>
													<input type="text" class="form-control" id="ccost"
														placeholder="Cost" name="ccost" value="${cost.ccost}">
												</div>
												<button type="submit" class="btn btn-primary"
													style="margin-left: 520px;">修改</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							<button type="button" class="btn btn-default btn-lg"
								onclick="delCost('${cost.cid}')">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div align="center">
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<!-- 上一页按钮 -->
					<!-- 判断当前页是否是第一页 -->
					<c:if test="${pageBean.currentPage==1}">
						<li class="disabled"><a href="javascript:void(0);"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:if test="${pageBean.currentPage!=1}">
						<li><a
							href="${pageContext.request.contextPath}/showAllCost?currentPage=${pageBean.currentPage-1}"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
						</a></li>
					</c:if>
					<c:forEach begin="1" end="${pageBean.totalPage}" var="page">
						<!-- 判断当前页 -->
						<c:if test="${pageBean.currentPage==page}">
							<li class="active"><a href="javascript:void(0);">${page}</a></li>
						</c:if>
						<c:if test="${pageBean.currentPage!=page}">
							<li><a
								href="${pageContext.request.contextPath}/showAllCost?currentPage=${page}">${page}</a></li>
						</c:if>
					</c:forEach>
					<!-- 判断当前页是否是最后一页 -->
					<c:if test="${pageBean.currentPage==pageBean.totalPage}">
						<li class="disabled"><a href="javascript:void(0);"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
						<li><a href="${pageContext.request.contextPath}/showAllCost?currentPage=${pageBean.currentPage+1}"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:if>
					<!--<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>  -->
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>