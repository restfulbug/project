<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link media="screen" href="${ctx}/statics/grade.css" type="text/css" rel="stylesheet" /> 

<script src="${ctx}/statics/jquery-latest.pack.js" type="text/javascript"></script> 
<script src="${ctx}/statics/grade.js" type="text/javascript"></script> 
<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/css/main.css" type="text/css" />
</head>
<body>
	<div id="main">
      <div class="sort_switch">
          <ul id="TabsNav">
          	  <li class="selected"><a href="#">维修评价</a></li>
          </ul>
      </div>
      <div id="tagContent0" class="sort_content">
			<input type="hidden" value="${taskId}" name="taskId"/>
        	<div class="currency_area hue9">
            	<div class="the_content">
                	<table class="tableHue2" width="100%" border="1" bordercolor="#dddddd" cellspacing="0" cellpadding="0">
                      <tbody>
                        <tr>
                          <td width="15%" class="title1">故障类型:</td>
                          <td class="left">${repairForm.faultType.faultName}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">具体故障信息:</td>
                          <td class="left">${repairForm.detailDesc}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">故障等级:</td>
                          <td class="left">${repairForm.faultLevel.levelName}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">区域类型:</td>
                          <td class="left">${repairForm.dictionary.keyName}</td>
                        </tr>
                         <tr>
                          <td width="15%" class="title1">楼栋号:</td>
                          <td class="left">${repairForm.building.buildNo} </td>
                        </tr>
                        
                        <tr>
                          <td width="15%" class="title1">具体位置信息:</td>
                          <td class="left">${repairForm.detailLocation}</td>
                        </tr>
                        <tr>
                          <td width="15%" class="title1">联系电话:</td>
                          <td class="left">${repairForm.tel}</td>
                        </tr>
                      </tbody>
                  </table>
                </div>
            </div>
            <div id="tagContent0"> 
	<h2 align="left">评分</h2> 
	<div class="the_content"> 
		<div id="myPoint">
			<span><big>5</big><small>.0</small></span> 
			<div>
				<img src="${ctx}/statics/star5.gif" />
				<em>(一般)</em>
			</div>
		</div> 
		<div id="doPoint"> 
			<p align="left">请点击以下星级进行评分：我的评分=(维修速度+维修质量+维修态度)/3</p> 
			<table cellspacing="0" cellpadding="0" border="0"> 
					<tbody> 
						<tr> 
							<th>维修质量：</th> 
							<td><span class="star5" id="item1" v="5"><small>1</small><small>2</small><small>3</small><small>4</small><small>5</small><small>6</small><small>7</small><small>8</small><small>9</small><small>10</small></span></td> 
							<td><strong>5</strong> <em>(一般)</em></td>
						</tr> 
						<tr> 
							<th>维修速度：</th> 
							<td><span class="star5" id="item2" v="5"><small>1</small><small>2</small><small>3</small><small>4</small><small>5</small><small>6</small><small>7</small><small>8</small><small>9</small><small>10</small></span></td> 
							<td><strong>5</strong> <em>(一般)</em></td>
						</tr> 
						<tr> 
							<th>维修态度：</th> 
							<td><span class="star5" id="item3" v="5"><small>1</small><small>2</small><small>3</small><small>4</small><small>5</small><small>6</small><small>7</small><small>8</small><small>9</small><small>10</small></span></td> 
							<td><strong>5</strong> <em>(一般)</em></td>
						</tr>
					</tbody>
			</table>
		</div> 
		<form id="auditForm" name="form1" action="${ctx}/process-assert/${taskId}" method="post">
			<input id="pointV1" type="hidden" value="5" name="pointV1" /> 
			<input id="pointV2" type="hidden" value="5" name="pointV2" /> 
			<input id="pointV3" type="hidden" value="5" name="pointV3" /> 
			<input id="pointV4" type="hidden" value="5" name="score" /> 
		<div align="left"><label >评论内容：<textarea id="content" name="content" rows="5" cols="50"></textarea></label></div>
		</div>
             <div class="fun_area" align="left"><input type="submit" value="提交" class="input_button1"/>
      </div>
		</form>
</div>
</div>  
</body>
</html>
