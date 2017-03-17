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
    
    <title>My JSP 'listAll.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="this is my page">
	<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
	<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
  </head>
  <body>
  	<jsp:include page="../header.jsp"></jsp:include>
  	<div class="container">
  		<!-- 左边显示所有图书 -->
  		<div class="col-xs-9" class="container">
		  	<form action="book/listAll" method="get">
			  	<div class="container">
			  		<input type="text" name="keyword" placeholder="请输入关键字" value="${requestScope.books.cretiera.keyword }">&nbsp;&nbsp;价格：<input type="text" name="minprice" size="1" value="${requestScope.books.cretiera.minPrice==0? '':requestScope.books.cretiera.minPrice }">-
			  		<input type="text" name="maxprice" size="1" value="${requestScope.books.cretiera.maxPrice==999999? '':requestScope.books.cretiera.maxPrice}">&nbsp;&nbsp;<input type="submit" value="搜索">
			  	
				  	<select name="orderby">
				  		<option value="1">以价格的升序排列</option>
				  		<option value="2">以价格的降序排列</option>
				  		<option value="3">以销量的升序排列</option>
				  		<option value="4">以销量的降序排列</option>
				  	</select>
				  	<a href="book/listAll?keyword=${requestScope.books.cretiera.keyword }&minprice=${requestScope.books.cretiera.minPrice}&maxprice=${requestScope.books.cretiera.maxPrice}&orderby=${requestScope.books.cretiera.orderBy}&pageNo=1">首页</a >
				  	<a href="book/listAll?keyword=${requestScope.books.cretiera.keyword }&minprice=${requestScope.books.cretiera.minPrice}&maxprice=${requestScope.books.cretiera.maxPrice}&orderby=${requestScope.books.cretiera.orderBy}&pageNo=${requestScope.books.cretiera.pageNo-1 }">&nbsp;&nbsp;上一页</a>&nbsp;&nbsp;第${requestScope.books.cretiera.pageNo }/${requestScope.books.cretiera.maxPageNo }页
				  	<a href="book/listAll?keyword=${requestScope.books.cretiera.keyword }&minprice=${requestScope.books.cretiera.minPrice}&maxprice=${requestScope.books.cretiera.maxPrice}&orderby=${requestScope.books.cretiera.orderBy}&pageNo=${requestScope.books.cretiera.pageNo+1 }">&nbsp;&nbsp;下一页</a>
				  	<a href="book/listAll?keyword=${requestScope.books.cretiera.keyword }&minprice=${requestScope.books.cretiera.minPrice}&maxprice=${requestScope.books.cretiera.maxPrice}&orderby=${requestScope.books.cretiera.orderBy}&pageNo=${requestScope.books.cretiera.maxPageNo }">&nbsp;&nbsp;末页</a>&nbsp;&nbsp;跳至<input type="text" name="pageNo" size="1" value="${requestScope.books.cretiera.pageNo }">页<input type="submit" value="确定">
			  	</div>
		  	</form>
		  	<div class="row" class="col-xs-9">
		  		<c:if test="${empty requestScope.books }">
	  			<div>您给的查询条件太多啦！</div>
		 	 	</c:if>
				<c:forEach items="${requestScope.books.items }" var="book">
					<div class="col-xs-3 col-md-3" style="margin-top: 20px">
							<div><a href="book/bookDetail?id=${book.id }"><img src="${book.surface }" width="150px" height="200px"></a></div>
							<div><a href="book/bookDetail?id=${book.id }">${book.name }</a></div>
							<div>${book.price }</div>
							<div><a href="order/add?id=${book.id }" class="btn btn-success">加入购物车</a></div>
					</div>
				</c:forEach>
			</div>
  		</div>
  		<!-- 右边显示购物车 -->
  		<div class="col-xs-3" class="container"> 
  			<h4>我的购物车</h4>
  			<c:if test="${empty sessionScope.user.cart }">
  				<span>购物车还是空的哦。</span>
  			</c:if>
  			<c:if test="${!empty sessionScope.user.cart }">
  				<c:forEach items="${sessionScope.user.cart.items }" var="item">
					<div class="col-xs-4"><a href="book/bookDetail?id=${item.book.id }"><img src="${item.book.surface }" width="50px"></a></div>
					<div class="col-xs-8"><a href="book/bookDetail?id=${item.book.id }">${item.book.name }</a></div>
					<div class="col-xs-12">${item.book.price }元/本*${item.count }本=${item.itemPrice }元</div>
					<hr>
  				</c:forEach>
  				<div class="col-xs-12">共计${sessionScope.user.cart.total }元&nbsp;&nbsp;<a href="order/to_confirm" class="btn btn-success">去结算</a></div>
  			</c:if>
  		</div>
  	</div>
  </body>
</html>
