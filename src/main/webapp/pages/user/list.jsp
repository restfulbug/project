﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$(".delete").click(function(){
		var label = $(this).next(":hidden").val();
		var flag = confirm("确定要删除用户"+" " + label +" "+"吗?");
		if(flag){
			var url = $(this).attr("href");
			$("#_form").attr("action", url);
			$("#_method").val("DELETE");
			$("#_form").submit();
		} 
		 return false; 
	});
})
</script>
<script>
	function add() {
		window.location.href="user-add";
	}
</script>
</head>

<body>
  <form action="" method="post" id="_form">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	<div id="main">
      <div class="">
           <input type="button" value="添加" class="input_button3" onClick="add()"/>
      </div>
           <div class="sort_switch">
              <ul id="TabsNav">
          	      <li class="selected"><a href="#">用户</a></li>
              </ul>
           </div>
      
      <div class="sort_content">
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
         <c:if test="${page == null || page.numberOfElements == 0 }">
		没有任何记录. 
	</c:if>
	<c:if test="${page != null && page.numberOfElements > 0 }">
              <thead>
                <tr>
                  <td width="10%"><strong>编号</strong></td>
                  <td width="10%"><strong>姓名</strong></td>
                  <td width="15%"><strong>是否在职</strong></td>
                  <td width="15%"><strong>角色</strong></td>
                  <td width="30%"><strong>电话</strong></td>
                  <td width="15%"><strong>操作</strong></td>
                </tr>
              </thead>
              <tbody id="tbody">
              <c:forEach items="${page.content}" var="user">
                <tr>
                  <td>${user.id}</td>
                  <td>${user.userName}</td>
                  <td>${user.isExist}</td>
                 <c:forEach items="${user.roles}" var="roles" ><td>${roles.description}</td></c:forEach>
                  <td>${user.telephone}</td>
                  <td><a href="${ctx}/user/${user.id}"  class="delete">删除</a>
                  <input type="hidden" value="${user.userName}"/></td>
                </tr>
              </c:forEach>
              <tr>
				<td width="10%" colspan="16" align="center" height="50px">
					共 ${page.totalElements } 条记录
					共 ${page.totalPages } 页
					当前 ${page.number + 1 } 页
					<a href="?pageNo=${page.number + 1 - 1 }">上一页</a>&nbsp;&nbsp;
					<a href="?pageNo=${page.number + 1 + 1 }" id="page">下一页</a>
				</td>
			</tr></c:if>
              </tbody>
          </table>
      </div>
</div>
</body>
</html>
