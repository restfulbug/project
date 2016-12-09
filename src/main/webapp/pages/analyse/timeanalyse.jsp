<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
 <script type="text/javascript">
 $(function () {
	 // 基于准备好的dom，初始化echarts实例
     var myChart = echarts.init(document.getElementById('main'));
     var arrname=[];
     var arrnum=[];
     // 指定图表的配置项和数据
     myChart.setOption({
     	    title: {
     	        text: '报修趋势分析'
     	    },
     	   tooltip: {
   	        trigger: 'item'
   	    },
     	    legend: {
     	        data:['报修量']
     	    },
     	    grid: {
     	        left: '3%',
     	        right: '4%',
     	        bottom: '3%',
     	        containLabel: true
     	    },
     	    toolbox: {
     	        feature: {
     	            saveAsImage: {}
     	        }
     	    },
     	    xAxis: {
     	    	  type: 'category',
      	        boundaryGap: false,
     	        data: []
     	    },
     	    yAxis: {
     	    	 type: 'value'
     	    },
     	    series: [
     	        {
     	            name:'报修量',
     	            type:'line',
     	            stack:'总量',
     	            data:[]
     	        }]
     	});
   
         var url = "${ctx}/rep/timeAnalyseJson";
		 var args = {"arg":"${arg}","time": new Date()};
         $.post(url,args,function (data) {
    	 for (var i = 0; i < data.length; i++) {
    		 arrname.push(data[i].name+"点");
    		 arrnum.push(data[i].num);
		}
    	 // 填入数据
         myChart.setOption({
             xAxis: {
            	 data: arrname
             },
             series: [{
                 // 根据名字对应到相应的系列
                 name: '报修量',
                 data: arrnum
             }]
         });
     });
    
})
 </script>

</head>
<a href="<c:url value="/rep/timeAnalyse/today"/>">今天</a>
<a href="<c:url value="/rep/timeAnalyse/yesterday"/>">昨天</a>
<a href="<c:url value="/rep/timeAnalyse/thisWeek"/>">本周</a>
<a href="<c:url value="/rep/timeAnalyse/thisMonth"/>">本月</a>
<a href="<c:url value="/rep/timeAnalyse/thisYear"/>">本年</a>
<body>
    <div align="right"   id="main" style="width: 1000px;height:400px;"></div>
  <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
			<tr>
				<th width="7%" >总数量</th>
			 	<c:forEach items="${timeCounts}" var="time">
					<th>${time.name}</th>
			    </c:forEach> 
			</tr>
				<tr>
				<td align="center">${all}</td>
				<c:forEach items="${timeCounts}" var="time">
					<td align="center">${time.num}</td>
			    </c:forEach> 
				</tr>
		</table>
</body>
</html>