<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络报修系统</title>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function name() {
	$("#faultType").change(function () {
		
		var faultTypeId = $("#faultType").val();
		 if (faultTypeId != "") {
			var url = "${ctx}/solution";
			var args = {"faultTypeId":faultTypeId,"time": new Date()};
			$.post(url,args,function(data){
				  $("#solution").val(data);
			});
		}
	})
	 
})
</script>
</head>
<body>
<c:set value="${ctx}/rep" var="url"></c:set>
	 <c:if test="${repairForm.id != null }">
		<c:set value="${pageContext.request.contextPath }/rep/${repairFrom.id}" var="url"></c:set>
	</c:if> 
<form action="${url}" method="POST" >
	<div id="main">
       <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="" id="vacation"><a href="${ctx}/rep">填写报修表</a></li>
          </ul>
      </div>
      <div id="tagContent0" class="sort_content">
        	  
        	<div class="currency_area hue9">
            	<div class="the_content">
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0" >
                      <tbody>
                      <form:form modelAttribute="repairForm">
                       		<c:if test="${repairForm.id != null }">
			             <form:hidden path="id"/>
			              <input type="hidden" name="_method" value="PUT"/>
		                </c:if>
		                <form:input path="user.id"  type="hidden" value="${sessionScope.user.id}" />
                         <tr>
                            <td width="15%" class="title1">故障类型:</td>
                            <td class="left">
                            <form:select   path="faultType.id" id="faultType" items="${faultTypes }" 
			                  itemLabel="faultName" itemValue="id"></form:select>
                           </td>
                        </tr>
                         <tr>
                          <td width="15%" class="title1">解决小方案:</td>
                           <td class="left"> <textarea id="solution"></textarea></td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">具体故障信息:</td>
                          <td class="left"><form:input path="detailDesc" type="text" class="input_text2" size="100" /></td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">故障等级:</td>
                          <td class="left"><form:select path="faultLevel.id" items="${faultLevels }" 
			                  itemLabel="levelName" itemValue="id"></form:select> </td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">区域类型:</td>
                          <td class="left"><form:select path="Dictionary.id" items="${positions }" 
			                  itemLabel="keyName" itemValue="id"></form:select> </td>
                        </tr>
                         <tr>
                          <td width="15%" class="title1">楼栋号:</td>
                          <td class="left"><form:select path="Building.id" items="${buildings }" 
			                  itemLabel="buildNo" itemValue="id"></form:select> </td>
                        </tr>
                        
                        <tr>
                          <td width="15%" class="title1">具体位置信息:</td>
                          <td class="left"><form:input path="detailLocation" type="text" class="input_text2" size="100" /></td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">联系电话:</td>
                          <td class="left"><form:input path="tel"  id="tel_" type="text" class="input_text2" size="30" /></td>
                        </tr>
                        </form:form>
                      </tbody>
                  </table>
                </div>
            </div>
            <div class="fun_area" style="text-align:center;"><input type="submit" value="确 定" class="input_button1"/></div>
      </div>
</div>
</form>
</body>
</html>