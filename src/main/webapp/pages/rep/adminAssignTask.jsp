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
     $("#ad").click(function(){
    	var userId = $('#option option:selected').val();
		var proId = $("#proId").attr("value");
		var url = "${ctx}/process-claimDis/"+userId+"/"+proId;
		 document.getElementById("ad").action = url;
		 document.getElementById("ad").submit();
		 alert("派发成功");
		return false;
	});
})
</script>
</head>

<body>
	<div id="main">
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="" id="vacation"><a href="${ctx}/process-listDisTask">任务派发</a></li>
          </ul>
      </div>
      
      <div class="sort_content">
           <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
              <thead>
                <tr>
                <td width="5%"><strong>编号</strong></td>
				<td width="5%"><strong>报修人姓名</strong></td>
				<td width="15%"><strong>创建时间</strong></td>
				<td width="5%"><strong>状态</strong></td>
				<td width="10%"><strong>故障类型</strong></td>
				<td width="15%"><strong>故障详细描述</strong></td>
				<td width="5%"><strong>区域类型</strong></td>
				<td width="5%"><strong>楼栋号</strong></td>
				<td width="15%"><strong>详细地址</strong></td>
				<td width="10%"><strong>分配任务</strong></td>
				<td width="10%"><strong>确定</strong></td>
			</tr>
			</thead>
             <tbody id="tbody">
			 <c:forEach items="${reps}" var="rep">
				<tr>
					<td height="40px" align="center">${rep.id }</td>
					<td align="center">${rep.user.userName}</td>
					<td align="center">
						<fmt:formatDate value="${rep.creatDate}" pattern="yyyy-MM-dd hh:mm:ss"/>
					</td>
					<td align="center">${rep.repairState.stateName}</td>
					<td align="center">${rep.faultType.faultName}</td>
					<td align="center">${rep.detailDesc}</td>
					<td align="center">${rep.dictionary.keyName}</td>
					<td align="center">${rep.building.buildNo}</td>
					<td align="center">${rep.detailLocation}</td>
					<td align="center">
									<select id= "option">
									<option value="">选择员工</option>
									<c:forEach items="${users}" var="user">
							        <option id= "userId" value="${user.id}">${user.userName }</option>
						            </c:forEach>
						            </select>
					</td>
					<input type="hidden" value="${rep.id}"/>
					<input type="hidden"  id = "proId" value="${rep.processInstanceId}"/>
				        <td align="center"><form action="" id="ad">
                         <input type="button" value="确定" />
                          </form>
                        </td>
				</tr>
		   </c:forEach> 
	</tbody>
          </table>
      </div>

</div>
    
</body>
</html>
