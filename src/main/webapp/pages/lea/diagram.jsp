<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

</head>
<body>
	<div id="main">
		<div class="where">
			<ul>
			</ul>
		</div>
		<div class="sort_switch">
			<ul id="TabsNav">
				<li class="selected"><a href="#">流程图</a></li>
			</ul>
		</div>
		<div id="tagContent0" class="sort_content" >
			<div class="currency_area hue9">
				 <div class="the_content">
					<img alt="显示图片" src="${ctx}/showdiagram/${id}"></img>
				</div> 
			</div>
		</div>
	</div>
	</form>
</body>

</html>
