<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>文件上传</title>
</head>
<body>
	<center>
		<form action="<%=basePath %>/uploadFile.do" method="post" enctype="multipart/form-data" >
			<table border="1" width="300px">
				<tr>
					<td>文件：</td>
					<td><input type="file" name="bigHeadImg"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="上传">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>