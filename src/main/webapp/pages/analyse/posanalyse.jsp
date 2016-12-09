<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
     var res = [];
     // 指定图表的配置项和数据
     myChart.setOption({
     	    title: {
     	        text: '报修区域分析',
     	         x:'center'
     	    },
     	   tooltip: {
     		  trigger: 'item',
  	        formatter: "{a} <br/>{b} : {c} ({d}%)"
   	    },
     	  legend: {
     	    orient: 'vertical',
   	        left: 'left',
   	        data: []
     	    },
     	   series : [
         	        {
         	            name: '报修区域',
         	            type: 'pie',
         	            radius : '55%',
         	            center: ['50%', '60%'],
         	            data:[],
         	            itemStyle: {
         	                emphasis: {
         	                    shadowBlur: 10,
         	                    shadowOffsetX: 0,
         	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
         	                }
         	            }
         	        }
         	    ]
     	});
         var url = "${ctx}/rep/posAnalyseJson";
		 var args = {"arg":"${arg}","time": new Date()};
         $.post(url,args,function (data) {
    	 for (var i = 0; i < data.length; i++) {
    		 arrname.push(data[i].name)
    		 res.push({
                 name:data[i].name,
                 value:data[i].num
             })
		}
    	 // 填入数据
         myChart.setOption({
             legend: {
            	 data: arrname
             }, 
             series: [{
                 name: '报修区域',
                 data: res
             }]
         });
     });
    
})
</script>
<body>

<a href="<c:url value="/rep/posAnalyse/today"/>">今天</a>
<a href="<c:url value="/rep/posAnalyse/yesterday"/>">昨天</a>
<a href="<c:url value="/rep/posAnalyse/thisWeek"/>">本周</a>
<a href="<c:url value="/rep/posAnalyse/thisMonth"/>">本月</a>
<a href="<c:url value="/rep/posAnalyse/thisYear"/>">本年</a>

    <div align="right"   id="main" style="width: 800px;height:400px;"></div>
  <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
			<tr>
				<th width="7%" >总数量</th>
			 	<c:forEach items="${posCounts}" var="pos">
					<th>${pos.name}</th>
			    </c:forEach> 
			</tr>
				<tr>
				<td align="center">${all}</td>
				<c:forEach items="${posCounts}" var="pos">
					<td align="center">${pos.num}</td>
			    </c:forEach> 
				</tr>
		</table>
</body>
</html>