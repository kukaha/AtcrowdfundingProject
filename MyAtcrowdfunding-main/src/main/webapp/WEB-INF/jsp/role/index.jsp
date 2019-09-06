<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input id="queryText" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button id="queryBtn" type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button onClick="removeBatchBtn()" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/role/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input id="allCheckbox" type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
							 </ul>
					 </td>
				 </tr>
			  </tfoot>
            </table>
          </div>
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
			    showMenu()
			    queryPageRole(1);
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
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });
            
          //改变页面
            function pageChange(pageno){
//          同步请求   	window.location.href="${APP_PATH}/user/index.do?pageno="+pageno;
            	queryPageRole(pageno);
            }
            
            var jsonObj = {
        			"pageno" : 1,
        			"pagesize" : 10
        		}
            
            var loadingIndex = -1;
            function queryPageRole(pageno){
            	jsonObj.pageno = pageno;
            	$.ajax({
            		type : "POST",
            		data : jsonObj,
            		url : "${APP_APTH}/role/doIndex.do",
            		beforeSend : function(){
            			loadingIndex = layer.load(2,{time : 10*1000})
            			return true;
            		},
            		success : function(result){
            			layer.close(loadingIndex);
            			if(result.success){
            				var page = result.page; 
            				var data = page.datas;
            				var content = '';
            				
            				$.each(data,function(i,role){
            					content+='<tr>';
            					content+='  <td>'+(i+1)+'</td>';
            					content+='  <td><input id="'+role.id+'" type="checkbox"></td>';
            					content+='  <td>'+role.name+'</td>';
            					content+='  <td>';
            					content+='	  <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
            					content+='	  <button type="button" onclick="updateBtn('+role.id+')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
            					content+='	  <button type="button" onclick="removeBtn('+role.id+',\''+role.name+'\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content+='  </td>';
            					content+='</tr>';
            				});
            				
							var contentBar = '';
            				
            				if(page.pageno==1){
            					contentBar+='<li class="disabled"><a href="#">上一页</a></li>';
            				}else{
            					contentBar+='<li><a href="#" onClick="pageChange('+(page.pageno-1)+')">上一页</a></li>';
            				}
            				
            				for(var i = 1;i<=page.totalno;i++){
            					contentBar+='<li';
            						if(page.pageno == i){
            							contentBar+=' class="active"';
            						}
            					contentBar+='><a href="#" onclick="pageChange('+i+')">'+i+' <span class="sr-only">(current)</span></a></li>'
            				}
            				
            				
            				if(page.pageno==page.totalno){
            					contentBar+='<li class="disabled"><a href="#">下一页</a></li>';
            				}else{
            					contentBar+='<li><a href="#" onClick="pageChange('+(page.pageno+1)+')">下一页</a></li>';
            				}
            				$("tbody").html(content);
            				$(".pagination").html(contentBar);
            			}else{
            				layer.msg(result.message,{time:1000,icon:5,shift:6});
            			}
            		},
            		error : function(){
            			layer.msg("查询角色失败",{time:1000,icon:5,shift:6});
            		}
            	});
            }
            
            $("#queryBtn").click(function(){
            	var queryText = $("#queryText").val();
            	jsonObj.queryText = queryText;
            	queryPageRole(1);
            });
            
            function updateBtn(id){
            	window.location.href="${APP_PATH}/role/toUpdate.htm?id="+id;
            }
            
            function removeBtn(id,name){
            	layer.confirm("你确认要删除"+name+"名称吗",{icon:3,title:'提示'},function(cindex){
//                 	window.location.href="${APP_PATH}/user/doRemove.do?id="+id;
    				//确认需要做的事情
    				layer.close(cindex);
	            	$.ajax({
	            		type : "POST",
	            		data : {
	            			"id" : id
	            		},
	            		url:"${APP_PATH}/role/doRemove.do",
	            		beforeSend : function(){
	            			return true;
	            		},
	            		success : function(result){
	            			if(result.success){
	            				window.location.href="${APP_PATH}/role/index.htm";
	            			}else{
	            				layer.msg(result.message,{time:1000,icon:5,shift:6});
	            			}
	            		},
	            		error : function(){
	            			layer.msg("删除名称失败",{time:1000,icon:5,shift:6});
	            		}
	            	})
    			},function(cindex){
    				//取消要做的事情
    				layer.close(cindex);
    			});
            }
            
            function removeBatchBtn(){
            	var selectCheckbox = $("tbody tr td input:checked");
            	if(selectCheckbox.length==0){
            		layer.msg("至少选择一个名称！！！",{time:1000,icon:5,shift:6});
					return false;	            				
    			}
//             	var idStr = "";
//             	$.each(selectCheckbox,function(i,checkbox){
// 					if(i!=0){
// 						idStr += "&";
// 					}
// 					idStr += "id="+checkbox.id;
//             	});
            	//alert(checkbox.id);
            	var jsonObj = {};
            	$.each(selectCheckbox,function(i,checkbox){
            		jsonObj["ids["+i+"]"] = checkbox.id;
            	});
           		layer.confirm("你确认要删除这些用户吗",{icon:3,title:'提示'},function(cindex){
   				//确认需要做的事情
   				layer.close(cindex);
            		$.ajax({
	            		type : "POST",
	            		//data : idStr,
	            		data : jsonObj,
	            		url:"${APP_PATH}/role/doRemoveBatch.do",
	            		beforeSend : function(){
	            			return true;
	            		},
	            		success : function(result){
	            			if(result.success){
	            				window.location.href="${APP_PATH}/role/index.htm";
	            			}else{
	            				layer.msg(result.message,{time:1000,icon:5,shift:6});
	            			}
	            		},
	            		error : function(){
	            			layer.msg("批量删除名称失败",{time:1000,icon:5,shift:6});
	            		}
	            	})
           		},function(cindex){
       				//取消要做的事情
       				layer.close(cindex);
       			});
            }
            
            $("#allCheckbox").click(function(){
            	var checkedstatus = this.checked;
            	var checkboxAll = $("tbody tr td input[type='checkbox']").prop("checked",checkedstatus);
//             	var checkboxAll = $("tbody tr td input[type='checkbox']").attr("checked",checkedstatus);
            	//alert(checkboxAll.length);
            	$.each(checkboxAll,function(i,n){
            		n.checked = checkedstatus;
            	});
            })
        </script>
  </body>
</html>
