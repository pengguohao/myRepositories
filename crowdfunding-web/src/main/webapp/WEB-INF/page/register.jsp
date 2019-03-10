<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎注册</title>
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>
	.yanzhengImg{
	width: 40px;
	height: 40px"
	}
	</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container" >

      <form class="form-signin" role="form" method="post" id="login_form" action="toLogin">
      	<br>
      	<br>
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
        <table width="780px" style="position: relative;left: -100px;border-collapse:   separate;   border-spacing:   10px; top: 30px">
        	<tr>
        		<td width="100px"><h4>登录账号</h4></td>
        		<td width="250px">
        		<input type="text" class="form-control" id="regusername" placeholder="请输入登录账号" autofocus>
				</td>
        		<td width="200px">&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>由字母、数字、下划线组成<br>&nbsp;&nbsp;字母开头，6-16位</span></td>
        		<td width="200px"><span id="regUsernameSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>登录密码</h4></td>
        		<td width="250px">
        		<input type="password" class="form-control" id="regpassword" placeholder="请输入登录密码" autofocus>
				</td>
        		<td width="200px">&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>由字母、数字、下划线 组成<br>&nbsp;&nbsp;6-16位</span></td>
        		<td><span id="regPasswordSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>确认密码</h4></td>
        		<td width="250px">
        		<input type="password" class="form-control" id="repeatpassword" placeholder="请再次输入密码" autofocus>
				</td>
        		<td colspan="2"><span id="regPasswordSpan2"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>用户昵称</h4></td>
        		<td width="250px">
        		<input type="text" class="form-control" id="regname" placeholder="请输入用户昵称" autofocus>
				</td>
        		<td width="200px">&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>由中文汉字组成，<br>&nbsp;&nbsp;2-6位</span></td>
        		<td><span id="regPerNameSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>注册邮箱</h4></td>
        		<td width="250px">
        		<input type="text" class="form-control" id="regEmail" placeholder="请输入邮箱" autofocus>
				</td>
        		<td colspan="2"><span id="regEmailSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>验证码</h4></td>
        		<td>
        		<input type="text" class="form-control" id="yanzCode" name="yzCode" placeholder="请输入验证码" style="width:118px;float: left;"/>
				<img id="reg_ImgCode" src="" class="form-control" style="width: 118px;height: 44px;padding: 0px;margin-top: 10px;top: -10px;float: left;left: 10px">
				</td>
				<td>
				<img id="regyzCodeSpan" src="" style="position:relative; top: -5px">
				</td>
        		<td></td>
        	</tr>
        	
        	<tr>
        		<td></td>
        		<td><label>
          		<a href="${APP_PATH}/resetPwd">忘记密码？</a>
          		</label>
          		<label style="float:right">
            	<a href="${APP_PATH}/login">已有账号</a>
          		</label>
				</td>
				<td></td>
				<td></td>
        	</tr>
        	
        	<tr>
        		<td></td>
        		<td style="text-align: center">
        		<button type="button" class="btn btn-success" id="regGoRegbtn"><i class="glyphicon glyphicon-plus"></i> 注册</button>
        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-danger" id="regResetbtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</td>
				<td></td>
				<td></td>
        	</tr>
        	
        </table>
		  
        <div class="checkbox">

    <script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/layer/layer.js"></script>
    <script>
    
    
   	$(function(){
   		usernameFlag=false;
   		passwordFlag=false;
   		repeatpwdFlag=false;
   		emailFlag=false;
   		pernameFlag=false;
   		yanzhengFlag=false;
   		$("#reg_ImgCode").attr("src","yanzCode?dowhat=register&time="+new Date());
   		$("#yanzCode").blur( function(){
   			yanzhengFlag=checkCodeRrturnBoolean();
   		});
   		
   		$("#reg_ImgCode").click(function(){
   			refleshCode();
   		});
   		
   		$("#regusername").blur( function(){
   			usernameFlag=checkForward("regusername",/^[a-zA-z]\w{5,15}$/,"登录账号","regUsernameSpan","${APP_PATH}/check/checkName");
   		});
   		
   		$("#regpassword").blur( function(){
   			passwordFlag=checkPassword("regpassword",/^(\w){6,16}$/,"登陆密码","regPasswordSpan");
   		});
   		
   		$("#repeatpassword").blur( function(){
 				if($("#repeatpassword").val()!=$("#regpassword").val()){
 					var str="<span style='color: red;font-size: 15px;font-style: blod'>两次输入的密码不一致，请重新确认！</span>"
          			$("#regPasswordSpan2").html(str);
 					repeatpwdFlag=false;
 				}else{
 					if($("#repeatpassword").val()!=""){
 					var str="<span style='color: green;font-size: 15px;font-style: blod'>两次输入的密码一致！</span>"
 		          	$("#regPasswordSpan2").html(str);
 					repeatpwdFlag=true;
 					}else{
 						$("#regPasswordSpan2").html("");
 						repeatpwdFlag=false;
 					}
 				}
   		});
   		
   		$("#regname").blur( function(){
   			pernameFlag=checkForward("regname",/^[\u4E00-\u9FA5]{2,6}$/,"用户昵称","regPerNameSpan","${APP_PATH}/check/checkPName");
   			
   			
   		});
   		
   		$("#regEmail").blur( function(){
   			emailFlag=checkForward("regEmail",/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/,"邮箱","regEmailSpan","${APP_PATH}/check/checkEmail");
   			
   			
   		});
   		
   		$("#regGoRegbtn").click(function(){
   			doRegister();
   		});
   		
   		$("#regResetbtn").click(function(){
   			resetRegisterInfo();
   		});
   		
   		
   		
   	})
            //校验函数
            function checkForward(inputID,Reg,useInfo,showID,url){
            	var naFlag=false;
            	var pname=$("#"+inputID).val();
    			if(pname==""){
    				var puname="<span style='color: red;font-size: 15px;font-style: blod'>"+useInfo+"不能为空！</span>"
            		$("#"+showID).html(puname);
    				naFlag=false;
    			}else{
        	　　	if(Reg.test(pname)){
        		if(check(pname,url,useInfo,showID)){
        				var puname="<span style='color: green;font-size: 15px;font-style: blod'>"+useInfo+"合法！</span>"
	            		$("#"+showID).html(puname);
        				naFlag=true;
            		}else{
            			naFlag=false;
            		}
        	　　	}else{
        			var puname="<span style='color: red;font-size: 15px;font-style: blod'>"+useInfo+"非法！</span>"
            		$("#"+showID).html(puname);
        			naFlag=false;
        		}
    		}
    			return naFlag;
            }
   	
   	
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
		    	    	var uname="<span style='color: red;font-size: 15px;font-style: blod'>"+spanName+"已存在！</span>"
		    	    	$("#"+spanID).html(uname);
		    	    	ajaxFlag=false;
		    	     }else{
		    	    	var uname="<span style='color: red;font-size: 15px;font-style: blod'>"+spanName+"非法！</span>"
		    	    	$("#"+spanID).html(uname);
		    	    	ajaxFlag=false;
		    	    	}
		    	     }
		    	});
		    	return ajaxFlag;
		    }
   	
		   //校验密码的函数
           function checkPassword(inputID,Reg,useInfo,showID){
           	var naFlag=false;
           	var pname=$("#"+inputID).val();
   			if(pname==""){
   				var puname="<span style='color: red;font-size: 15px;font-style: blod'>"+useInfo+"不能为空！</span>"
           		$("#"+showID).html(puname);
   				naFlag=false;
   			}else{
       	　　	if(Reg.test(pname)){
       				var puname="<span style='color: green;font-size: 15px;font-style: blod'>"+useInfo+"合法！</span>"
	            		$("#"+showID).html(puname);
       				naFlag=true;
           		
       	　　	}else{
       			var puname="<span style='color: red;font-size: 15px;font-style: blod'>"+useInfo+"非法！</span>"
           		$("#"+showID).html(puname);
       			naFlag=false;
       			}
   			}
   			return naFlag;
           }
		   
           function doRegister() {
           	var username=$("#regusername").val();
           	var password=$("#regpassword").val();
           	var repeatpwd=$("#repeatpassword").val();
           	var pername=$("#regname").val();
           	var regemail=$("#regEmail").val();
           	var yanZhengCode=$("#yanzCode").val();
           	if(username==""){
           		layer.msg("登录账号不能为空！",{time:1500, icon:5, shift:6}, function(){
           			document.querySelector("#regusername").focus();
           		});
           		return;
           	}else if(password==""){
           		layer.msg("登录密码不能为空！",{time:1500, icon:5, shift:6}, function(){
           			document.querySelector("#regpassword").focus();
           		});
           			return;
           	}else if(pername==""){
           		layer.msg("用户昵称不能为空！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#regname").focus();
           		});
           			return;
           	}else if(regemail==""){
           		layer.msg("注册邮箱不能为空！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#regname").focus();
           		});
           			return;
           	}else if(yanZhengCode==""){
           		layer.msg("验证码不能为空！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#regEmail").focus();
           		});
           			return;
           	}else if(repeatpwd!=password){
           		layer.msg("两次输入的密码不一致，请重新确认！",{time:1500, icon:5, shift:6}, function(){
           		});
           			return;
           	}else if(!yanzhengFlag){
           		yanzhengFlag=checkCodeRrturnBoolean();
           		return;
           	}else if(usernameFlag&&passwordFlag&&repeatpwdFlag&&emailFlag&&pernameFlag){
           		var loadingIndex = layer.msg('正在注册', {icon: 16});
                $.ajax({
                	   type: "POST",
                	   url: "${APP_PATH}/check/doRegister",
                	   data: {
                	   "username":username,
                	   "password":password,
                	   "repeatpwd":repeatpwd,
                	   "pname":pername,
                	   "email":regemail,
                	   "yanZhengCode":yanZhengCode
                		},       		   
                	   success: function(msg){
                	     if(msg.numCode==1){
                	    	 layer.close(loadingIndex);
                	    	 layer.confirm("【"+pername+"】,恭喜您成功注册为本站会员,是否立即跳转到登陆页面？",  {icon: 3, title:'注册成功'}, function(cindex){
                				    layer.close(cindex);
                				    resetRegisterInfo();
                				    location.href="${APP_PATH}/login" 
                				}, function(cindex){
                				    layer.close(cindex);
                				    resetRegisterInfo();
                				});
                	     }else if(msg.numCode==0){
                	    	 layer.close(loadingIndex);
                	    	 layer.msg("注册失败，请稍后重试！",{time:1500, icon:5, shift:6}, function(){
                	    		 refleshCode();
                	    		});
                	     }else if(msg.numCode==2){
                	    	 layer.close(loadingIndex);
                	    	 layer.msg("注册信息不通过，请重新确认！",{time:1500, icon:5, shift:6}, function(){
                	    		 refleshCode();
                	    	});
                	     }
                	   }
                	});
           		
           	}else{
           		
           		layer.msg("注册信息不通过，请重新确认！",{time:1500, icon:5, shift:6}, function(){
           		});
           		$("#reg_ImgCode").attr("src","yanzCode?dowhat=register&time="+new Date());
	    		 yanzhengFlag=false;
	    		 console.log("1==>"+yanzhengFlag);
           	}
           	
           }
           function resetRegisterInfo(){
        	    $("#regusername").val("");
                $("#regpassword").val("");
              	$("#repeatpassword").val("");
              	$("#regname").val("");
              	$("#regEmail").val("");
              	$("#yanzCode").val("");
              	$("#regUsernameSpan").html("");
              	$("#regPasswordSpan").html("");
              	$("#regPasswordSpan2").html("");
              	$("#regPerNameSpan").html("");
              	$("#regEmailSpan").html("");
              	$("#regyzCodeSpan").attr("src","");
              	$("#regyzCodeSpan").removeClass("yanzhengImg");
              	$("#reg_ImgCode").attr("src","yanzCode?dowhat=register&time="+new Date());
              	usernameFlag=false;
           	    passwordFlag=false;
           	    repeatpwdFlag=false;
           	    emailFlag=false;
           	    pernameFlag=false;
           	    yanzhengFlag=false;
           }
		   
		   function refleshCode(){
			   yanzhengFlag=false;
			   $("#reg_ImgCode").attr("src","yanzCode?dowhat=register&time="+new Date());
			   $("#regyzCodeSpan").attr("src","");
	           $("#regyzCodeSpan").removeClass("yanzhengImg");
		   }
		   
		   function checkCodeRrturnBoolean(){
			    var codeFlag;
	   			var loginCode=$("#yanzCode").val();
	   			var dowhat="register";
	   	        $.ajax({
	         	   type: "POST",
	         	   url: "${APP_PATH}/check/yan_code",
	         	   async:false,
	         	   data: {
	         	   "loginCode":loginCode,
	         	   "dowhat":dowhat
	         		},       		   
	         	   success: function(msg){
	         	     if(msg.ajaxCode){
	         	    	$("#regyzCodeSpan").addClass("yanzhengImg");
	            		$("#regyzCodeSpan").attr("src","${APP_PATH}/images/yangzlaugh.png");
	            		codeFlag=true;
	         	    	
	         	     }else{
	         	    	$("#regyzCodeSpan").addClass("yanzhengImg");
	            		$("#regyzCodeSpan").attr("src","${APP_PATH}/images/yangzcry.png");
	            		codeFlag=false;
	         	    	}
	         	    }
	         	});
	   			return codeFlag;
	   		}
		   
   	
    </script>	
</body>
</html>