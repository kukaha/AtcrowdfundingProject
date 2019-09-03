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

	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tree-closed {
	    height : 40px;
	}
	.tree-expanded {
	    height : auto;
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
          <!-- 动态包含被包含的页面也会独立编译，生产字节码文件 -->
<%--             <jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include> --%>
            <!-- 静态包含被包含的页面不会生成字节码文件，只会将所被包含的页面加入到当前页面一起编译 -->
<%--             <%@ include file="/WEB-INF/jsp/common/top.jsp" %> --%>
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userinfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form id="serarch" class="navbar-form navbar-right">
            <input id="finputSearch" type="text" class="form-control" placeholder="Search...">
            <button onclick="doSearch()" type="button" class="btn btn-default btn-success dropdown-toggle">搜索</button>
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
          <h1 class="page-header">控制面板</h1>

          <div class="row placeholders">
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/sky" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
            <div class="col-xs-6 col-sm-3 placeholder">
              <img data-src="holder.js/200x200/auto/vine" class="img-responsive" alt="Generic placeholder thumbnail">
              <h4>Label</h4>
              <span class="text-muted">Something else</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
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
            
            function doSearch(){
            	var finputSearch = $("#finputSearch");
            	var searchIndex = -1;
            	$.ajax({
            		type : "GET",
            		data : {
            			"keyWord" : finputSearch.val()
            		},
            		url : "${APP_PATH}/doSearch.do",
            		beforeSend : function(){
            			searchIndex = layer.msg("正在搜索...",{icon:5});
            			return true;
            		},
            		success : function(result){
            			layer.close(searchIndex);
            			if(result.success){
            				layer.msg(result.data,{time:5000,icon:6,shift:6});
            			}else{
            				layer.msg(result.message,{time:1000,icon:5,shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("搜索异常",{time:1000,icon:5,shift:6});
            		}
            	});
            	
            }
            
            
            
        </script>
  </body>
</html>