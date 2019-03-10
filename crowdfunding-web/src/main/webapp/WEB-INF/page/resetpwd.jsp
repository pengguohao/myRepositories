<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码修改</title>
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
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-edit"></i>&nbsp;&nbsp;&nbsp;密码修改</h2>
        <table width="780px" style="position: relative;left: -100px;border-collapse:   separate;   border-spacing:   10px; top: 30px">
        	<tr>
        		<td width="100px"><h4>登陆账号</h4></td>
        		<td width="250px">
        		<input type="text" class="form-control" id="resetusername" placeholder="请输入要修改密码的登陆账号" autofocus>
				</td>
        		<td width="200px"><span id="resetusernameSpan"></td>
        		<td width="200px"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>登录密码</h4></td>
        		<td width="250px">
        		<input type="password" class="form-control" id="resetpassword" placeholder="请输入要希望重置的登录密码" autofocus>
				</td>
        		<td width="200px">&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>由字母、数字、下划线 组成<br>&nbsp;&nbsp;6-16位</span></td>
        		<td><span id="resetPasswordSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>确认密码</h4></td>
        		<td width="250px">
        		<input type="password" class="form-control" id="resetpassword2" placeholder="请再次确认登录密码" autofocus>
				</td>
        		<td colspan="2"><span id="resetPasswordSpan2"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>注册邮箱</h4></td>
        		<td width="250px">
        		<input type="text" class="form-control" id="regEmail" placeholder="请输入注册邮箱" autofocus>
				</td>
				<td width="200px">&nbsp;&nbsp;<span style='color: green;font-size: 14px;font-style: blod'>注册账号时使用的邮箱</span></td>
        		<td><span id="regEmailSpan"></span></td>
        	</tr>
        	
        	<tr>
        		<td width="80px"><h4>邮箱验证码</h4></td>
        		<td>
        		<input type="text" class="form-control" id="yEmailCode" name="yzCode" placeholder="邮箱验证码" style="width:100px;float: left;"/>
				<button type="button" class="btn btn-info" id="regSendEmailbtn" style="width:100px;float: left;position: relative;top: 2px;left: 45px;height: 42px;"><span id="getEmailSpan" style="font: bold;font-size: 16px;position: relative;left: -2px">获取验证码</span></button>
				</td>
				<td>
				<img id="regyzCodeSpan" src="" style="position:relative; top: 0px">
				</td>
        		<td></td>
        	</tr>
        	
        	<tr>
        		<td></td>
        		<td style="text-align: center">
        		<button type="button" class="btn btn-success" id="resetGoResetbtn"><i class="glyphicon glyphicon-plus"></i> 确认</button>
        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="btn btn-danger" id="resetResetbtn" ><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
   		yanzhengFlag=false;
   		$("#resetusername").blur( function(){
   			usernameFlag=checkForward("resetusername",/^[a-zA-z]\w{5,15}$/,"登录账号","resetusernameSpan","${APP_PATH}/check/checkName4ResetPwd");
   		});
   		
   		$("#resetpassword").blur( function(){
   			passwordFlag=checkPassword("resetpassword",/^(\w){6,16}$/,"登陆密码","resetPasswordSpan");
   		});
   		
   		$("#resetpassword2").blur( function(){
 				if($("#resetpassword2").val()!=$("#resetpassword").val()){
 					var str="<span style='color: red;font-size: 15px;font-style: blod'>两次输入的密码不一致，请重新确认！</span>"
          			$("#resetPasswordSpan2").html(str);
 					repeatpwdFlag=false;
 				}else{
 					if($("#resetpassword").val()!=""){
 					var str="<span style='color: green;font-size: 15px;font-style: blod'>两次输入的密码一致！</span>"
 		          	$("#resetPasswordSpan2").html(str);
 					repeatpwdFlag=true;
 					}else{
 						$("#resetPasswordSpan2").html("");
 						repeatpwdFlag=false;
 					}
 				}
   		});
   		
   		
   		$("#regEmail").blur( function(){
   			emailFlag=cheEmailByUsername(/^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/);
   		});
   		
   		$("#regSendEmailbtn").click(function(){
   			regSendEmail4DoReset();
   		});
   		
   		$("#yEmailCode").blur(function(){
   			yanzhengFlag=doCheckEmailCode4DoReset();
   		});
   		
   		
   		$("#resetGoResetbtn").click(function(){
   			doReSetPwd();
   		});
   		
   		$("#resetResetbtn").click(function(){
   			refleshInfo();
   		});
   		
   		
   		
   	})

   	
   		//校验邮箱信息
   		function cheEmailByUsername(Reg){
   			var EmaiC=false;
   			var email=$("#regEmail").val();
   			if($("#resetusername").val()==""){
   				var puname="<span style='color: red;font-size: 15px;font-style: blod'>登陆账号信息不能为空！</span>";
        		$("#regEmailSpan").html(puname);
        		EmaiC=false
   			}else if(email==""){
   				var puname="<span style='color: red;font-size: 15px;font-style: blod'>注册邮箱信息不能为空！</span>";
        		$("#regEmailSpan").html(puname);
        		EmaiC=false;
   			}else if(!Reg.test(email)){
   				console.log("111")
   				var puname="<span style='color: red;font-size: 15px;font-style: blod'>邮箱信息错误，请重新确认！</span>";
        		$("#regEmailSpan").html(puname);
        		EmaiC=false;
   			}else{
   				var username=$("#resetusername").val();
   				$.ajax({
   					type:"POST",
   					url:"${APP_PATH}/check/checkEmail4ResetPwd",
   					async:false,
   					data:{
   						"username":username,
   						"email":email
   					},
   					success:function(result){
   						EmaiC=result.ajaxCode;
   						if(result.ajaxCode){
   							var puname="<span style='color: green;font-size: 15px;font-style: blod'>邮箱信息正确！</span>";
   			        		$("#regEmailSpan").html(puname);
   						}else{
   							console.log("222")
   							var puname="<span style='color: red;font-size: 15px;font-style: blod'>邮箱信息错误，请重新确认！</span>";
   			        		$("#regEmailSpan").html(puname);
   						}
   					}
   				})
   			}
   			return EmaiC;
   		}
   	
	   	//发送验证码函数
	   	function regSendEmail4DoReset(){
	   		refleshCode();
	   		var toadress=$("#regEmail").val();
	   		if(emailFlag){
	   			var loadingIndex = layer.msg('正在给邮箱发送信息', {icon: 16});
	   			$.ajax({
   					type:"POST",
   					url:"${APP_PATH}/check/buildEmailCode",
   					async:false,
   					data:{
   						"toAdress":toadress
   					},
   					success:function(result){
   						EmaiC=result.ajaxCode;
   						if(result.ajaxCode){
   							layer.close(loadingIndex);
   							i=120;
   							$("#regSendEmailbtn").attr("disabled","disabled");
   							setInterval(function(){
   								if(i==0){
   									$("#regSendEmailbtn").removeAttr("disabled");
   									$("#getEmailSpan").html("获取验证码");
   									clearInterval();
   								}else{
   								$("#getEmailSpan").html(i);
   								i-=1;
   								}
   							},1000);
   						}else{
   							layer.close(loadingIndex);
   							layer.alert("系统繁忙，请稍后重试", function(index){
   							    layer.close(index);
   							});
   						}
   					}
   				})
   				
	   		}else{
	   			layer.alert("请先输入正确的邮箱信息", function(index){
				    layer.close(index);
				});
	   		}
	   	}
   	
	   	function doCheckEmailCode4DoReset(){
	   		var EmailCode=$("#yEmailCode").val();
	   	 	var codeFlag;
			var dowhat="register";
	        $.ajax({
      	   	type: "POST",
      	   	url: "${APP_PATH}/check/checkEmailCode",
      	   	async:false,
      	   	data: {
      	   	"EmailCode":EmailCode,
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
	   	};
   	
   	
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
        				var puname="<span style='color: green;font-size: 15px;font-style: blod'>"+useInfo+"正确！</span>"
	            		$("#"+showID).html(puname);
        				naFlag=true;
            		}else{
            			naFlag=false;
            		}
        	　　	}else{
        			var puname="<span style='color: red;font-size: 15px;font-style: blod'>"+useInfo+"错误，请重新确认！</span>"
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
		    	    	var uname="<span style='color: red;font-size: 15px;font-style: blod'>"+spanName+"错误，请重新确认！</span>"
		    	    	$("#"+spanID).html(uname);
		    	    	ajaxFlag=false;
		    	     }else{
		    	    	var uname="<span style='color: red;font-size: 15px;font-style: blod'>"+spanName+"错误，请重新确认！</span>"
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
		   
           function doReSetPwd() {
           	var username=$("#resetusername").val();
           	var password=$("#resetpassword").val();
           	var repeatpwd=$("#resetpassword2").val();
           	var regemail=$("#regEmail").val();
           	var yanZhengCode=$("#yEmailCode").val();
           	if(username==""){
           		layer.msg("登录账号不能为空！",{time:1500, icon:5, shift:6}, function(){
           			document.querySelector("#resetusername").focus();
           		});
           		return;
           	}else if(password==""){
           		layer.msg("登录密码不能为空！",{time:1500, icon:5, shift:6}, function(){
           			document.querySelector("#resetpassword").focus();
           		});
           			return;
           	}else if(regemail==""){
           		layer.msg("注册邮箱不能为空！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#regEmail").focus();
           		});
           			return;
           	}else if(yanZhengCode==""){
           		layer.msg("邮箱验证码不能为空！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#yEmailCode").focus();
           		});
           			return;
           	}else if(repeatpwd!=password){
           		layer.msg("两次输入的密码不一致，请重新确认！",{time:1500, icon:5, shift:6}, function(){
           		});
           			return;
           	}else if(!yanzhengFlag){
           		layer.msg("请先填写正确的邮箱验证码！",{time:1500, icon:5, shift:6}, function(){
               		document.querySelector("#yEmailCode").focus();
           		});
           		return;
           	}else if(usernameFlag&&passwordFlag&&repeatpwdFlag&&emailFlag){
           		var loadingIndex = layer.msg('正在修改密码，请勿离开', {icon: 16});
                $.ajax({
                	   type: "POST",
                	   url: "${APP_PATH}/check/resetPassword4User",
                	   data: {
                	   "username":username,
                	   "password":password,
                	   "repeatpwd":repeatpwd,
                	   "email":regemail,
                	   "yanZhengCode":yanZhengCode
                		},       		   
                	   success: function(msg){
                	     if(msg.numCode==1){
                	    	 layer.close(loadingIndex);
                	    	 layer.msg("密码成功重置，请牢记密码，切勿泄露以造成重大损失",{time:1500, icon:1, shift:2}, function(cindex){
                				    layer.close(cindex);
                				    $.ajax({
                				    	type:"POST",
                				    	url:"resetEmailCode",
                				    	data:{},
                				    	success:function(){}
                				    })
                				    refleshInfo();
                				});
                	     }else if(msg.numCode==0){
                	    	 layer.close(loadingIndex);
                	    	 layer.msg("密码修改失败，请稍后重试！",{time:1500, icon:5, shift:6}, function(){
                	    		});
                	     }else if(msg.numCode==2){
                	    	 layer.close(loadingIndex);
                	    	 layer.msg("账号信息验证不通过，请重新确认！",{time:1500, icon:5, shift:6}, function(){
                	    	});
                	     }else{
                	     }
                	   }
                	});
           		
           	}else{
           		layer.msg("账号信息验证不通过，请重新确认！",{time:1500, icon:5, shift:6}, function(){
           		});
           	}
           	
           }
           function refleshInfo(){
        	    $("#resetusername").val("");
                $("#resetusernameSpan").html("");
              	$("#resetpassword").val("");
              	$("#resetPasswordSpan").html("");
              	$("#resetpassword2").val("");
              	$("#resetPasswordSpan2").html("");
              	$("#regEmail").val("");
              	$("#regEmailSpan").html("");
              	$("#yEmailCode").val("");
              	refleshCode();
              	usernameFlag=false;
           	    passwordFlag=false;
           	    repeatpwdFlag=false;
           	    emailFlag=false;
           	    pernameFlag=false;
	           $("#regSendEmailbtn").removeAttr("disabled");
			   $("#getEmailSpan").html("获取验证码");
           }
		   //清楚验证码的对错图标，并把验证码标记为未校验过的状态
		   function refleshCode(){
			   yanzhengFlag=false;
			   $("#regyzCodeSpan").attr("src","");
	           $("#regyzCodeSpan").removeClass("yanzhengImg");
		   }
		   
   	
    </script>	
</body>
</html>