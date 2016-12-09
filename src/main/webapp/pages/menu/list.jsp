<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var label = $(this).next(":hidden").val();
			var flag = confirm("确定要删除角色" + label + "吗?");
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
		window.location.href="menu-add";
	}
</script>
</head>

<body>
  <form action="" method="post" id="_form">
		<input type="hidden" id="_method" name="_method"/>
	</form>
	<div id="main">

      <div class="">

                <input type="button" value="添加" class="input_button3" onclick="add()"/>
      </div>
        
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">角色</a></li>
          </ul>
      </div>
      
      <div class="sort_content">
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
         <c:if test="${menus == null || menus.size() == 0 }">
		没有任何角色. 
	</c:if>
	<c:if test="${menus != null && menus.size() > 0 }">
              <thead>
                <tr>
                  <td width="5%"><strong>编号</strong></td>
                  <td width="5%"><strong>父编号</strong></td>
                  <td width="20%"><strong>名称</strong></td>
                  <td width="15%"><strong>链接</strong></td>
                  <td width="5%"><strong>是否展示</strong></td>
                  <td width="5%"><strong>排序</strong></td>
                  <td width="20%"><strong>描述</strong></td>
                  <td width="10%"><strong>编辑</strong></td>
                  <td width="00%"><strong>删除</strong></td>
                </tr>
              </thead>
              <tbody id="tbody">
              <c:forEach items="${menus}" var="menu">
                <tr>
                  <td>${menu.id}</td>
                  <td>${menu.parentId}</td>
                  <td>${menu.name}</td>
                  <td>${menu.url}</td>
                  <td>${menu.isShow}</td>
                  <td>${menu.sort}</td>
                  <td>${menu.description}</td>
                  <td>
                     <a href="${ctx}/menu/${menu.id}"  class="assign">修改</a>
                   </td>
                   <td>
                     <a href="${ctx}/menu/${menu.id}"  class="delete">删除</a>
                      <input type="hidden" value="${menu.name}"/>
                  </td>
                </tr>
              </c:forEach>
              </tbody>
              </c:if>
          </table>
      </div>
</div>
</body>
</html>
