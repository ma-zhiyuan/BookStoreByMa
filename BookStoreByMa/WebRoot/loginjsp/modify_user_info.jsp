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
    
    <title>My JSP 'modify_user_info.jsp' starting page</title>
    
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
    	<h4>修改个人信息</h4>
    	<form action="user/modifyInfo" method="post" role="form" enctype="multipart/form-data">
			<div class="form-group">
			    <label for="gender" class="col-sm-2 control-label">性别</label>
			    <c:if test="${sessionScope.user.gender==1 }">
			    	<input type="radio" name="gender" value="1" checked="checked">男&nbsp;&nbsp;
			    </c:if>
			    <c:if test="${sessionScope.user.gender!=1 }">
			    	<input type="radio" name="gender" value="1">男&nbsp;&nbsp;
			    </c:if>
			    <c:if test="${sessionScope.user.gender==0 }">
			    	<input type="radio" name="gender" value="0" checked="checked">女
			    </c:if>
			    <c:if test="${sessionScope.user.gender!=0 }">
			    	<input type="radio" name="gender" value="0">女
			    </c:if>
 			</div>
 			<div class="form-group">
    			<label for="age" class="col-sm-2 control-label">年龄</label>
      			<input type="text" id="age" name="age" value="${sessionScope.user.age }">
  			</div>
 			<div class="form-group">
    			<label for="profile" class="col-sm-2 control-label">头像</label>
    			<input type="file" id="profile" name="profile">
  			</div>
  			<input type="submit" class="btn btn-info" value="更新">
    	</form>
    </div>
  </body>
</html>
