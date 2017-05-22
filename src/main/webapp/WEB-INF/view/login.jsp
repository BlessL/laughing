<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录页面</title>
	<meta name="keywords" content="登录页面">
	<meta name="content" content="登录页面">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="lib/bootstrap/css/login.css">
    <script type="text/javascript" src="lib/bootstrap/js/jquery-1.8.0.min.js"></script>
</head>
<body class="login_bj" >
<div class="zhuce_body">
	<%--
		<div class="logo"><a href="#"><img src="images/logo.png" width="114" height="54" border="0"></a></div>
 	--%>
    <div class="zhuce_kong login_kuang">
    	<div class="zc">
        	<div class="bj_bai">
            <h3>登录</h3>
       	  	  <form action="login" method="post">
                <input name="email" type="text" class="kuang_txt" placeholder="邮箱地址">
                <input name="password" type="text" class="kuang_txt" placeholder="密码">
                <%--
	                <div>
	               		<a href="#">忘记密码</a><input name="" type="checkbox" value="" checked><span>记住我</span> 
	                </div>
                 --%>
                <input name="login" type="submit" class="btn_zhuce" value="登录">
                </form>
            </div>
        </div>
    </div>
</div>
    
</body>
</html>