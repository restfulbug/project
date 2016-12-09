<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>

</head>
<body>
 
    <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div align="right"   id="main" style="width: 500px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
         var option = {
        	    title: {
        	        text: '深圳月最低生活费组成（单位:元）',
        	        subtext: 'From ExcelHome',
        	        sublink: 'http://e.weibo.com/1341556070/AjQH99che'
        	    },
        	    tooltip : {
        	        trigger: 'axis',
        	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	        },
        	        formatter: function (params) {
        	            var tar = params[1];
        	            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
        	        }
        	    },
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    xAxis: {
        	        type : 'category',
        	        splitLine: {show:false},
        	        data : ['总费用','房租','水电费','交通费','伙食费','日用品数']
        	    },
        	    yAxis: {
        	        type : 'value'
        	    },
        	    series: [
        	        {
        	            name: '辅助',
        	            type: 'bar',
        	            stack:  '总量',
        	            itemStyle: {
        	                normal: {
        	                    barBorderColor: 'rgba(0,0,0,0)',
        	                    color: 'rgba(0,0,0,0)'
        	                },
        	                emphasis: {
        	                    barBorderColor: 'rgba(0,0,0,0)',
        	                    color: 'rgba(0,0,0,0)'
        	                }
        	            },
        	            data: [0, 1700, 1400, 1200, 300, 0]
        	        },
        	        {
        	            name: '生活费',
        	            type: 'bar',
        	            stack: '总量',
        	            label: {
        	                normal: {
        	                    show: true,
        	                    position: 'inside'
        	                }
        	            },
        	            data:[2900, 1200, 300, 200, 900, 300]
        	        }
        	    ]
        	};
         myChart.setOption(option);
 </script>
  <table class="tableHue1" width="100%" border="1" bordercolor="#a4d5e3" cellspacing="0" cellpadding="0">
            
			<tr>
				<th width="20%" >总数量</th>
				<th width="20%" >行政楼</th>
				<th width="20%" >家属楼</th>
				<th width="20%" >东区</th>
				<th width="20%" >西区</th>
			</tr>
				<tr>
					<td>${count}</td>
					<td>${count}</td>
					<td>${count}</td>
					<td>${count}</td>
					<td>${count}</td>
				</tr>
		</table>
</body>
</html>