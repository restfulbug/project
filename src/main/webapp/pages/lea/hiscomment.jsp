﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />

<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>

</head>

<body>
	<div id="main">
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="" id="vacation"><h2>批注信息</h2></li>
          </ul>
      </div>
      <div class="sort_content">
      <%--  <c:if test="${comments == null && comments.size== 0 }">
		<strong>没有任何批注信息</strong>
		</c:if>
		<c:if test="${comments != null && comments.size > 0 }">  --%>
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
               
              <thead>
                <tr>
                  <td width="10%"><strong>批注时间</strong></td>
                  <td width="20%"><strong>批注人</strong></td>
                  <td width="15%"><strong>批注信息</strong></td>
                </tr>
              </thead>
              <tbody id="tbody">
             <c:forEach var="comm" items="${comments}">
	
		 <tr>
		    <td>
		    <fmt:formatDate value="${comm.time}" pattern="yyyy-MM-dd hh:mm:ss"/>
		    </td>
			<td>管理员</td>
			<td>${comm.message}</td>
		 </tr>
	</c:forEach>
              </tbody>
          </table>
     <%--    </c:if>  --%>
      </div>
</div>
</body>
</html>
