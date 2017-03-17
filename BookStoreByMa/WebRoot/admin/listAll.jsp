<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'listAll.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="this is my page">
	<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
	<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
  </head>
  
  <body>
  	<jsp:include page="admin-index.jsp"></jsp:include>
    <div class="container">
  		<c:if test="${empty requestScope.books }">
  			<div>您给的查询条件太多啦！</div>
	  	</c:if>
	  	<div class="container">
	  		<div><a class="btn btn-info" href="admin/addbook.jsp">增加书籍</a></div>
		  	<table class=table>
		  		<thead>
		  			<tr>
		  				<th>ID</th>
		  				<th>书名</th>
		  				<th>价格</th>
		  				<th>库存</th>
		  				<th>销量</th>
		  				<th>下架</th>
		  				<th>更新</th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<c:forEach items="${requestScope.books }" var="book">
		  				<tr>
		  					<td>${book.id }</td>
		  					<td>${book.name }</td>
		  					<td>${book.price }</td>
		  					<td>${book.stock }</td>
		  					<td>${book.sold }</td>
		  					<td>
		  						<c:if test="${book.isOnSale==1 }"><a href="book/down?id=${book.id }">下架</a></c:if>
		  						<c:if test="${book.isOnSale==0 }"><a href="book/up?id=${book.id }">上架</a></c:if>
		  					</td>
		  					<td><a href="book/to_update?id=${book.id }">更新</a></td>
		  				</tr>
		  			</c:forEach>
		  		</tbody>
		  	</table>
	  	</div>
	</div>
  </body>
</html>
