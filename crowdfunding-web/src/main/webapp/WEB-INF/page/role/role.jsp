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
   			$checkBoxes = []; 
   			 
			selAsked="";
   			useQuery(1);
   			
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
 		    
 		   $("#user_selbtn").click(function(){
 			   	 selAsked=$("#selAsked").val();
 			   	 useQuery(1);
 	         })
 		    
    	   $("#user_delesbtn").click(function(){
    		   deleteRoles();
           });
 		   
 		   $("#all_user_checked").click(function(){
 			  selAllCheckBox();
 			   
 		   })
 		   
 		   
 		   
    	 })
    
         $("tbody .btn-primary").click(function(){
             window.location.href = "edit";
         });
            
         $("tbody .btn-success").click(function(){
             window.location.href = "assignRole.html";
         });
         
         function selAllCheckBox(){
        	 var $cBoxed=$("#user_disp_tbody").find('input:checkbox');
        	 if($("#all_user_checked").prop("checked")){
    			 $($cBoxed).each(function(){
    			   $(this).prop("checked", true);
				 });
        	 }else{
        		 $($cBoxed).each(function(){
      			   $(this).prop("checked", false);
  				 });
        	 }
        	 
        	 
		}
         
         
         function deleteRoles() {
        	var $boxed = [];
         	var $boxes = $("#user_disp_tbody").find('input:checked');
				$($boxes).each(function(){
				$boxed.push($(this).attr("roleId"));
				});
				
				for(var i=0;i<$boxed.length;i++){
					  if($.inArray($boxed[i],$checkBoxes)<0){
						 $checkBoxes.push($boxed[i]);
					  }
					}
         	if ($checkBoxes.length == 0 ) {
                 layer.msg("请选择需要删除的角色信息", {time:2000, icon:5, shift:6}, function(){});
                 return;
         	} else {
     				layer.confirm("删除选择的角色信息, 是否继续",  {icon: 3, title:'提示'}, function(cindex){
     				//获取要删除的数据
     				
     				var msgStr=$checkBoxes.join(',');
     				// 删除选择的用户信息
     				$.ajax({
     					type : "POST",
     					url  : "${APP_PATH}/role/deleteRoles",
     					data : {
     						"rids":msgStr
     					},
     					beforeSend:function(){
                	   		loadingIndex = layer.msg('处理中', {icon: 16});
                	   	},
     					success : function(result) {
     						if ( result.ajaxCode ) {
     							 layer.close(loadingIndex); 
     	            	    	 useQuery(1);	 
     	            	    	 layer.msg("成功删除角色信息！", {time:1000, icon:1, shift:2}, function(){
     	            	    	 });
     						} else {
     							layer.close(loadingIndex); 
     	            	    	 layer.msg("操作失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
     						}
     					}
     				});
     				
     				layer.close(cindex);
     			}, function(cindex){
     			    layer.close(cindex);
     			});
         	}
         }
         
         function assignPowerBtn(rid){
        	 $.ajax({
      	   		 type: "POST",
      	   		 url: "${APP_PATH}/permission/doAssign4Role",
        	   	 data: {
        		 "rid":rid
        	   		}, 
        	   	 beforeSend:function(){
            	   		loadingIndex = layer.msg('处理中', {icon: 16});
            	   	},
        	   	 success: function(result){
        	     if(result.ajaxCode){
        	    	layer.close(loadingIndex);
             	    location.href="${APP_PATH}/role/assignPower";
        	     }else{
        	    	layer.close(loadingIndex); 
            	    layer.msg("操作失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});	
        	     }
        	     }
        	});
         }
         
         
         function useQuery(para){
        	 var $cdBoxes=$("#user_disp_tbody").find('input:checked');
        	 //判断复选框是否被选中，如果被选中先加入到数组中去
        	 $($cdBoxes).each(function(){
        		 if(($.inArray($(this).attr("roleId"),$checkBoxes))<0){
        		 $checkBoxes.push($(this).attr("roleId"));
        		 }
				});
        	 
     		$.ajax({
         	   type: "POST",
         	   url: "selAllPageRoles",
         	   data: {
         		   "pageNum":para,
         		  	"selAsked":selAsked
         	   },       		   
         	   success: function(result){
       		   		var str_body="";
       		   		var str_foot="";
           	     	if(result.success){
           	     		var roles=result.pageDispRoles;
           	     		var pageSize=result.pageSize;
           	   			$.each(roles,function(i,role){
           	   				str_body+="<tr pageSize='"+pageSize+"' id='user_pageInfo_tr'>"
           	   				str_body+="<td>"+(i+1)+"</td>"
           	   				str_body+="<td><input type='checkbox' roleId='"+role.rid+"'></td>"
           	   				str_body+="<td>"+role.name+"</td>"
           	   				str_body+="<td>"
           	   				str_body+="<button type='button' class='btn btn-success btn-xs' onclick='assignPowerBtn("+role.rid+")'><i class=' glyphicon glyphicon-check'></i></button>"
           	   				str_body+="<button type='button' class='btn btn-primary btn-xs' onclick='updInfoBtn("+role.rid+")'><i class=' glyphicon glyphicon-pencil'></i></button>"
           	   				str_body+="<button type='button' class='btn btn-danger btn-xs' onclick='delOneInfoBtn("+role.rid+")'><i class=' glyphicon glyphicon-remove'></i></button>"
           	   				str_body+="</td>"
           	   				str_body+="</tr>"
             	   		})
             	   		
           	   			str_foot+="<tr>"
 		     			str_foot+="<td colspan='6' align='center'>"
 						str_foot+="<ul class='pagination' >"
        	   			if(result.pageNum==1){
 						str_foot+="<li class='disabled'><a href='javascript:void(0)' >上一页</a></li>"
        	   			}else{
        	   				str_foot+="<li><a href='javascript:void(0)' onclick='useQuery("+(result.pageNum-1)+")'>上一页</a></li>"
        	   				}
           	   			for(var i=0;i<result.pageNums;i++){
           	   			if((i+1)==result.pageNum){
           	   			str_foot+="<li class='active'><a href='javascript:void(0)' >"+(i+1)
           	   			}else{
           	   			str_foot+="<li><a href='javascript:void(0)' onclick='useQuery("+(i+1)+")'>"+(i+1)	
           	   			}
           	   			if((i+1)==result.pageNum){
       	   				str_foot+="<span class='sr-only'>(current)</span></a></li>"
       	   				}else{
       	   				str_foot+="</a></li>";
       	   				}
           	   			}
 						if(result.pageNum>=result.pageNums){
 						str_foot+="<li class='disabled'><a href='javascript:void(0)' >下一页</a></li>"
 						}else{
 						str_foot+="<li><a href='javascript:void(0)'  onclick='useQuery("+(result.pageNum+1)+")'>下一页</a></li>"
 						}
 						str_foot+="</ul>"
 			 			str_foot+="</td>"
 		 				str_foot+="</tr>"
             	   		
             	     }else{
             	    	 str_body="<tr><td colspan='7' align='center'><ul class='pagination' ><li class='disabled'>未找到相关信息，请重新查询！</li></ul></td></tr>";
             	    	 str_foot="";
             	     }
             	     $("#user_disp_tbody").html(str_body);
             	     $("#user_disp_tfoot").html(str_foot);
             	     
             	  
             	     
             	     
             	     //判断前一页的复选框是否被选中，如若被选中过 则复原
             	     var $cdBoxes2=$("#user_disp_tbody").find('input:checkbox');
        			 $($cdBoxes2).each(function(){
        			 if(($.inArray($(this).attr("roleId"),$checkBoxes))>=0){
        			   $(this).prop("checked", true);
        			 }
					 });
        			 
        			 var $cdBoxes3=$("#user_disp_tbody").find('input:checked');
        			//判断复选框是否全被选中，如果是全选按钮被选中，否则全选按钮为不选中
                	 if($cdBoxes3.length==$("#user_pageInfo_tr").attr("pageSize")){
                		 $("#all_user_checked").prop("checked",true);
                	 }else{
                		 $("#all_user_checked").prop("checked",false);
                	 }
        			 
             	    }
         	});
   	
         }
         
         function updInfoBtn(rid){
        	 $.ajax({
      	   		 type: "POST",
      	   		 url: "${APP_PATH}/role/roleEdit",
        	   	 data: {
        		 "rid":rid
        	   		}, 
        	   	 beforeSend:function(){
            	   		loadingIndex = layer.msg('处理中', {icon: 16});
            	   	},
        	   	 success: function(result){
        	     if(result.ajaxCode){
        	    	 layer.close(loadingIndex); 
        	    	 location.href="${APP_PATH}/role/role_Edit"
        	     }else{
        	    	 layer.close(loadingIndex); 
        	    	 layer.msg("操作失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
        	     }
        	     }
        	});
		}
         
         
         
         function delOneInfoBtn(rid){
        	 layer.confirm("是否删除该用户信息？",  {icon: 3, title:'提示'}, function(cindex){
 			    layer.close(cindex);
 			    
        		 $.ajax({
          	   		 type: "POST",
          	   		 url: "${APP_PATH}/role/delByOne",
            	   	 data: {
            		 "rid":rid
            	   		}, 
            	   	 beforeSend:function(){
                	   		loadingIndex = layer.msg('处理中', {icon: 16});
                	   	},
            	   	 success: function(result){
            	     if(result.ajaxCode){
            	    	 layer.close(loadingIndex); 
            	    	 useQuery(1);	 
            	    	 layer.msg("成功删除用户信息！", {time:1000, icon:1, shift:2}, function(){
            	    	 });
            	     }else{
            	    	 layer.close(loadingIndex); 
            	    	 layer.msg("操作失败，请稍后重试！", {time:1000, icon:5, shift:6}, function(){});
            	     }
            	     }
            	});
        		 
 				}, function(cindex){
 			    	layer.close(cindex);
        	 	return;
 				});
        	 
         }
         
         
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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 角色信息</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件" id="selAsked">
    </div>
  </div>
  <button type="button" class="btn btn-warning" id="user_selbtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" id="user_delesbtn" style="float:right;margin-left:10px;" ><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/role_Add'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
          <form id="userForm">
            <table class="table  table-bordered">
              <thead>
                <tr>
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="all_user_checked">
				  </th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody id="user_disp_tbody">
              	
              
                
                
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
