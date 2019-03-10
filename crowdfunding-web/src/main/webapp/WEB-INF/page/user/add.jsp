<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
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
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="javascript:void(0)" onclick="goToUser()">用户信息</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">新增用户<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="add_inputForm">
				  <div class="form-group">
					<label for="exampleInputPassword1">登陆账号</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>字母、数字、下划线组成，字母开头，6-16位</span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="add_username"></span>
					<input type="text" class="form-control" id="exampleInputUsername1" placeholder="请输入登陆账号">
				  </div>
				  <div class="form-group">
					<label for="exampleInputPassword1">用户名称</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>2-6位中文汉字</span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="add_pname"></span>
					<input type="text" class="form-control" id="exampleInputPerName1" placeholder="请输入用户名称">
				  </div>
				  <div class="form-group">
					<label for="exampleInputEmail1">邮箱地址</label>&nbsp;&nbsp;&nbsp;&nbsp;<span id="add_email"></span>
					<input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
				  </div>
				  <button type="button" class="btn btn-success" id="add_add_btn"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button type="button" class="btn btn-danger" id="add_reset_btn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info" >
				<h4>欢迎使用帮助功能</h4>
				<p>这是待加入的功能</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>默认密码</h4>
				<p>每个用户的默认密码为：88888</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
    <script type="text/javascript">
            $(function () {
            	nameFlag=false;
            	pnameFlag=false;
            	emailFlag=false;
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
			    
			    $("#exampleInputUsername1").blur(function checkUserName(){
			    	nameFlag=checkForward("exampleInputUsername1",/^[a-zA-z]\w{5,15}$/,"账号","add_username","${APP_PATH}/check/checkName");
	            })
			    $("#exampleInputPerName1").blur(function checkPName(){
			    	pnameFlag=checkForward("exampleInputPerName1",/^[\u4E00-\u9FA5]{2,6}$/,"用户名","add_pname","${APP_PATH}/check/checkPName");
	            })
	            $("#exampleInputEmail1").blur(function checkEmail(){
	            	emailFlag=checkForward("exampleInputEmail1",/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/,"邮箱","add_email","${APP_PATH}/check/checkEmail");　　
	            })

	            
	            
	            
			    $("#add_add_btn").click(function(){
			    		addUser();
			    })
	            
	            $("#add_reset_btn").click(function(){
			    		reBuildPara();
			    		layer.msg("重置完成！", {time:1000, icon:1, shift:5}, function(){});
			    })
	            
	            
            });
            //ajax校验函数，用来校验是否存在的函数（校验值，url，类型，显示的标签id）
            function check(strValue,urlName,spanName,spanID){
            	var ajaxFlag;
        		$.ajax({
          	   		 type: "POST",
          	   		 url: urlName,
            	   	 data: {
            		 strName:strValue
            	   		},  
            	   	 async:false,
            	   	 success: function(result){
            	     if(result.numCode=="1"){
            	    	 ajaxFlag=true;
            	     }else if(result.numCode=="0"){
            	    	var uname="<span style='color: red;font-size: 12px;font-style: blod'>"+spanName+"已存在！</span>"
            	    	$("#"+spanID).html(uname);
            	    	ajaxFlag=false;
            	     }else{
            	    	var uname="<span style='color: red;font-size: 12px;font-style: blod'>"+spanName+"非法！</span>"
            	    	$("#"+spanID).html(uname);
            	    	ajaxFlag=false;
            	    	}
            	     }
            	});
            	return ajaxFlag;
            }
            
            //校验函数
            function checkForward(inputID,Reg,useInfo,showID,url){
            	var naFlag=false;
            	var pname=$("#"+inputID).val();
    			if(pname==""){
    				var puname="<span style='color: red;font-size: 12px;font-style: blod'>"+useInfo+"不能为空！</span>"
            		$("#"+showID).html(puname);
    				naFlag=false;
    			}else{
        	　　	if(Reg.test(pname)){
        		if(check(pname,url,useInfo,showID)){
        				var puname="<span style='color: green;font-size: 12px;font-style: blod'>"+useInfo+"合法！</span>"
	            		$("#"+showID).html(puname);
        				naFlag=true;
            		}else{
            			naFlag=false;
            		}
        	　　	}else{
        			var puname="<span style='color: red;font-size: 12px;font-style: blod'>"+useInfo+"非法！</span>"
            		$("#"+showID).html(puname);
        			naFlag=false;
        		}
    		}
    			return naFlag;
            }
            
            //添加用户函数
            function addUser(){
            	if(nameFlag&&pnameFlag&&emailFlag){
		    		var username=$("#exampleInputUsername1").val();
		    		var pname=$("#exampleInputPerName1").val();
		    		var email=$("#exampleInputEmail1").val();
		    		var loadingIndex;
		    		$.ajax({
	          	   		 type: "POST",
	          	   		 url: "${APP_PATH}/user/add_insert",
	            	   	 data: {
	            		 "username":username,
	            		 "pname":pname,
	            		 "email":email
	            	   		},  
	            	   	 beforeSend:function(){
	            	   		$("#add_add_btn").attr({ disabled: "disabled" })
	            	   		loadingIndex = layer.msg('处理中', {icon: 16});
	            	   	 },
	            	   	 success: function(result){
	            	     if(result.numCode=="1"){
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("成功添加用户信息！", {time:1000, icon:1, shift:2}, function(){});	
	            	    	 $("#add_add_btn").removeAttr("disabled");
	            	    	 reBuildPara();
	            	     }else if(result.numCode=="0"){
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("用户信息添加失败，请重新添加！", {time:1000, icon:5, shift:6}, function(){});	
	            	    	 $("#add_add_btn").removeAttr("disabled");
	            	    	 reBuildPara();
	            	     }else{
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("系统繁忙，请稍后再试！", {time:1000, icon:5, shift:6}, function(){});
	            	    	 $("#add_add_btn").removeAttr("disabled");
	            	    	 reBuildPara();
	            	    	}
	            	     }
	            	});
		    		
		    	}else{
		    		layer.msg("信息验证不通过，请重新输入", {time:1000, icon:5, shift:6}, function(){});	
		    	}
            	
            }
            //重置函数
            function reBuildPara(){
            	$("#add_inputForm")[0].reset();
            	$("#add_username").html("");
			    $("#add_pname").html("");
			    $("#add_email").html("");
			    nameFlag=false;
            	pnameFlag=false;
            	emailFlag=false;
            }
            
            
            function  goToUser(){
				location.href="${APP_PATH}/user/user";            	
            }
        </script>
  </body>
</html>
