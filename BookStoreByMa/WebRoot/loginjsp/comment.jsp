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
    
    <title>My JSP 'comment.jsp' starting page</title>
    
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
		<h4>评价,订单号:${sessionScope.order.id }</h4>
		<form action="order/comment" method="post">
			<c:forEach items="${sessionScope.order.items }" var="item">
				<div style="margin-top: 20px">
					<div><img src="${item.book.surface }" height="50px">&nbsp;&nbsp;${item.book.name }</div>
					<label>星级</label>
					<div>
						<select name="star-${item.id }">
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
						</select>
					</div>
					<label>评价</label>
					<div>
						<textarea rows="5" cols="80" name="content-${item.id }"></textarea>
					</div>
				</div>
			</c:forEach>
			<input type="submit" value="提交">
		</form>
    </div>
  </body>
</html>
