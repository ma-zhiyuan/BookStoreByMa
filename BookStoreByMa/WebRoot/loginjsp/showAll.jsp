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
    
    <title>My JSP 'showAll.jsp' starting page</title>
    
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
	    <h4>我的订单</h4>
    	<c:if test="${empty requestScope.orders }">
    		<div>您还没有订单</div>
    	</c:if>
    	<c:forEach items="${requestScope.orders }" var="order">
    		<div>
    			<div>订单号：${order.id }&nbsp;&nbsp;下单日期：${order.time }&nbsp;&nbsp;订单状态：${order.isSend==0?'未发货':'已发货' }&nbsp;&nbsp;<c:if test="${order.isSend==1}"><c:if test="${order.isCommented==0 }"><a href="order/to_comment?orderid=${order.id }">去评价</a></c:if><c:if test="${order.isCommented==1 }">已评价</c:if></c:if></div>
    			<c:forEach items="${order.items }" var="item">
					<div style="margin-top: 5px"><a href="book/bookDetail?id=${item.book.id }"><img src="${item.book.surface }" width="50px"></a>&nbsp;&nbsp;<a href="book/bookDetail?id=${item.book.id }">${item.book.name }</a>&nbsp;&nbsp;${item.book.price }元/本*${item.count }本=${item.itemPrice }元</div>
  				</c:forEach>
  				<div>订单金额：${order.total }&nbsp;&nbsp;收件人：${order.address.consignee }&nbsp;&nbsp;电话:${order.address.tel }&nbsp;&nbsp;地址：${order.address.addr }</div>
    		</div>
    		<hr>
    	</c:forEach>
    </div>
  </body>
</html>
