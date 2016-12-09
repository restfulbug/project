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
<script>
	var processType = "${processType}";
	$("#" + processType).attr("class", "selected");
</script>
</head>

<body>
	<div id="main">
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="" id="vacation"><a href="${ctx}/listProcessInstanceRep">我的报修申请</a></li>
          </ul>
      </div>
      <div class="sort_content">
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
              <thead>
                <tr>
                  <td width="20%"><strong>编号</strong></td>
                  <td width="20%"><strong>状态</strong></td>
                  <td width="25%"><strong>创建时间 </strong></td>
                  <td width="20%"><strong>操作 </strong></td>
                   <td width="15%"><strong>审批记录 </strong></td>
                </tr>
              </thead>
              <tbody id="tbody">
             <c:forEach var="bas" items="${baseForms}">
	<c:if test="${empty bas}">
		没有任何申请记录
		</c:if>
	<c:if test="${!empty bas}">
		
		<tr>
			<td>${bas.proId}</td>
			<td>${bas.processStatus}</td>
			<td>${bas.requestDate}</td>
			<td><a href="${pageContext.request.contextPath }/getId/${bas.proId}">查看流程进度</a></td>
			<td><a href="${pageContext.request.contextPath }/viewHisComment/${bas.proId}">查看审核记录</a></td>
		</tr>
		</c:if>
	</c:forEach>
              </tbody>
          </table>
      </div>

</div>
    
</body>
</html>
