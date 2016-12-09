<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#roleName").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			$(this).val(val);
			
			var url = "${pageContext.request.contextPath }/ajaxValidateRoleName";
			var args = {"roleName":val,"date":new Date()};
			
			$.post(url, args, function(data){
				if(data == "0"){
					alert("账户名可用!");
				}else if(data == "1"){
					alert("账户名 不可用!");
				}else{
					alert("网络或程序出错. ");
				}
			});
		});
	})
</script>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
</head>

<body>
<form method="post" action="${ctx}/role-add" >
	<div id="main">
        
        <div class="where">
            <ul>
            </ul>
        </div>
        
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">添加用户</a></li>
          </ul>
      </div>
      
      <div id="tagContent0" class="sort_content">
        	  
        	<div class="currency_area hue9">
          		<div class="the_title T10">
            		<h5><strong>用户</strong></h5>
            	</div>
            	<div class="the_content">
					
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0" >
                      <tbody>
                      <form:form modelAttribute="role">
                        <tr>
                          <td width="15%" class="title1">角色名：</td>
                          <td class="left"><form:input path= "roleName" type="text" id="account" size="30" /></td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">角色描述：</td>
                          <td class="left"><form:input path= "description" type="text" class="input_text2" size="30" /></td>
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
