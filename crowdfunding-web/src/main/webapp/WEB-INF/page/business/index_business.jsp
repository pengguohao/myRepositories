<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理系统</title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	
	.hidLi{
		display: none;
		
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
	
	<script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
    <script type="text/javascript">
   		 $(function(){
   			 //该数组用来装选中的复选框
   			
 		    $(".list-group-item").click(function(){
 			    if ( $(this).find("ul") ) {
 					$(this).toggleClass("tree-closed");
 					if ( $(this).hasClass("tree-closed") ) {
 						$("ul", this).hide("fast");
 					} else {
 						$("ul", this).show("fast");
 					}
 				}
 			});
 		    
 		    
 		   
 		   
 		   
    	 })
    
         $("tbody .btn-primary").click(function(){
             window.location.href = "edit";
         });
            
         $("tbody .btn-success").click(function(){
             window.location.href = "assignRole.html";
         });
         
         
         
         
         
         
         
         
         
         
         
         
         
         
         	
      
         
        </script>
	
	
  </head>

  <body id="user_page_body" >

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<div class="btn-group">
				  <button type="button" class="btn btn-default btn-success dropdown-toggle" data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i>${loginUser.pname}<span class="caret"></span>
				  </button>
					  <ul class="dropdown-menu" role="menu">
						<li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
						<li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
						<li class="divider"></li>
						<li><a href="${APP_PATH}/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
					  </ul>
			    </div>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<%@include file="/WEB-INF/page/common/menu.jsp" %>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 用户信息</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
</form>
 <hr style="clear:both;">
          <div class="table-responsive">
          <form id="userForm" action="${APP_PATH}/portal/fileUpfiles" enctype="multipart/form-data" method="post">
            <table class="table  table-bordered">
              <thead>
              </thead>
              <tbody id="user_disp_tbody">
              <tr>
              		<td>
              		文件：<input type="file" name="file">
              		</td>
              		<td><input type="submit" value="上传"></td>
              </tr>
              
                
                
              </tbody>
			  <tfoot id="user_disp_tfoot">
			     
			  </tfoot>
            </table>
           </form>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>
	

  </body>
</html>
