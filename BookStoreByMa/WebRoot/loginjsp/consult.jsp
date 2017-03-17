<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'consult.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="this is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <jsp:include page="../header.jsp"></jsp:include>
    <div class="container">
    	<h4>发布咨询</h4>
    	<form role="form" action="book/consult" method="post">
    		<div>您要的详询的内容</div>
    		<input type="hidden" name="bookid" value="${param.bookid }">
    		<textarea rows="5" cols="70" name="content"></textarea><br>
    		<input type="submit" value="提交">
    	</form>
    </div>
  </body>
</html>
