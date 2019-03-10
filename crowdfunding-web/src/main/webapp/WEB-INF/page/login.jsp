<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆</title>
<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
	<style>

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

    <div class="container">

      <form class="form-signin" role="form" method="post" id="login_form" action="toLogin">
       	  <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="username" name="username" placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="password" name="password" placeholder="请输入登录密码">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="yanzCode" name="yzCode" placeholder="请输入验证码" style="width:140px;float: left;"/>
			<img id="login_ImgCode" src="" class="form-control" style="width: 135px;height: 44px;padding: 0px;float: right;margin-top: 10px;top: -10px">
		  </div>
		  <div class="form-group has-success has-feedback" style="top: 5px;float: left;width: 300px">
			<select class="form-control" id="loginOption">
                <option value="0">会员</option>
                <option value="1">管理</option>
            </select>
		  </div>
        <div class="checkbox" style="float: left">
          <label>
            <input type="checkbox" value="remember-me" id="rememberMe"> 记住我
          </label>
          <br>
          <label>忘记密码</label>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <label>
                      <a href="${APP_PATH}/reg">我要注册</a>
          </label>
        </div>
        
        <div class="form-group has-success has-feedback" style="float: left;width: 300px">
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
		</div>
        
      </form>
    </div>
    <script src="${APP_PATH}/js/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/layer/layer.js"></script>
    <script>
    
    
   	$(function(){
   		doRemember("cloginUserInfo");
   		$("#login_ImgCode").attr("src","yanzCode?dowhat=login&time="+new Date());
   		
   		$("#yanzCode").blur( function(){
   			var loginCode=$("#yanzCode").val();
   			var dowhat="login";
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
         	    	codeFlag=true;
         	     }else{
         	    	codeFlag=false;
         	    	}
         	    }
         	});
   			
   		});
   		
   		
   		
   		$("#login_ImgCode").click(function(){
   			var time=new Date();
   			$("#login_ImgCode").attr("src","yanzCode?dowhat=login&time="+time)
   		});
   		
   		
   		
   		
   	})
   	
   	function getCokies(cookieName){
   			var str1=document.cookie.split(';');
			var str2=[];
			for(var i=0;i<str1.length;i++){
				str2=str1[i].split('=');
				var s=str2[0].substring(1,str2[0].length);
				if(s==cookieName){
					return str2[1];
				}
			}
   	}
   	
   	function doRemember(cookieName){
   		if(typeof(getCokies(cookieName))!="undefined"){
   			cookieStr=getCokies(cookieName);
   			var str3=cookieStr.substring(1,cookieStr.length-1).split(',');
   			$("#username").val(str3[0]);
   			$("#password").val(str3[1]);
   			$("#rememberMe").prop("checked",true);
   			$("#loginOption option").each(function(){
   				if($(this).val()==str3[2]){
   					$(this).prop("selected",true);
   				}
   			})
   		}
   	}
   	
    function dologin() {
    	var username=$("#username").val();
    	var password=$("#password").val();
    	var yanZhengCode=$("#yanzCode").val();
    	var flag=$("#loginOption option:selected").val();
    	if(username==""){
    		layer.msg("用户名不能为空！",{time:1500, icon:5, shift:6}, function(){
    			document.querySelector("#username").focus();
    		});
    		return;
    	}else if(password==""){
    		layer.msg("密码不能为空！",{time:1500, icon:5, shift:6}, function(){
    			document.querySelector("#password").focus();
    		});
    			return;
    		}else if(yanZhengCode==""){
    		layer.msg("验证码不能为空！",{time:1500, icon:5, shift:6}, function(){
        		document.querySelector("#yanzCode").focus();
    		});
    			return;
    	}
    	
    	if(codeFlag){
    	var loadingIndex = layer.msg('正在登陆', {icon: 16});
    	var remember=$("#rememberMe").prop("checked");
        $.ajax({
        	   type: "POST",
        	   url: "${APP_PATH}/check/toLogin",
        	   data: {
        	   "username":username,
        	   "password":password,
        	   "remember":remember,
        	   "flag":flag
        		},       		   
        	   success: function(msg){
        	     if(msg.numCode==1){
        	    	 var uid=msg.infoCode;
        	    	 $.ajax({
	          	   		 type: "POST",
	          	   		 url: "${APP_PATH}/permission/sellMenu4Uer",
	            	   	 data: {
	            	   		 "uid":uid
	            	   	 }, 
	            	   	 async:false,
	            	   	 success: function(result){}
	            	});
        	    	 layer.close(loadingIndex);
        	    	 if(flag==1){
        	    		 layer.confirm("是否进入后台管理系统？",  {icon: 3, title:'提示'}, function(cindex){
        	 			    layer.close(cindex);
        	 			   location.href="${APP_PATH}/user/user" 
        	 			}, function(cindex){
        	 			   location.href="${APP_PATH}/index"
        	 			});
        	    	 }else{
        	    	 location.href="${APP_PATH}/index"
        	    	 }
        	     }else if(msg.numCode==0){
        	    	 layer.close(loadingIndex);
        	    	 layer.msg("用户名或密码不正确，请重新输入！",{time:1500, icon:5, shift:6}, function(){
        	    		 $("#login_ImgCode").attr("src","yanzCode?dowhat=login&time="+new Date());
        	        		document.querySelector("#username").focus();
        	    		});
        	     }else{
        	    	 layer.close(loadingIndex);
        	    	 layer.msg("请选择正确的角色信息！",{time:1500, icon:5, shift:6}, function(){
        	    		 $("#login_ImgCode").attr("src","yanzCode?time="+new Date());
        	    	});
        	     }
        	   }
        	});
        
        	codeFlag=false;
    	}else{
    		
    		layer.msg("请输入正确的验证码！",{time:1500, icon:5, shift:6}, function(){
        		document.querySelector("#yanzCode").focus();
    		});
    	}
    	
    }
    </script>	
</body>
</html>