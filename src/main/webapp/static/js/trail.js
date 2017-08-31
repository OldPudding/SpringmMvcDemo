$(function(){
	$.eValid.init();
    var flag = $("#flag").val();//标识是查看，审批，修改
	switch (flag){
	case "1" ://点击查看
		$("#save").hide();
		$("#back").hide();
		$("#btnYes").hide();
		$("#btnNo").hide();
		$("#btnSubmit").hide();
		readOnlyForm('');
		break;
	case "2"://点击审批
		$("#save").hide();
		$("#back").hide();
		$("#btnYes").show();
		$("#btnNo").show();
		$("#btnSubmit").hide();
		readOnlyForm('currentAdvice');
		break;
	case "3" ://修改或新建
		$("#btnYes").hide();
		$("#btnNo").hide();
		$("#back").hide();
		break;
	case "4" : //撤销
		$("#save").hide();
		$("#btnYes").hide();
		$("#btnNo").hide();
		$("#btnSubmit").hide();
		readOnlyForm('currentAdvice');
		break;
	}
 
});

/**
 * 统一验证非空
 */
function checkValidate(){
	// 循环获取文本框，验证非空
	$("input[type='text']").each(function () {
	    $(this).validatebox({required: true});
	});
}
/**
 * 除notName外，设置只读
 * @param notName
 */
function readOnlyForm(notName){
	var allForm = $('form').find('input,textarea,select');
	if(notName=='' || notName==undefined || notName==null){
		allForm.attr('readonly',true);
	} else {
		allForm.attr('readonly',true);
		$('textarea[name="'+notName+'"]').attr('readonly',false);
	}
	
}

//返回
function returnList(){
	 opener.location.reload();
	 window.close();
}

//easyUI验证保存
function saveForm(url) {
	//checkValidate();
	$('#saveform').form('submit' ,{
		url:url,
		onSubmit: function(){
			progressLoad();
			var isValid = $(this).form('validate');
            if (!isValid) {
                progressClose();
            }
            return isValid;
		},
		success:function(result){
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				alert(result.msg);
				returnList();
			} else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

//easyUI验证提交
function submitForm(url) {
	//checkValidate();
	$('#saveform').form('submit' ,{
		url:url,
		onSubmit: function(){
			progressLoad();
			var isValid = $.eValid.validate('','#saveform'); // 验证单选多选
			if( $(this).form('validate') && isValid ){
				 return true;
			} else {
				progressClose();
				return false;
			}
			
		},
		success:function(result){
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				alert(result.msg);
				returnList();
			} else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

//上传文件类的保存专用
function saveFormFile(url) {
	$('#saveform').form('submit' ,{
		url:url,
		onSubmit: function(){
			progressLoad();
			var isValid = $(this).form('validate');
            if (!isValid) {
                progressClose();
            }
            return isValid;
		},
		success:function(data){
			 progressClose();
			 var oid = $.parseJSON(data).newOid;
			 var fileList = $.parseJSON(data).fileList;
			 $("#oid").val(oid);
			 showSaveFiles(fileList);
			 $.messager.alert("操作提示","数据保存成功","info");
		}
	});
}

function addTr(list){
	for (var i = 0; i < list.length; i++) {						
		$("#processView").append("<tr></tr>");
		$("#processView tr:last").append("<td width='17%' class = 'full'>" + list[i].operator+ "&nbsp;</td>");
		$("#processView tr:last").append("<td width='10%' class = 'full'>" + list[i].submit+ "&nbsp;</td>");
		$("#processView tr:last").append("<td width='50%' class = 'full'>" + list[i].advice+ "&nbsp;</td>");
		$("#processView tr:last").append("<td width='23%' class = 'full'>" + list[i].finishTime+ "&nbsp;</td>");
	}
}

//审批
function trail(result,url){
	$('#saveform').form('submit' ,{
		url:url,
		onSubmit: function(){
			if (!$(this).form('validate')){
				$.messager.alert("操作提示","必填项有未填","warning");
				return false;
			}else{
				if("yes" == result){
					 $.messager.confirm("系统提示", "您当前的选择是“同意”该申请", function (r) {
						 if(r){
							 progressLoad();
							 postParam(url,result);
						 }
					 });
				}else if("no" == result){
					 $.messager.confirm("系统提示", "您当前的选择是“驳回”该申请", function (r) {
						 if(r){
							 progressLoad();
							 postParam(url,result);
						 }
					 });
				}
			}
		}
	});
}

//撤销
function drawBack(url){
	$('#saveform').form('submit' ,{
		onSubmit: function(){
			if (!$(this).form('validate')){
				$.messager.alert("操作提示","必填项有未填","warning");
				return false;
			}else{
				 $.messager.confirm("系统提示", "您当前的操作是“撤销”该申请", function (r) {
					 if(r){
						 progressLoad();
						 postParam(url,"");
					 }
				 });
			}
		}
	});
}
$(function(){
 		$('.length100').validatebox({
 			required : true,
 			validType : ['length[1,100]']
 		});
 		 
 		$('.integer').validatebox({
 			required : true,
 			validType : ['length[1,20]','integer']
 		});
 		//允许小数和整数
 		$('.int_and_mone').validatebox({
 			required : true,
 			validType : ['length[1,20]','mone']
 		});
 		$('.required').validatebox({
 			required : true
 		});
 	 
	});


function fixWidth(percent)  
{  
    return document.body.clientWidth * percent ; //这里你可以自己做调整  
} 

//post 审批
function postParam(url,result){
	var currentFlow =$("#currentFlow").val();
	var orderid =$("#orderid").val();
	var oid =$("#oid").val();
	var version = $("#version").val();
	var currentAdvice =$("#currentAdvice").val();
	var bkid =$("#bkid").val();
	var param = {'orderid':orderid,'currentAdvice':currentAdvice,
			'result':result,'oid':oid,'currentFlow':currentFlow,
			'version':version, 'bkid':bkid};
	
	$.post(url,param,function(data){
		progressClose();
		var data = $.parseJSON(data);
		var msg = data.msg;
		alert(msg);
		returnList();
	});
}

//easyUI验证导出
function exportForm(url) {
	$('#saveform').form('submit' ,{
		url:url,
		onSubmit: function(){
            return true;
		}
	});
}
