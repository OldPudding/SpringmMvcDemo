$(function(){	
	$(document).ready(
			function(){  
				initSize();
			}
		 )
});
		 
		 
	    $(window).resize(
		    function () 
		    {
		    	initSize();
		    }
	    );
		



/**
	 * 
	 * 统一在这里设置尺寸，在ready和resize时调用
	 * add by lq
	 * 
	 * **/
function initSize(){

	$('#searchpanel').panel('resize',{
		width:$(window).width(),
    });	
	$('#paperslist').datagrid('resize',{
		width:$(window).width(),
		height:$(window).height()*0.9-$('#searchpanel').height(),
	});	
}

function fixWidth(percent)  
{  
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
} 

//列表按钮权限---有流程
function returnStr(row,role,urlRoot){
	var oid = row.oid;
	var version = row.version;
	var urlInfo =  urlRoot + '/formInfo.do?oid=' + oid + '&flag=1';
	var urlModify = urlRoot + '/formInfo.do?oid=' + oid + '&flag=3';
	var urlBack = urlRoot + '/formInfo.do?oid=' + oid + '&currentFlow=' + user_back_name + '&flag=4';
	switch (role){
	case first_role + ''://登陆者为初审
		urlExamine = urlRoot + '/formInfo.do?oid=' + oid + '&currentFlow=' + first_name + '&flag=2';
		if(version == status_firstTrail){//初审中
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a><a href= " + urlExamine + " class='linkbutton-verify' target = _blank>审批</a>";
		}else {
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
		}
		break;
	case recheck_role + ''://登陆者为复审
		urlExamine = urlRoot + '/formInfo.do?oid=' + oid + '&currentFlow=' + recheck_name + '&flag=2';
		if(version == status_recheck){//复审中
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a><a href= " + urlExamine + " class='linkbutton-verify' target = _blank>审批</a>";
		}else {
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
		}
		break;
	case final_role + ''://登陆者为终审
		urlExamine = urlRoot + '/formInfo.do?oid=' + oid + '&currentFlow=' + final_name + '&flag=2';
		if(version == status_finalTrail){//终审中
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a><a href= " + urlExamine + " class='linkbutton-verify' target = _blank>审批</a>";
		}else {
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
		}
		break;
	case user_role + ''://登入者为用户
		if(version == status_firstTrail || version == status_recheck || version == status_finalTrail){//审批之前可以撤销任务和查看
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a><a href= " + urlBack + " class='linkbutton-back' target = _blank>撤销</a>";
		}else if(version == status_trailOk){//通过审核用户只能查看
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
		}else if(version == status_back || version == status_application){//状态驳回或待申请中用户可以修改
			return "<a href= " + urlModify + " class='linkbutton-edit' target = _blank>修改</a>";
		}else{
			return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
		}
		break;
	default :
		// 仅有查看权限
		return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
	}
}
// 列表审批状态
function returnTrailStatus(value){
	if( value == 1 ) {
		return "初审";
	} else if ( value == 2 ) {
		return "复审";
	} else if ( value == 3 ) {
		return "终审";
	} else if ( value == 4 ) {
		return "通过审核";
	} else if ( value == 5 ) {
		return "待申请";
	} else if ( value == 0 ) {
		return "驳回";
	} else {
		return "未知";
	}
}

// 列表按钮权限---普通
function returnGen(row,role,urlRoot){
	var oid = row.oid;
	var version = row.version;
	var urlInfo =  urlRoot + '/formInfo.do?oid=' + oid + '&flag=1';
	var urlModify = urlRoot + '/formInfo.do?oid=' + oid + '&flag=3';
	
	//登入者为用户
	if(role == user_role + '') {
		return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a>";
	} else {
		// 查看和修改
		return "<a href= " + urlInfo + " class='linkbutton-view' target = _blank>查看</a><a href= " + urlModify + " class='linkbutton-edit' target = _blank>修改</a> ";
	}
	
}

function strIcon(){
	$('.linkbutton-view').linkbutton({plain:true,iconCls:'icon-view-new'});
	$('.linkbutton-edit').linkbutton({plain:true,iconCls:'icon-edit-new'});
	$('.linkbutton-verify').linkbutton({plain:true,iconCls:'icon-verify-new'});
	$('.linkbutton-back').linkbutton({plain:true,iconCls:'icon-back'});
	$('.linkbutton-print').linkbutton({plain:true,iconCls:'icon-print'});
}
function genIcon(){
	 $('.linkbutton-view').linkbutton({plain:true,iconCls:'icon-view-new'});
	 $('.linkbutton-edit').linkbutton({plain:true,iconCls:'icon-edit-new'});
	 $('.linkbutton-print').linkbutton({plain:true,iconCls:'icon-print'});
}

function doReset(){
	$('#listForm').form('reset');
}