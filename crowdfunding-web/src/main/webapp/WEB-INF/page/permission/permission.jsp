<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/css/common.css"/>
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
           <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 许可维护</a></div>
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
              <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限菜单列表 <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
			  <div class="box">
			  <div id="dialogBg"></div>
			  <div id="dialog" class="animated" style="width:600px;height: 300px;top:300px;left: 750px">
			  <img class="dialogIco" width="50" height="50" src="${APP_PATH}/images/pencil_ico.png" alt="" />
			  <div class="dialogTop" style="width:500px">
				<a href="javascript:;" class="claseDialogBtn">关闭</a>
			  </div>
			  <form action="" method="post" id="editForm">
			  <table style="text-align:left;border-collapse:   separate;   border-spacing:   10px;">
			  
			  <tr></tr>
			  <tr></tr>
			  <br>
			  <tr>
			  <td style="width: 200px;padding-left: 130px">
			  <font color="#ff0000">* </font>父菜单：
			  </td>
			  <td style="width: 300px">
			  <select id="menuOpts">

			  </select>
			  </td>
			  <td td style="width: 100px"></td>
			  </tr>
			  <td style="width: 200px;padding-left: 130px">
			  <li><font color="#ff0000">* </font>权限许可名称：
			  </td>
			  <td>
			  <input type="text" id="menuName" value="" class="ipt" />
			  </td>
			  <td></td>
			  <tr >
			  <td style="width: 200px;padding-left: 130px"><font color="#ff0000">&nbsp; </font>权限图标地址：</td>
			  <td><input type="text" id="menuIconUrl" value="" class="ipt" /></label></td>
			  <td></td>
			  </tr>
			  <tr>
			  <td colspan="3">
			  <button type="button" class="btn btn-danger" id="close2submit" style="margin-left:60px;"><i class=" glyphicon glyphicon-remove"></i>取消</button>
			  <button type="button" class="btn btn-primary" id="true2submit" style="float:left;margin-left:180px;" onclick=""><i class="glyphicon glyphicon-plus"></i> 新增</button>
			  </td>
			  </tr>
			  </table>
			  </form>
		      </div>
			  </div>
                  <ul id="treeDemo" class="ztree"></ul>
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
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>没有默认类</h4>
				<p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
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
	<script src="${APP_PATH}/ztree/jquery.ztree.all-3.5.min.js"></script>
	<script src="${APP_PATH}/layer/layer.js"></script>
        <script type="text/javascript">
        
        var w,h,className;
        function getSrceenWH(){
        	w = $(window).width();
        	h = $(window).height();
        	$('#dialogBg').width(w).height(h);
        }

        window.onresize = function(){  
        	getSrceenWH();
        }  
        $(window).resize();   
        
        
        
        
        
        
            $(function () {
            	
            	
            		getSrceenWH();

            		
            		//关闭弹窗
            		$('.claseDialogBtn').click(function(){
            			closeDialogFun();
            			clearInputValue();
            		});
            	
            	
            	
            	$("#true2submit").click(function(){
            		if(addMenuFun($("#menuOpts option:selected").val(),$("#menuName").val(),$("#menuIconUrl").val())){
            			loadTreeFunByIni();
	            		closeDialogFun();
	            		clearInputValue();
            		}	
            	})
            	
            	$("#close2submit").click(function(){
            		
            		closeDialogFun();
        			clearInputValue();
            		
            	})
            	
            	
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
				
            	loadTreeFunByIni();
            });
			
			function updMenuFun(mid,goUrl,doInfo,name) {
				var flag=false;
				$.ajax({
	      	   		 type: "POST",
	      	   		 url: goUrl,
	        	   	 data: {
	        		 "mid":mid,
	        		 "name":name,
	        	   		}, 
	        	   	 async:false,
	        	   	 beforeSend:function(){
	            	   		loadingIndex = layer.msg('处理中', {icon: 16});
	            	   	},
	        	   	 success: function(result){
	        	     if(result.ajaxCode){
	        	    	 flag=true;
	        	    	 layer.close(loadingIndex); 
	        	     }else{
	        	    	 flag=false;
	        	    	 layer.close(loadingIndex); 
	        	    	 layer.msg("权限许可"+doInfo+"失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
	        	     }
	        	     }
	        	});
				return flag;
			};
			
			function openDialogFun(){
    			className = "bounceIn";
    			$('#dialog').removeAttr('class').addClass('animated '+className+'').fadeIn();
    		}
			
			function closeDialogFun(){
				$('#dialogBg').fadeOut(300,function(){
    				$('#dialog').addClass('bounceOutUp').fadeOut();
    			});
				
			}
			
			function selectedFun(id) {
				$("#menuOpts option").each(function(){
					if($(this).val()==id){
						$(this).prop("selected",true);
					}
				});
			}
			
			function clearInputValue(){
				$("#menuName").val("");
        		$("#menuIconUrl").val("");
			}

			//增
			function addMenuFun(pid,name,icon) {
				var flag=false;
				$.ajax({
         	   			type: "POST",
         	   		 	url: "${APP_PATH}/permission/addMenu4Manager",
           	   	 		data: {
           	   		 		"name":name,
         	   		 		"pid":pid,
         	   		 		"icon":icon
           	   	 			  }, 
           	  			async:false,
     	   	 			beforeSend:function(){loadingIndex = layer.msg('处理中', {icon: 16});},
     	   				success: function(result){
	     	     		if(result.ajaxCode){
		     	    		flag=true;
		     	    	 	layer.close(loadingIndex); 
		     	    		layer.msg("权限许可添加成功！", {time:1000, icon:1, shift:2}, function(){});
	     	     		}else{
		     	    	 	flag=false;
		     	    	 	layer.close(loadingIndex); 
		     	    	 	layer.msg("权限许可添加失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
	     	     		 }
     	     		    }
           			  });
				return flag;
			};
			

			
			//封装实现树的封装
			function loadTreeFunByIni(){
				
				var setting = {
						view: {
							selectedMulti: false,
							addDiyDom: function(treeId, treeNode){
								var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
								if ( treeNode.icon ) {
									icoObj.removeClass("button ico_docu ico_open").addClass("fa fa-fw " + treeNode.icon).css("background","");
								}
	                            
							},
							addHoverDom: addHoverDom,
							removeHoverDom: removeHoverDom
						},
						async: {
							enable: false,
							url:"tree.txt",
							autoParam:["id", "name=n", "level=lv"]
						},
						check: {
							enable: false,
							chkStyle: "checkbox"
						},
						edit: {
							enable: true,
							editNameSelectAll: true,
							showRemoveBtn: showRemoveBtn,
							showRenameBtn: true
						},
						callback: {
							beforeRename: bfRename,
						    onRename: onRename,
						    beforeRemove: beforeRemove,
							onRemove:true
							
						},
						data: {
							key: {
								url: "xUrl"
							}
						}

					};
				
				var d = {};
				$.ajax({
          	   		 type: "POST",
          	   		 url: "${APP_PATH}/permission/sellAllMenu",
            	   	 data: {}, 
            	   	 async:false,
            	   	 success: function(result){
            	   		var str="";
            	   		str+="<option value='"+result[0].id+"' disabled='disabled'>"+result[0].name+"</option>";
            	   		for(var i=0;i<result[0].children.length;i++){
            	   		str+="<option value='"+result[0].children[i].id+"' disabled='disabled'>"+result[0].children[i].name+"</option>";
            	   		}
            	   		$("#menuOpts").html(str);	 
            	     	d=result;
            	     }
            	}); 
			$.fn.zTree.init($("#treeDemo"), setting, d);
			}
			
			//回调函数bfRename
			function bfRename(treeId, treeNode, newName, isCancel) {
		    //新名称为空时提示名称不能为空
		    if (newName.length == 0) {
		        setTimeout(function () {
		            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		            zTree.cancelEditName();
		            layer.alert("权限许可名称不能为空!", function(index){
					    // 回调方法
					    layer.close(index);
					});
		            
		        }, 200);
		        return false;
		    }
		    var Cnodes = getPeerNodes(treeNode);
			
		    //获取当前节点的同级兄弟节点，不包含本节点
		    function getPeerNodes(targetNode) {
		        	if (targetNode == null) {
		            return null;
		        	} else {
		            if (targetNode.getParentNode() != null) {
		                return targetNode.getParentNode().children;
		            }
		            return null;
		        }
		   			}
		    
		  	  	for (var n in Cnodes) {
		        if (Cnodes[n].name == newName) {
		            if (Cnodes[n].id != treeNode.id) {
		           		setTimeout(function () {
		                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		                zTree.cancelEditName();
		                layer.alert("待更新的权限许可名称已存在，请重新编辑！", function(index){
						    // 回调方法
						    layer.close(index);
						});
		            	}, 200)
		            	return false;
		            }else{
		            		setTimeout(function () {
			                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			                zTree.cancelEditName();
			            	}, 10)
			            	return true;
		            }
		        }
		   		}
		    
		    return updMenuFun(treeNode.id,"${APP_PATH}/permission/renameMenu","重命名",newName);
			};
			
			function onRename(e, treeId, treeNode, isCancel) {
			    if (isCancel) return;
			};
			
			function showRemoveBtn(treeId, treeNode) {
				if(treeNode.level == 0){
					return false;
				}else if(treeNode.level == 1){
					if(treeNode.children.length == 0){
					return true;
					}else{
					return false;
					}
				}else if(treeNode.level == 2){
					if(treeNode.children.length == 0){
						return true;
						}else{
						return false;
						}
				}else{
					return false;
				}
			}
			
			function addHoverDom(treeId, treeNode) {
				var sObj = $("#" + treeNode.tId + "_span");
				if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0){
					return;
				}
				
				if ( treeNode.level == 0 ) {
					addStr = "<span class='fa fa-fw fa-plus rbg' id='addBtn_" + treeNode.tId
					+ "' title='add menu' ></span>";
				} else if ( treeNode.level == 1 ) {
					addStr = "<span class='fa fa-fw fa-plus rbg' id='addBtn_" + treeNode.tId
					+ "' title='add menu'  ></span>";}else{
						addStr="";
					}
				sObj.after(addStr);
				var btn = $("#addBtn_"+treeNode.tId);
				 if (btn){
					btn.bind("click", function(){
						openDialogFun();
						//var  i=document.getElementById( 'sel' ).options[document.getElementById( 'sel' ).selectedIndex].value;
						selectedFun(treeNode.id);
						return false;
					});
			  	}
			};
			function removeHoverDom(treeId, treeNode) {
				$("#addBtn_"+treeNode.tId).unbind().remove();
			};
			
			function beforeRemove(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.selectNode(treeNode,false,false);
				if(confirm("确认删除权限许可 <-- " + treeNode.name + " -->吗？")){
					return updMenuFun(treeNode.id,"${APP_PATH}/permission/delMenu","删除","默认");
				}else{
					return false;
				}
			}
        </script>
  </body>
</html>
