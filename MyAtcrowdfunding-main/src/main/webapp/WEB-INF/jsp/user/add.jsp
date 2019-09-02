<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
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
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userinfo.jsp"></jsp:include>
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
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li><a href="#">数据列表</a></li>
				  <li class="active">新增</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form id="addFrom">
				  <div class="form-group">
					<label for="floginacct">登陆账号</label>
					<input type="text" class="form-control" id="floginacct" placeholder="请输入登陆账号">
					<p class="help-block label label-warning">请输入合法的登陆账号, 格式为：6-16，包含任意的字母、数字，不可以使用其他符号</p>
				  </div>
				  <div class="form-group">
					<label for="fusername">用户名称</label>
					<input type="text" class="form-control" id="fusername" placeholder="请输入用户名称">
					<p class="help-block label label-warning">请输入合法的用户名称, 格式为：2到6位中文 </p>
				  </div>
				  <div class="form-group">
					<label for="femail">邮箱地址</label>
					<input type="email" class="form-control" id="femail" placeholder="请输入邮箱地址">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button id="addBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
				  <button id="resetBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script type="text/javascript" src="${APP_PATH}/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
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
			    showMenu();
            });
            
            function showMenu() {
            	var href = window.location.href;
            	var host = window.location.host;
            	var index = href.indexOf(host);
            	var path = href.substring(index + host.length)
            	var contextPath = "${APP_PATH}";
            	var pathAddress = path.substring(contextPath.length)
            	var alink = $(".list-group a[href='" + pathAddress + "']")
            	alink.css("color", "red")
            	alink.parent().parent().parent().removeClass("tree-closed")
            	alink.parent().parent().show()
            }
            
            var floginacct = $("#floginacct")
            var fusername = $("#fusername")
            var femail = $("#femail")
            
            $("#addBtn").click(function(){
            	$.ajax({
            		type : "POST",
            		data : {
            			"loginacct" : floginacct.val(),
            			"username" : fusername.val(),
            			"email" : femail.val()
            		},
            		url : "${APP_PATH}/user/doAdd.do",
            		beforeSend : function(){
            			if($.trim(floginacct.val())== ""){
            	    		layer.msg("用户登录账户不能为空，请重新输入",{time:2000,icon:5,shift:6},function(){
            	    			//获取该失败表单的文本框焦点
            	        		floginacct.val("")
            	        		floginacct.focus();
            	    		});
            	    		return false;
            	    	}else{
            	    		var reg = /^([a-zA-Z0-9]){6,16}$/i;
            	    		if(reg.test(floginacct.val())){
            	    		}else{
            	    			layer.msg("用户账号格式不正确，请重新输入6-16，包含任意的字母、数字，不可以使用其他符号",{time:2000,icon:5,shift:6},function(){
                	    			//获取该失败表单的文本框焦点
                	        		floginacct.val("")
                	        		floginacct.focus();
                	    		});
            	    			return false;
            	    		}
            	    	}
            			if($.trim(fusername.val())== ""){
            	    		layer.msg("用户名称不能为空，请重新输入",{time:2000,icon:5,shift:6},function(){
            	    			//获取该失败表单的文本框焦点
            	        		fusername.val("")
            	        		fusername.focus();
            	    		});
            	    		return false;
            	    	}else{
            	    		var reg = /^[\u4e00-\u9fa5]{2,6}$/;
            	    		if(reg.test(fusername.val())){
            	    		}else{
            	    			layer.msg("用户名格式不正确，请重新输入2到6位中文",{time:2000,icon:5,shift:6},function(){
                	    			//获取该失败表单的文本框焦点
                	        		fusername.val("")
                	        		fusername.focus();
                	    		});
            	    			return false;
            	    		}
            	    	}
            			
            			if($.trim(femail.val())== ""){
            	    		layer.msg("用户邮箱地址不能为空，请重新输入",{time:2000,icon:5,shift:6},function(){
            	    			//获取该失败表单的文本框焦点
            	        		femail.val("")
            	        		femail.focus();
            	    		});
            	    		return false;
            	    	}else{
            	    		var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            	    		if(reg.test(femail.val())){
            	    		}else{
            	    			layer.msg("用户邮箱地址格式不正确，请重新输入",{time:2000,icon:5,shift:6},function(){
                	    			//获取该失败表单的文本框焦点
                	        		femail.val("")
                	        		femail.focus();
                	    		});
            	    			return false;
            	    		}
            	    	}
            			return true;
            		},
            		success : function(result){
            			if(result.success){
            				window.location.href="${APP_PATH}/user/index.htm";
            			}else{
            				layer.msg(result.message,{time:1000,icon:5,shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("保存用户失败",{time:1000,icon:5,shift:6});
            		}
            	})
            });
            
            $("#resetBtn").click(function(){
            	$("#addFrom")[0].reset();
            });
        </script>
  </body>
</html>