<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome to 哈哈网</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>

        <script src="lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $(".knob").knob();
        });
        
        <%-- 点赞  --%>
   		
   		function like(id){
   		 $.ajax({
   			 url:"/laughing/addLike",
   			 data:{contentId:id},
   			 type:"post",
   			 dataType:"json",  
   			 success:function(data) {    
   		        if(data.msg =="true" ){    
   		            alert("修改成功！");    
   		            window.location.reload();    
   		        }else{    
   		            view(data.msg);    
   		        }    
   		     },    
   		     error : function() {   
   		          $("#like"+id).text(parseInt($("#like"+id).html()) + 1);
   		     }    
   		 });
   		}
    </script>
    
    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/premium.css">

</head>
<body class=" theme-blue">

    <script type="text/javascript">
        $(function(){
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
    </script>
    <style type="text/css">
        #line-chart {
            height:300px;
            width:800px;
            margin: 0px auto;
            margin-top: 1em;
        }
        .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover { 
            color: #fff;
        }
    </style>

    <script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>

    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
  
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="" href="index.html"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> 给人开心，自己也开心</span></a></div>

        <div class="navbar-collapse collapse" style="height: 1px;">
          <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> Jack Smith
                    <i class="fa fa-caret-down"></i>
                </a>

              <ul class="dropdown-menu">
                <li><a href="./">My Account</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Admin Panel</li>
                <li><a href="./">Users</a></li>
                <li><a href="./">Security</a></li>
                <li><a tabindex="-1" href="./">Payments</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="sign-in.html">Logout</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </div>
    
    <div class="sidebar-nav">
    <ul>
	    <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> 笑话<i class="fa fa-collapse"></i></a></li>
	    <li>
	    	<ul class="dashboard-menu nav nav-list collapse in">
	            <li><a href="/laughing/index"><span class="fa fa-caret-right"></span> 今天最好笑5条</a></li>
	            <li ><a href="/laughing/allContents?pageIndex=1&pageSize=3"><span class="fa fa-caret-right"></span> 全部笑话</a></li>
	    	</ul>
	    </li>


    </ul>
    </div>

    <div class="content">
        <div class="header">
        	<img src="images/dog.jpg" border="0"/>
        </div>
        <div class="btn-toolbar list-toolbar">
    		<button class="btn btn-primary" onclick="window.location.href='/laughing/addContent'"><i class="fa fa-plus"></i>  我也要讲个笑话</button>
		</div>
        <div class="main-content">
		
	  <table class="table table-striped table-bordered table-hover">
			  <th>标题</th>
			  <th>内容</th>
			  <th>笑值</th>
			  <th>时间</th>
			<c:forEach var="list" items="${paginationData.currData}">
				<tr>
					<td>${list.title}</td>
					<td>${list.content}</td>
					<td id="like${list.contentId}" value="${list.laughValue}" align="right">${list.laughValue}
					&nbsp;&nbsp;   <img id="b${list.contentId}" src="images/xl.jpg" float="right" align="right" width="30" height="30" onclick="like(${list.contentId})"/></td>
				    
					<td>${list.createTime}</td>
				</tr>
			</c:forEach>
	  </table>
	  
	  <center>
		  <nav>
		  <ul class="pagination">
		  <c:if test="${paginationData.isFirst() }">
			   <li class="disabled">
				  <a href="#" aria-label="Previous">
					<span aria-hidden="true"><<上一页</span>
				  </a>
				</li>
			</c:if>
			<c:if test="${not paginationData.isFirst() }">
				<li>
				  <a href="/laughing/allContents?pageIndex=${paginationData.previousIndex }&pageSize=${paginationData.pageSize}" aria-label="Previous">
					<span aria-hidden="true"><<上一页</span>
				  </a>
				</li>
			</c:if>
			<c:if test="${paginationData.pageLinkNumber gt 0}">
				<c:forEach var="linkIndex" begin="${paginationData.beginIndex}" end="${paginationData.endIndex}">
					<c:if test="${linkIndex eq  paginationData.pageIndex}" var="isCurr">
						<li class="active"><a href="#">${linkIndex}</a></li>
					</c:if>
					<c:if test="${not isCurr}">
						<li><a href="/laughing/allContents?pageIndex=${linkIndex}&pageSize=${paginationData.pageSize}" >${linkIndex}</a></li>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${paginationData.isLast() }">
			   <li class="disabled">
				  <a href="#" aria-label="Next">
					<span aria-hidden="true">下一页>></span>
				  </a>
				</li>
			</c:if>
			<c:if test="${not paginationData.isLast()}">
				<li>
				  <a href="/laughing/allContents?pageIndex=${paginationData.getNextIndex()}&pageSize=${paginationData.pageSize}" aria-label="Next">
					<span aria-hidden="true">下一页>> </span>
				  </a>
				</li>
			</c:if>
		  </ul>
		</nav>
	  </center>
    
        <footer>
	        <div class="stats">
				    <p class="stat">您是第 <span class="label label-danger"> 109888</span> 位访客</p>
			</div>
            <p align = "center">©2017
        </footer>
        </div>
    </div>


    <script src="lib/bootstrap/js/bootstrap.js"></script>
    <script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>
    
  
</body></html>
