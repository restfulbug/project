<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络报修系统</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function(){
	 $("#page").click(function(){
		 var currpage = ${page.number + 1 } ;
		    var totalpage = ${page.totalPages};
			 if (currpage>=totalpage){
					alert("已是最后一页");
			 return false;
			 }
	 });
})
</script>
</head>
<body>
	<div id="main">
      <div class="sort_content">
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
            
	<c:if test="${page == null || page.numberOfElements == 0 }">
		没有任何记录. 
	</c:if>
	<c:if test="${page != null && page.numberOfElements > 0 }">
			<tr>
				<th width="5%">编号</th>
				<th width="5%">请假类型</th>
				<th width="20%">申请时间</th>
				<th width="15%">请假开始时间</th>
				<th width="15%">请假结束时间</th>
				<th width="10%">请假总天数</th>
				<th width="10%">请假人姓名</th>
				<th width="10%">请假原因</th>
			    <th width="10%">查看流程进度</th>
			</tr>
			<c:forEach items="${page.content}" var="lea">
				<tr>
					<td height="50px" align="center">${lea.id}</td>
					<td align="center">${lea.leaveType}</td>
					<td align="center">
						<fmt:formatDate value="${lea.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td align="center">
						<fmt:formatDate value="${lea.startTime}" pattern="yyyy-MM-dd"/>
					</td>
					<td align="center">
						<fmt:formatDate value="${lea.endTime}" pattern="yyyy-MM-dd "/>
					</td>
					<td align="center">${lea.totalDay}</td>
					<td align="center">${lea.user.userName}</td>
					<td align="center">${lea.leaveReason}</td>
					<td align="center"><a href="${ctx}/getId/${lea.processInstanceId}" style="margin-left: 15px">查看</a></td> 
					<td>
					
				</tr>
			</c:forEach>
			
			<tr>
				<td width="10%" colspan="16" align="center" height="50px">
					共 ${page.totalElements } 条记录&nbsp;
					共 ${page.totalPages } 页&nbsp;
					当前 ${page.number + 1 } 页&nbsp;
					<a href="?pageNo=${page.number + 1 - 1 }">上一页</a>&nbsp;&nbsp;
					<a href="?pageNo=${page.number + 1 + 1 }" id="page">下一页</a>
				</td>
			</tr>
			
	</c:if>
		</table>
</body>
</html>