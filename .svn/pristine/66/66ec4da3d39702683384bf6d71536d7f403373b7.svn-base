<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页页面</title>
	<!-- 引入bootstrap分页 -->
	<link rel="stylesheet" href="<%=basePath%>/static/js/bootstrap/bootstrap.css" />
	<script src="<%=basePath%>/static/js/bootstrap/jquery.min.js"></script>
	<script src="<%=basePath%>/static/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=basePath%>/static/js/bootstrap/bootstrap-paginator.js"></script>
	<script>
	$(function() {
		$('#pagination').bootstrapPaginator({
			bootstrapMajorVersion: 3,
			currentPage: ${requestScope.pageInfo.pageNum },
			totalPages: ${requestScope.pageInfo.pages },
			//pageSize:${requestScope.pageInfo.pageSize},//add by chenzhiwei
			pageUrl: function(type, page, current) {//,pageSize
				return '<%=basePath%>getUsers.do?pageNum=' + page;
				<%-- return '<%=basePath%>getUsers.do?pageNum=' + page+'&pageSize='+pageSize; --%>
			},
			itemTexts: function(type, page, current) {
				switch(type) {
					case "first":
						return "首页";
					case "prev":
						return "上一页";
					case "next":
						return "下一页";
					case "last":
						return "末页";
					case "page":
						return page;
				}
			}
		});
	});
	</script>
</head>
<body>
	<center>
		<table border="1px" width="800px">
			<thead>
				<tr>
					<th>id主键</th>
					<th>username用户</th>
					<th>password密码</th>
				</tr>
			</thead>
			<tbody>
				<%-- <c:forEach items="requestScope.pageInfo.Page" var="map">
					<tr>
						<td>${map.id }</td>
						<td>${map.username }</td>
						<td>${map.password }</td>
					</tr>
				</c:forEach> --%>
				<c:forEach items="${requestScope.userList }" var="map">
					<tr>
						<td>${map.id }</td>
						<td>${map.username }</td>
						<td>${map.password }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	<!-- 把分页搞出来 -->
	<ul id="pagination"></ul>
	</center>
</body>
</html>