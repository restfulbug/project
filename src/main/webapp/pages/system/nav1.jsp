<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>

<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/nav.css" type="text/css" />
<script>
	window.onload = function() {
	};
	function clickNav(a) {
		var childUl = document.getElementById("opt_" + a.id);
		if (childUl.style.display == "block" || childUl.style.display == "")	{
			childUl.style.display = "none";
		} else {
			childUl.style.display = "block";
		}
	}
</script>
</head>
<body>
	 	<div class="admin_memu">
	 	  <ul id="nav">
	 	      <c:forEach  items="${menus}" var="menu">
                <c:if test="${menu.parentId eq '0' }">
                <li><a href="#" onclick="clickNav(this);return false;" class="parent" id="1"><h5><strong>${menu.name }</strong></h5></a>
                   <%--   <li class="sui-dropdown"><a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">${menu.name } <i class="caret"></i></a> --%>
                           <!--  <ul role="menu" class="sui-dropdown-menu"> -->
                              <ul id="opt_1" class="child_area">
                                <c:forEach items="${menus}" var="son">
                                    <c:if test="${son.parentId eq menu.id }">
                                       <li class="last"><a href="${ctx}/${son.href}" target="mainFrame"><h6>${son.name }</h6></a></li>
                                       <%--  <li role="presentation"><a role="menuitem" tabindex="-1" href=<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>${son.href}>${son.name }</a><> --%>
                                    </c:if>
                                </c:forEach>
                            </ul>
                </c:if>
              </c:forEach>
	 	
	 	
	 	  </ul>
	 	</div>
       <%--  <ul class="sui-nav">
            <c:forEach  items="${menus}" var="menu">
                <c:if test="${menu.parentId eq '1' }">
                     <li class="sui-dropdown"><a href="javascript:void(0);" data-toggle="dropdown" class="dropdown-toggle">${menu.name } <i class="caret"></i></a>
                            <ul role="menu" class="sui-dropdown-menu">
                                <c:forEach items="${menus}" var="son">
                                    <c:if test="${son.parentId eq menu.id }">
                                        <li role="presentation"><a role="menuitem" tabindex="-1" href=<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>${son.href}>${son.name }</a><>
                                    </c:if>
                                </c:forEach>
                            </ul>
                </c:if>
            </c:forEach>
        </ul>
    </div> --%>
          <%--   <div class="admin_memu">
		<ul id="nav">
        	<li><a href="#" onclick="clickNav(this);return false;" class="parent" id="1"><h5><strong>用户功能</strong></h5></a>
            	<ul id="opt_1" class="child_area">
                	<li class="last"><a href="${ctx}/rep" target="mainFrame"><h6>填写报修表</h6></a></li>
                	<li class="last"><a href="${ctx}/listProcessInstanceRep" target="mainFrame"><h6>查看我的报修申请</h6></a></li>
                	<li class="last"><a href="${ctx}/process-listRepTask/candidate" target="mainFrame"><h6>我的任务</h6></a></li>
                </ul>
            </li> 
        	 
        	<li><a href="#" onclick="clickNav(this);return false;" class="parent" id="1"><h5><strong>请假模块</strong></h5></a>
            	<ul id="opt_1" class="child_area">
					<li class="last"><a href="${ctx}/leave" target="mainFrame"><h6>申请请假</h6></a></li>
					<li class="last"><a href="${ctx}/listProcessInstanceLea" target="mainFrame"><h6>查看我的请假申请</h6></a></li>
					<li class="last"><a href="${ctx}/process-listLeaTask/candidate" target="mainFrame"><h6>我的任务</h6></a></li>
                </ul>
            </li>
            
        	<li><a href="#" onclick="clickNav(this);return false;" class="parent" id="1"><h5><strong>汇总查询</strong></h5></a>
            	<ul id="opt_1" class="child_area">
            	<li class="last"><a href="${ctx}/reps" target="mainFrame"><h6>查看所有报修表</h6></a></li>
					<li class="last"><a href="${ctx}/leas" target="mainFrame"><h6>查看所有请假表</h6></a></li>
                </ul>
            </li>
            <li><a href="#" onclick="clickNav(this);return false;" class="parent" id="15"><h5><strong>管理员功能</strong></h5></a>
            	<ul id="opt_15" class="child_area">
            	    <li class="last"><a href="${ctx}/process-listDisTask" target="mainFrame"><h6>管理员派发任务</h6></a></li>
                	<li class="last"><a href="${ctx}/role-list" target="mainFrame"><h6>角色管理</h6></a></li>
                	<li class="last"><a href="${ctx}/user-list" target="mainFrame"><h6>用户管理</h6></a></li>
                	<li class="last"><a href="${ctx}/menu-list" target="mainFrame"><h6>菜单管理</h6></a></li>
                	<li class="last"><a href="${ctx}/pd-list" target="mainFrame"><h6>流程定义 </h6></a></li>
                	<li class="last"><a href="${ctx}/dic" target="mainFrame"><h6>数据字典维护</h6></a></li>
                </ul>
            </li>
            <li><a href="#" onclick="clickNav(this);return false;" class="parent" id="15"><h5><strong>数据分析</strong></h5></a>
            	<ul id="opt_15" class="child_area">
                	<li class="last"><a href="${ctx}/rep/timeAnalyse/today" target="mainFrame"><h6>时间分析</h6></a></li>
                	<li class="last"><a href="${ctx}/rep/posAnalyse/today" target="mainFrame"><h6>区域分析</h6></a></li>
                	<li class="last"><a href="${ctx}/pages/analyse/timeAnalyse.jsp" target="mainFrame"><h6>来源分析</h6></a></li>
                	<li class="last"><a href="${ctx}/pages/assesAnalyse" target="mainFrame"><h6>评价分析</h6></a></li>
                </ul>
            </li>
            <li><a href="#" onclick="clickNav(this);return false;" class="parent" id="15"><h5><strong>个人信息</strong></h5></a>
            	<ul id="opt_15" class="child_area">
                	<li class="last"><a href="${ctx}/per/info/${user.id}" target="mainFrame"><h6>个人信息</h6></a></li>
                	<li class="last"><a href="${ctx}/per/pass/${user.id}" target="mainFrame"><h6>修改密码</h6></a></li>
                </ul>
            </li>
            
        </ul>
	</div> 
 --%>
</body>
</html>
