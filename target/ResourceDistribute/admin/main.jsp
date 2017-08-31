<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>稿件编辑分发平台</title>
    <!-- 导入共通样式页面 -->
	<%@ include file="common.jsp"%>	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/nav.js"></script>
	
	<script type="text/javascript">
        $(function() {
        	
        	// 编辑器
        	$('#wxeditor').click(function() {
            	var url = '${pageContext.request.contextPath}/wxeditor/index.jsp';
            	addTab('编辑器',url);
            });
            
			// 安全退出
            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (data){
                	if (data){
                		 location.href = '${pageContext.request.contextPath}/user/logout.do'
                	}
                });
            })
        });
    </script>
</head>
  
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">

	<noscript>
	<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
	    <img src="${pageContext.request.contextPath}/static/commons/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
	</div>
	</noscript>
	
	<!-- 顶部 -->
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(${pageContext.request.contextPath}/static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        	欢迎您，${sessionScope.currentUser.realname} 
        	
        	&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="loginOut">安全退出</a>
        </span>
        <span style="padding-left:10px; font-size: 16px; ">
        	<img src="${pageContext.request.contextPath}/static/images/blocks.gif" width="20" height="20" align="absmiddle" />
        	稿件编辑发布平台
        </span>
    </div>
    
    <!-- 底部 -->
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">主办单位:通讯信息报社</div>
    </div>
    
    <!--  导航 -->
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false"></div>
    </div>
    
    <!-- 面板 -->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " style="background: #e7f7ff;" >
				<h1>欢迎登陆稿件编辑分发平台！</h1>
				<br />
				<a href="#" id="wxeditor">编辑器（临时入口）</a>
			</div>
		</div>
    </div>
</body>
</html>
