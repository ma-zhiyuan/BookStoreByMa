<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user_info.jsp' starting page</title>
    
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
    	<h4><img src="${sessionScope.user.profile }">个人信息</h4>
    	<table role="table" class="table table-bordered ">
    		<tr>
    			<td>ID</td>
    			<td>${sessionScope.user.id }</td>
    		</tr>
    		<tr>
    			<td>用户名</td>
    			<td>${sessionScope.user.name }</td>
    		</tr>
    		<tr>
    			<td>邮箱</td>
    			<td>${sessionScope.user.email }</td>
    		</tr>
    		<tr>
    			<td>年龄</td>
    			<td>${sessionScope.user.age }</td>
    		</tr>
    		<tr>
    			<td>性别</td>
    			<td>${sessionScope.user.gender==0?'女':'男' }</td>
    		</tr>
    		<tr>
    			<td>账户余额</td>
    			<td>${sessionScope.user.balance } &nbsp;&nbsp;<button class="btn" data-toggle="modal" data-target="#rechargeWindow">去充值</button></td>
    		</tr>
    		<tr>
    			<td colspan="2"><a href="loginjsp/modify_user_info.jsp" class="btn btn-info">修改个人信息</a>&nbsp;&nbsp;<button class="btn" data-toggle="modal" data-target="#modifypwdWindow">修改密码</button></td>
    		</tr>
    	</table>
    </div>
    
    <!-- 充值 -->
    <div class="modal fade" id="rechargeWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabelrecharge" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabelrecharge">用户充值中心</h4>
				</div>
				<div class="modal-body">
					<!-- 用户登录表单 -->
					<form action="user/recharge" method="post" role="form" id="recharge" class="form-horizontal">
						<div class="container">
							<div class="form-group">
							    <label for="cardid" class="col-sm-2 control-label">会员卡</label>
							    <div class="col-sm-4">
						    		<input name="cardid" type="text" class="form-control" id="cardid" placeholder="请输入会员卡号">
							    </div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit"  form="recharge" class="btn btn-primary">充值</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
		
	    <!-- 修改密码 -->
    	<div class="modal fade" id="modifypwdWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabelpwd" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabelpwd">修改密码</h4>
				</div>
				<div class="modal-body">
					<!-- 用户登录表单 -->
					<form action="user/modifypwd" method="post" role="form" id="modifypwd" class="form-horizontal">
						<div class="container">
							<div class="form-group">
							    <label for="oldpwd" class="col-sm-2 control-label">原密码</label>
							    <div class="col-sm-4">
						    		<input name="oldpwd" type="password" class="form-control" id="oldpwd" placeholder="原密码">
							    </div>
							</div>
							<div class="form-group">
							  	  <label for="password" class="col-sm-2 control-label">新密码</label>
								   <div class="col-sm-4">
							    		<input name="password" type="password" class="form-control" id="password" placeholder="新密码">
							    </div>
					   		</div>
							<div class="form-group">
							  	  <label for="password2" class="col-sm-2 control-label">新密码</label>
								   <div class="col-sm-4">
							    		<input name="password2" type="password" class="form-control" id="password2" placeholder="确认密码">
							    </div>
					   		</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="submit"  form="modifypwd" class="btn btn-primary">修改</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
