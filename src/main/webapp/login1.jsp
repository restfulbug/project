<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ctx}/css/top.css" type="text/css" />
<title>登录 </title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<script src="${ctx}/js/bootstrap.min.js"></script>
<script type="text/javascript">
function reloadValidateCode(){
	$("#validateCodeImg").attr("src","${ctx}/validateCode?data=" + new Date()
	+ Math.floor(Math.random() * 24));
}
</script>

<style>

.mainDiv {
    background-color:#CFE8E9;
	height: 100%;
}

.formDiv {
	font-size: 20px; 
	margin-bottom: 20px;
}

.formDiv input {
	width: 200px; 
	height: 30px;
}

.textDiv {
	margin-top: 30px;
}

.textDiv input {
	width: 200px; 
	height: 30px;
}

.buttonDiv {
	margin-top: 30px;
}

.buttonDiv input {
	width: 80px; 
	height: 40px;
	margin-left: 40px;
}
</style>
</head>
<body>
<!-- <div class="mainDiv" > -->
<div class="mainDiv" >

	<div style="margin-top: 90px; ">
		<div style="height: 40px;"></div>
		<div style="height: 40px; margin-top: 100px;">
			<h1 style="font-size:30px; margin-top: 100px;"><strong>河南科技学院报修系统</strong></h1>
	    </div>
		
		<div class="formDiv">
			<span style="color:red; height: 30px;"> ${loginMsg} </span>
			<form method="post" action="${ctx}/login">
				<div class="textDiv">
					<span style="width: 300px; margin-right: 20px;">用户名: </span>
					<input type="text" name="account"/>
				
				</div>
				<div class="textDiv">
					<span style="width: 300px; margin-right: 40px;">密码:</span> 
					<input type="password" name="password"/>
				</div>
				<div class="textDiv">
					<span style="width: 300px; margin-right: 20px;">验证码:</span> 
					<input type="text" name="validateCode"/>
				</div>
				 <div class="textDiv" >
				 <img id="validateCodeImg" src="${ctx}/validateCode" />
					 <a href="#" onclick="javascript:reloadValidateCode();"></a>
					 <br>看不清？<br> 
				</div>
				<div class="buttonDiv">
					<input type="submit" value="确定"/>
					<input type="button" value="取消"/>
				</div>
			</form>
		</div>
	</div>
</div>
</body>

</html>
