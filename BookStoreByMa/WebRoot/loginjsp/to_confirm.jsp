<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'to_confirm.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<h4>订单确认</h4>
	<c:if test="${empty sessionScope.user.cart }">
		<span>购物车还是空的哦。</span>
	</c:if>
	<c:if test="${!empty sessionScope.user.cart }">
		<c:forEach items="${sessionScope.user.cart.items }" var="item">
			<div class="col-xs-4">
				<a href="book/bookDetail?id=${item.book.id }"><img
					src="${item.book.surface }" width="50px"></a>
			</div>
			<div class="col-xs-8">
				<a href="book/bookDetail?id=${item.book.id }">${item.book.name }</a>
			</div>
			<div class="col-xs-12">${item.book.price }元/本*${item.count }本=${item.itemPrice }元</div>
			<hr>
		</c:forEach>
		<form action="order/confirm_order" method="get" id="confirm"> 
			<c:forEach items="${sessionScope.user.addrs }" var="addr">
				<input type="radio" value="${addr.id }" name="addr">收件人：${addr.consignee}&nbsp;&nbsp;电话：${addr.tel}&nbsp;&nbsp;地址：${addr.addr }<br>
			</c:forEach>
			<input type="radio" name="addr" value="other">其它地址：
			收件人：<input type="text" name="newconsignee">
			电话:<input type="text" name="newtel">
			地址：<input type="text" name="newaddr">
		</form>
		<div class="col-xs-12">
			共计${sessionScope.user.cart.total }元&nbsp;&nbsp;<input type="submit"
				class="btn btn-success" form="confirm" value="提交订单">
		</div>
	</c:if>
</body>
</html>
