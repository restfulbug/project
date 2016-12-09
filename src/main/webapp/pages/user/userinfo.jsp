<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
</script>
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
</head>

<body>
	<div id="main">
        <div class="where">
            <ul>
            </ul>
        </div>
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">个人信息</a></li>
          </ul>
      </div>
      <div id="tagContent0" class="sort_content">
        	<div class="currency_area hue9">
          		<div class="the_title T10">
            		<h5><strong>用户信息</strong></h5>
            	</div>
            	<div class="the_content">
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0" >
                      <tbody>
                        <tr>
                          <td width="20%" class="title1">账户名：</td>
                          <td class="left">${user.account}</td>
                        </tr>
                        <tr>
                          <td width="20%" class="title1">用户名称：</td>
                          <td class="left">${user.userName}</td>
                        </tr>
                        <tr>
                          <td width="20%" class="title1">密码：</td>
                          <td class="left">${user.password}</td>
                        </tr>
                        
                        <tr>
                          <td width="20%" class="title1">电话：</td>
                          <td class="left">${user.telephone}</td>
                        </tr>
                        <tr>
                          <td width="20%" class="title1">上次登录IP：</td>
                          <td class="left">${user.loginIp}</td>
                        </tr>
                        <tr>
                          <td width="20%" class="title1">上次登录时间：</td>
                          <td class="left">${user.loginDate}</td>
                        </tr>
                      </tbody>
                  </table>
                </div>
            </div>
      </div>
</div>
</body>

</html>
