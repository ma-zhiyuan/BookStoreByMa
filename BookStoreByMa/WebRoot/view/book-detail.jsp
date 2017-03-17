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
    
    <title>My JSP 'book-detail.jsp' starting page</title>
    
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
		<c:if test="${empty requestScope.book }">
			<div>该书籍已下架</div>
		</c:if>
		<!-- 基本信息 -->
    	<div>
    		<div class="col-md-3"><img src="${requestScope.book.surface }" width="150px" height="200px"></div>
    		<div class="col-md-9">
    			<table class="table table-bordered">
    				<tr>
    					<td>ID</td>
    					<td>${requestScope.book.id }</td>
    					<td>书名</td>
    					<td>${requestScope.book.name }</td>
    				</tr>
    				<tr>
    					<td>价格</td>
    					<td>${requestScope.book.price }</td>
    					<td>出版社</td>
    					<td>${requestScope.book.publishCorp }</td>
    				</tr>
    				<tr>
    					<td>库存</td>
    					<td>${requestScope.book.stock }</td>
    					<td>已售</td>
    					<td>${requestScope.book.sold }本</td>
    				</tr>
    				<tr>
    					<td colspan="4"><a class="btn btn-success" href="order/add?id=${book.id }">加入购物车</a></td>
    				</tr>
    			</table>
    		</div>
    	</div>
    	<!-- 详情 -->
    	<div>
    	${requestScope.book.details }
    	</div><hr>
    	<!-- 评论 -->
    	<div>
    		<c:if test="${empty requestScope.book.comments }">
    			<div>暂无评论</div>
    		</c:if>
    		<c:forEach items="${requestScope.book.comments }" var="comm">
    			<div>${comm.user.name }</div>
    			<div>${comm.stars }</div>
    			<div>${comm.time }</div>
    			<div>${comm.content }</div>
    			<hr>
    		</c:forEach>
    	</div><hr>
    	<!-- 咨询 -->
    	<div>
    		<c:if test="${empty requestScope.book.consults }">
    			<div>暂无咨询</div>
    		</c:if>
    		<a href="loginjsp/consult.jsp?bookid=${requestScope.book.id }">发布咨询</a>
    		<c:forEach items="${requestScope.book.consults }" var="con">
    			<div>${con.user.name }</div>
    			<div>${con.time }</div>
    			<div>${con.content }</div>
    			<div>
    				<c:if test="${con.isReplied==1 }">管理员回复：${con.reply.content }</c:if>
    				<c:if test="${con.isReplied==0 }">管理员暂未回复</c:if>
    			</div>
    		</c:forEach>
    	</div>
    </div>
  </body>
</html>
