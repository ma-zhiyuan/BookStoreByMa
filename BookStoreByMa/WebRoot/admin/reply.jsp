<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'reply.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="this is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <jsp:include page="admin-index.jsp"></jsp:include>
    <div class="container">
    	<h4>咨询回复</h4>
    	<c:if test="${empty requestScope.consults }">
    		<div>暂无新咨询</div>
    	</c:if>
    	<div><a href="book/bookDetail?id=${requestScope.consults[0].book.id }" target="_blank"><img src="${requestScope.consults[0].book.surface }" height="50px">&nbsp;&nbsp;${requestScope.consults[0].book.name }</a></div>
    	<div>咨询内容：${requestScope.consults[0].content }&nbsp;&nbsp;咨询时间：${requestScope.consults[0].time }</div>
    	<form action="book/reply" method="post">
    		<input type="hidden" name="consultId" value="${requestScope.consults[0].id }">
    		回复:<br><textarea rows="5" cols="70" name="reply"></textarea><br>
    		<input type="submit" value="回复">
    	</form>
    </div>
  </body>
</html>
