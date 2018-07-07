<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<html>
<head>
<title>Spring MVC File Upload</title>
</head>
<body>
	<h2>Submitted Files</h2>
	<table>
		<c:forEach items="${files}" var="file">
			<tr>
				<td>OriginalFileName:</td>
				<td>${file.originalFilename}</td>
			</tr>
			<tr>
				<td>Type:</td>
				<td>${file.contentType}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>