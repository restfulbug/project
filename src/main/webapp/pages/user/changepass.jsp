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
          	  <li class="selected"><a href="#">密码修改</a></li>
          </ul>
      </div>
      <div id="tagContent0" class="sort_content">
        	<div class="currency_area hue9">
        	<form method="post" action="${ctx}/per/pass/${userId}" >
             <div class="the_content">
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0" >
                      <tbody>
                        <tr>
                          <td width="20%" class="title1">原密码：</td>
                          <td class="left"> <input  type="password" name="prepassword"/></td>
                        </tr>
                        <tr>
                          <td width="20%" class="title1">新密码：</td>
                          <td class="left"><input  type="password" name="newpassword"/></td>
                        </tr>
                      </tbody>
                  </table>
                  <input  type="submit" value="提交"/>
                </div>
             
             </form>
            	
            </div>
      </div>
</div>

</body>

</html>
