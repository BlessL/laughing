<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>注册页面</title>
	<meta name="keywords" content="注册页面">
	<meta name="content" content="注册页面">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="lib/bootstrap/css/login.css">
    <script type="text/javascript" src="lib/bootstrap/js/jquery.min.js"></script>

</head>
<body class="login_bj" >

<div class="zhuce_body" >
    <div class="zhuce_kong">
    	<div class="zc">
        	<div class="bj_bai">
            <h3>欢迎注册</h3>
       	  	  <form action="register" method="post">
	                <input name="nick" type="text" class="kuang_txt phone" placeholder="昵称">
	                <%-- <input name="" type="text" class="kuang_txt phone" placeholder="手机号"> --%> 
	                <input name="email" type="text" class="kuang_txt email" placeholder="邮箱">
	                <input name="password" type="text" class="kuang_txt possword" placeholder="密码">
	                <input name="password2" type="text" class="kuang_txt possword" placeholder="再次输入密码">
	                <%--
	                <input name="" type="text" class="kuang_txt yanzm" placeholder="验证码">
		                <div>
		                	<div class="hui_kuang"><img src="images/zc_22.jpg" width="92" height="31"></div>
		                	<div class="shuaxin"><a href="#"><img src="images/zc_25.jpg" width="13" height="14"></a></div>
		                </div>
		                <div>
		               		<input name="" type="checkbox" value=""><span>已阅读并同意<a href="#" target="_blank"><span class="lan">《XXXXX使用协议》</span></a></span>
		                </div>
	                 --%>
	                <input name="注册" type="submit" class="btn_zhuce" value="注册">
                </form>
            </div>
            
            
            <%-- 
            	<div class="bj_right">
	            	<p>使用以下账号直接登录</p>
	                <a href="#" class="zhuce_qq">QQ注册</a>
	                <a href="#" class="zhuce_wb">微博注册</a>
	                <a href="#" class="zhuce_wx">微信注册</a>
	                <p>已有账号？<a href="login.html">立即登录</a></p>
            	</div>
            --%>
        </div>
    </div>

</div>
    
</body>
</html>