<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="zh-CN" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>网络报修系统</title>
<link rel="icon" href="img/logo.png"/>
<link href="${ctx}/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/css/mmss.css"/>
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css"/>

<script src="${ctx}/js/jquery-1.11.3.js"></script>
<script src="${ctx}/js/bootstrap.js"></script>

</head>
<body>

  <section>
     <div class="container-fluid">
        <div class="row "> 
            <div class=" bg-blue"> 
                <br/>
                <div class="panel-group sidebar-menu" id="accordion" aria-multiselectable="true">
                    <c:forEach items="${menus}" var="menu" >
                    <c:if test="${menu.parentId eq '0' }">
                   
                    <div class="panel panel-default menu-first">
                          <a data-toggle="collapse" data-parent="#accordion" href="#${menu.id}" aria-expanded="true"
                             aria-controls="collapseOne">
                              <i class="icon-user-md icon-large"></i>${menu.name }</a>

                        <div id="${menu.id}" class="panel-collapse collapse " >
                            <ul class="nav nav-list menu-second">
                                <c:forEach items="${menus}" var="son">
                                    <c:if test="${son.parentId eq menu.id }">
                                     <li><a href="${ctx}/${son.url}" target="mainFrame"><i class="icon-user"></i> ${son.name }</a></li>
                                    </c:if>
                                </c:forEach>
                                
                            </ul>
                        </div>
                    </div>
                    </c:if>
                    </c:forEach>
                </div>
             </div>
        </div>
    </div>
</section>
</body>
</html>

