<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin-index.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="keyword1,keyword2,keyword3">
	<meta name="description" content="this is my page">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="scripts/bootstrap.min.css">
	<link href="scripts/navbar.css" rel="stylesheet">
	<script src="scripts/assets/js/ie-emulation-modes-warning.js"></script>
	<script type="text/javascript" src="scripts/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
	<script src="scripts/assets/js/ie10-viewport-bug-workaround.js"></script>
  </head>
  
  <body>
    <!-- 后台管理的首页 -->
    <!-- 查看所有书 -->
	<div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="admin/admin-index.jsp">后台管理</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="book/adminAll">查看所有书</a></li>
              <li><a href="" class="btn" data-toggle="modal" data-target="#cardWindow">发布会员卡</a></li>
              <li><a href="" class="btn" data-toggle="modal" data-target="#cardWindowQR">QR会员卡</a></li>
              <li><a href="book/getUnrepliedConults">未回复咨询:${requestScope.unrepliedcount }</a></li>
              <li><a href="adminservlet/allOrder">所有订单</a></li>
            </ul>
            <!-- <ul class="nav navbar-nav navbar-right">
              <li class="active"><a href="./">Default</a></li>
              <li><a href="../navbar-static-top/">Static top</a></li>
              <li><a href="../navbar-fixed-top/">Fixed top</a></li>
            </ul> -->
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
    </div>
    
    <!-- 发布会员卡 -->
    <div class="modal fade" id="cardWindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabelcards" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabelcards">发布会员卡</h4>
				</div>
				<div class="modal-body">
					<form action="adminservlet/addCards" method="post" role="form" id="addCards" class="form-horizontal">
							<div class="form-group">
							    <label for="number" class="col-sm-2 control-label">数量</label>
							    <div class="col-sm-4">
						    		<input name="number" type="text" class="form-control" id="number" placeholder="请输入会员卡的数量">
							    </div>
							</div>
							<div class="form-group">
							  	  <label for="balance" class="col-sm-2 control-label">金额</label>
								   <div class="col-sm-4">
							    		<input name="balance" type="text" class="form-control" id="balance" placeholder="请输入每张会员卡的金额">
							    </div>
					   		</div>
					</form>
				</div>
				<div class="modal-footer">
					<input type="submit"  form="addCards" class="btn btn-primary" value="生成会员卡">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>	
		
		
		
	<div class="modal fade" id="cardWindowQR" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabelcardsQR" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabelcardsQR">发布会员卡</h4>
				</div>
				<div class="modal-body">
					<form action="card/code" method="post" role="form" id="addCardsQR" class="form-horizontal">
							<div class="form-group">
							    <label for="numberqr" class="col-sm-2 control-label">数量</label>
							    <div class="col-sm-4">
						    		<input name="numberqr" type="text" class="form-control" id="numberqr" placeholder="请输入会员卡的数量">
							    </div>
							</div>
							<div class="form-group">
							  	  <label for="balanceqr" class="col-sm-2 control-label">金额</label>
								   <div class="col-sm-4">
							    		<input name="balanceqr" type="text" class="form-control" id="balanceqr" placeholder="请输入每张会员卡的金额">
							    </div>
					   		</div>
					</form>
				</div>
				<div class="modal-footer">
					<input type="submit"  form="addCardsQR" class="btn btn-primary" value="生成会员卡">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
		
		
		
	</div>


  </body>
</html>
