<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
</head>
<body>
<form:form method="post"  action="${ctx}/process-completeRep/${taskId}" id="auditForm">
	<div id="main">
        
        <div class="where">
            <ul>
            </ul>
        </div>
        
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">审批</a></li>
          </ul>
      </div>
      
      <div id="tagContent0" class="sort_content">
			<input type="hidden" value="${taskId}" name="taskId"/>
        	<div class="currency_area hue9">
            	<div class="the_content">
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td width="15%" class="title1">故障类型:</td>
                          <td class="left">${repairForm.faultType.faultName}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">具体故障信息:</td>
                          <td class="left">${repairForm.detailDesc}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">故障等级:</td>
                          <td class="left">${repairForm.faultLevel.levelName}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">区域类型:</td>
                          <td class="left">${repairForm.dictionary.keyName}</td>
                        </tr>
                         <tr>
                          <td width="15%" class="title1">楼栋号:</td>
                          <td class="left">${repairForm.building.buildNo} </td>
                        </tr>
                        
                        <tr>
                          <td width="15%" class="title1">具体位置信息:</td>
                          <td class="left">${repairForm.detailLocation}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">联系电话:</td>
                          <td class="left">${repairForm.tel}</td>
                        </tr>
                           <c:if test="${!empty comments}">
                        <tr>
                          <td width="10%" class="title1">审批：</td>
                          	<td class="left">
								<table>
									<c:forEach var="comment" items="${comments}">
									<tr>
										<td>审批人:</td>
										<td>${comment.userName}</td>
									</tr>
									<tr>
										<td>审批时间:</td>
										<td>${comment.time}</td>
									</tr>
									<tr>
										<td>审批意见:</td>
										<td>${comment.content}</td>
									</tr>
									</c:forEach>
								</table>
							</td>
                        </tr>
                        </c:if>
                         <tr>
                          <td width="15%" class="title1">我的意见：</td>
                          	<td class="left">
								<textarea cols="40" rows="6" name="content"></textarea>
							</td>
                        </tr>
                      </tbody>
                  </table>
				  
                </div>
            </div>
             <c:if test="${taskflag==0}">
             <div class="fun_area" style="text-align:center;"><input type="submit" value="提交" class="input_button1"/>
             </c:if>
             <c:if test="${taskflag==1}">
                  <div class="fun_area" style="text-align:center;"><input type="submit" value="通过" class="input_button1"/>
	            	<input type="button" value="驳回" class="input_button1" onClick="cancelAdjust()"/>
                  </div>
             </c:if>
      </div>
</div>
</form:form>
<script>
	function cancelAdjust() {
		document.getElementById("auditForm").action = "${ctx}/process-cancelAdjustRep/${taskId}";
		document.getElementById("auditForm").submit();
	}
</script>
</body>
</html>
