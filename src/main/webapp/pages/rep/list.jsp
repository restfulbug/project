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
      <a href="${ctx}/createDate/1">当天</a>
	    <a href="${ctx}/createDate/7">本周</a>
		  <form action="" id="formuser" >
			按报修人姓名查询: <input type="text" name="userName" id="userName">
			<input type="submit" value="提交" id="button">
		</form>
		<br>
          <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
            
	<c:if test="${page == null || page.numberOfElements == 0 }">
		没有任何记录. 
	</c:if>
	<c:if test="${page != null && page.numberOfElements > 0 }">
			<tr>
				<th width="5%">编号</th>
				<th width="5%">报修人姓名</th>
				<th width="5%">维修人姓名</th>
				<th width="15%">创建时间</th>
				<th width="5%">状态</th>
				<th width="10%">故障类型</th>
				<th width="15%">故障详细描述</th>
				<th width="5%">区域类型</th>
				<th width="5%">楼栋号</th>
				<th width="15%">详细地址</th>
				<th width="10%">手机号</th>
			    <th width="5%">查看流程进度</th>
			</tr>
			
			<c:forEach items="${page.content}" var="rep">
				<tr>
					<td height="50px" align="center">${rep.id }</td>
					<td align="center">${rep.user.userName}</td>
					<td align="center">${rep.staff.userName}</td>
					<td align="center">
						<fmt:formatDate value="${rep.creatDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td align="center">${rep.repairState.stateName}</td>
					<td align="center">${rep.faultType.faultName}</td>
					<td align="center">${rep.detailDesc}</td>
					<td align="center">${rep.dictionary.keyName}</td>
					<td align="center">${rep.building.buildNo}</td>
					<td align="center">${rep.detailLocation}</td>
					<td align="center">${rep.tel}</td>
					<input type="hidden" value="${rep.id}"/>
					  <td><a href="${ctx}/getId/${rep.processInstanceId}" style="margin-left: 15px">查看</a></td> 
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
	 <script type="text/javascript">
		$("#button").click(function(){
			var userName=$("#userName").val();
			var url= "${ctx}/per/"+userName;
			$("#formuser").attr("action",url);
		})
	</script>
 
</body>
</html>