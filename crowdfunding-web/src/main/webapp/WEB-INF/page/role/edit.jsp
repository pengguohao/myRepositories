<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
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
					<i class="glyphicon glyphicon-user"></i>${loginUser.pname} <span class="caret"></span>
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
				  <li><a href="${APP_PATH}/role/role">用户信息</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">信息修改<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="add_inputForm" eduId="${editRole.rid}">
				  <div class="form-group">
					<label for="exampleInputPassword1">角色名称</label>&nbsp;&nbsp;&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'></span>&nbsp;&nbsp;&nbsp;&nbsp;<span id="add_rolename"></span>
					<input type="text" class="form-control" id="editRolename" value="${editRole.name}" inIValue="${editRole.name}">
				  </div>
				  <button type="button" class="btn btn-success" id="updUserBtn"><i class="glyphicon glyphicon-edit"></i> 修改</button>
				  <button type="button" class="btn btn-danger" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
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
            	nameFlag=true;
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
			    
			    $("#editRolename").blur(function(){
			    	nameFlag=checkFun("editRolename","add_rolename","角色名称","${APP_PATH}/role/checkName");
			    })
			    
			    
			    $("#updUserBtn").click(function(){
			    	var uID=$("#add_inputForm").attr("eduId");
			    	updUser("editRolename",uID);
			    })
			    
			    $("#resetBtn").click(function(){
			    	reBuildPara();
			    	layer.msg("重置完成！", {time:1000, icon:1, shift:5}, function(){});	
			    })
			    
            });
            
            //校验函数1
            function checkFun(inputId,spanID,useInfo,urlName){
            	if($("#"+inputId).val()!=$("#"+inputId).attr("inIValue")){
		    		return checkForward(inputId,useInfo,spanID,urlName);
		    	}else{
		    		$("#"+spanID).html("<span style='color: green;font-size: 12px;font-style: blod'>"+useInfo+"不变！</span>");
		    		return true;
		    	}
            	
            }
            
            //校验函数2
            function checkForward(inputID,useInfo,showID,url){
            	var naFlag=false;
            	var pname=$("#"+inputID).val();
    			if(pname==""){
    				var puname="<span style='color: red;font-size: 12px;font-style: blod'>"+useInfo+"不能为空！</span>"
            		$("#"+showID).html(puname);
    				naFlag=false;
    			}else{
        	　　	
        		if(check(pname,url,useInfo,showID)){
        				var puname="<span style='color: green;font-size: 12px;font-style: blod'>"+useInfo+"合法！</span>"
	            		$("#"+showID).html(puname);
        				naFlag=true;
            		}else{
            			naFlag=false;
            		}
        	　　	
    		}
    			return naFlag;
            }
            
          //ajax校验函数，用来校验是否存在的函数（校验值，url，类型，显示的标签id）
            function check(strValue,urlName,spanValue,spanID){
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
            	    	var uname="<span style='color: red;font-size: 12px;font-style: blod'>"+spanValue+"已存在！</span>"
            	    	$("#"+spanID).html(uname);
            	    	ajaxFlag=false;
            	     }
            	     }
            	});
            	return ajaxFlag;
            }
            
            
            //更改用户信息函数
            function updUser(userNameID,uID){
            	alert(nameFlag);
            	if(nameFlag){
		    		var username=$("#"+userNameID).val();
		    		if(username==$("#"+userNameID).attr("inIValue")){
		    			layer.msg("用户信息不变，不需要修改，请重新确认！", {time:1000, icon:2, shift:5}, function(){});	
		    			return ;
		    		}else{
		    			
		    		var loadingIndex;
		    		$.ajax({
	          	   		 type: "POST",
	          	   		 url: "${APP_PATH}/role/updRoleInfo",
	            	   	 data: {
	            		 "name":username,
	            		 "rid":uID
	            	   		},  
	            	   	 beforeSend:function(){
	            	   		$("#updUserBtn").attr({ disabled: "disabled" })
	            	   		loadingIndex = layer.msg('处理中', {icon: 16});
	            	   	 },
	            	   	 success: function(result){
	            	     if(result.numCode=="1"){
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("成功修改角色信息！", {time:1000, icon:1, shift:2}, function(){});	
	            	    	 $("#"+userNameID).attr("inIValue",username);
	            	    	 reBuildPara();
	            	    	 $("#updUserBtn").removeAttr("disabled");
	            	     }else if(result.numCode=="0"){
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("角色信息修改失败！", {time:1000, icon:5, shift:6}, function(){});	
	            	    	 $("#updUserBtn").removeAttr("disabled");
	            	    	 reBuildPara();
	            	     }else{
	            	    	 layer.close(loadingIndex); 
	            	    	 layer.msg("系统繁忙，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
	            	    	 $("#updUserBtn").removeAttr("disabled");
	            	    	 reBuildPara();
	            	    	}
	            	     }
	            	});
		    			
		    		}
		    		
		    	}else{
		    		layer.msg("信息验证不通过，请重新输入", {time:1000, icon:5, shift:6}, function(){});	
		    	}
            	
            }
            //重置函数
            function reBuildPara(){
            	$("#editRolename").val($("#editRolename").attr("inIValue"))
            	$("#add_rolename").html("");
			    nameFlag=true;
            }
            
            
        </script>
  </body>
</html>
