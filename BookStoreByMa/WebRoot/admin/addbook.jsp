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

<title>My JSP 'addbook.jsp' starting page</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="keyword1,keyword2,keyword3">
<meta name="description" content="this is my page">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
	<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
	<link rel="stylesheet" href="themes/default/default.css" />
	<link rel="stylesheet" href="plugins/code/prettify.css" />
	<script charset="utf-8" src="kindeditor.js"></script>
	<script charset="utf-8" src="lang/zh_CN.js"></script>
	<script charset="utf-8" src="plugins/code/prettify.js"></script>
	<script type="text/javascript">
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="details"]', {
				cssPath : 'plugins/code/prettify.css',
				uploadJson : 'admin/upload_json.jsp',
				fileManagerJson : 'admin/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['addbook'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['addbook'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
</head>

<body>
	<jsp:include page="admin-index.jsp"></jsp:include>
	<!-- 后台增加书的表单 -->
	<div class="container">
		<form name="addbook" class="form-horizontal" action="book/addBook" method="post" role="form" enctype="multipart/form-data">
			<c:if test="${!empty requestScope.book }">
				<input type="hidden" name="id" value="${requestScope.book.id }">
			</c:if>
			<div class="form-group">
				<label for="bookname" class="col-sm-2 control-label"></label>
				<div class="col-sm-10"><h3>增加图书</h3></div>
			</div>
			<div class="form-group">
				<label for="bookname" class="col-sm-2 control-label">书名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="bookname"
						placeholder="请输入书名" name="bookname" value="${requestScope.book.name }">
				</div>
			</div>
			<div class="form-group">
				<label for="bookprice" class="col-sm-2 control-label">价格</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="bookprice"
						placeholder="请输入价格" name="bookprice" value="${requestScope.book.price }">
				</div>
			</div>
			<div class="form-group">
				<label for="bookcorp" class="col-sm-2 control-label">出版社</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="bookcorp"
						placeholder="请输入出版社" name="corp" value="${requestScope.book.publishCorp }">
				</div>
			</div>
			<div class="form-group">
				<label for="bookstock" class="col-sm-2 control-label">库存</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="bookstock"
						 name="stock" value="${requestScope.book.stock }">
				</div>
			</div>
			<div class="form-group">
				<label for="booktype" class="col-sm-2 control-label">类型</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="booktype"
						name="type" value="${requestScope.book.type }">
				</div>
			</div>
			<div class="form-group">
				<label for="bookpic" class="col-sm-2 control-label">封面</label>
				<div class="col-sm-10">
					<img src="${requestScope.book.surface }">
					<input type="file" class="form-control" id="bookpic"
						name="pic">
				</div>
			</div>
			<div class="form-group">
				<label for="details" class="col-sm-2 control-label">详细介绍</label>
				<div class="col-sm-10">
					<textarea name="details" cols="100" rows="8" style="width:500px;height:700px;visibility:hidden;">${requestScope.book.details }</textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-info">提交</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
