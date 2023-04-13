<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>page.jsp</title>
</head>
<body>
	<h1><%= request.getRequestURI() %></h1>
	<hr>
	
	<form action="/fileUpload/doit" method="post" enctype="multipart/form-data">
		<div>Uploader : <input type="text" name="uploader"></div>
		
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>
		<div><input type="file" name="files"></div>
		<div><input type="submit" name="upload"></div>
	</form>
	
</body>
</html>