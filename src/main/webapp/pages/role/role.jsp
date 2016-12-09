<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function () {
	$("#roleid").change(function () {
		
		var roleId = $("#roleid").val();
		 if (roleId != "") {
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

	<div id="main">
        
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">角色组 </a></li>
          </ul>
      </div>
      
      <div class="sort_content">
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
              <tbody id="tbody">
               <%-- <select id="roleid">
					<option value="">选择角色</option>
				    <c:forEach items="${roles}" var="role">
				    <option value="${role.id}">${role.description}</option>
					</c:forEach>
			 </select> --%> 
               </tbody>
          </table>
      </div>
	</div>
</body>
</html>
