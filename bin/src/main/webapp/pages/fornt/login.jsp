<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<center>
		<form action="/login.do">
			<table border="1px">
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"
						placeholder="请输入3-6位字符串"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" placeholder="请输入6-8位字符串"></td>
				</tr>
				<tr>
					<td><input type="submit" value="提交"></td>
					<td><input type="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>