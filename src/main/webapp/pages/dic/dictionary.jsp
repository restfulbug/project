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
$(function(){
	$("#option").change(function(){
		var val = $(this).val();
		alert(val);
		var url = "${pageContext.request.contextPath }/dic/val";
		var args = {"dicId":val,"date":new Date()};
		
		$.post(url, args, function(data){
		});
	});
})
</script>
</head>
<body>
	<div id="main">  
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">字典维护</a></li>
          </ul>
      </div>
      <div id="tagContent0" class="sort_content">
        	<div class="currency_area hue9">
            	<div class="the_content">
                	 <table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td width="15%" class="title1">字典类型:</td>
                          <td align="center">
									<select id="option">
									<option value="">请选择...</option>
									<c:forEach items="${dic}" var="dic">
							        <option id= "userId" value="${dic.id}">${dic.keyWord}</option>
						            </c:forEach>
						            </select>
					       </td>
                          <td width="15%" class="title1">字典类型:</td>
                          <td align="center">
									<c:forEach items="${typeDic}" var="tdic">
						            </c:forEach>
					       </td>
                        </tr>
                      </tbody>
                  </table>
                </div>
            </div>
      </div>
</div>
</body>
</html>
