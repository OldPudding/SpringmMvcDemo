/**
 * 格式化列表时间
 * @param timestamp
 * @returns
 */
function timeToString(timestamp){
	if(timestamp != null){
		var now = new Date(timestamp);
		var year = now.getFullYear();
		var month =(now.getMonth() + 1).toString();
		var day = (now.getDate()).toString();
		var hour = (now.getHours()).toString();
		var minute = (now.getMinutes()).toString();
		var second = (now.getSeconds()).toString();
		if (month.length == 1) {
			month = "0" + month;
		}
		if (day.length == 1) {
			day = "0" + day;
		}
		if (hour.length == 1) {
			hour = "0" + hour;
		}
		if (minute.length == 1) {
			minute = "0" + minute;
		}
		if (second.length == 1) {
			second = "0" + second;
		}
		var dateTime = year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return dateTime;
	}
	return null;
}

/**
 * 格式化列表日期
 * @param timestamp
 * @returns
 */
function dateToString(timestamp){
	if(timestamp != null){
		var now = new Date(timestamp);
		var year = now.getFullYear();
		var month =(now.getMonth() + 1).toString();
		var day = (now.getDate()).toString();
		if (month.length == 1) {
			month = "0" + month;
		}
		if (day.length == 1) {
			day = "0" + day;
		}
		var dateTime = year +"-" + month +"-" + day;
		return dateTime;
	}
	return null;
}

/**
 * 格式化性别
 * @param gender
 * @returns
 */
function genderToString(gender){
	var str = "";
	if(gender==1){
		str = "男";
	}
	if(gender==0){
		str = "女";
	}
	return str;
}


//----附件列表----//
var addUrl = contextPath + '/static/images/add.png';
var delUrl = contextPath + '/static/images/minus.png';

//删除当前
function delFileTr(obj){
	var fileName = $(obj).parent("td").parent("tr").children('td').eq(0).html();
	//如果当前行附件已经提交过了，则不让删除当前行
	if(fileName == ''){
		$.messager.confirm("系统提示", "确定删除？", function (r) {
			if(r){
				$(obj).parent("td").parent("tr").remove();
			}
		});
	}else{
		$.messager.alert('','该行文件已经存在，请点击左侧的“<font color = red>删除</font>”，删除附件','info');
	}
}

//当新建的时候初始化附件表格（）
function fileInit(){
	var way = 'addFileTable()';
	var str=getFileTr(addUrl,way);
	$("#file_table tr:last").after(str);
	//addFileTable();
}

//动态新增表格行
function addFileTable(){
	var way = 'delFileTr(this)';
	var str=getFileTr(delUrl,way);
	$("#file_table tr:last").after(str);
}
//构造行字符串
function getFileTr(url,way){
	var str =  "<tr>" +
	    "<td id = 'uploadedFile'></td>" + 
	    "<td id = 'deal'><font color = 'red'>待上传</font></td>" + 
	    "<td id = 'size'>&nbsp;</td>" +
	    "<td colspan = '2' id = 'file'><input type = 'file' id = 'attachments'  name = 'attachments'><a href = ' javascript:void(0);' onclick='" + way + "'><img width = '15' height = '20' src =" + url +  "></a></td>" + 
"</tr>";
	return str;
}

//查看附件信息
function showFiles(list){
	if(list.length == 0){//如果当前无附件默认空行
		var way = 'addFileTable()';
		var str=getFileTr(addUrl,way);
		$("#file_table tr:last").after(str);
	}else{
		for(var i = 0;i < list.length;i++){
			var downloadUrl = contextPath + '/attachment/download.do?fileId=' + list[i].id;
			//第一行让图标显示为增加的样式
			if(i == 0){
				var way = 'addFileTable()';
				var str=getFileTr(addUrl,way);
				$("#file_table tr:last").after(str)
			}else{
				var way = 'delFileTr(this)';
				var str=getFileTr(delUrl,way);
				$("#file_table tr:last").after(str);
			}
			//修改操作列显示为“下载 | 删除”
			$("#uploadedFile").html(list[i].originalUrl);
			var str = list[i].id;
			$("#deal").html("<a href = '" + downloadUrl +  "'>下载</a> | <a href = ' javascript:void(0);' onclick='deleteFile(" + str +",this)'>删除</a>");
			$("#size").html(list[i].size);
			//让选择文件控件失效
			$("#attachments").attr("disabled","true");
			//清空各列的id（必须清空，防止重复）
			$("#uploadedFile").attr("id","");
			$("#deal").attr("id","");
			$("#size").attr("id","");
			$("#attachments").attr("id","");
		}
	}
	
}

//删除附件
function deleteFile(id,obj){
	var url = contextPath +  '/static/images/add.png';
	var way = 'addFileTable()';
	var str =  "<td id = 'uploadedFile'>&nbsp;</td>" + 
	    "<td id = 'deal'><font color = 'red'>待上传</font></td>" + 
	    "<td id = 'size'>&nbsp;</td>" +
	    "<td colspan = '2' id = 'file'><input type = 'file' id = 'attachments'  name = 'attachments'><a href = ' javascript:void(0);' onclick='" + way + "'><img width = '15' height = '20' src =" + url +  "></a></td>";
	 $.messager.confirm("系统提示", "确定删除？", function (r) {
		 if(r){
			 var deleteUrl = contextPath + '/attachment/delete.do';
			 var param = {'id':id};
			 $.post(deleteUrl,param,function(data){
				 var msg = $.parseJSON(data).msg;
				 $.messager.alert("操作提示",msg,"info");
				//删除完后将当前行修改为空的状态
				 $(obj).parent("td").parent("tr").html(str);;
			 });
		 }
	 });
}