<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<!DOCTYPE html>
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
	<link rel="stylesheet" href="${APP_PATH}/ztree/zTreeStyle.css">
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
				  <li><a href="${APP_PATH}/role/role">角色信息</a></li>
				  <li class="active">分配访问权限</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				<div class="form-group" style="width: 300px;">
					<label for="exampleInputPassword1" >操作前的访问权限列表</label><br>
                        <ul id="assignTree" class="ztree"></ul>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li class="btn btn-default glyphicon fa fa-arrow-right" id="left2rightBtn" style="cursor: not-allowed;"></li>
                            <br>
                            <li class="btn btn-default glyphicon fa fa-check" style="margin-top:20px;position: relative;top: 40px;" id="right2leftBtn"></li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:160px;width: 300px;">
					<label for="exampleInputPassword1">操作后的访问权限列表</label><br>
						<ul id="assignTreeNot" class="ztree"></ul>
				  </div>
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
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
	
        <script type="text/javascript">
            $(function () {
            	var rid=${menuMap["id"]};
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
			    
			    
			    
			    
			    
			    
			    var iniAssignTreeSetting = {
					view: {
						selectedMulti: false,
						addDiyDom: function(treeId, treeNode){
							var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
							if ( treeNode.icon ) {
								icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
							}
                            
						},
					},
					async: {
						enable: false,
						url:"tree.txt",
						autoParam:["id", "name=n", "level=lv"]
					},
					check: {
						enable: true,
						chkStyle: "checkbox"
					},
					edit: {
						enable: false
					},
					callback: {
						beforeClick: beforeClick
						
					}

				};
			    
			    var cnaDoTreeSetting = {
						view: {
							selectedMulti: false,
							addDiyDom: function(treeId, treeNode){
								var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
								if ( treeNode.icon ) {
									icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
								}
	                            
							},
						},
						async: {
							enable: false,
							url:"tree.txt",
							autoParam:["id", "name=n", "level=lv"]
						},
						check: {
							enable: true,
							chkStyle: "checkbox"
						},
						edit: {
							enable: false
						},
						callback: {
							onClick : function(event, treeId, json) {
									
							}
							
						}

					};
			    
			    
			    
			  //节点单击前事件
	            function beforeClick(treeId, treeNode, clickFlag) {
				  
	    		}
					
				//$.fn.zTree.init($("#treeDemo"), setting); //异步访问数据
				//操作前的权限树
				assingTreeAjax(rid,"${APP_PATH}/permission/assignTree4Role","assignTree",iniAssignTreeSetting,true);
				//操作后的权限树
				assingTreeAjax(rid,"${APP_PATH}/permission/assignTree4Role","assignTreeNot",iniAssignTreeSetting,false);
				
				$("#right2leftBtn").click(function(){
					
					layer.confirm("是否确定更新当前角色权限信息",  {icon: 3, title:'提示'}, function(cindex){
					    layer.close(cindex);
					    layer.close(cindex);
					    var treeObj = $.fn.zTree.getZTreeObj("assignTreeNot");
			    		var checkedNodes = treeObj.getCheckedNodes();
			    		var menus=[];
			    		$.each(checkedNodes,function(i,node){
			    			menus.push(node.id);
			    		})
			    		var str=menus.join(',');
			    		hasOrNotAssign(rid,str,"${APP_PATH}/permission/updAssignPower");
					}, function(cindex){
					    return;
					});
					
			    	//hasOrNotAssign("notAssignList","${APP_PATH}/permission/delAssignMenu","hasAssignList","取消")
			    })
				 
            });
            
            
            function assingTreeAjax(rid,urlStr,treeUl,assignTreeSetting,disChecked){
            	$.ajax({
       	   		 type: "POST",
       	   		 url: urlStr,
       	   		 async:false,
         	   	 	 data: {
         		 	 "rid":rid,
         		 	 "disChecked":disChecked
         	   		 }, 
         	   	    success: function(result){
					$.fn.zTree.init($("#"+treeUl), assignTreeSetting, result);
         	   	    }
         			});
            	
            }
			
            
            function refleshPageInfo(rid){
            	$.ajax({
         	   		 type: "POST",
         	   		 url: "${APP_PATH}/permission/doAssign4Role",
           	   	 	 data: {
           		 	 "rid":rid
           	   		 }, 
           	   	    success: function(result){}
           			});
            }
            
            function hasOrNotAssign(rid,menus,goUrl){
		    	$.ajax({
	      	   		 type: "POST",
	      	   		 url: goUrl,
	        	   	 data: {
	        		 "rid":rid,
	        		 "menuIds":menus
	        	   		}, 
	        	   	 beforeSend:function(){
	            	   		loadingIndex = layer.msg('处理中', {icon: 16});
	            	   	},
	        	   	 success: function(result){
	        	     if(result.ajaxCode){
	        	    	 layer.close(loadingIndex); 
	        	    	 layer.msg("更新访问权限成功！", {time:1000, icon:1, shift:2}, function(){});
	        	    	 //refleshPageInfo(rid);
	        	     }else{
	        	    	 layer.close(loadingIndex); 
	        	    	 layer.msg("更新访问权限失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
	        	     }
	        	     }
	        	});
            	
            	
            	
            }
            
        </script>
  </body>
</html>
