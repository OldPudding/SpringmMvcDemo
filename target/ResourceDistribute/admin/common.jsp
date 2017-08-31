<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<meta name="renderer" content="webkit"> 

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/commons/css/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/commons/js/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/commons/js/themes/icon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/js/validatebox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/datagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/resize.js"></script>

<!-- easyUI验证扩展，主要针对radio,checkbox -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/commons/easyValidator/easyValidator.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/static/commons/easyValidator/easyValidator.min.js"></script>

<script type="text/javascript">
var contextPath = '${pageContext.request.contextPath}'; /* js全局url */
var pageSize = 10;
var pageList = [10,20,30];

var userType = '${sessionScope.currentUser.userType}';
//审读状态
var status_application = "5";//待申请
var status_firstTrail = "1";//初审
var status_recheck = "2";//复审
var status_finalTrail = "3";//
var status_trailOk = "4";//通过审核
var status_back = "0";//撤销

//角色
var first_role = 3;//终审
var recheck_role = 4;//复审
var final_role = 5;//初审
var user_role = 2;//用户
var admin_role = 1;//管理员

//流程
var first_name = "初审";
var recheck_name = "复审";
var final_name = "终审";
var user_back_name = "撤销";
</script>

